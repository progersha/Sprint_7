package create_courier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import qa_scooter.courier.Courier;
import qa_scooter.courier.CreateCourier;
import org.junit.Test;
import qa_scooter.courier.DeleteCourier;
import qa_scooter.login.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    private static final String LOGIN = "Ivanushka";
    private static final String PASSWORD = "123456";
    private static final String FIRST_NAME = "Ivan";

    Courier courier = new Courier(LOGIN, PASSWORD, FIRST_NAME);
    CreateCourier createdCourier = new CreateCourier(courier);
    Courier courierForDelete= new Courier(LOGIN, PASSWORD);

    @Test
    @DisplayName("Курьера можно создать")
    public void creatingCourierTest() {
        createdCourier.createCourier()
                .assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void creatingDuplicateCourierTest() {
        createdCourier.createCourier();
        createdCourier.createCourier()
                .assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteCreatedCourier() {
        DeleteCourier deleteCourier = new DeleteCourier(courierForDelete);
        LoginCourier loginCourier = new LoginCourier(courierForDelete);
        deleteCourier.deleteCourier(loginCourier.getCourierId());
    }
}
