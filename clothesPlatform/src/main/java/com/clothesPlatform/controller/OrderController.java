package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.Order;
import com.clothesPlatform.repository.OrderRepository;
import com.clothesPlatform.service.ClothingService;
import com.clothesPlatform.service.OrderService;
import com.clothesPlatform.service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClothingService clothingService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation("批量生成订单")
    @PostMapping("/saveOrder")
    public String saveOrder(String uId,String destination){
        List<Integer> list1 = orderRepository.findCidByUid(uId);
        for (int i = 0; i < list1.size(); i++) {
            int cId = list1.get(i);
            orderService.saveOrder(uId,cId,destination);
        }
        shoppingCartService.deleteCartsByUserId(uId);

        return "success";

    }

    @ApiOperation("查询所有订单")
    @GetMapping("/findAllOrder/{page}/{size}")
    public Page<Order> findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return orderService.findAllOrder(page,size);
    }

    @ApiOperation("通过订单编号查询订单，传入参数是orderId,返回对象数组")
    @GetMapping("/findOrder/{orderId}")
    public Order findOrder(@PathVariable("orderId") int orderId) {
        return orderService.findOrder(orderId);
    }

    @ApiOperation("查询与物主有关的订单，传入参数是owner")
    @GetMapping("/findOrderByOwner/{owner}")
    public List<Order> findOrderByOwner(@PathVariable("owner") String owner) {
        List<Order> lists = orderService.findOrderByOwner(owner);
        List<Order> list2 = new ArrayList<>();
        for (int i = 0; i < lists.size();i++){
            Order order = lists.get(i);
            Clothing clothing = clothingService.findClothing(order.getCid());
            order.setClothing(clothing);
            list2.add(order);
        }
        return list2;
    }

    @ApiOperation("查询与租客有关的订单，传入参数是renter")
    @GetMapping("/findOrderByRenter/{renter}")
    public List<Order> findOrderByRenter(@PathVariable("renter") String renter) {
        List<Order> lists = orderService.findOrderByRenter(renter);
        List<Order> list2 = new ArrayList<>();
        for (int i = 0; i < lists.size();i++){
            Order order = lists.get(i);
            Clothing clothing = clothingService.findClothing(order.getCid());
            order.setClothing(clothing);
            list2.add(order);
        }
        return list2;
    }

    @ApiOperation("通过时间段查询订单，传入参数是start,end")
    @GetMapping("/searchOrders/{start}/{end}")
    public List<Order> searchOrders(@PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) throws ParseException {
        System.out.println(start);
        System.out.println(end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(start);
        String endTime = sdf.format(end);
        Date start1 = sdf.parse(startTime);
        Date end1 = sdf.parse(endTime);
        return orderService.findOrderByDate(start1,end1);
    }

    @ApiOperation("通过订单编号删除订单，传入参数是orderId")
    @DeleteMapping("/deleteOrder")
    public void deleteOrder(int orderId) {
        orderService.deleteOrder(orderId);
    }
}