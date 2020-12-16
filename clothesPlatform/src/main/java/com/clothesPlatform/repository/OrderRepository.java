package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "select origin from `order` where order_id =?1",nativeQuery = true)
    public int findOriginByOrderId(Integer orderId);
    @Query(value = "select destination from `order` where order_id =?1",nativeQuery = true)
    public int findDestinationByOrderId(Integer orderId);
    @Query(value = "select * from `order` where `owner` =?1",nativeQuery = true)
    List<Order> findOrderByOwner(String owner);
    @Query(value = "select * from `order` where `renter` =?1",nativeQuery = true)
    List<Order> findOrderByRenter(String renter);
    @Query(value = "select cid from `order` where order_id =?1",nativeQuery = true)
     int findClothingByOrderId(Integer orderId);
    @Query(value = "select o.* from `order` o where o.date between DATE_FORMAT(?1,'%Y-%m-%d') and DATE_FORMAT(?2,'%Y-%m-%d')",nativeQuery = true)
    List<Order> findOrderByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);
    @Query(value = "select c.belong from clothing c where c.clothes_id=?1",nativeQuery = true)
    String findbelongByCid(int cid);
    @Query(value = "select u.address from user u left join clothing c on u.user_id=c.belong where c.clothes_id=?1",nativeQuery = true)
    String findOriginByCid(int cid);
    @Query(value = "select s.c_id from shopping_cart s where u_id=?1",nativeQuery = true)
    List<Integer> findCidByUid(String uId);
}
