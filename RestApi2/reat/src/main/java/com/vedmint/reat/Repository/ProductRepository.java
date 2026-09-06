package com.vedmint.reat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vedmint.reat.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
