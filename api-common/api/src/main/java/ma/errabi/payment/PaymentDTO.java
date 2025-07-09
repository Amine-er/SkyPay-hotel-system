package ma.errabi.payment;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private Long userId;
    private String email;
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvv;
    private BigDecimal amount;
}
