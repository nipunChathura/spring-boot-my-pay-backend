package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.CardDTO;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/
public interface CardService {
    public Long saveCard(CardDTO cardDTO);
    public Long updateCard(CardDTO cardDTO);
    public Long deleteCard(Long id);
    public List<CardDTO> getAllCustomerHasCard(Long customerId);
}
