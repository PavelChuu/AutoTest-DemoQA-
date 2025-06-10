import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class ElementsTest {
    static WebDriver driver;
    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void TextBoxTest() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("UserName");
        driver.findElement(By.id("userEmail")).click();
        driver.findElement(By.id("userEmail")).sendKeys("UserEmail@gmail.com");
        driver.findElement(By.id("currentAddress")).click();
        driver.findElement(By.id("currentAddress")).sendKeys("CurrentAddress");
        driver.findElement(By.id("permanentAddress")).click();
        driver.findElement(By.id("permanentAddress")).sendKeys("PermanentAddress");
        driver.findElement(By.id("submit")).click();
    }
}
