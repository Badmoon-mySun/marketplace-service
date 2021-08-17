package ru.demo.marketplaceservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.marketplaceservice.entity.Order;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByBuyerEmail(String buyerEmail, Pageable pageable);
    Page<Order> findAllByCreatedAtIsBetween(Calendar start, Calendar end, Pageable pageable);
    Page<Order> findAllByCreatedAtAfter(Calendar date, Pageable pageable);
}
