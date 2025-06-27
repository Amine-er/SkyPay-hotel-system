package ma.errabi.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long userId;
    private Long roomId;
    private LocalDate startTime;
    private LocalDate endTime;
    private UUID reference;
}
