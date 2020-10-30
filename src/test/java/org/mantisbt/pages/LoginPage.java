package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage {
    public EventFiringWebDriver driver;

    public LoginPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getUserField() {
        return driver.findElement(By.xpath("//input[@placeholder='Пользователь']"));
    }

    private WebElement getLogin2Button() {
        return driver.findElement(By.xpath("//input[@value='Вход']"));
    }

    //ввод логина
    public void inputUser(String login) {
        getUserField().sendKeys(login);
    }

    //Нажатие на кнопку
    public void clickLogin2Btn() {
        getLogin2Button().click();
    }
}
