package org.mantisbt.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mantisbt.Captcha;
import org.mantisbt.ConfProperties;
import org.mantisbt.pages.MainPage;
import org.mantisbt.pages.RegistrationPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationTest extends AbstractTest {
    public static MainPage mainPage;
    public static RegistrationPage registrationPage;

    @BeforeAll
    public static void setup() {
        //получение ссылки на страницу входа из файла настроек
        eventDriver.get(ConfProperties.getProperty("host") + ConfProperties.getProperty("desktopPage"));
        mainPage = new MainPage(eventDriver);
        registrationPage = new RegistrationPage(eventDriver);
    }
        @Test
        public void registrationTest() throws Exception {
            //значение loginReg/EmailReg из файла настроек
            mainPage.clickRegistrationButton();
            wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("host") + ConfProperties.getProperty("registrationPage")));
            registrationPage.inputLogin(ConfProperties.getProperty("loginReg"));
            registrationPage.inputEmail(ConfProperties.getProperty("EmailReg"));

            // Ввод капчи
            String base64 = registrationPage.getBase64Captcha();
            String requestId = Captcha.send(base64);
            for (int i = 0; i<10; i++){
                Thread.sleep(5000);
                String result = Captcha.get(requestId);
                if (result!=null){
                    System.out.println("captcha="+result);
                    registrationPage.inputCaptcha(result);
                    break;
                }
            }

            registrationPage.clickLoginBtn();
            //TODO потверждение регистрации на почте
    }
}
