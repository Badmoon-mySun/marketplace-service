package ru.demo.marketplaceservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.entity.Order;
import ru.demo.marketplaceservice.service.OrderService;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto orderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping ("/all")
    public Page<OrderDto> allOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}
