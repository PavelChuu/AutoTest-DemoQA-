import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Selenide.*;

public class WebTableTest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "firefox";
        Configuration.timeout = 600000;
        Configuration.holdBrowserOpen = true;
        open("https://demoqa.com/webtables");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @DisplayName("Add new entry in table")
    @RepeatedTest(20)
    public void addNew() {
        Faker faker = new Faker();
        SelenideLogger.addListener("allure", new AllureSelenide());

        $("#addNewRecordButton").click();
        $("#firstName").click();
        $("#firstName").val(faker.name().firstName());
        $("#lastName").click();
        $("#lastName").val(faker.name().lastName());
        $("#userEmail").click();
        $("#userEmail").val(faker.internet().emailAddress());
        $("#age").click();
        $("#age").val(String.valueOf(faker.number().numberBetween(18, 100)));
        $("#salary").click();
        $("#salary").val(String.valueOf(faker.number().numberBetween(1, 100000)));
        $("#department").click();
        $("#department").val(faker.lorem().word());
        $("#submit").click();
    }

    @DisplayName("Edit entry in table")
    @Test
    public void editEntry() {
        Faker faker = new Faker();
        SelenideLogger.addListener("allure", new AllureSelenide());

        $("span[title=\"Edit\"]").click();
        $("#firstName").click();
        $("#firstName").val(faker.name().firstName());
        $("#lastName").click();
        $("#lastName").val(faker.name().lastName());
        $("#userEmail").click();
        $("#userEmail").val(faker.internet().emailAddress());
        $("#age").click();
        $("#age").val(String.valueOf(faker.number().numberBetween(18, 100)));
        $("#salary").click();
        $("#salary").val(String.valueOf(faker.number().numberBetween(1, 100000)));
        $("#department").click();
        $("#department").val(faker.lorem().word());
        $("#submit").click();
    }

    @DisplayName("Delete entry in table")
    @Test
    public void deleteEntry() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        $("span[title=\"Delete\"]").click();
    }

    @DisplayName("Rows")
    @Test
    public void Rows() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String[] numbers = {"10", "20", "25", "50", "100", "5"};
        for (String num : numbers) {
            //Скролл до конца страницы
            Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
            $("select[aria-label='rows per page']").click();
            // Ищем элемент через селектор "option[value='%s']" на место %s вставляется значение num которое берётся из массива numbers
            $(String.format("option[value='%s']", num)).click();
        }
    }

    @DisplayName("Turn the page")
    @Test
    public void turnPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Faker faker = new Faker();

        //Узнаём максимальное число страниц и присваиваем это значение element
        SelenideElement element = $("span[class='-totalPages']");
        String max = element.getText();
        //Конвертируем переменную max в int и присваиваем его maxValue
        int maxValue;
        maxValue = Integer.parseInt(max);
        $("input[aria-label=\"jump to page\"]").click();
        $("input[aria-label=\"jump to page\"]").val(String.valueOf(faker.number().numberBetween(1, maxValue))).pressEnter();
    }

    @DisplayName("Previous and Next page")
    @Test
    public void PrevAndNext() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        $("div[class='-next'] button").click();
        $("div[class='-previous'] button").click();
        $("div[class='-next'] button").click();
    }
}
