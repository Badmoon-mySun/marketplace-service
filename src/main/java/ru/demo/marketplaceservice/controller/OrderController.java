package ru.demo.marketplaceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.service.OrderService;

import javax.validation.Valid;

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

    @GetMapping
    public Page<OrderDto> allOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }

    @PostMapping
    public OrderDto create(@Valid OrderCreateForm orderCreateForm) {
        return orderService.createOrder(orderCreateForm);
    }
}
