package ru.demo.marketplaceservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;

import java.util.Date;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public interface OrderService {
    OrderDto getOrderById(Long id);
    Page<OrderDto> getOrders(Pageable pageable);
    OrderDto createOrder(OrderCreateForm orderCreateForm);
    OrderDto updateOrder(OrderDto orderDto);
    void deleteOrderById(Long id);
    Page<OrderDto> getOrdersByBuyerEmail(String buyerEmail, Pageable pageable);
    Page<OrderDto> filterOrdersByDate(Date from, Date to, Pageable pageable);
    Page<OrderDto> getOrdersByVendorCode(Long vendorCode, Pageable pageable);
}
