package Base;

import Pages.LoginPage;
import Pages.SecureAreaPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;
    public String homepageURL;
    public LoginPage loginPage;
    public SecureAreaPage secureAreaPage;

    @BeforeClass
    public void setUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        excelReader = new ExcelReader("C:\\Users\\aleksandar.vuksanovi\\OneDrive - Smart kolektiv\\Documents\\Aleksandar\\Aleksandar\\IT Bootcamp\\Kurs\\Selenium\\Test\\Test Data.xlsx");
        homepageURL = excelReader.getStringData("URLs", 1, 0);
        loginPage = new LoginPage(driver, wdwait);
        secureAreaPage = new SecureAreaPage(driver, wdwait);
    }

    @AfterClass
    public void tearDown () {
        //driver.close();
        //driver.quit();
    }
}
