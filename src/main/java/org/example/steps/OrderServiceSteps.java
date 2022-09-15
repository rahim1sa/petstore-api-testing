package org.example.steps;

import org.example.entities.Order;
import org.example.service.OrderService;

import java.util.List;

import static org.example.service.uritemplate.OrderServiceUri.ORDERS;
import static org.example.service.uritemplate.OrderServiceUri.ORDERS_BY_ID;

public class OrderServiceSteps {
    private static final OrderService ORDER_SERVICE = OrderService.getInstance();
    public static Order getOrdersById(int id) {return ORDER_SERVICE.getRequest(ORDERS_BY_ID, id).as(Order.class);}
    public static Order createOrder(Order order) {
        return ORDER_SERVICE.postRequest(ORDERS, order).as(Order.class);
    }
    public static void deleteOrderById(int id) {ORDER_SERVICE.deleteRequest(ORDERS_BY_ID, id);}
}
