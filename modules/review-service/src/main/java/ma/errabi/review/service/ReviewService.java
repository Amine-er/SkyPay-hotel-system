package ma.errabi.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.review.ReviewDTO;
import ma.errabi.review.mapper.ReviewMapper;
import ma.errabi.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional(readOnly = true)
    public Page<ReviewDTO> getAllReviews(int page, int size) {
        log.info("Fetching all reviews with pagination - page: {}, size: {}", page, size);
        try {
            Pageable pageable = PageRequest.of(page, size);
            return reviewRepository.findAll(pageable).map(reviewMapper::toDto);
        } catch (Exception e) {
            log.error("Error while fetching reviews - page: {}, size: {}. Exception: {}", page, size, e.getMessage(), e);
            throw new RuntimeException("Unable to fetch reviews. Try again later.");
        }
    }

    @Transactional(readOnly = true)
    public ReviewDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    public ReviewDTO createReview(ReviewDTO dto) {
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(dto)));
    }

    public ReviewDTO updateReview(Long id, ReviewDTO dto) {
        var existing = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        reviewMapper.updateFromDto(dto, existing);
        return reviewMapper.toDto(reviewRepository.save(existing));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
