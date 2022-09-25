package lk.ideahub.mypay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "payment")
public class Payment implements SupperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date datetime;
    @Column(name = "paidAmount", nullable = false)
    private BigDecimal paidAmount;
    @Column(name = "status", columnDefinition = "TINYINT", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "counter_id", referencedColumnName = "id", nullable = false)
    private Counter counter;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", paidAmount=" + paidAmount +
                ", status=" + status +
                ", card=" + card +
                ", counter=" + counter +
                '}';
    }
}
