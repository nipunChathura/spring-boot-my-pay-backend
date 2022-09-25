package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
    List<Customer> toCustomerList(List<CustomerDTO> customerDTOS);
    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);
}
