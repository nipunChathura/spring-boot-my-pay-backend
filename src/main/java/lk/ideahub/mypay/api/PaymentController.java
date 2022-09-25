package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.dto.PaymentDTO;
import lk.ideahub.mypay.service.PaymentService;
import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> makePayment(@RequestBody PaymentDTO paymentDTO) {
        Long id = paymentService.makePayment(paymentDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> getPaymentById(@PathVariable Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", paymentDTO),
                HttpStatus.OK);
    }

    @GetMapping(path = "all/{customerId}")
    public ResponseEntity<StandardResponse> getAllPaymentByCustomer(@PathVariable Long customerId) {
        List<PaymentDTO> allPaymentByCustomer = paymentService.getAllPaymentByCustomer(customerId);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", allPaymentByCustomer),
                HttpStatus.OK);
    }
}
