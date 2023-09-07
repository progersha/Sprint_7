package create_order;

import io.qameta.allure.junit4.DisplayName;
import qa_scooter.order.CreateOrder;
import qa_scooter.order.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String deliveryDate;
    private String comment;
    private int rentTime;
    private String[] color;

    public CreateOrderTest(String firstName,
                           String lastName,
                           String address,
                           String metroStation,
                           String phone,
                           int rentTime,
                           String deliveryDate,
                           String comment,
                           String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.rentTime = rentTime;
        this.color = color;
    }

    @Parameterized.Parameters()
    public static Object[][] getParameters() {
        return new Object[][] {
                {"Иванов", "Иван", "Ивановская 1", "1", "+79212223341", 1, "2023-09-01T11:00:00.000Z", "Комментарий", new String[]{"BLACK"}},
                {"Петров", "Петр", "Петровская 2", "2", "+79212223342", 2, "2023-09-02T12:00:00.000Z", "Комментарий", new String[]{"GREY"}},
                {"Семенов", "Семен", "Семеновская 3", "3", "+79212223343", 3, "2023-03-30T13:00:00.000Z", "Комментарий", new String[]{"BLACK", "GREY"}},
                {"Романов", "Роман", "Романовская 4", "4", "+79212223344", 4, "2023-04-30T14:00:00.000Z", "Комментарий", new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrderTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        CreateOrder createOrder = new CreateOrder(order);
        createOrder.createOrder()
                .assertThat().statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}