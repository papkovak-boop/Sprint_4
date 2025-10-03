package scooter.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scooter.pages.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTest extends BaseTest {

    private final int questionIndex;
    private final String expectedAnswer;

    public QuestionsTest(String browserType, int questionIndex, String expectedAnswer) {
        super(browserType);
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "Браузер: {0}, Вопрос: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"chrome", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"chrome", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"chrome", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"chrome", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"chrome", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"chrome", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"chrome", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"chrome", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

                {"firefox", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"firefox", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"firefox", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"firefox", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"firefox", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"firefox", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"firefox", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"firefox", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void testQuestionAnswer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        mainPage.clickQuestion(questionIndex);
        String actualAnswer = mainPage.getAnswerText(questionIndex);

        assertEquals("Текст ответа не совпадает для вопроса с индексом " + questionIndex + " в браузере " + getBrowserType(),
                expectedAnswer, actualAnswer);
    }
}
