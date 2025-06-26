package ma.errabi.payment.controller;

import lombok.RequiredArgsConstructor;
import ma.errabi.payment.PaymentDTO;
import ma.errabi.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/check-balance")
    public Boolean checkBalance(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.checkAndDeductBalance(paymentDTO);
    }
}

