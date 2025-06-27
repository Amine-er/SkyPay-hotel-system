package ma.errabi.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import ma.errabi.payment.PaymentDTO;
import ma.errabi.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/check-balance")
    @Operation(summary = "Check balance for a user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Payment details")
    @ApiResponse(responseCode = "200", description = "Successfully checked balance")
    public Boolean checkBalance(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.checkAndDeductBalance(paymentDTO);
    }
}

