package com.clothesPlatform.service;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface OrderService {

    String saveOrder(String uId,int cId,String destination);

    Page<Order> findAllOrder(int page,int size);

    Order findOrder(int order);

    List<Order> findOrderByOwner(String order);

    List<Order> findOrderByRenter(String order);

    List<Order> findOrderByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);

    void deleteOrder(int order);

}
