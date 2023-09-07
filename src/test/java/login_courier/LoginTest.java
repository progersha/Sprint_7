package login_courier;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.courier.Courier;
import qa_scooter.courier.CreateCourier;
import qa_scooter.login.LoginCourier;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginTest {

    private static final String LOGIN = "qa_scooter/login";
    private static final String PASSWORD = "123456";
    private static final String FIRST_NAME = "Ivan";

    Courier createData = new Courier(LOGIN, PASSWORD, FIRST_NAME);
    Courier loginData = new Courier(LOGIN, PASSWORD);
    CreateCourier createdCourier = new CreateCourier(createData);
    LoginCourier loginCourier = new LoginCourier(loginData);

    @Before
    public void setUp(){
        createdCourier.createCourier();
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    public void loginCourierTest() {
        loginCourier.loginCourier()
                .assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());
    }
}
