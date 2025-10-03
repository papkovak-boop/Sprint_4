package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы для первой страницы заказа:

    // Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    // Поле "Телефон"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локаторы для второй страницы заказа:

    // Поле "Когда привезти самокат"
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Поле "Срок аренды"
    private final By rentalPeriod = By.className("Dropdown-placeholder");

    // Опция "Сутки" в выпадающем списке
    private final By periodOption = By.xpath(".//div[text()='сутки']");

    // Чекбокс "Черный жемчуг"
    private final By colorBlack = By.id("black");

    // Чекбокс "Серая безысходность"
    private final By colorGrey = By.id("grey");

    // Поле "Комментарий для курьера"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать" верхняя
    private final By orderButtonTop = By.xpath("//button[contains(@class='Button_Button__ra12g') and text()='Заказать']");

    // Кнопка "Заказать" нижняя
    private final By orderButtonBottom = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    // Кнопка "Да" в подтверждающем окне
    private final By confirmButton = By.xpath(".//button[contains(., 'Да')]");

    // Сообщение об успешном создании заказа
    private final By successMessage = By.xpath(".//div[contains(., 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton(String position) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        if ("top".equals(position)) {
            wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop));
            driver.findElement(orderButtonTop).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
            driver.findElement(orderButtonBottom).click();
        }
    }

    public void fillFirstPage(String name, String lastName, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(metroField).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondPage(String date, String comment, String color, String buttonPosition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(dateField));

        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod));
        driver.findElement(rentalPeriod).click();

        wait.until(ExpectedConditions.elementToBeClickable(periodOption));
        driver.findElement(periodOption).click();

        if ("black".equals(color)) {
            wait.until(ExpectedConditions.elementToBeClickable(colorBlack));
            driver.findElement(colorBlack).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(colorGrey));
            driver.findElement(colorGrey).click();
        }

        driver.findElement(commentField).sendKeys(comment);
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        clickOrderButton(buttonPosition);
    }

    public void confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).isDisplayed();
    }
}
