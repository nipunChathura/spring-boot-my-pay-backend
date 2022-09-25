package lk.ideahub.mypay.service.impl;

import lk.ideahub.mypay.dto.RegistrationRequestDTO;
import lk.ideahub.mypay.entity.OTP;
import lk.ideahub.mypay.exception.EntryDuplicateException;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.repository.CustomerRepository;
import lk.ideahub.mypay.repository.OTPRepository;
import lk.ideahub.mypay.service.RegistrationService;
import lk.ideahub.mypay.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private TwilioOTPService twilioOTPService;

    @Override
    public void senOtp(String phoneNumber) {
        String exist = customerRepository.existsCustomerPhoneNumber(phoneNumber);
        if (exist.equals("false")) {
            String otp = twilioOTPService.generateOTP();
            otpRepository.save(new OTP(0L, phoneNumber, otp));
            twilioOTPService.sendRegistrationOTP(phoneNumber, otp);
        } else {
            throw new EntryDuplicateException("Phone number is already exists");
        }
    }

    @Override
    public void validOtp(String phoneNumber, String userInputOtp) {
        OTP otp = otpRepository.getOTPByPhoneNumber(phoneNumber);
        if (otp != null) {
            otpRepository.deleteById(otp.getId());
            if (otp.getOtp().equals(userInputOtp)) {
                twilioOTPService.validatedOTP(
                        phoneNumber,
                        "Verification success. Thank You.");
            } else {
                twilioOTPService.validatedOTP(
                        phoneNumber,
                        "Verification fail. Thank You.");
            }
        } else {
            throw new EntryNotFoundException("This Phone number has not otp");
        }
    }
}
