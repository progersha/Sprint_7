package qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import qa_scooter.ScooterHttpClient;

public class CreateOrder extends ScooterHttpClient {

    Order order;

    public CreateOrder(Order order) {
        this.order = order;
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder() {
        return doPostRequest(getORDERS_URL(), order);
    }
}
