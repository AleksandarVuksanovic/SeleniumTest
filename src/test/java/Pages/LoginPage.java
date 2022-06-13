package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wdwait;
    WebElement username;
    WebElement password;
    WebElement loginButton;
    WebElement loggedOutNotification;
    WebElement invalidDataNotification;

    public LoginPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getUsername() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
    }

    public WebElement getLoggedOutNotification() {
        return driver.findElement(By.cssSelector(".flash.success"));
    }

    public WebElement getInvalidDataNotification() {
        return driver.findElement(By.id("flash"));
    }

    //--------------------------

    public void enterUsername (String userName) {
        getUsername().clear();
        getUsername().sendKeys(userName);
    }

    public void enterPassword (String pass) {
        getPassword().clear();
        getPassword().sendKeys(pass);
    }

    public void clickOnLoginButton () {
        getLoginButton().click();
    }

}
