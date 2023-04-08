package Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "vegetableID", nullable = false)
    private Vegetable vegetable;

    @Column
    private int quantity;

    @Column
    private float price;

    public OrderDetail() {}

    public OrderDetail(Order order, Vegetable vegetable, int quantity, float price) {
        this.order = order;
        this.vegetable = vegetable;
        this.quantity = quantity;
        this.price = price;
    }
}
