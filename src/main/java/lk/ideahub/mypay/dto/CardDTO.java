package lk.ideahub.mypay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDTO implements SuperDTO {
    private Long id;
    private String cardNumber;
    private String bankName;
    private String cardholderName;
    private String validThru;
    private int cvv;
    private String cardType;
    private int status;
    private Long customerId;
    private String customerName;
}
