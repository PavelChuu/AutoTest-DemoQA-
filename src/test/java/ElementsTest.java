import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;


public class ElementsTest {
    @BeforeEach
    public void setUp() {
        Configuration.browser = "firefox";
        Configuration.timeout = 600000;
        open("https://demoqa.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @DisplayName("Test TextBox")
    @Test
    public void TextBoxTest() {
        //Подключаем Faker
        Faker faker = new Faker();
        //Подключаем Логи Allure
        SelenideLogger.addListener("allure", new AllureSelenide());
        //Сам тест
        $("svg[viewBox='0 0 448 512']").click();
        $("#item-0 > svg[viewBox='0 0 1024 1024']").click();
        $("#userName").click();
        $("#userName").val(faker.name().firstName());
        $("#userEmail").click();
        $("#userEmail").val(faker.internet().emailAddress());
        $("#currentAddress").click();
        $("#currentAddress").val(faker.address().fullAddress());
        $("#permanentAddress").click();
        $("#permanentAddress").val(faker.address().fullAddress());
        $("#submit").click();
    }

    @Test
    public void CheckBoxTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Duration.ofSeconds(5);
        $(".rct-options > .rct-option-expand-all > .rct-icon-expand-all").click();
    }

}
