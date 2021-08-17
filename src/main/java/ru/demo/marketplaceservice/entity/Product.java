package ru.demo.marketplaceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(precision = 2, nullable = false)
    private Double price;

    @Column(length = 100, nullable = false)
    private String currency;

    private Integer vendorCode;

    private Boolean deleted;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
