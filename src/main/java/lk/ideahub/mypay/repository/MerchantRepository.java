package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(*) = 1 THEN 'true' ELSE 'false' END FROM merchant WHERE nic_number = ?1 and status = 1", nativeQuery = true)
    String existsMerchant(String nic);
}
