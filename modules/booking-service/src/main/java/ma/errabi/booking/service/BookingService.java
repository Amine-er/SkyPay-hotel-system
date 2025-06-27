package ma.errabi.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.booking.BookingDTO;
import ma.errabi.booking.domain.Booking;
import ma.errabi.booking.mapper.BookingMapper;
import ma.errabi.booking.repository.BookingRepository;
import ma.errabi.payment.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RestTemplate restTemplate;
    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    @Transactional
    public UUID makeReservation(Long roomId, LocalDate startDate, LocalDate endDate, PaymentDTO paymentDTO) {
        log.info("Initiating reservation for roomId: {}, startDate: {}, endDate: {}, userId: {}",
                roomId, startDate, endDate, paymentDTO.getUserId());

        if (!isRoomAvailable(roomId, startDate, endDate)) {
            log.warn("Room with ID {} is not available for the dates: {} - {}", roomId, startDate, endDate);
            throw new IllegalArgumentException("Room is not available for the given dates.");
        }

        if (!checkAndDeductBalance(paymentDTO)) {
            log.warn("Payment validation failed or insufficient balance for userId: {}", paymentDTO.getUserId());
            throw new IllegalArgumentException("Insufficient balance or payment validation failed.");
        }

        UUID bookingReference = UUID.randomUUID();
        BookingDTO bookingDTO = BookingDTO.builder()
                .userId(paymentDTO.getUserId())
                .roomId(roomId)
                .startTime(startDate)
                .endTime(endDate)
                .reference(bookingReference)
                .build();

        saveBooking(bookingDTO);
        log.info("Successfully saved booking. Booking reference: {}", bookingReference);

        return bookingReference;
    }

    private boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        log.debug("Checking room availability for roomId: {}, startDate: {}, endDate: {}",
                roomId, startDate, endDate);

        Optional<Booking> existingBooking = bookingRepository
                .findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        roomId, endDate, startDate);
        return !existingBooking.isPresent();
    }

    private boolean checkAndDeductBalance(PaymentDTO paymentDTO) {
        log.debug("Initiating payment validation for userId: {}", paymentDTO.getUserId());

        Boolean response = restTemplate.postForObject(paymentServiceUrl, paymentDTO, Boolean.class);
        return Boolean.TRUE.equals(response);
    }

    private void saveBooking(BookingDTO bookingDTO) {
        log.debug("Saving booking for roomId: {}, userId: {}, reference: {}",
                bookingDTO.getRoomId(), bookingDTO.getUserId(), bookingDTO.getReference());

        Booking bookingEntity = bookingMapper.toEntity(bookingDTO);
        bookingRepository.save(bookingEntity);
    }
}