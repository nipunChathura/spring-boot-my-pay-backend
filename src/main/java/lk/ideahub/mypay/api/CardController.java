package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.CardDTO;
import lk.ideahub.mypay.service.CardService;
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
@RequestMapping("/api/v1/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> saveCard(@RequestBody CardDTO cardDTO) {
        Long id = cardService.saveCard(cardDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> updateCard(@RequestBody CardDTO cardDTO) {
        Long id = cardService.updateCard(cardDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", id),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteCard(@PathVariable Long id) {
        Long cardId = cardService.deleteCard(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", cardId),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<StandardResponse> getAllCardHasCustomer(@PathVariable Long customerId) {
        List<CardDTO> allCustomerHasCard = cardService.getAllCustomerHasCard(customerId);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", allCustomerHasCard),
                HttpStatus.OK);
    }
}
