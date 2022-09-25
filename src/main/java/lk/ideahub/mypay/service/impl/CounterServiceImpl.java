package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.CounterDTO;
import lk.ideahub.mypay.entity.Counter;
import lk.ideahub.mypay.entity.Outlet;
import lk.ideahub.mypay.exception.EntryDuplicateException;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CounterRepository;
import lk.ideahub.mypay.repository.OutletRepository;
import lk.ideahub.mypay.service.CounterService;
import lk.ideahub.mypay.util.mapper.CounterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private CounterMapper counterMapper;

    @Autowired
    private OutletRepository outletRepository;

    @Override
    public Long saveCounter(CounterDTO counterDTO) {
        String s = counterRepository.existsCounter(counterDTO.getCounterNumber(), counterDTO.getOutletId());
        if (s.equals("false")) {
            Outlet outlet = outletRepository.findById(counterDTO.getOutletId()).orElseThrow(() -> {
                throw new EntryNotFoundException("Outlet is not exists");
            });
            Counter counter = counterMapper.toCounter(counterDTO);
            counter.setOutlet(outlet);
            Counter save = counterRepository.save(counter);
            return save.getId();
        } else {
            throw new EntryDuplicateException("Outlet has already exists counter number");
        }
    }

    @Override
    public Long updateCounter(CounterDTO counterDTO) {
        Counter counter = counterRepository.findById(counterDTO.getId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Counter is not exists");
        });
        Counter counter1 = counterMapper.toCounter(counterDTO);
        counter1.setOutlet(outletRepository.findById(counterDTO.getOutletId()).get());
        return counterRepository.save(counter1).getId();
    }

    @Override
    public Long deleteCounter(Long id) {
        Counter counter = counterRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Counter is not exists");
        });
        counter.setStatus(0);
        Counter save = counterRepository.save(counter);
        return save.getId();
    }

    @Override
    public List<CounterDTO> getAllCounterHasOutlet(Long outletId) {
        Outlet outlet = outletRepository.findById(outletId).orElseThrow(() -> {
            throw new EntryNotFoundException("Outlet is not exists");
        });
        List<Counter> counter = counterRepository.getAllCounterByOutletId(outletId);
        List<CounterDTO> counterDTOS = new ArrayList<>();
        counter.forEach(counter1 -> {
            counterDTOS.add(toCounterDTO(counter1, outlet.getName()));
        });
        return counterDTOS;
    }

    public CounterDTO toCounterDTO(Counter counter, String outletName) {
        return new CounterDTO(
                counter.getId(),
                counter.getCounterNumber(),
                counter.getCashierName(),
                counter.getStatus(),
                counter.getOutlet().getId(),
                outletName
        );
    }
}
