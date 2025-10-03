package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // Локаторы с комментариями:

    // Кнопка принятия куки
    private final By cookieButton = By.id("rcc-confirm-button");

    // Верхняя кнопка "Заказать"
    private final By topOrderButton = By.className("Button_Button__ra12g");

    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Заголовки вопросов в разделе "Вопросы о важном"
    private final By questionHeaders = By.xpath(".//div[@class='accordion__button']");

    // Текст ответов в разделе "Вопросы о важном"
    private final By questionAnswers = By.xpath(".//div[@class='accordion__panel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void clickQuestion(int index) {
        List<WebElement> questions = driver.findElements(questionHeaders);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questions.get(index));
        questions.get(index).click();
    }

    public String getAnswerText(int index) {
        List<WebElement> answers = driver.findElements(questionAnswers);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).getText();
    }
}