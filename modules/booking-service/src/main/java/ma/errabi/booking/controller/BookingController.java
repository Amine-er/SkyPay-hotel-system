package ma.errabi.booking.controller;

import lombok.RequiredArgsConstructor;
import ma.errabi.booking.service.BookingService;
import ma.errabi.payment.PaymentDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/make-reservation")
    public UUID makeReservation(
            @RequestParam Long roomId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestBody PaymentDTO paymentDTO) {
        return bookingService.makeReservation(roomId, startDate, endDate, paymentDTO);
    }
}