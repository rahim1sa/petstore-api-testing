package org.example.ordertest;

import org.example.entities.Order;
import org.example.steps.OrderServiceSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderServiceTest {
    private Order newOrder() {
        Random random = new Random();
        Order actualOrder = new Order()
                .setId(random.nextInt(10000))
                .setPetId(random.nextInt(10000))
                .setQuantity(random.nextInt(10000))
                .setShipDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .setStatus("placed")
                .setComplete(true);
        return actualOrder;
    }
    public void assertionCheck(Order actual, Order expected){
        Assert.assertEquals(actual.getId(), expected.getId(),"Expected order doesn't have matched Id");
        Assert.assertEquals(actual.getPetId(), expected.getPetId(),"Expected order doesn't have matched PetId");
        Assert.assertEquals(actual.getQuantity(), expected.getQuantity(),"Expected order doesn't have matched Quantity");
//        Assert.assertEquals(actual.getShipDate(), expected.getShipDate(),"Expected order doesn't have matched Ship Date");
        Assert.assertEquals(actual.getStatus(), expected.getStatus(),"Expected order doesn't have matched Status");
        Assert.assertEquals(actual.getComplete(), expected.getComplete(),"Expected order didn't complete");
    }
    @Test
    public void createOrdersTest() {
        Order actualOrder = newOrder();
        Order expectedOrder = OrderServiceSteps.createOrder(actualOrder);
        assertionCheck(actualOrder,expectedOrder);
    }
    @Test
    public void getOrdersByIdTest() {
        Order expectedOrder = OrderServiceSteps.createOrder(newOrder());
        Order orderById = OrderServiceSteps.getOrdersById(expectedOrder.getId());
        assertionCheck(orderById,expectedOrder);
    }
    @Test
    public void deleteOrdersTest() {
        Order actualOrder = newOrder();
        Order expectedOrder = OrderServiceSteps.createOrder(actualOrder);
        OrderServiceSteps.deleteOrderById(expectedOrder.getId());
        assertionCheck(actualOrder,expectedOrder);
    }
}
