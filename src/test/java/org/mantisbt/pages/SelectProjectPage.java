package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SelectProjectPage {

    public EventFiringWebDriver driver;

    public SelectProjectPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getSelectProjectButton() {
        return driver.findElement(By.xpath("//input[@value='Выбрать проект']"));
    }

    public void clickSelectProjectButton() {
        getSelectProjectButton().click();
    }
}
