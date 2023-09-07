package qa_scooter.login;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import qa_scooter.ScooterHttpClient;
import qa_scooter.courier.Courier;
import qa_scooter.courier.CourierId;

public class LoginCourier extends ScooterHttpClient {

    Courier courier;

    public LoginCourier(Courier courier) {
        this.courier = courier;
    }

    @Step("Логин")
    public ValidatableResponse loginCourier() {
        return doPostRequest(getLOGIN_COURIER(), courier);
    }

    @Step("Получение ID курьера")
    public String getCourierId() {
        return loginCourier().extract().body().as(CourierId.class).getId();
    }
}
