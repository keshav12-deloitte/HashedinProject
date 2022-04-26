package AdminFunctionalityTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.AdminLoginPage;
import resources.baseClass.BaseClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdminNegativeTest2 extends BaseClass {
    WebDriver driver;
    String projectlink = "https://hashedin-frontend-urtjok3rza-wl.a.run.app/";
    public static Logger logger = Logger.getLogger(AdminNegativeTest2.class);
    static ExtentReports extent = new ExtentReports();
    static ExtentSparkReporter spark = new ExtentSparkReporter("AdminPositiveTestCase.html");
    ExtentTest test;
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile = new File(fileWithPath);

        FileUtils.copyFile(SrcFile, DestFile);
    }

    @BeforeTest
    public void setup() throws InterruptedException {
        extent.attachReporter(spark);
        test = extent.createTest("verifyAdminLoginPage");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
        driver = new ChromeDriver(); //launch browser
        logger.info("Chrome browser Launched successfully");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(projectlink);//open Url
        logger.info("Url is Launched Successfully");
        driver.manage().window().maximize();// maximizing Window
        test.pass("Successfully launched Browser and website");

    }

    @Test(priority = 1)
    public void verifyAdminLoginPage() throws IOException, InterruptedException {
        test = extent.createTest("verifyAdminLoginPage");
        AdminLoginPage login = new AdminLoginPage(driver);
        Assert.assertTrue(login.loginBtnIsExist());
        logger.info("Login Button is Visible");
        test.log(Status.PASS, "Login Button is Visible");
        login.adminSignin(2);
        logger.info("Admin logged in Successfully");
        test.pass("Successfully verified AdminLoginPage");
    }
}
