package ma.errabi.review.controller;

import lombok.RequiredArgsConstructor;
import ma.errabi.review.ReviewDTO;
import ma.errabi.review.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Get all reviews")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the reviews") })
    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> getAllReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(reviewService.getAllReviews(page, size));
    }

    @Operation(summary = "Get a review by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the review"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getById(
            @Parameter(description = "ID of the review to be obtained") @PathVariable Long id) {
        ReviewDTO review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }
    @Operation(summary = "Get reviews by room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reviews for the room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ReviewDTO>> getByRoomId(
            @Parameter(description = "ID of the room to get reviews for") @PathVariable Long roomId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByRoomId(roomId);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "Create a new review")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Review created")})
    @PostMapping
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO dto) {
        ReviewDTO created = reviewService.createReview(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Update an existing review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(
            @Parameter(description = "ID of the review to be updated") @PathVariable Long id,
            @RequestBody ReviewDTO dto) {
        ReviewDTO updated = reviewService.updateReview(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Review deleted"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID of the review to be deleted") @PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
