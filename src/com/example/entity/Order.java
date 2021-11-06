package com.example.entity;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderNum;
    private String orderDate;
    private List<Orderline> orderlines;
    private Customer customer;

    public Order() {
    }

    public Order(int orderNum, List<Orderline> orderlines, Customer customer) {
        this.orderNum = orderNum;
        this.orderlines = orderlines;
        this.customer = customer;
    }



    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
