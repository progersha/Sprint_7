package login_courier;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.courier.Courier;
import qa_scooter.login.LoginCourier;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.equalTo;

public class NonExistentUserLoginTest {

    private static final String login = "login123";
    private static final String password = "123456";
    Courier courier = new Courier(login, password);

    @Test
    @DisplayName("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void nonExistentUserLoginTest() {
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
