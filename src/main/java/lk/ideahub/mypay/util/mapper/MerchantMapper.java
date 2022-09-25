package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.MerchantDTO;
import lk.ideahub.mypay.entity.Merchant;
import org.mapstruct.Mapper;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    Merchant toMerchant(MerchantDTO merchantDTO);
    MerchantDTO toMerchantDTO(Merchant merchant);
}
