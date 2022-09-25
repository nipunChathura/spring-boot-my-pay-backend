package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.PaymentDTO;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface PaymentService {
    public Long makePayment(PaymentDTO paymentDTO);
    public PaymentDTO getPaymentById(Long Id);
    public List<PaymentDTO> getAllPaymentByCustomer(Long customerId);
}
