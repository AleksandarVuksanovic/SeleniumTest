package Test;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetup () {
        driver.manage().window().maximize();
        driver.navigate().to(homepageURL);
    }


    @Test (priority = 10)
    public void successfulLogin () {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(secureAreaPage.getLogoutButton().isDisplayed());
        Assert.assertTrue(secureAreaPage.getWelcomeMessage().isDisplayed());
        String expectedWelcomeMessage = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(secureAreaPage.getWelcomeMessage().getText(), expectedWelcomeMessage);

    }

    @Test (priority = 20)
    public void successfulLogout () {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(secureAreaPage.getLogoutButton().isDisplayed());
        Assert.assertTrue(secureAreaPage.getWelcomeMessage().isDisplayed());
        //ulogujemo se i asertujemo da smo uspešno ulogovani

        secureAreaPage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getLoggedOutNotification().isDisplayed());
       //potom se izlogujemo i asertujemo da smo se uspešno izlogovali

    }

    @Test (priority = 30)
    public void loginWithInvalidUsername () {
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            //petlju krećemo od indeksa 1, jer je na indeksu 0 naslov "valid username"
            String invalidUsername = excelReader.getStringData("Login", i, 2);
            String validPassword = excelReader.getStringData("Login", 1, 1);
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(validPassword);
            loginPage.clickOnLoginButton();
        }

        Assert.assertTrue(loginPage.getInvalidDataNotification().isDisplayed());
        //asertujemo da se pojavila notifikacija da su uneti podaci neispravni

        boolean check = false;
        try { check = secureAreaPage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
        }
        Assert.assertFalse(check);
        //asertujemo da se ne prikazuje logout dugme

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //asertujemo da se prikazuje login dugme
    }

    @Test (priority = 40)
    public void loginWithInvalidPassword () {
            for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
                //petlju krećemo od indeksa 1, jer je na indeksu 0 naslov "valid password"
                String validUsername = excelReader.getStringData("Login", 1, 0);
                String invalidPassword = excelReader.getStringData("Login", i, 3);
                loginPage.enterUsername(validUsername);
                loginPage.enterPassword(invalidPassword);
                loginPage.clickOnLoginButton();
            }
        Assert.assertTrue(loginPage.getInvalidDataNotification().isDisplayed());
        //asertujemo da se pojavila notifikacija da su uneti podaci neispravni

        boolean check = false;
        try { check = secureAreaPage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
        }
        Assert.assertFalse(check);
        //asertujemo da se ne prikazuje logout dugme

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //asertujemo da se prikazuje login dugme

    }

    @Test (priority = 50)
    public void loginWithoutEnteringCredentials () {
        loginPage.clickOnLoginButton();
        //pokušavamo da se ulogujemo bez unošenja podataka, samo klikom na dugme login

        Assert.assertTrue(loginPage.getInvalidDataNotification().isDisplayed());
        //asertujemo da se pojavila notifikacija da su uneti podaci neispravni

        boolean check = false;
        try { check = secureAreaPage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
        }
        Assert.assertFalse(check);
        //asertujemo da se ne prikazuje logout dugme

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //asertujemo da se prikazuje login dugme
    }

    @Test (priority = 60)
    public void loginWithCapslockUsername () throws InterruptedException {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.enterUsername(validUsername.toUpperCase()); //unosimo validan username i dajemo komandu da se prebaci u caps lock
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(loginPage.getInvalidDataNotification().isDisplayed());
        //asertujemo da se pojavila notifikacija da su uneti podaci neispravni

        boolean check = false;
        try { check = secureAreaPage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
        }
        Assert.assertFalse(check);
        //asertujemo da se ne prikazuje logout dugme

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //asertujemo da se prikazuje login dugme

    }

    @AfterMethod
    public void deleteAllCookies () {
        driver.manage().deleteAllCookies();
    }


}
