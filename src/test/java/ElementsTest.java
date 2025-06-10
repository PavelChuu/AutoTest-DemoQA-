import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ElementsTest {

    static WebDriver driver;
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "firefox";}
    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();}

    @Test
    public void TextBoxTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://demoqa.com/text-box");
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
}
