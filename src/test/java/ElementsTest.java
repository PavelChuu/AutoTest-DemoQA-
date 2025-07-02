import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ElementsTest {

    @DisplayName("Test TextBox")
    @ParameterizedTest
    @MethodSource("BrowserProvider#provideArgument")
    void TextBoxTest(String browser) {
        Configuration.browser = browser;
        //Подключаем Faker
        Faker faker = new Faker();
        //Подключаем Логи Allure
        SelenideLogger.addListener("allure", new AllureSelenide());

        String [] array = new String[4];
        array[0] = faker.name().firstName();
        array[1] = faker.internet().emailAddress();
        array[2] = faker.address().fullAddress();
        array[3] = faker.address().fullAddress();

        open("https://demoqa.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        //Сам тест
        $("svg[viewBox='0 0 448 512']").click();
        $("#item-0 > svg[viewBox='0 0 1024 1024']").click();
        $("#userName").click();
        $("#userName").val(array[0]);
        $("#userEmail").click();
        $("#userEmail").val(array[1]);
        $("#currentAddress").click();
        $("#currentAddress").val(array[2]);
        $("#permanentAddress").click();
        $("#permanentAddress").val(array[3]);
        $("#submit").click();

        SelenideElement name = $("p[id='name']");
        name.shouldBe(visible);
        name.shouldHave(exactText("Name:" + array[0]));

        SelenideElement email = $("p[id='email']");
        email.shouldBe(visible);
        email.shouldHave(exactText("Email:" + array[1]));

        SelenideElement cAddress = $("p[id='currentAddress']");
        cAddress.shouldBe(visible);
        cAddress.shouldHave(exactText("Current Address :" + array[2]));

        SelenideElement pAddress = $("p[id='permanentAddress']");
        pAddress.shouldBe(visible);
        pAddress.shouldHave(exactText("Permananet Address :" + array[3]));
        WebDriverRunner.closeWebDriver();
    }

    @DisplayName("Radio Button Test")
    @ParameterizedTest
    @MethodSource("BrowserProvider#provideArgument")
    void RadioButtonTest(String browser) {
        Configuration.browser = browser;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://demoqa.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();

        $("svg[viewBox='0 0 448 512']").click();
        $("#item-2 > svg[viewBox='0 0 1024 1024']").click();
        SelenideElement select = $("span[class ='text-success']");

        $("label[for='yesRadio']").click();
        select.shouldBe(visible);
        select.shouldHave(exactText("Yes"));

        $("label[for='impressiveRadio']").click();
        select.shouldBe(visible);
        select.shouldHave(exactText("Impressive"));
        WebDriverRunner.closeWebDriver();
    }

    @DisplayName("Buttons Test")
    @ParameterizedTest
    @MethodSource("BrowserProvider#provideArgument")
    public void ButtonsTest(String browser) {
        Configuration.browser = browser;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://demoqa.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();

        $("svg[viewBox='0 0 448 512']").click();
        $("#item-4 > svg[viewBox='0 0 1024 1024']").click();

        $("#doubleClickBtn").doubleClick();
        $("#doubleClickMessage").should(visible);

        Actions actions = new Actions(Selenide.webdriver().driver().getWebDriver());
        var element = $("#rightClickBtn");
        actions.contextClick(element.getWrappedElement()).perform();
        $("#rightClickMessage").should(visible);

        $("div[class = 'mt-4']:nth-of-type(3) button[class='btn btn-primary']").click();
        $("#dynamicClickMessage").should(visible);
        WebDriverRunner.closeWebDriver();
    }
}

