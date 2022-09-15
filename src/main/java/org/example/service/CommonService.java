package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class CommonService {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";

    private final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given();
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    protected Response getRequest(String uri) {

        Response response = requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
                .when().get(prepareUri.apply(uri));
        response.statusCode();
        Log.info("Sending Get request to the URL " + uri);
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    protected Response postRequest(String uri, Object body) {
        Response response =requestSpecification.body(body).expect().statusCode(HttpStatus.SC_OK).log().ifError()
                .when().post(prepareUri.apply(uri));

        Log.info("Sending Post request to the URL " + uri);
        Log.info("Current order infos: " + body.toString());
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());

        return  response;
    }

    protected void deleteRequest(String uri) {
        Response response = requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
                .when().delete(prepareUri.apply(uri));
        Log.info("Sending Delete request to the URL " + uri);
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
    }
}
