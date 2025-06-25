package ma.errabi.room.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

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

    private String imageUrl;

    private BigDecimal price;
}
