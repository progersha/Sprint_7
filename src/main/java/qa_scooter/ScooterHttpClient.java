package qa_scooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ScooterHttpClient {

    private static final String QA_SCOOTER_URL = "http://qa-scooter.praktikum-services.ru";
    private static final String COURIER_URL = "/api/v1/courier/";
    private static final String LOGIN_URL = "/api/v1/courier/login/";
    private static final String ORDERS_URL = "/api/v1/orders/";

    public String getCOURIER_URL() {
        return COURIER_URL;
    }

    public String getLOGIN_COURIER() {
        return LOGIN_URL;
    }

    public String getORDERS_URL() {
        return ORDERS_URL;
    }

    public ValidatableResponse doPostRequest(String url, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(url).then();
    }

    public ValidatableResponse doGetRequest(String url) {
        return given(baseRequest()).get(url).then();
    }

    public ValidatableResponse doDeleteRequest(String url) {
        return given(baseRequest()).delete(url).then();
    }

    public RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(QA_SCOOTER_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setRelaxedHTTPSValidation()
                .build();
    }
}
