import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;


public class ElementsTest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "firefox";
    }

    @Test
    public void TextBoxTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://demoqa.com/text-box");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Duration.ofSeconds(5);
        $("#userName").click();
        $("#userName").val("UserName");
        $("#userEmail").click();
        $("#userEmail").val("UserEmail@gmail.com");
        $("#currentAddress").click();
        $("#currentAddress").val("currentAddress");
        $("#permanentAddress").click();
        $("#permanentAddress").val("permanentAddress");
        $("#submit").click();
    }

    @Test
    public void CheckBoxTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://demoqa.com/checkbox");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Duration.ofSeconds(5);
        $(".rct-options > .rct-option-expand-all > .rct-icon-expand-all").click();
    }
}
