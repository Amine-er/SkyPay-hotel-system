package ma.errabi.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.booking.BookingDTO;
import ma.errabi.booking.domain.Booking;
import ma.errabi.booking.mapper.BookingMapper;
import ma.errabi.booking.repository.BookingRepository;
import ma.errabi.payment.PaymentDTO;
import org.springframework.stereotype.Service;
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

    public UUID makeReservation(Long roomId, LocalDate startDate, LocalDate endDate, PaymentDTO paymentDTO) {
        if (!isRoomAvailable(roomId, startDate, endDate)) {
            throw new IllegalArgumentException("Room is not available for the given dates.");
        }

        if (!checkAndDeductBalance(paymentDTO)) {
            throw new IllegalArgumentException("Insufficient balance or payment validation failed.");
        }

        UUID bookingReference = UUID.randomUUID();
        BookingDTO bookingDTO = new BookingDTO(paymentDTO.getUserId(), roomId, startDate, endDate, bookingReference);
        saveBooking(bookingDTO);

        return bookingReference;
    }

    private boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        Optional<Booking> existingBooking = bookingRepository
                .findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        roomId, endDate, startDate);
        return !existingBooking.isPresent();
    }

    private boolean checkAndDeductBalance(PaymentDTO paymentDTO) {
        String paymentServiceUrl = "http://localhost:8084/payment/check-balance";
        Boolean response = restTemplate.postForObject(paymentServiceUrl, paymentDTO, Boolean.class);

        return Boolean.TRUE.equals(response);
    }

    private void saveBooking(BookingDTO bookingDTO) {
        Booking bookingEntity = bookingMapper.toEntity(bookingDTO);
        bookingRepository.save(bookingEntity);
    }
}