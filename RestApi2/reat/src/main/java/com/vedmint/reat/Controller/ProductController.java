package com.vedmint.reat.Controller;

import java.util.List;
import com.vedmint.reat.Dto.ProductDto;
import com.vedmint.reat.Dto.CreateProductDto;
import com.vedmint.reat.Response.ApiResponse;
import com.vedmint.reat.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        return ResponseEntity.ok(
                ApiResponse.success("Products retrieved successfully", productService.getAllProducts()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> createProduct(
            @Valid @RequestBody CreateProductDto createProductDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Product created successfully",
                        productService.createProduct(createProductDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Product retrieved successfully", productService.getProductById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable Long id,
            @Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(
                ApiResponse.success("Product updated successfully", productService.updateProduct(id, productDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<ProductDto>> activateProduct(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Product activated successfully", productService.activateProduct(id)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<ProductDto>> deactivateProduct(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Product deactivated successfully", productService.deactivateProduct(id)));
    }

    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<ApiResponse<ProductDto>> softDeleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Product soft deleted successfully", productService.softDeleteProduct(id)));
    }
}
