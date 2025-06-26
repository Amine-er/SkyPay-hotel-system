package ma.errabi.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private RoomType type;
    private String description;
    private List<String> imageUrl;
    private BigDecimal price;
}
