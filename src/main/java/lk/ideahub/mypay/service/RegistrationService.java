package lk.ideahub.mypay.service;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/
public interface RegistrationService {
    public void senOtp(String phoneNumber);
    public void validOtp(String phoneNumber, String userInputOtp);
}
