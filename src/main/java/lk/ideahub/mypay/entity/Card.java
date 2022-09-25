package lk.ideahub.mypay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "card")
public class Card implements SupperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "card_number", nullable = false)
    private String cardNumber;
    @Column(name = "bank_name", nullable = false)
    private String bankName;
    @Column(name = "cardholder_name", nullable = false)
    private String cardholderName;
    @Column(name = "valid_thru", nullable = false)
    private String validThru;
    @Column(name = "cvv", nullable = false)
    private int cvv;
    @Column(name = "card_type", nullable = false)
    private String cardType;
    @Column(name = "status", columnDefinition = "TINYINT", nullable = false)
    private int status;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", bankName='" + bankName + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", validThru='" + validThru + '\'' +
                ", cvv=" + cvv +
                ", cardType='" + cardType + '\'' +
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }
}
