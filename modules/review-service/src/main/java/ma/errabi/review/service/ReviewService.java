package ma.errabi.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.exception.SystemException;
import ma.errabi.review.ReviewDTO;
import ma.errabi.review.mapper.ReviewMapper;
import ma.errabi.review.repository.ReviewRepository;
import ma.errabi.utils.ErrorConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }

    @Transactional(readOnly = true)
    public ReviewDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByRoomId(Long roomId) {
        log.info("Fetching reviews for room ID: {}", roomId);
        return reviewRepository.getReviewByRoomId(roomId).stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());    }

    public ReviewDTO createReview(ReviewDTO dto) {
        log.info("Creating new review: {}", dto);
        try {
            return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(dto)));
        } catch (Exception e) {
            log.error("Error while creating review: {}. Exception: {}", dto, e.getMessage(), e);
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }

    public ReviewDTO updateReview(Long id, ReviewDTO dto) {
        log.info("Updating review with ID: {}. New data: {}", id, dto);
        try {
            var existing = reviewRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Review not found"));
            reviewMapper.updateFromDto(dto, existing);
            return reviewMapper.toDto(reviewRepository.save(existing));
        }catch (Exception e) {
            log.error("Error while updating review with ID: {}. Exception: {}", id, e.getMessage(), e);
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }

    public void deleteReview(Long id) {
        log.info("Deleting review with ID: {}", id);
        try {
            reviewRepository.deleteById(id);
        }catch (Exception e) {
            log.error("Error while deleting review with ID: {}. Exception: {}", id, e.getMessage(), e);
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }
}
