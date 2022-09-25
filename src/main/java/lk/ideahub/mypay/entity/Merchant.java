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

@Entity(name = "merchant")
public class Merchant implements SupperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "nic_number", nullable = false)
    private String nic_number;
    @Column(name = "status", columnDefinition = "TINYINT", nullable = false)
    private int status;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Outlet> outlets;

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", nic_number='" + nic_number + '\'' +
                ", status=" + status +
                '}';
    }
}
