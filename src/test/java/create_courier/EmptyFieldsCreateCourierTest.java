package create_courier;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.courier.Courier;
import qa_scooter.courier.CreateCourier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsCreateCourierTest {

    private String login;
    private String password;
    private String firstName;

    public EmptyFieldsCreateCourierTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters()
    public static Object[][] getParameters() {
        return new Object[][]{
                {"", "123456", "Иван"},
                {"login", "", "Иван"},
                {"login", "123456", ""}
        };
    }

    @Test
    @DisplayName("Если какого-то поля нет, запрос возвращает ошибку")
    public void createCourierWithEmptyFieldsTest() {
        Courier courier = new Courier(login, password, firstName);
        CreateCourier createdCourier = new CreateCourier(courier);
        createdCourier.createCourier()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
