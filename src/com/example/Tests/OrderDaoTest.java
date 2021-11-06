package com.example.Tests;
import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderDaoTest {
@Test
public void TestDao(){
    //Liste d'objets de type "OrderLine"
    ArrayList<Orderline>orderlines=new ArrayList<Orderline>();
    //Creation de la base de données
    DatabaseConnection bdd=new DatabaseConnection("sa","","org.h2.Driver", "jdbc:h2:mem:test");
    Connection connection = bdd.connect();
    bdd.createDb(connection);
    OrderDao orderDao=new OrderDao();
    orderDao.setConn(connection);
    //Creation d'un objet de type "Customer"
    Customer custumer=new Customer("1234","AMOKRANE","Ilhem","Bejaia","123456789");
    //Creation de deux objets : produit1 et produit2 de type "Product"
    Product produit1=new Product();
    produit1.setProductID("1");
    Product produit2=new Product();
    produit2.setProductID("2");
    //Creation de deux objets : orderLine1 et orderLine2 de type "OrderLine"
    Orderline orderLine1=new Orderline(produit1,10);
    Orderline orderLine2=new Orderline(produit2,20);
    orderlines.add(orderLine1);
    orderlines.add(orderLine2);
    //Creation d'un objet de type Order
    Order order=new Order(11,orderlines,custumer);
    //Tests
    orderDao.insertOrder(order);
    assertArrayEquals(orderlines.toArray(),orderDao.getOrderDetails(11).toArray());
    orderDao.deleteOrder(11);
    assertNull(orderDao.getOrderDetails(11));
}
}