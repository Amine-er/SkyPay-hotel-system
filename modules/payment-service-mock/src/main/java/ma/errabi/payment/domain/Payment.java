package ma.errabi.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String fullName;
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvv;

    private BigDecimal balance;
}
