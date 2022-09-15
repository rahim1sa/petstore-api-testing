package org.example.service;
import io.restassured.response.Response;
import org.example.service.uritemplate.UriTemplate;

public class OrderService extends CommonService {
    private static OrderService instance;
    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }
    public Response getRequest(UriTemplate uri) {
        return super.getRequest(uri.getUri());
    }

    public Response getRequest(UriTemplate uri, int id) {return super.getRequest(uri.getUri(id));}

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public void deleteRequest(UriTemplate uri, int id) {super.deleteRequest(uri.getUri(id));}
}