package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.MerchantDTO;
import lk.ideahub.mypay.entity.Counter;
import lk.ideahub.mypay.entity.Merchant;
import lk.ideahub.mypay.entity.Outlet;
import lk.ideahub.mypay.exception.EntryDuplicateException;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CounterRepository;
import lk.ideahub.mypay.repository.MerchantRepository;
import lk.ideahub.mypay.repository.OutletRepository;
import lk.ideahub.mypay.service.MerchantService;
import lk.ideahub.mypay.util.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Long saveMerchant(MerchantDTO merchantDTO) {
        String s = merchantRepository.existsMerchant(merchantDTO.getNic_number());
        if (s.equals("false")) {
            Merchant merchant = merchantMapper.toMerchant(merchantDTO);
            Merchant save = merchantRepository.save(merchant);
            return save.getId();
        } else {
            throw new EntryDuplicateException("Merchant is already exists");
        }
    }

    @Override
    public Long updateMerchant(MerchantDTO merchantDTO) {
        merchantRepository.findById(merchantDTO.getId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Merchant is not exists");
        });
        Merchant save = merchantRepository.save(merchantMapper.toMerchant(merchantDTO));
        return save.getId();
    }

    @Override
    public Long deleteMerchant(Long id) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Merchant is not exists");
        });
        List<Outlet> outlets = outletRepository.getAllOutletByMerchantId(id);
        outlets.forEach(outlet -> {
            List<Counter> counters = counterRepository.getAllCounterByOutletId(outlet.getId());
            counters.forEach(counter -> {counter.setStatus(0);});
            counterRepository.saveAll(counters);
            outlet.setStatus(0);
            outletRepository.save(outlet);
        });
        merchant.setStatus(0);
        Merchant save = merchantRepository.save(merchant);
        return save.getId();
    }
}
