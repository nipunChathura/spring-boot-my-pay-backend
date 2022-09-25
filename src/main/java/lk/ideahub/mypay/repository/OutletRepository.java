package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface OutletRepository extends JpaRepository<Outlet, Long> {

    @Query(value = "select * from outlet where merchant_id = ?1 and status = 1", nativeQuery = true)
    List<Outlet> getAllOutletByMerchantId(Long merchantId);

}
