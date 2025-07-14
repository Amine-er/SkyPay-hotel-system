package ma.errabi.review.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reviews", schema = "schema_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private Long userId;
    private int rating;
    @CreationTimestamp
    private LocalDate date;
    private String stayDuration;
    private String comment;
    private Boolean showMore;
    private Boolean hasThumbsUp;
}
