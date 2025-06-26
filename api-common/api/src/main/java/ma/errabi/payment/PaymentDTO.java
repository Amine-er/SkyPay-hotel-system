package ma.errabi.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long userId;
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvv;
    private BigDecimal amount;

}
