package com.occasionalbaker.bakersite.backend.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "userorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String orderDate;

    @Column
    private String orderTime;

    @Column
    private String estimatedDeliveryDate;

    @NotNull
    @Column
    private String orderNumber;

    @NotNull
    @Column
    private String orderName;

    @NotNull
    @Column
    private String orderDetails;

    @NotNull
    @Column
    private String address;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Order(){

    }

    public Order(String username,String orderNumber,String orderName, String orderDetails, String address,String orderDate,String orderTime) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.username = username;
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.orderDetails = orderDetails;
        this.address = address;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", estimatedDeliveryDate='" + estimatedDeliveryDate + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderDetails='" + orderDetails + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
