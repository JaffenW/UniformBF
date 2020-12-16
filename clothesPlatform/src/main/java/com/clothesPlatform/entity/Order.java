package com.clothesPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "[order]")
public class Order {//订单类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[order_id]")
    //订单编号
    private int orderId;
    //服装id
    @Column(name = "[cid]")
    private int cid;
    //衣服的所有者
    @Column(name = "[owner]")
    private String owner;
    //租赁人
    @Column(name = "[renter]")
    private String renter;
    //订单发起的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "[date]")
    private Date date;
    //租借时间
    @Column(name = "[time]")
    private String time;
    //发货地
    @Column(name = "[origin]")
    private String origin;
    //收货地
    @Column(name = "[destination]")
    private String destination;
    //押金
    @Column(name = "[deposit]")
    private int deposit;
    @Transient
    private Clothing clothing;

}