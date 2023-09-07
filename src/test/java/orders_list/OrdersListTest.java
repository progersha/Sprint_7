package orders_list;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.ScooterHttpClient;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest extends ScooterHttpClient {
    @Test
    @DisplayName("Список заказов")
    public void orderListTest() {
        doGetRequest(getORDERS_URL())
                .assertThat().statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}
