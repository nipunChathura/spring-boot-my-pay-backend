package lk.ideahub.mypay.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lk.ideahub.mypay.dto.*;
import lk.ideahub.mypay.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Service
public class TwilioOTPServiceImpl implements TwilioOTPService {

    @Value("${twilio.account_sid}")
    private String sid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.trial_number}")
    private String myPayPhoneNumber;

    @Override
    public void sendRegistrationOTP(String phoneNumber, String otp) {
        PhoneNumber to = new PhoneNumber(phoneNumber);
        PhoneNumber from = new PhoneNumber(myPayPhoneNumber);
        String otpMessage = "Dear Customer, Your OTP is ## "+ otp+ "##. Use this passcode to your registration. Thank You.";
        Message message = Message.creator(to, from, otpMessage).create();
    }

    @Override
    public void validatedOTP(String sendPhoneNumber, String validMessage) {
        Twilio.init(sid, authToken);
        Message message = Message.creator(new PhoneNumber(sendPhoneNumber),
                        new PhoneNumber(myPayPhoneNumber),
                        validMessage).create();
    }

    @Override
    public void sendPaymentNotification(String phoneNumber, BigDecimal amount) {
        try {
            Twilio.init(sid, authToken);
            Message message = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(myPayPhoneNumber),
                    "Dear Customer, Your paid "+amount.doubleValue()+". Thank You.").create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateOTP() {
       return new DecimalFormat("000000").
               format(new Random().nextInt(999999));
    }
}
