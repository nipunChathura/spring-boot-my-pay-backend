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

@Entity(name = "outlet")
public class Outlet implements SupperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "contact_number", nullable = false)
    private String contactNumber;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "status", columnDefinition = "TINYINT", nullable = false)
    private int status;
    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", nullable = false)
    private Merchant merchant;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<Counter> counters;

    @Override
    public String toString() {
        return "Outlet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", merchant=" + merchant +
                '}';
    }
}
