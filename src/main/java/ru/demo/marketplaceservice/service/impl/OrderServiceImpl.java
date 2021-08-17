package ru.demo.marketplaceservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.entity.Order;
import ru.demo.marketplaceservice.exception.NotFoundException;
import ru.demo.marketplaceservice.repository.OrderRepository;
import ru.demo.marketplaceservice.service.OrderService;

import java.util.Calendar;
import java.util.Optional;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order = optionalOrder.orElseThrow(NotFoundException::new);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Page<OrderDto> getOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(order -> modelMapper.map(order, OrderDto.class));
    }

    @Override
    public OrderDto createOrder(OrderCreateForm orderCreateForm) {
        Order order = modelMapper.map(orderCreateForm, Order.class);

        Calendar date = Calendar.getInstance();
        order.setCreatedAt(date);
        order.setOrderNumber(date.hashCode());

        order = orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }
}
