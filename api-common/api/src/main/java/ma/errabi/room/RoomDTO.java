package ma.errabi.room;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private Long id;
    private RoomType type;
    private String description;
    private List<String> imageUrl;
    private BigDecimal price;
}
