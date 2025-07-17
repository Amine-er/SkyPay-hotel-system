package ma.errabi.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.errabi"})
public class UserServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(UserServiceApplication.class, args);
    }
}
