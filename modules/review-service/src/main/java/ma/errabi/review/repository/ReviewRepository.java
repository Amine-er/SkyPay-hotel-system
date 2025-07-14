package ma.errabi.review.repository;

import ma.errabi.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewByRoomId(Long roomId);
}
