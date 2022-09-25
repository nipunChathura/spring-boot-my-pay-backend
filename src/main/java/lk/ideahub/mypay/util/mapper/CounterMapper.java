package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.CounterDTO;
import lk.ideahub.mypay.entity.Counter;
import org.mapstruct.Mapper;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface CounterMapper {
    Counter toCounter(CounterDTO counterDTO);
    CounterDTO toCounterDto(Counter counter);
}
