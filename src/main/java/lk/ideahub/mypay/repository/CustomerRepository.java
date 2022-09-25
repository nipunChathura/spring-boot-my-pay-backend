package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from customer where status = 1", nativeQuery = true)
    Page<Customer> getAllCustomer(Pageable page);

    @Query(value = "SELECT CASE WHEN COUNT(*) = 1 THEN 'true' ELSE 'false' END FROM customer WHERE phone_number = ?1", nativeQuery = true)
    String existsCustomerPhoneNumber(String phoneNumber);

}
