package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.CounterDTO;
import lk.ideahub.mypay.dto.OutletDTO;
import lk.ideahub.mypay.entity.Outlet;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface OutletService {
    public Long saveOutlet(OutletDTO outletDTO);
    public Long updateOutlet(OutletDTO outletDTO);
    public Long deleteOutlet(Long id);
    public OutletDTO getOutletById(Long id);
}
