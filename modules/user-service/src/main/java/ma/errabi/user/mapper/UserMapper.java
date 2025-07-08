package ma.errabi.user.mapper;

import ma.errabi.user.UserDTO;
import ma.errabi.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDto(User entity);
}
