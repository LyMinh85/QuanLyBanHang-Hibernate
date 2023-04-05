package Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    @Column
    private String fullname;
    @Column
    private String password;
    @Column
    private String address;
    @Column
    private String city;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Order> orders;

}
