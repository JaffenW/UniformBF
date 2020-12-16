package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.Order;
import com.clothesPlatform.repository.OrderRepository;
import com.clothesPlatform.repository.UserRepository;
import com.clothesPlatform.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public String saveOrder(String uId,int cId,String destination) {
        Order order = new Order();
        Date date = new Date();
        order.setCid(cId);
        order.setRenter(uId);
        order.setDestination(destination);
        order.setOrigin(orderRepository.findOriginByCid(order.getCid()));
        order.setDate(date);
        order.setOwner(orderRepository.findbelongByCid(order.getCid()));
        order.setDeposit(50);
        order.setTime("7天");
        Order result = orderRepository.save(order);
        if (result != null) {
            return "success";
        }else {
            return "error";
        }
    }

    @Override
    public Page<Order> findAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findOrder(int orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> findOrderByOwner(String order) {
        return orderRepository.findOrderByOwner(order);
    }

    @Override
    public List<Order> findOrderByRenter(String order) {
        return orderRepository.findOrderByRenter(order);
    }

    @Override
    public List<Order> findOrderByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return orderRepository.findOrderByDate(start,end);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }
}