package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.entity.Customer;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CustomerRepository;
import lk.ideahub.mypay.service.CustomerService;
import lk.ideahub.mypay.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Long saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer.setStatus(1);
        return customerRepository.save(customer).getId();
    }

    @Override
    public Long updateCustomer(CustomerDTO customerDTO) {
        customerRepository.findById(customerDTO.getId()).orElseThrow(()->{
            throw new EntryNotFoundException("Customer is not exists");
        });
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer.setStatus(1);
        return customerRepository.save(customer).getId();
    }

    @Override
    public Long deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Customer is not exists");
        });
        customer.setStatus(0);
        return customerRepository.save(customer).getId();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.toCustomerDTO(customerRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Customer is not exists");
        }));
    }

    @Override
    public List<CustomerDTO> getAllCustomer(int page, int size) {
        return customerMapper.toCustomerDTOList(customerRepository.getAllCustomer(PageRequest.of(page, size)).getContent());
    }
}
