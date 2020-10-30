package org.mantisbt.tests;

import org.junit.jupiter.api.AfterAll;
import org.mantisbt.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractTest {
    private static WebDriver driver;
    public static EventFiringWebDriver eventDriver;
    protected static Wait<WebDriver> wait;

    static {
        init();
    }

    private static void init() {
        try {
            // Создание драйвера
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.manage().window().maximize();
            eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Настройка ожиданий
            wait = new WebDriverWait(eventDriver,
                    Integer.parseInt(ConfProperties.getProperty("timeOutInSeconds")),
                    Integer.parseInt(ConfProperties.getProperty("sleepInMillis")))
                    .withMessage("Превышено время ожидания");
        } catch (Exception e) {
            // Если драйвера нет, то зачем дальше жить
            System.exit(-1);
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
