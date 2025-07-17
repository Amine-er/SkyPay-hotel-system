package ma.errabi.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.booking.BookingDTO;
import ma.errabi.booking.domain.Booking;
import ma.errabi.booking.mapper.BookingMapper;
import ma.errabi.booking.repository.BookingRepository;
import ma.errabi.exception.SystemException;
import ma.errabi.payment.PaymentDTO;
import ma.errabi.utils.ErrorConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final WebClient.Builder webClientBuilder;
    private final EmailService emailService;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public Mono<UUID> makeReservation(Long roomId, LocalDate startDate, LocalDate endDate, PaymentDTO paymentDTO) {
        log.info("Initiating reservation for roomId={}, startDate={}, endDate={}, userId={}",
                roomId, startDate, endDate, paymentDTO.getUserId());
        try {
            return checkRoomAvailability(roomId, startDate, endDate)
                    .flatMap(available -> validateAvailability(available, roomId, startDate, endDate))
                    .flatMap(valid -> validateAndCharge(paymentDTO))
                    .flatMap(valid -> saveBooking(paymentDTO, roomId, startDate, endDate));
        } catch (Exception e) {
            log.error("Unexpected error during reservation: {}", e.getMessage(), e);
            return Mono.error(new SystemException(ErrorConstants.SERVER_ERROR_DESC));
        }
    }

    private Mono<Boolean> checkRoomAvailability(Long roomId, LocalDate start, LocalDate end) {
        return bookingRepository
                .findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(roomId, end, start)
                .hasElement()
                .map(found -> !found);
    }

    private Mono<Boolean> validateAndCharge(PaymentDTO paymentDTO) {
        return webClientBuilder.build()
                .post()
                .uri(paymentServiceUrl)
                .bodyValue(paymentDTO)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false)
                .flatMap(success -> {
                    if (!success) {
                        log.warn("Payment failed or insufficient balance for userId={}", paymentDTO.getUserId());
                        return Mono.error(new IllegalArgumentException("Payment failed or insufficient balance."));
                    }
                    return Mono.just(true);
                });
    }

    private Mono<UUID> saveBooking(PaymentDTO paymentDTO, Long roomId, LocalDate start, LocalDate end) {
        UUID reference = UUID.randomUUID();
        BookingDTO dto = BookingDTO.builder()
                .userId(paymentDTO.getUserId())
                .roomId(roomId)
                .startTime(start)
                .endTime(end)
                .reference(reference)
                .build();

        Booking entity = bookingMapper.toEntity(dto);
        return bookingRepository.save(entity)
                .doOnSuccess(saved -> emailService.sendReservationConfirmation(paymentDTO.getEmail(), reference))
                .thenReturn(reference);
    }

    private Mono<Boolean> validateAvailability(boolean available, Long roomId, LocalDate start, LocalDate end) {
        if (!available) {
            log.warn("Room {} not available from {} to {}", roomId, start, end);
            return Mono.error(new IllegalArgumentException("Room is not available for the given dates."));
        }
        return Mono.just(true);
    }
}
