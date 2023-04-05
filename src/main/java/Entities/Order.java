package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    @ManyToOne
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "orderdetail",
            joinColumns = { @JoinColumn(name = "OrderID") },
            inverseJoinColumns = { @JoinColumn(name = "VegetableID") }
    )
    private List<Vegetable> vegetables;
    @Column
    private LocalDate date;
    @Column
    private float total;
    @Column
    private String note;

}
