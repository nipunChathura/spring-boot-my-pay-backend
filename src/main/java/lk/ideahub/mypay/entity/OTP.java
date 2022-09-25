package lk.ideahub.mypay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "otp")
public class OTP implements SupperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "otp", nullable = false)
    private String otp;
}
