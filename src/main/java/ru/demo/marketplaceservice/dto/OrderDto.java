package ru.demo.marketplaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.demo.marketplaceservice.entity.Product;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "Order number cannot be null")
    private Integer orderNumber;

    @Email(message = "Enter correct email")
    @NotBlank(message = "Please enter buyer message")
    private String buyerEmail;

    @NotNull(message = "Created date cannot be null")
    private Calendar createdAt;

    @NotEmpty(message = "Products cannot be empty")
    private List<ProductDto> products;
}
