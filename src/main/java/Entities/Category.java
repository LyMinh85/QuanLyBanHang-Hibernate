package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    @Column
    private String nane;
    @Column
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Vegetable> vegetables;
}