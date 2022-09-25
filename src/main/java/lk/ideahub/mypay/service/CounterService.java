package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.CounterDTO;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface CounterService {
    public Long saveCounter(CounterDTO counterDTO);
    public Long updateCounter(CounterDTO counterDTO);
    public Long deleteCounter(Long id);
    public List<CounterDTO> getAllCounterHasOutlet(Long outletId);
}
