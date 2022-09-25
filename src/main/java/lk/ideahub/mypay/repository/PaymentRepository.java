package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "select payment.id as paymentId, payment.date, payment.paid_amount, payment.status, payment.card_id, c.card_number, payment.counter_id, c3.counter_number from payment left join card c on payment.card_id = c.id join customer c2 on c.customer_id = c2.id join counter c3 on payment.counter_id = c3.id where c2.id = ?1 and payment.status = 1", nativeQuery = true)
    List<Object []> getAllPaymentByCustomerId(Long customerId);
}
