package lk.ideahub.mypay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO implements SuperDTO {
    private Long id;
    private String datetime;
    private BigDecimal paidAmount;
    private int status;
    private Long cardId;
    private String cardNumber;
    private Long counterId;
    private String counterNumber;
}
