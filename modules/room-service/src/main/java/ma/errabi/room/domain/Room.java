package ma.errabi.room.domain;

import jakarta.persistence.*;
import lombok.Data;
import ma.errabi.room.RoomType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rooms", schema = "schemaroom")
@Data
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private String description;

    private List<String> imageUrl;

    private BigDecimal price;
}
