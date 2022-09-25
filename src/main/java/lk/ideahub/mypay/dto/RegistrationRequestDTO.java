package lk.ideahub.mypay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationRequestDTO {
    private String phoneNumber;
    private String userName;
    private String oneTimePassword;
}
