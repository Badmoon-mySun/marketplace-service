package ru.demo.marketplaceservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.demo.marketplaceservice.dto.OrderDto;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public interface OrderService {
    OrderDto getOrderById(Long id);
    Page<OrderDto> getOrders(Pageable pageable);
}
