package ru.demo.marketplaceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.marketplaceservice.entity.Product;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
