package scooter.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scooter.pages.MainPage;
import scooter.pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;
    private final String color;
    private final String buttonPosition;

    public OrderTest(String browserType, String name, String lastName, String address, String metro,
                     String phone, String date, String comment, String color, String buttonPosition) {
        super(browserType);
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.color = color;
        this.buttonPosition = buttonPosition;
    }

    @Parameterized.Parameters(name = "Браузер: {0}, Заказ: {1} {2}, Кнопка: {9}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Chrome - верхняя кнопка
                {"chrome", "Иван", "Иванов", "ул. Ленина, д. 1", "Сокольники", "89991234567",
                        "01.12.2024", "Позвонить за час до доставки", "black", "top"},
                // Chrome - нижняя кнопка
                {"chrome", "Мария", "Петрова", "пр. Мира, д. 15", "Комсомольская", "89997654321",
                        "05.12.2024", "Оставить у двери", "grey", "bottom"},
                // Firefox - верхняя кнопка
                {"firefox", "Иван", "Иванов", "ул. Ленина, д. 1", "Сокольники", "89991234567",
                        "01.12.2024", "Позвонить за час до доставки", "black", "top"},
                // Firefox - нижняя кнопка
                {"firefox", "Мария", "Петрова", "пр. Мира, д. 15", "Комсомольская", "89997654321",
                        "05.12.2024", "Оставить у двери", "grey", "bottom"}
        });
    }

    @Test
    public void testSuccessfulOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.acceptCookies();

        // Выбор точки входа
        if ("top".equals(buttonPosition)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        // Заполнение формы заказа
        orderPage.fillFirstPage(name, lastName, address, metro, phone);
        orderPage.fillSecondPage(date, comment, color);
        orderPage.confirmOrder();

        // Проверка успешного оформления
        assertTrue("Сообщение об успешном оформлении заказа не отображается в браузере " + getBrowserType(),
                orderPage.isSuccessMessageDisplayed());
    }
}