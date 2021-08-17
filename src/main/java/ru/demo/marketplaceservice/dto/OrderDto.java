package ru.demo.marketplaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.demo.marketplaceservice.entity.Product;

import java.util.Calendar;
import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Integer orderNumber;
    private String buyerEmail;
    private Calendar createdAt;
    private List<Product> products;
}
