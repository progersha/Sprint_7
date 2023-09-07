package login_courier;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.courier.Courier;
import qa_scooter.login.LoginCourier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsLoginTest {

    private String login;
    private String password;

    public EmptyFieldsLoginTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters()
    public static Object[][] getParameters() {
        return new Object[][]{
                {"", "123456"},
                {"qa_scooter/login", ""},
                {"", ""}
        };
    }

    @Test
    @DisplayName("Для авторизации нужно передать все обязательные поля")
    public void emptyFieldsLoginCourierTest() {
        Courier courier = new Courier(login, password);
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
