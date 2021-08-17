package ru.demo.marketplaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateForm {
    @Email(message = "Please enter correct email")
    @NotBlank(message = "Please enter buyer email")
    private String buyerEmail;

    @NotEmpty(message = "The order cannot be without products")
    private List<ProductDto> products;
}
