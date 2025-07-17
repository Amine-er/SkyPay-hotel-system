package ma.errabi.booking;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.errabi"})
public class BookingServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BookingServiceApplication.class, args);
    }
}
