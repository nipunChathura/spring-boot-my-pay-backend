package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.service.CustomerService;
import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Long id = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        Long id = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", id),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable Long id) {
        Long customerId = customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", customerId),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> getCustomerById(@PathVariable Long id) {
        CustomerDTO dto = customerService.getCustomerById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", dto),
                HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<StandardResponse> getAllCustomer(@RequestParam int page, @RequestParam int size) {
        List<CustomerDTO> allCustomer = customerService.getAllCustomer(page, size);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", allCustomer),
                HttpStatus.OK);
    }


}
