package org.mantisbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DeskriptionTaskPage {

    public EventFiringWebDriver driver;

    public DeskriptionTaskPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //получение списка категорий
    private WebElement getCategoryList() {
        return driver.findElement(By.xpath("//select[@name='category_id']"));
    }

    //получение категории из списка
    private WebElement getCategoryItems() {
        return getCategoryList().findElement(By.xpath(".//option[text()=\"other\"]"));
    }

    //получение списка версий продукта
    private WebElement getVersionList() {
        return driver.findElement(By.xpath("//select[@name='product_version']"));
    }

    //выбор версии из списка
    private WebElement getVersionItems() {
        return getVersionList().findElement(By.xpath(".//option[@value='1.3.18']"));
    }

    private WebElement getTopicField() {
        return driver.findElement(By.xpath("//input[@id='summary']"));
    }

    //ввод темы
    public void inputTopic(String Topic) {
        getTopicField().sendKeys(Topic);
    }

    private WebElement getDescriptionField() {
        return driver.findElement(By.xpath("//textarea[@id='description']"));
    }

    //ввод описания
    public void inputDescription(String description) {
        getDescriptionField().sendKeys(description);
    }

    private WebElement getStepsField() {
        return driver.findElement(By.xpath("//textarea[@id='steps_to_reproduce']"));
    }

    //ввод описания
    public void inputStepsField(String steps) {
        getStepsField().sendKeys(steps);
    }

    private WebElement getPrivateButton() {
        return driver.findElement(By.xpath("//span[contains(text(),'приватная')]"));
    }

    //выбор категории
    public void clickCategoryItems() {
        getCategoryItems().click();
    }

    //выбор версии продукта
    public void clickVersionItems() {
        getVersionItems().click();
    }

    //Нажатие на радиобатон Приватная
    public void clickPrivateButtonActually() {
        getPrivateButton().click();
    }

    private WebElement getCreateTask() {
        return driver.findElement(By.xpath("//input[@value='Создать задачу']"));
    }

    //Нажатие на кнопку
    public void clickCreateTask() {
        getCreateTask().click();
    }

}
