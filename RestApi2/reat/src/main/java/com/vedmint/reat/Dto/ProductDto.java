package com.vedmint.reat.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import com.vedmint.reat.Model.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean isActive;
    private UserDto user;

    public static ProductDto fromEntity(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        productDto.setDeletedAt(product.getDeletedAt());
        productDto.setIsActive(product.getIsActive());
        if (product.getUser() != null) {
            productDto.setUser(UserDto.fromEntity(product.getUser()));
        }
        return productDto;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setCreatedAt(this.createdAt);
        product.setUpdatedAt(this.updatedAt);
        product.setDeletedAt(this.deletedAt);
        product.setIsActive(this.isActive);
        if (this.user != null) {
            product.setUser(this.user.toEntity());
        }
        return product;
    }
}
