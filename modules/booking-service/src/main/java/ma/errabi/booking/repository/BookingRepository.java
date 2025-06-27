package ma.errabi.booking.repository;

import ma.errabi.booking.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends MongoRepository<Booking, UUID> {
    Optional<Booking> findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long roomId, LocalDate endDate, LocalDate startDate);
}
