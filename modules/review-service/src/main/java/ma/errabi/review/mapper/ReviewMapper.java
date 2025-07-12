package ma.errabi.review.mapper;

import ma.errabi.review.ReviewDTO;
import ma.errabi.review.domain.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDto(Review review);
    Review toEntity(ReviewDTO dto);
    List<ReviewDTO> toDtoList(List<Review> reviews);
    void updateFromDto(ReviewDTO dto, @MappingTarget Review entity);
}
