package ru.demo.marketplaceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.marketplaceservice.entity.Order;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
