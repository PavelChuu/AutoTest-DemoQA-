import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CheckBoxTest {

    @DisplayName("Check Box Test")
    @ParameterizedTest
    @MethodSource("BrowserSetUp#SetUp")
    public void CheckBoxTest(String browser) {
        Configuration.browser = browser;
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://demoqa.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("svg[viewBox='0 0 448 512']").click();
        $("#item-1 > svg[viewBox='0 0 1024 1024']").click();
        ElementsCollection elements = $$("span[class='text-success']");
        SelenideElement element = $("span[class='text-success']");
        //Проверка кнопок expand-all и collapse-all
        for (int i = 0; i < 10; i++) {
            switch (i){
                case 0:
                    $("svg[class$='rct-icon-expand-all']").click();
                    $("svg[class$='rct-icon-expand-close']").shouldNot(visible);
                    break;
                case 1:
                    $("svg[class$='rct-icon-collapse-all']").click();
                    $("svg[class$='rct-icon-expand-open']").shouldNot(visible);
                    break;
            }
        }
        $("svg[class$='rct-icon-expand-close']").click();
        $("svg[class$='rct-icon-expand-close']").click();
        //Выбор папки "Desktop"
        for (int i = 0; i < 3; i++) {
            switch (i) {
                //Выбор чек-бокса "Notes"
                case 0:
                    $("label[for='tree-node-notes']").click();
                    element.should(visible, text("notes"));
                    $("label[for='tree-node-notes']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Commands"
                case 1:
                    $("label[for='tree-node-commands']").click();
                    element = $("span[class='text-success']");
                    element.should(visible, text("commands"));
                    $("label[for='tree-node-commands']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-боксов "Notes" и "Commands"
                case 2:
                    $("label[for='tree-node-desktop'] ").click();
                    /*
                    Проверка, что коллекция select существует и в ней есть элементы с текстом:
                    "desktop", "notes", "commands"
                     */
                    elements.shouldBe(CollectionCondition.size(3));
                    elements.shouldBe(CollectionCondition.texts("desktop", "notes", "commands"));
                    $("label[for='tree-node-desktop'] ").click();
                    elements.should(CollectionCondition.size(0));
                    break;
            }
        }

        $("svg[class$='rct-icon-expand-close']").click();
        $("svg[class$='rct-icon-expand-close']").click();
        //Выбор папки "Documents"
        for (int i = 0; i < 10; i++) {
            switch (i) {
                //Выбор чек-бокса "React"
                case 0:
                    $("label[for='tree-node-react']").click();
                    element.should(visible, text("react"));
                    $("label[for='tree-node-react']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Angular"
                case 1:
                    $("label[for='tree-node-angular']").click();
                    element.should(visible, text("angular"));
                    $("label[for='tree-node-angular']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Veu"
                case 2:
                    $("label[for='tree-node-veu']").click();
                    element.should(visible, text("veu"));
                    $("label[for='tree-node-veu']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-боксов "React", "Angular" и "Veu"
                case 3:
                    $("label[for='tree-node-workspace']").click();
                    /*
                    Проверка, что коллекция select существует и в ней есть элементы с текстом:
                    "workspace", "react", "angular", "veu"
                    */
                    elements.shouldBe(CollectionCondition.size(4));
                    elements.shouldBe(CollectionCondition.texts("workspace", "react", "angular", "veu"));
                    $("label[for='tree-node-workspace']").click();
                    elements.should(CollectionCondition.size(0));
                    break;
                //Выбор чек-бокса "Public"
                case 4:
                    $("svg[class$='rct-icon-expand-close']").click();
                    $("label[for='tree-node-public']").click();
                    element.should(visible, text("public"));
                    $("label[for='tree-node-public']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Private"
                case 5:
                    $("label[for='tree-node-private']").click();
                    element.should(visible, text("private"));
                    $("label[for='tree-node-private']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Classified"
                case 6:
                    $("label[for='tree-node-classified']").click();
                    element.should(visible, text("classified"));
                    $("label[for='tree-node-classified']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "General"
                case 7:
                    $("label[for='tree-node-general']").click();
                    element.should(visible, text("general"));
                    $("label[for='tree-node-general']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-боксов "Public", "Private", "Classified", "General"
                case 8:
                    $("label[for='tree-node-office']").click();
                    /*
                    Проверка, что коллекция select существует и в ней есть элементы с текстом:
                    "office", "public", "private", "classified", "general"
                    */
                    elements.shouldBe(CollectionCondition.size(5));
                    elements.shouldBe(CollectionCondition.texts("office", "public", "private", "classified", "general"));
                    $("label[for='tree-node-office']").click();
                    elements.should(CollectionCondition.size(0));
                    break;
                case (9):
                    //Выбор всей папки "Documents"
                    $("label[for='tree-node-documents']").click();
                    /*
                    Проверка, что коллекция select существует и в ней есть элементы с текстом:
                    "documents", "workspace", "react", "angular", "veu", "office", "public", "private", "classified", "general"
                    */
                    elements.shouldBe(CollectionCondition.size(10));
                    elements.shouldBe(CollectionCondition.texts("documents", "workspace", "react", "angular", "veu", "office", "public", "private", "classified", "general"));
                    $("label[for='tree-node-documents']").click();
                    elements.should(CollectionCondition.size(0));
            }
        }

        $("svg[class$='rct-icon-expand-close']").click();
        //Выбор папки "Downloads"
        for (int i = 0; i < 3; i++) {
            switch (i) {
                //Выбор чек-бокса "Word File.doc"
                case 0:
                    $("label[for='tree-node-wordFile']").click();
                    element.should(visible, text("wordFile"));
                    $("label[for='tree-node-wordFile']").click();
                    element.should((disappear));
                    break;
                //Выбор чек-бокса "Excel File.doc"
                case 1:
                    $("label[for='tree-node-excelFile']").click();
                    element.should(visible, text("excelFile"));
                    $("label[for='tree-node-excelFile']").click();
                    element.should((disappear));
                    break;
                //Выбор всей папки "Downloads"
                case 2:
                    $("label[for='tree-node-downloads']").click();
                     /*
                    Проверка, что коллекция select существует и в ней есть элементы с текстом:
                    "downloads", "wordFile", "excelFile"
                    */
                    elements.shouldBe(CollectionCondition.size(3));
                    elements.shouldBe(CollectionCondition.texts("downloads", "wordFile", "excelFile"));
                    $("label[for='tree-node-downloads']").click();
                    elements.should(CollectionCondition.size(0));
                    break;
            }
        }
        //Выбор всех чек-боксов
            $("label[for='tree-node-home']").click();
        /*
        Проверка, что коллекция select существует и в ней есть элементы с текстом:
        "home", "desktop", "notes", "commands", "documents", "workspace", "react", "angular", "veu", "office", "public", "private", "classified", "general", "downloads", "wordFile", "excelFile"
        */
            elements.shouldBe(CollectionCondition.size(17));
            elements.shouldBe(CollectionCondition.texts("home", "desktop", "notes", "commands", "documents", "workspace", "react", "angular", "veu", "office", "public", "private", "classified", "general", "downloads", "wordFile", "excelFile"));
            $("label[for='tree-node-home']").click();
            elements.should(CollectionCondition.size(0));
    WebDriverRunner.closeWebDriver();
    }
}
