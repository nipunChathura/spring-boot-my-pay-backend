package lk.ideahub.mypay.repository;

import lk.ideahub.mypay.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(*) = 1 THEN 'true' ELSE 'false' END FROM counter WHERE counter.counter_number = ?1 and outlet_id = ?2;", nativeQuery = true)
    String existsCounter(String counterNumber, Long outletId);

    @Query(value= "select * from counter where outlet_id = ?1 and status = ?2", nativeQuery = true)
    List<Counter> getAllCounterByOutletId(Long outletId);

    @Query(value = "select counter_number from counter where id = ?1", nativeQuery=true)
    String getCounterNumber(Long counterId);
}
