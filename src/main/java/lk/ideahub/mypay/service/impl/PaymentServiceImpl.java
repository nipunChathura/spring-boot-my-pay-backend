package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.PaymentDTO;
import lk.ideahub.mypay.entity.Card;
import lk.ideahub.mypay.entity.Counter;
import lk.ideahub.mypay.entity.Payment;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CardRepository;
import lk.ideahub.mypay.repository.CounterRepository;
import lk.ideahub.mypay.repository.PaymentRepository;
import lk.ideahub.mypay.service.EncryptDecryptService;
import lk.ideahub.mypay.service.PaymentService;
import lk.ideahub.mypay.service.TwilioOTPService;
import lk.ideahub.mypay.util.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private EncryptDecryptService encryptDecryptService;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private TwilioOTPService twilioOTPService;

    @Override
    public Long makePayment(PaymentDTO paymentDTO) {
        Card card = cardRepository.findById(paymentDTO.getCardId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Card is not exists");
        });
        Counter counter = counterRepository.findById(paymentDTO.getCounterId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Counter is not exists");
        });
        Payment payment = paymentMapper.toPayment(paymentDTO);
        payment.setCard(card);
        payment.setCounter(counter);
        String phoneNumber = cardRepository.getCardholderPhoneNumber(card.getId());
        twilioOTPService.sendPaymentNotification(phoneNumber, paymentDTO.getPaidAmount());
        return paymentRepository.save(payment).getId();
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Payment is not exists");
        });
        return toPaymentDTO(payment);
    }

    public PaymentDTO toPaymentDTO(Payment payment){
        return new PaymentDTO(
                payment.getId(),
                payment.getDatetime().toString(),
                payment.getPaidAmount(),
                payment.getStatus(),
                payment.getCard().getId(),
                cardRepository.getCardNumber(payment.getCard().getId()),
                payment.getCounter().getId(),
                counterRepository.getCounterNumber(payment.getCounter().getId())
        );
    }

    @Override
    public List<PaymentDTO> getAllPaymentByCustomer(Long customerId) {
        List<Object[]> allPaymentByCustomerId = paymentRepository.getAllPaymentByCustomerId(customerId);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        allPaymentByCustomerId.forEach(objects -> {
            PaymentDTO paymentDTO = toPaymentDTO(objects);
            paymentDTO.setCardNumber(encryptDecryptService.decrypt(paymentDTO.getCardNumber()));
            paymentDTOS.add(paymentDTO);
        });
        return paymentDTOS;
    }

    public PaymentDTO toPaymentDTO(Object[] objects) {
        return new PaymentDTO(
                Long.parseLong(objects[0].toString()),
                objects[1].toString(),
                new BigDecimal(objects[2].toString()),
                Integer.parseInt(objects[3].toString()),
                Long.parseLong(objects[4].toString()),
                objects[5].toString(),
                Long.parseLong(objects[6].toString()),
                objects[7].toString()
        );
    }
}
