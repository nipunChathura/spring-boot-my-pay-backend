package lk.ideahub.mypay.service;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

public interface EncryptDecryptService {
    public String encrypt(String data);
    public String decrypt(String data);
}
