package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SuccessPage {
    public EventFiringWebDriver driver;

    public SuccessPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Проверка на успешность создания задачи (Элемент найден")
     */
    public void checkSuccess() {
        boolean exist = driver.findElements(By.xpath("//p[contains(text(),'Действие успешно выполнено.')]")).size() > 0;
        assertTrue(exist,"Task not created");
    }

    private WebElement getTextWithNumeric() {
        return driver.findElement(By.xpath("//a[contains(text(),'Просмотреть созданную задачу')]"));
    }

    public String getTaskNumber() {
        return getTextWithNumeric().getText().split(" ")[3];
    }
}