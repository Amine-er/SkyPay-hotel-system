package ma.errabi.payment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.errabi"})
public class PaymentServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
