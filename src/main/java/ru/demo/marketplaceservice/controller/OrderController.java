package ru.demo.marketplaceservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.service.OrderService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get order by id", tags = "order")
    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "Get all orders and filter by buyer email (optionally)", tags = "order")
    @GetMapping
    public Page<OrderDto> allOrders(@RequestParam(required = false) String buyerEmail, Pageable pageable) {
        if (StringUtils.hasText(buyerEmail))
            return orderService.getOrdersByBuyerEmail(buyerEmail, pageable);

        return orderService.getOrders(pageable);
    }

    @Operation(summary = "Create order", tags = "order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@RequestBody @Valid OrderCreateForm orderCreateForm) {
        return orderService.createOrder(orderCreateForm);
    }

    @Operation(summary = "Update order", tags = "order")
    @PutMapping
    public OrderDto update(@RequestBody @Valid OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @Operation(summary = "Delete order by id", tags = "order")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

    @Operation(summary = "Filter orders by created date with parameters from and to (optionally)", tags = "order")
    @GetMapping("/date")
    public Page<OrderDto> filterByDate(
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
            Pageable pageable) {

        return orderService.filterOrdersByDate(from, to, pageable);
    }

    @Operation(summary = "Filter orders by product vendor code", tags = "order")
    @GetMapping("/vendor")
    public Page<OrderDto> filterByVendorCode(@RequestParam Long vendorCode, Pageable pageable) {
        return orderService.getOrdersByVendorCode(vendorCode, pageable);
    }
}
