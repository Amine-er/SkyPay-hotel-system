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
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentDTO.getUserId());

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            log.debug("User found: {}", paymentMapper.toDto(payment));

            if (!payment.getCardNumber().equals(paymentDTO.getCardNumber()) ||
                    !payment.getExpiryDate().equals(paymentDTO.getExpiryDate()) ||
                    !payment.getCvv().equals(paymentDTO.getCvv())) {
                log.error("Invalid card details provided for userId: {}", paymentDTO.getUserId());
                throw new IllegalArgumentException("Invalid card details provided.");
            }

            if (payment.getBalance().compareTo(paymentDTO.getAmount()) < 0) {
                log.warn("Insufficient funds for userId: {}. Current Balance: {}, Requested Amount: {}",
                        paymentDTO.getUserId(), payment.getBalance(), paymentDTO.getAmount());
                return true; // Insufficient funds
            }

            payment.setBalance(payment.getBalance().subtract(paymentDTO.getAmount()));
            paymentRepository.save(payment);

            return false; // // Sufficient funds
        } else {
            log.error("User with ID {} not found.", paymentDTO.getUserId());
            throw new IllegalArgumentException("User with ID " + paymentDTO.getUserId() + " not found.");
        }
    }

}
