package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.OutletDTO;
import lk.ideahub.mypay.entity.Counter;
import lk.ideahub.mypay.entity.Merchant;
import lk.ideahub.mypay.entity.Outlet;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CounterRepository;
import lk.ideahub.mypay.repository.MerchantRepository;
import lk.ideahub.mypay.repository.OutletRepository;
import lk.ideahub.mypay.service.OutletService;
import lk.ideahub.mypay.util.mapper.OutletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class OutletServiceImpl implements OutletService {

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private OutletMapper outletMapper;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public Long saveOutlet(OutletDTO outletDTO) {
        Outlet outlet = outletMapper.toOutlet(outletDTO);
        Merchant merchant = merchantRepository.findById(outletDTO.getMerchantId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Merchant is not exists");
        });
        outlet.setMerchant(merchant);
        return outletRepository.save(outlet).getId();
    }

    @Override
    public Long updateOutlet(OutletDTO outletDTO) {
        Outlet outlet = outletRepository.findById(outletDTO.getId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Outlet is not exists");
        });
        Outlet toOutlet = outletMapper.toOutlet(outletDTO);
        toOutlet.setMerchant(outlet.getMerchant());
        return outletRepository.save(toOutlet).getId();
    }

    @Override
    public Long deleteOutlet(Long id) {
        Outlet outlet = outletRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Outlet is not exists");
        });
        List<Counter> allCounterByOutletId = counterRepository.getAllCounterByOutletId(outlet.getId());
        if (!allCounterByOutletId.isEmpty()) {
            allCounterByOutletId.forEach(counter -> {
                counter.setStatus(0);
                counterRepository.save(counter);
            });
        }
        outlet.setStatus(0);
        return outletRepository.save(outlet).getId();
    }

    @Override
    public OutletDTO getOutletById(Long id) {
        Outlet outlet = outletRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Outlet is not exists");
        });
        OutletDTO outletDTO = outletMapper.toOutletDto(outlet);
        outletDTO.setMerchantName(merchantRepository.findById(outlet.getMerchant().getId()).get().getName());
        return outletDTO;
    }
}
