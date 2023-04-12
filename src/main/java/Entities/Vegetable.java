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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vegetable", cascade = CascadeType.ALL)
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

    public Vegetable() {}

    public Vegetable(String vegetableName, String unit, int amount, String image, float price) {
        this.vegetableName = vegetableName;
        this.unit = unit;
        this.amount = amount;
        this.image = image;
        this.price = price;
    }

    public Object[] toObject(){
        return new Object[]{
                getVegetableID(),getVegetableName(),getPrice(),getAmount(), getUnit(),getCategory().getName(), getImage()
        };
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "vegetableID=" + vegetableID +
                ", category=" + category +
                ", vegetableName='" + vegetableName + '\'' +
                ", unit='" + unit + '\'' +
                ", amount=" + amount +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }

}
