package ru.demo.marketplaceservice;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.demo.marketplaceservice.dto.OrderCreateForm;
import ru.demo.marketplaceservice.dto.OrderDto;
import ru.demo.marketplaceservice.dto.ProductDto;
import ru.demo.marketplaceservice.entity.Order;
import ru.demo.marketplaceservice.entity.Product;
import ru.demo.marketplaceservice.repository.OrderRepository;
import ru.demo.marketplaceservice.service.OrderService;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@SpringBootTest
public class OrderServiceIntegrationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        Product firstProduct = Product.builder()
                .id(1L)
                .name("product1")
                .price(1000.0)
                .currency("P")
                .deleted(false)
                .vendorCode(123123213L)
                .build();

        Product secondProduct = Product.builder()
                .id(2L)
                .name("product2")
                .price(2000.0)
                .currency("P")
                .deleted(true)
                .vendorCode(123123213L)
                .build();

        Order firstOrder = Order.builder()
                .id(1L)
                .buyerEmail("test@test.ru")
                .orderNumber(123213123)
                .createdAt(Calendar.getInstance())
                .products(Collections.singletonList(firstProduct))
                .build();

        Order secondOrder = Order.builder()
                .id(2L)
                .buyerEmail("test@test.ru")
                .orderNumber(423432141)
                .createdAt(Calendar.getInstance())
                .products(List.of(new Product[]{firstProduct, secondProduct}))
                .build();

        firstProduct.setOrders(List.of(new Order[]{firstOrder, secondOrder}));
        secondProduct.setOrders(Collections.singletonList(secondOrder));

        Mockito.when(orderRepository.findById(firstOrder.getId()))
                .thenReturn(Optional.of(firstOrder));

        Mockito.when(orderRepository.findById(secondOrder.getId()))
                .thenReturn(Optional.of(secondOrder));
    }

    @Test
    public void whenValidIdOrderShouldBeFound() {
        Long id = 1L;
        OrderDto orderDto = orderService.getOrderById(id);

        Assertions.assertEquals(orderDto.getId(), id);
    }

    @Test
    public void checkUpdateOrder() {
        List<Product> products = orderRepository.findById(2L).get().getProducts();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .buyerEmail("123@test.ru")
                .orderNumber(123213123)
                .createdAt(Calendar.getInstance())
                .products(productDtos)
                .build();

        OrderDto updatedOrder = orderService.updateOrder(orderDto);

        Assertions.assertEquals(orderDto, updatedOrder);
    }

    @Test
    public void checkCreateOrderWithValidParams() {
        List<Product> products = orderRepository.findById(2L).get().getProducts();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        OrderCreateForm orderCreateForm = OrderCreateForm.builder()
                .buyerEmail("test@test.com")
                .products(productDtos)
                .build();

        OrderDto savedOrder = orderService.createOrder(orderCreateForm);

        Assertions.assertEquals(orderCreateForm.getProducts(), savedOrder.getProducts());
        Assertions.assertEquals(orderCreateForm.getBuyerEmail(), savedOrder.getBuyerEmail());
    }
}
