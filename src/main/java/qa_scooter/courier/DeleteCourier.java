package qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import qa_scooter.ScooterHttpClient;

public class DeleteCourier extends ScooterHttpClient {
    Courier courier;

    public DeleteCourier(Courier courier) {
        this.courier = courier;
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(String id) {
        return doDeleteRequest(getCOURIER_URL() + id);
    }
}
