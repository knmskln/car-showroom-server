package com.bsuir.server.services.impl;
import com.bsuir.server.entities.Order;
import com.bsuir.server.repositories.OrderRepository;
import com.bsuir.server.services.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
//    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;
//    private final OrderReqMapper orderReqMapper;
//
//    @Override
//    public List<GetOrderResp> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        return orderMapper.toDto(orders);
//    }
//
//    @Override
//    public GetOrderResp createOrder(CreateOrderReq createOrderReq) {
//        Order order = orderReqMapper.toEntity(createOrderReq);
//        Order savedOrder = orderRepository.save(order) ;
//        return orderMapper.toDto(savedOrder);
//    }
//
//    @Override
//    public SimpleResp deleteOrder(Long id) {
//        orderRepository.deleteById(id);
//        return new SimpleResp();
//    }
//
//    @Override
//    public GetOrderResp getOrder(Long id) throws Exception {
//        Optional<Order> order = orderRepository.findById(id);
////        if (order.isEmpty()){
////            throw new Exception();
////        }
//        return orderMapper.toDto(order.get());
//    }
}
