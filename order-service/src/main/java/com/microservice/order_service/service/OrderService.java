package com.microservice.order_service.service;

import com.microservice.order_service.dto.OrderResponseDTO;
import com.microservice.order_service.dto.ProductDTO;
import com.microservice.order_service.entity.Order;
import com.microservice.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderService(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder( Order order) {
        // fetch product details from product service
        return webClientBuilder.build().get().uri("http://localhost:8081/products/" + order.getProductId()).retrieve().bodyToMono(ProductDTO.class).map(
                productDTO -> {
                    OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                    orderResponseDTO.setQuantity(order.getQuantity());
                    orderResponseDTO.setProductId(order.getProductId());
                    //set product details
                    orderResponseDTO.setProductName(productDTO.getName());
                    orderResponseDTO.setProductPrice(productDTO.getPrice());
                    orderResponseDTO.setTotalPrice(order.getQuantity() * productDTO.getPrice());
                    orderRepository.save(order);
                    orderResponseDTO.setOrderId(order.getOrderId());
                    return ResponseEntity.ok().body(orderResponseDTO);
                }
        );

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
