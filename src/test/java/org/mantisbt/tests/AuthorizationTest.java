package org.mantisbt.tests;

import org.junit.jupiter.api.*;
import org.mantisbt.ConfProperties;
import org.mantisbt.pages.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizationTest extends AbstractTest {
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static PasswordPage passwordPage;
    public static DesktopPage desktopPage;
    public static SelectProjectPage selectProjectPage;
    public static DeskriptionTaskPage deskriptionTaskPage;
    public static SuccessPage successPage;

    @BeforeAll
    public static void setup() {
        //получение ссылки на страницу входа из файла настроек
        eventDriver.get(ConfProperties.getProperty("host") + ConfProperties.getProperty("desktopPage"));
        mainPage = new MainPage(eventDriver);
        loginPage = new LoginPage(eventDriver);
        passwordPage = new PasswordPage(eventDriver);
        desktopPage = new DesktopPage(eventDriver);
        selectProjectPage = new SelectProjectPage(eventDriver);
        deskriptionTaskPage = new DeskriptionTaskPage(eventDriver);
        successPage = new SuccessPage(eventDriver);
    }

    @Test
    @Order(1)
    public void authorizationTest() {
        System.out.println("authorizationTest Start");
        //значение login/password/Topic/description/steps из файла настроек
        mainPage.clickEnterButton();
        loginPage.inputUser(ConfProperties.getProperty("login"));
        loginPage.clickLogin2Btn();
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("host") + ConfProperties.getProperty("passwordPage")));
        passwordPage.inputPassword(ConfProperties.getProperty("password"));
        passwordPage.clickEnterIntoAccButton();
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("host") + ConfProperties.getProperty("desktopPage")));
    }

    @Test
    @Order(2)
    public void createTask(){
        System.out.println("createTask Start");
        desktopPage.clickCreateTaskButton();
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("host") + ConfProperties.getProperty("createTaskPage")));
        selectProjectPage.clickSelectProjectButton();
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("host") + ConfProperties.getProperty("descriptionTaskPage")));
        deskriptionTaskPage.clickCategoryItems();
        deskriptionTaskPage.clickVersionItems();
        deskriptionTaskPage.inputTopic(ConfProperties.getProperty("Topic"));
        deskriptionTaskPage.inputDescription(ConfProperties.getProperty("description"));
        deskriptionTaskPage.inputStepsField(ConfProperties.getProperty("steps"));
        deskriptionTaskPage.clickPrivateButtonActually();
        deskriptionTaskPage.clickCreateTask();
    }

    @Test
    @Order(3)
    public void checkTask(){
        System.out.println("checkTask Start");
        successPage.checkSuccess();
        System.out.println("Success. Task number " + successPage.getTaskNumber());
    }
}
