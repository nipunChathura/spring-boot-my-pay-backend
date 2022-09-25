package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.OutletDTO;
import lk.ideahub.mypay.entity.Outlet;
import org.mapstruct.Mapper;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface OutletMapper {
    Outlet toOutlet(OutletDTO outletDTO);
    OutletDTO toOutletDto(Outlet outlet);
}
