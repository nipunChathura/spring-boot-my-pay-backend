package lk.ideahub.mypay.util.mapper;

import lk.ideahub.mypay.dto.CardDTO;
import lk.ideahub.mypay.entity.Card;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toCard(CardDTO cardDTO);
    CardDTO toCustomerDTO(Card card);
    List<Card> toCustomerList(List<CardDTO> cardDTOS);
    List<CardDTO> toCustomerDTOList(List<Card> cards);
}
