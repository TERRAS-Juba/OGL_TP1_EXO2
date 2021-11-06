package com.example.controller;

import com.example.entity.Order;
import com.example.entity.Payment;

public interface IPayementManager {

    public boolean addPayement(Payment payment);

    public boolean isPaid(int orderNum);
}
