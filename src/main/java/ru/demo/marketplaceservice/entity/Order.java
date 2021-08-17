package ru.demo.marketplaceservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
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
@EqualsAndHashCode(exclude = {"products"})
@ToString(exclude = {"products"})
@Table(name = "marketplace_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer orderNumber;

    private String buyerEmail;

    private Calendar createdAt;

    @ManyToMany
    @JoinTable(name = "orders_has_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
