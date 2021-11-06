package com.example.Tests;

import com.example.controller.IPayementManager;
import com.example.controller.IStockManager;
import com.example.controller.OrderManager;
import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderManagerTest {

    @Test
    public void createOrder() {
        //Liste d'objets de type "OrderLine"
        ArrayList<Orderline> orderlines = new ArrayList<Orderline>();
        //Creation de la base de données
        DatabaseConnection bdd = new DatabaseConnection("sa", "", "org.h2.Driver", "jdbc:h2:mem:test");
        Connection connection = bdd.connect();
        bdd.createDb(connection);
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        orderDao.setConn(connection);
        //Creation d'un objet de type "Customer"
        Customer custumer = new Customer("1234", "AMOKRANE", "Ilhem", "Bejaia", "123456789");
        //Creation de deux objets : produit1 et produit2 de type "Product"
        Product produit1 = new Product();
        produit1.setProductID("1");
        produit1.setProductQte(100);
        Product produit2 = new Product();
        produit2.setProductID("2");
        produit2.setProductQte(200);
        //Creation de deux objets : orderLine1 et orderLine2 de type "OrderLine"
        Orderline orderLine1 = new Orderline(produit1, 10);
        Orderline orderLine2 = new Orderline(produit2, 20);
        orderlines.add(orderLine1);
        orderlines.add(orderLine2);
        //Creation d'un objet de type Order
        Order order = new Order(11, orderlines, custumer);
        //Création des mockitos
        IStockManager stockManager = Mockito.mock(IStockManager.class);
        IPayementManager paymentManger = Mockito.mock(IPayementManager.class);
        OrderManager orderManager = new OrderManager(orderDao, stockManager, paymentManger);
        //Tests
        when(stockManager.getProductQte(produit1)).thenReturn(0);
        when(stockManager.getProductQte(produit2)).thenReturn(0);
        assertFalse(orderManager.createOrder(order));
        when(stockManager.getProductQte(produit1)).thenReturn(100);
        when(stockManager.getProductQte(produit2)).thenReturn(200);
        assertTrue(orderManager.createOrder(order));
        when(paymentManger.isPaid(order.getOrderNum())).thenReturn(true);
        assertFalse(orderManager.cancelOrder(order.getOrderNum()));
        when(paymentManger.isPaid(order.getOrderNum())).thenReturn(false);
        assertTrue(orderManager.cancelOrder(order.getOrderNum()));
        verify(orderDao).deleteOrder(11);

    }
}