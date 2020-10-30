package org.mantisbt.pages;

import org.mantisbt.Captcha;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegistrationPage {
    public EventFiringWebDriver driver;

    public RegistrationPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getLoginField() {
        return driver.findElement(By.xpath("//input[@placeholder='Пользователь']"));
    }

    private WebElement getEmailField() {
        return driver.findElement(By.xpath("//input[@placeholder='Электронная почта']"));
    }

    //получение родительского элемента
    private WebElement getCaptchaSpan() {
        return driver.findElement(By.xpath("//span[@id='captcha-image']"));
    }

    //поиск дочернего элемента (Капча картинка)
    private WebElement getCaptchaImg() {
        return getCaptchaSpan().findElement(By.xpath(".//img"));
    }

    public String getBase64Captcha() {
        String base64 = new String();
        try {
            //скриншот всей страницы
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            //ищем координаты капчи
            WebElement ele = getCaptchaImg();
            Point point = ele.getLocation();

            //размеры капчи
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            //вырезание капчи из скрина
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);

            //в base64
            base64 = Captcha.encodeToString(eleScreenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;

    }

    private WebElement getNextButton() {
        return driver.findElement(By.xpath("//input[@value='Зарегестрироваться']"));
    }

    private WebElement getCaptcha() {
        return driver.findElement(By.xpath("//input[@id='captcha-field']"));
    }

    //ввод логина
    public void inputLogin(String loginReg) {
        getLoginField().sendKeys(loginReg);
    }

    //email
    public void inputEmail(String EmailReg) {
        getEmailField().sendKeys(EmailReg);
    }

    //captcha
    public void inputCaptcha(String Captcha) {
        getCaptcha().sendKeys(Captcha);
    }

    //кнопка
    public void clickLoginBtn() {
        getNextButton().click();
    }
}
