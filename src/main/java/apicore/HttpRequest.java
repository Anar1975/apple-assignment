package apicore;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.PropertiesHelper;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpRequest {

    PropertiesHelper propertiesHelper = new PropertiesHelper();

    @Step("http GET request.")
    public Response httpGetWithParams(Map params) {
        Response getRequestResponse;
        getRequestResponse = given()
                .log().uri()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .formParams(params)
                .when().get(propertiesHelper.getBaseUrl());
        System.out.println("\nResponse Headers:\n" + getRequestResponse.headers());
        System.out.println("\nResponse Body:");
        getRequestResponse.prettyPrint();
        return getRequestResponse;
    }
}
