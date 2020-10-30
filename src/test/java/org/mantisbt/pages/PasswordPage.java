package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class PasswordPage {
    public EventFiringWebDriver driver;

    public PasswordPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.xpath("//input[@id='password']"));
    }

    private WebElement getEnterIntoAccButton() {
        return driver.findElement(By.xpath("//input[@value='Вход']"));
    }

    //ввод пароля
    public void inputPassword(String password) {
        getPasswordField().sendKeys(password);
    }

    //Нажатие на кнопку
    public void clickEnterIntoAccButton() {
        getEnterIntoAccButton().click();
    }
}
