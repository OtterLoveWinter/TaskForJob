package org.mantisbt.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MainPage {
    public EventFiringWebDriver driver;

    public MainPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[text()=\"Зарегистрировать новую учётную запись\"]")
    private WebElement registrationButton;
    @FindBy(xpath = "//a[text()=\"Вход\"]")
    private WebElement enterButton;

    public void clickRegistrationButton() {
        registrationButton.click();
    }

    public void clickEnterButton() {
        enterButton.click();
    }
}
