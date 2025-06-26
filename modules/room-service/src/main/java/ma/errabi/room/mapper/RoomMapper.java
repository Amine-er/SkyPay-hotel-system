package ma.errabi.room.mapper;

import ma.errabi.room.RoomDTO;
import ma.errabi.room.domain.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {
    Room toEntity(RoomDTO dto);
    RoomDTO toDto(Room entity);
}
