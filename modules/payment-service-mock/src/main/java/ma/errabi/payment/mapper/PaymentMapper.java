package ma.errabi.payment.mapper;

import ma.errabi.payment.PaymentDTO;
import ma.errabi.payment.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    Payment toEntity(PaymentDTO dto);
    PaymentDTO toDto(Payment entity);
}
