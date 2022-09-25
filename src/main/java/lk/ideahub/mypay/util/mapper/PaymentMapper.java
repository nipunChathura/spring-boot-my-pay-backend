package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.PaymentDTO;
import lk.ideahub.mypay.entity.Payment;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "datetime", dateFormat = "yyyy-MM-dd")
    Payment toPayment(PaymentDTO paymentDTO);
    @IterableMapping(dateFormat = "yyyy-MM-dd")
    PaymentDTO toPaymentDto(Payment payment);
}
