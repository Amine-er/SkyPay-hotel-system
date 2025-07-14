package ma.errabi.review;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private Long roomId;
    private Long userId;
    private int rating;
    private LocalDate date;
    private String stayDuration;
    private String comment;
    private Boolean showMore;
    private Boolean hasThumbsUp;
}
