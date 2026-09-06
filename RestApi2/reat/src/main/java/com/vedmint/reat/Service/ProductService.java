package com.vedmint.reat.Service;

import org.springframework.stereotype.Service;
import com.vedmint.reat.Repository.ProductRepository;
import com.vedmint.reat.Repository.UserRepository;
import com.vedmint.reat.Dto.ProductDto;
import com.vedmint.reat.Dto.CreateProductDto;
import com.vedmint.reat.Dto.UpdateProductDto;
import com.vedmint.reat.Exception.ResourceNotFoundException;
import com.vedmint.reat.Model.Product;
import com.vedmint.reat.Model.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(CreateProductDto createProductDto) {
        User user = userRepository.findById(createProductDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + createProductDto.getUserId()));
        return ProductDto.fromEntity(productRepository.save(createProductDto.toEntity(user)));
    }

    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        User user = userRepository.findById(updateProductDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + updateProductDto.getUserId()));

        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());
        product.setUser(user);

        return ProductDto.fromEntity(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(ProductDto::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public ProductDto activateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.activate();
        return ProductDto.fromEntity(productRepository.save(product));
    }

    public ProductDto deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.deactivate();
        return ProductDto.fromEntity(productRepository.save(product));
    }

    public ProductDto softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.softDelete();
        return ProductDto.fromEntity(productRepository.save(product));
    }
}
