package Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vegetable")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vegetableID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vegetable")
    private List<OrderDetail> orderDetails;

    @Column
    private String vegetableName;

    @Column
    private String unit;

    @Column
    private int amount;

    @Column
    private String image;

    @Column
    private float price;
}
