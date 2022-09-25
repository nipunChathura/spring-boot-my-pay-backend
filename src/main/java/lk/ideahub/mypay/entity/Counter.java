package lk.ideahub.mypay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "counter")
public class Counter implements SupperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "counter_number", nullable = false)
    private String counterNumber;
    @Column(name = "cashier_name", nullable = false)
    private String cashierName;
    @Column(name = "status", columnDefinition = "TINYINT", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "outlet_id", referencedColumnName = "id", nullable = false)
    private Outlet outlet;

    @OneToMany(mappedBy = "counter", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Override
    public String toString() {
        return "Counter{" +
                "id=" + id +
                ", counterNumber='" + counterNumber + '\'' +
                ", cashierName='" + cashierName + '\'' +
                ", status=" + status +
                ", outlet=" + outlet +
                '}';
    }
}
