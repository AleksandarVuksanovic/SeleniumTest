package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecureAreaPage {
    WebDriver driver;
    WebDriverWait wdwait;
    WebElement welcomeMessage;
    WebElement logoutButton;

    public SecureAreaPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getWelcomeMessage() {
        return driver.findElement(By.className("subheader"));
    }

    public WebElement getLogoutButton() {
        return driver.findElement(By.cssSelector(".button.secondary.radius"));
    }

    //---------------

    public void clickOnLogoutButton () {
        getLogoutButton().click();
    }

}
