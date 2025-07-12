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
