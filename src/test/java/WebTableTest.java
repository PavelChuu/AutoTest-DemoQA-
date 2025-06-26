import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.jupiter.api.*;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    public void deleteEntry() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        $("span[title=\"Delete\"]").click();
    }

    @Disabled
    @DisplayName("Search")
    @RepeatedTest(5)
    @Order(4)
    public void search() {
        String search;
        SelenideLogger.addListener("allure", new AllureSelenide());
        Faker faker = new Faker();

        SelenideElement parentDiv = $("div[class='rt-tbody']");
        ElementsCollection childDivs = parentDiv.$$("div[class='rt-tr -odd'], div[class='rt-tr -even']");
        int childDivCount = childDivs.size();
        SelenideElement Child = childDivs.get(faker.number().numberBetween(1, childDivCount));

    }

    @DisplayName("Rows")
    @Test
    @Order(5)
    public void Rows() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String[] numbers = {"10", "20", "25", "50", "100", "5"};
        for (String num : numbers) {
            //Скролл до конца страницы
            Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
            $("select[aria-label='rows per page']").click();
            // Ищем элемент через селектор "option[value='%s']" на место %s вставляется значение num, которое берётся из массива numbers
            $(String.format("option[value='%s']", num)).click();
        }
    }

    @DisplayName("Previous and Next page")
    @Test
    @Order(6)
    public void PrevAndNext() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        $("div[class='-next'] button").click();
        $("div[class='-previous'] button").click();
        $("div[class='-next'] button").click();
    }

    @DisplayName("Sorting")
    @Test
    @Order(7)
    public void Sorting() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        $("div[role='columnheader']:nth-of-type(1)").click();
        $("div[role='columnheader']:nth-of-type(2)").click();
        $("div[role='columnheader']:nth-of-type(3)").click();
        $("div[role='columnheader']:nth-of-type(4)").click();
        $("div[role='columnheader']:nth-of-type(5)").click();
        $("div[role='columnheader']:nth-of-type(6)").click();
        $("div[role='columnheader']:nth-of-type(7)").click();
    }

    @DisplayName("Jump to page")
    @Test
    @Order(8)
    public void jumpPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Faker faker = new Faker();

        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        //Узнаём максимальное число страниц и присваиваем это значение переменной max
        SelenideElement element = $("span[class='-totalPages']");
        String max = element.getText();
        //Конвертируем переменную max в int и присваиваем его maxValue
        int maxValue, value;
        maxValue = Integer.parseInt(max);
        for (value = 1; value < maxValue; value++) {
            $("input[aria-label=\"jump to page\"]").click();
            $("input[aria-label=\"jump to page\"]").val(String.valueOf(value)).pressEnter();
        }
    }

}
