package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.CustomerDTO;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface CustomerService {
    public Long saveCustomer(CustomerDTO customerDTO);
    public Long updateCustomer(CustomerDTO customerDTO);
    public Long deleteCustomer(Long id);
    public CustomerDTO getCustomerById(Long id);
    public List<CustomerDTO> getAllCustomer(int page, int size);
}
