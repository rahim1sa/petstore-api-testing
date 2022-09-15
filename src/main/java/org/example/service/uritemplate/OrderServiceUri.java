package org.example.service.uritemplate;

public class OrderServiceUri {
    public static final UriTemplate ORDERS = new UriTemplate("store/order");
    public static final UriTemplate ORDERS_BY_ID = new UriTemplate("store/order/%s");
}
