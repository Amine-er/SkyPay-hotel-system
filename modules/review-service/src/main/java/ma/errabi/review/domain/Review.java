package ma.errabi.review.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reviews", schema = "schema_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestName;
    private String guestLocation;
    private int rating;
    private LocalDate date;
    private String stayDuration;
    private String comment;
    private String avatar;
    private int yearsOnPlatform;
    private Boolean showMore;
    private Boolean hasThumbsUp;
}
