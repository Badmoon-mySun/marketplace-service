package ru.demo.marketplaceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.marketplaceservice.entity.Order;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
