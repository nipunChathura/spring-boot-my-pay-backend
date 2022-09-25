package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Card;
import lk.ideahub.mypay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(*) = 1 THEN 'true' ELSE 'false' END FROM card c WHERE c.customer_id = ?1 AND c.card_number = ?2 AND  c.status = ?3", nativeQuery = true)
    String existsCard(Long customerId, String cardNumber, int status);

    @Query(value = "select c.id as crdId, c.card_number, c.bank_name, c.cardholder_name, c.valid_thru, c.cvv, c.card_type, c.status, c.customer_id, c2.name from card c left join customer c2 on c.customer_id = c2.id where customer_id = ?1 and c.status = 1", nativeQuery = true)
    List<Object []> getAllCardByCustomer(Long customerId);


    @Query(value = "select * from card where customer_id = ?1 and status = 1", nativeQuery = true)
    List<Card> getAllCardByCustomerId(Long customerId);

    @Query(value = "select cardholder_name from card where id = ?1", nativeQuery=true)
    String getCardNumber(Long cardId);

    @Query(value = "select c.phone_number from customer c join card c2 on c.id = c2.customer_id where c.id = ?1", nativeQuery = true)
    String getCardholderPhoneNumber(Long cardId);
}
