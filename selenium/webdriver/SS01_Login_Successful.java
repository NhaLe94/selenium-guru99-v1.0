package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SS01_Login_Successful {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");


    @BeforeClass
    public void beforeClass() {

        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.demo.guru99.com/V4/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.demo.guru99.com/V4/");
    }

    @Test
    public void TC_02_Invalid_Email() {

        driver.findElement(By.name("uid")).sendKeys("invalid");
        driver.findElement(By.name("password")).sendKeys("parYmUz");
        driver.findElement(By.name("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.switchTo().alert().accept();

    }
    @Test
    public void TC_03_Invalid_Password() {

        driver.findElement(By.name("uid")).sendKeys("mngr519359");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.name("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.switchTo().alert().accept();

    }

    @Test
    public void TC_04_Login() {

        //Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
        driver.findElement(By.name("uid")).sendKeys("mngr519359");
        driver.findElement(By.name("password")).sendKeys("parYmUz");
        driver.findElement(By.name("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @Test
    public void TC_05_Logout() {

        driver.findElement(By.linkText("Log out")).click();
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
