package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DesktopPage {

    public EventFiringWebDriver driver;

    public DesktopPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //поиск кнопки
    private WebElement getCreteButton() {
        return driver.findElement(By.xpath("//a[@href ='bug_report_page.php']"));
    }

    //нажатие на кнопку
    public void clickCreateTaskButton() {
        getCreteButton().click();
    }
}
