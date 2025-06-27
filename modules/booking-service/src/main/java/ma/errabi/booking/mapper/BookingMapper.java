package ma.errabi.booking.mapper;

import ma.errabi.booking.BookingDTO;
import ma.errabi.booking.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
    Booking toEntity(BookingDTO dto);
    BookingDTO toDto(Booking entity);
}
