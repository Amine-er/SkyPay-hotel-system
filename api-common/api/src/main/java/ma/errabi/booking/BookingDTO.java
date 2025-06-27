package ma.errabi.booking;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long userId;
    private Long roomId;
    private LocalDate startTime;
    private LocalDate endTime;
    private UUID reference;
}
