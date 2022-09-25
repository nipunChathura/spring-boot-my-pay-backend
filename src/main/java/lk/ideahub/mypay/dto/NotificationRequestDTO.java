package lk.ideahub.mypay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationRequestDTO implements SuperDTO{
    private String phoneNumber;
    private BigDecimal amount;
}
