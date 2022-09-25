package lk.ideahub.mypay.service;

import lk.ideahub.mypay.dto.NotificationRequestDTO;
import lk.ideahub.mypay.dto.NotificationResponseDTO;
import lk.ideahub.mypay.dto.RegistrationRequestDTO;
import lk.ideahub.mypay.dto.RegistrationResponseDTO;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

public interface TwilioOTPService {
    public void sendRegistrationOTP(String phoneNumber, String otp);
    public void validatedOTP(String phoneNumber, String message);
    public void sendPaymentNotification(String phoneNumber, BigDecimal amount);
    public String generateOTP();
}
