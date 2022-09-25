package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    @Query(value = "select * from otp where phone_number = ?1 order by id desc limit 1", nativeQuery = true)
    OTP getOTPByPhoneNumber(String phoneNumber);
}
