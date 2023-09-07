package qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import qa_scooter.ScooterHttpClient;

public class CreateCourier extends ScooterHttpClient {

    Courier courier;

    public CreateCourier(Courier courier) { this.courier = courier; }

    @Step("Создание курьера")
    public ValidatableResponse createCourier() {
        return doPostRequest(getCOURIER_URL(), courier);
    }
}
