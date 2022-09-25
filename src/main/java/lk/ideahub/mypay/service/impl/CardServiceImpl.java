package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.CardDTO;
import lk.ideahub.mypay.entity.Card;
import lk.ideahub.mypay.entity.Customer;
import lk.ideahub.mypay.exception.EntryDuplicateException;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CardRepository;
import lk.ideahub.mypay.repository.CustomerRepository;
import lk.ideahub.mypay.service.CardService;
import lk.ideahub.mypay.service.EncryptDecryptService;
import lk.ideahub.mypay.util.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EncryptDecryptService encryptDecryptService;

    @Override
    public Long saveCard(CardDTO cardDTO) {
        String s = cardRepository.existsCard(cardDTO.getCustomerId(), cardDTO.getCardNumber(), cardDTO.getStatus());
        if (s.equals("false")) {
            Customer customer = customerRepository.findById(cardDTO.getCustomerId()).orElseThrow(() -> {
                throw new EntryNotFoundException("Customer is not exists");
            });
            Card card = cardMapper.toCard(cardDTO);
            card.setCustomer(customer);
            card.setCardNumber(encryptDecryptService.encrypt(String.valueOf(card.getCardNumber())));
            Card save = cardRepository.save(card);
            return save.getId();
        } else {
            throw new EntryDuplicateException("Card is already exists");
        }

    }

    @Override
    public Long updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findById(cardDTO.getId()).orElseThrow(() -> {
            throw new EntryNotFoundException("Card is not exists");
        });
        Card toCard = cardMapper.toCard(cardDTO);
        card.setCardNumber(encryptDecryptService.encrypt(String.valueOf(card.getCardNumber())));
        toCard.setCustomer(card.getCustomer());
        return cardRepository.save(toCard).getId();
    }

    @Override
    public Long deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> {
            throw new EntryNotFoundException("Card is not exists");
        });
        card.setStatus(0);
        return cardRepository.save(card).getId();
    }

    @Override
    public List<CardDTO> getAllCustomerHasCard(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            throw new EntryNotFoundException("Customer is not exists");
        });
        List<Card> allCardByCustomerId = cardRepository.getAllCardByCustomerId(customerId);
        List<CardDTO> cardDTOS = new ArrayList<>();
        allCardByCustomerId.forEach(card -> {
            CardDTO cardDTO = toCardDTO(card, customer.getName());
            cardDTO.setCardNumber(encryptDecryptService.decrypt(card.getCardNumber()));
            cardDTOS.add(cardDTO);
        });
        return cardDTOS;
    }

    public CardDTO toCardDTO(Card card, String customerName) {
        return new CardDTO(
                card.getId(),
                card.getCardNumber(),
                card.getBankName(),
                card.getCardholderName(),
                card.getValidThru(),
                card.getCvv(),
                card.getCardType(),
                card.getStatus(),
                card.getCustomer().getId(),
                customerName
        );
    }
}
