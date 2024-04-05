package com.example.demo.servicelmpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderDetailEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.repository.OrderDetailEntityDAO;
import com.example.demo.repository.OrderEntityDAO;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * OrderServiceImpl
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderEntityDAO orderEntityDAO;
    @Autowired
    OrderDetailEntityDAO orderDetailEntityDAO;
    @Override
    public OrderEntity create(JsonNode orderDate) {
        ObjectMapper mapper = new ObjectMapper();

        OrderEntity order = mapper.convertValue(orderDate, OrderEntity.class);
        orderEntityDAO.save(order);

        TypeReference<List<OrderDetailEntity>> type = new TypeReference<List<OrderDetailEntity>>(){};
        List<OrderDetailEntity> details = mapper.convertValue(orderDate.get("orderDetail"), type)
        .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailEntityDAO.saveAll(details);
        System.out.println(details.get(0));
        return order;
    }
    // @Autowired
    // OrderEntityDAO orderEntityDAO;
    @Override
    public OrderEntity findById(Integer id) {
        return orderEntityDAO.findById(id).get();
    }
    @Override
    public List<OrderEntity> findByUsername(String username) {
       return orderEntityDAO.findByAccountUserName(username);
    }


    // @Override
    // public OrderEntity save(OrderEntity order) {
    //     return orderEntityDAO.save(order);
    // }

    // @Override
    // public List<OrderEntity> findByUserUserId(Integer userId) {
    //     return orderEntityDAO.findByUserUserId(userId);
    // }

    // @Override
    // public OrderEntity findByOrderId(Integer orderId) {
    //     return orderEntityDAO.findByOrderId(orderId);
    // }

    // @Override
    // public void delete(OrderEntity order) {
    //     orderEntityDAO.delete(order);
    // }

}
