package ma.errabi.booking.repository;

import ma.errabi.booking.domain.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, UUID> {
    Mono<Booking> findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long roomId, LocalDate endDate, LocalDate startDate);
}

