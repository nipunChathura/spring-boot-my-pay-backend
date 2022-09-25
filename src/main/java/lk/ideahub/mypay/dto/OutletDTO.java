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
public class OutletDTO implements SuperDTO {
    private Long id;
    private String name;
    private String contactNumber;
    private String address;
    private int status;
    private Long merchantId;
    private String merchantName;
}
