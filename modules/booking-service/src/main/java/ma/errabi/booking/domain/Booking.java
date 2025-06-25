package ma.errabi.booking.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "bookings")
@Data
public class Booking {
    private Long userId;
    private Long roomId;
    private LocalDate startTime;
    private LocalDate endTime;
    private UUID reference;
}
