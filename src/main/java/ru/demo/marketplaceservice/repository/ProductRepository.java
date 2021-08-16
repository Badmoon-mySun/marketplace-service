package ru.demo.marketplaceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.marketplaceservice.entity.Product;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
