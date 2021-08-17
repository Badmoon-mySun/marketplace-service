package ru.demo.marketplaceservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.entity.Order;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public interface OrderService {
    OrderDto getOrderById(Long id);
    Page<OrderDto> getOrders(Pageable pageable);
    OrderDto createOrder(OrderCreateForm orderCreateForm);
}
