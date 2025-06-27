package ma.errabi.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.payment.PaymentDTO;
import ma.errabi.payment.domain.Payment;
import ma.errabi.payment.mapper.PaymentMapper;
import ma.errabi.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public boolean checkAndDeductBalance(PaymentDTO paymentDTO) {
        log.info("Received check balance request for userId: {}, amount: {}", paymentDTO.getUserId(), paymentDTO.getAmount());

        Payment payment = findUserPayment(paymentDTO.getUserId());
        validateCardDetails(payment, paymentDTO);
        return processPayment(payment, paymentDTO.getAmount());
    }

    private Payment findUserPayment(Long userId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(userId);
        if (!paymentOptional.isPresent()) {
            log.error("User with ID {} not found.", userId);
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        log.debug("User found: {}", paymentMapper.toDto(paymentOptional.get()));
        return paymentOptional.get();
    }

    private void validateCardDetails(Payment payment, PaymentDTO paymentDTO) {
        if (!payment.getCardNumber().equals(paymentDTO.getCardNumber()) ||
                !payment.getExpiryDate().equals(paymentDTO.getExpiryDate()) ||
                !payment.getCvv().equals(paymentDTO.getCvv())) {
            log.error("Invalid card details provided for userId: {}", paymentDTO.getUserId());
            throw new IllegalArgumentException("Invalid card details provided.");
        }
    }

    private boolean processPayment(Payment payment, java.math.BigDecimal amount) {
        if (payment.getBalance().compareTo(amount) < 0) {
            log.warn("Insufficient funds for userId: {}. Current Balance: {}, Requested Amount: {}",
                    payment.getUserId(), payment.getBalance(), amount);
            return false;
        }

        payment.setBalance(payment.getBalance().subtract(amount));
        paymentRepository.save(payment);
        return true;
    }
}
