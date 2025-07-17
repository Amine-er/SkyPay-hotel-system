package ma.errabi.room;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.errabi"})
public class RoomServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(RoomServiceApplication.class, args);
    }
}
