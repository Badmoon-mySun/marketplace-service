package ru.demo.marketplaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String currency;
    private Integer vendorCode;
    private Boolean deleted;
}
