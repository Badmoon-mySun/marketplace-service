package ru.demo.marketplaceservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.dto.ProductDto;
import ru.demo.marketplaceservice.entity.Order;
import ru.demo.marketplaceservice.entity.Product;
import ru.demo.marketplaceservice.exception.NotFoundException;
import ru.demo.marketplaceservice.repository.OrderRepository;
import ru.demo.marketplaceservice.repository.ProductRepository;
import ru.demo.marketplaceservice.service.OrderService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
        Calendar date = Calendar.getInstance();

        Order order = Order.builder()
                .createdAt(date)
                .orderNumber(date.hashCode())
                .products(getCleanProducts(orderCreateForm.getProducts()))
                .build();

        order = orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new NotFoundException("Order for update don't found"));

        List<Product> products = getCleanProducts(orderDto.getProducts());

        Order updatedOrder = modelMapper.map(orderDto, Order.class);
        updatedOrder.setProducts(order.getProducts());
        order = orderRepository.save(updatedOrder);

        List<Product> difference = new ArrayList<>(order.getProducts());

        Order finalOrder = order;
        difference.removeAll(products);
        difference.forEach(product -> product.getOrders().remove(finalOrder));

        difference.addAll(products);
        difference.removeAll(order.getProducts());
        difference.forEach(product -> product.getOrders().add(finalOrder));

        order.setProducts(products);

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));

        orderRepository.delete(order);
    }

    @Override
    public Page<OrderDto> getOrdersByBuyerEmail(String buyerEmail, Pageable pageable) {
        Page<Order> orders = orderRepository.findAllByBuyerEmail(buyerEmail, pageable);
        return orders.map(order -> modelMapper.map(order, OrderDto.class));
    }

    @Override
    public Page<OrderDto> filterOrdersByDate(Date from, Date to, Pageable pageable) {
        if (from == null)
            throw new IllegalArgumentException("From date cannot be null");

        Calendar dateStart = Calendar.getInstance();
        dateStart.setTimeInMillis(from.getTime());

        Page<Order> orders;
        if (to != null) {
            Calendar dateEnd = Calendar.getInstance();
            dateEnd.setTimeInMillis(to.getTime());

            orders = orderRepository.findAllByCreatedAtIsBetween(dateStart, dateEnd, pageable);
        } else {
            orders = orderRepository.findAllByCreatedAtAfter(dateStart, pageable);
        }

        return orders.map(order -> modelMapper.map(order, OrderDto.class));
    }

    private List<Product> getCleanProducts(List<ProductDto> productDtos) {
        Set<Long> productIds = productDtos.stream()
                .map(ProductDto::getId).collect(Collectors.toSet());

        return productRepository.findAllById(productIds);
    }
}
