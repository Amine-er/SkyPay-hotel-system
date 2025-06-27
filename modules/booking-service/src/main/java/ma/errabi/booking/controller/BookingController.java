package ma.errabi.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import ma.errabi.booking.service.BookingService;
import ma.errabi.payment.PaymentDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/make-reservation")
    @Operation(summary = "Make a reservation for a room")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Payment details")
    @ApiResponse(responseCode = "200", description = "Successfully made reservation")
    public Mono<UUID> makeReservation(@RequestParam Long roomId,
                                      @RequestParam LocalDate startDate,
                                      @RequestParam LocalDate endDate,
                                      @RequestBody PaymentDTO paymentDTO) {
        return bookingService.makeReservation(roomId, startDate, endDate, paymentDTO);
    }

}