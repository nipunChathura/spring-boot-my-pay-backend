package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.MerchantDTO;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface MerchantService {
    public Long saveMerchant(MerchantDTO merchantDTO);
    public Long updateMerchant(MerchantDTO merchantDTO);
    public Long deleteMerchant(Long id);
}
