package AdminFunctionalityTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.AddUserProfilePage;
import pageObjects.AdminLoginPage;
import pageObjects.EmployeePage;
import pageObjects.ManagerPage;
import resources.baseClass.BaseClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdminNegativeTest3 extends BaseClass {

    WebDriver driver;
    String projectlink = "https://hashedin-frontend-urtjok3rza-wl.a.run.app/";
    public static Logger logger = Logger.getLogger(AdminNegativeTest3.class);
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
        System.setProperty("webdriver.chrome.driver", "C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
        driver = new ChromeDriver(); //launch browser
        logger.info("Chrome browser Launched successfully");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test = extent.createTest("Launch Browser and Website");
        driver.get(projectlink);//open Url
        logger.info("Url is Launched Successfully");
        test.pass("Successfully launched Browser and website");
        Thread.sleep(2000);
        driver.manage().window().maximize();// maximizing Window
    }

    @Test(priority = 1)
    public void verifyAdminLoginPage() throws IOException, InterruptedException {
        test = extent.createTest("verifyAdminLoginPage");
        AdminLoginPage login = new AdminLoginPage(driver);
        Assert.assertTrue(login.loginBtnIsExist());
        logger.info("Login Button is Visible");
        login.adminSignin(1);
        logger.info("Admin logged in Successfully");
        test.pass("Successfully verified AdminLoginPage");

    }

    @Test(priority = 2)
    public void verifyAdduserProfileTab() throws InterruptedException, IOException {
        test = extent.createTest("verifyAdduserProfileTab");
        AddUserProfilePage signup = new AddUserProfilePage(driver);
        Assert.assertTrue(signup.addUserProfileBtnIsExist());
        logger.info("Add User Profile button is Visibile");
        String statusOfManager = signup.adminAddingManager(3);
        Assert.assertEquals(statusOfManager, "Added successfully");
        logger.info("Manager Added Successfully");
        String statusOfEmployee = signup.adminAddingEmployee(3);
        Assert.assertEquals(statusOfEmployee, "Added successfully");
        String nameofManager = signup.manageraddedSuccessfully();
        logger.info("Employee Added Successfully");
        Assert.assertEquals(nameofManager, "Bhuvi");
        String nameofEmployee = signup.employeeaddedSuccessfully();
        Assert.assertEquals(nameofEmployee, "tripathi");
        test.pass("Successfully verified AdduserProfileTab");
    }

    @Test(priority = 3)
    public void verifyEmployeeTab() throws InterruptedException {
        test = extent.createTest("verifyEmployeeTab");
        EmployeePage verify = new EmployeePage(driver);
        Assert.assertTrue(verify.employeeBtnExist());
        logger.info("Employee Button is Visible");
        String statusOfDelEmployee = verify.viewAndDeleteEmployee();
        Assert.assertEquals(statusOfDelEmployee, "Removed Successfully");
        logger.info("Newly Added Employee is removed/deleted Successfully");
        test.pass("Successfully verified ManagerTab");
    }

    @Test(priority = 4)
    public void verifyManagerTab() throws InterruptedException {
        test = extent.createTest("verifyManagerTab");
        ManagerPage verify = new ManagerPage(driver);
        Assert.assertTrue(verify.managerTabIsExist());
        logger.info("Manager tab is Visible");
        String statusOfDelManager = verify.viewAndDeleteManager();
        Assert.assertEquals(statusOfDelManager, "Removed Successfully");
        logger.info("Newly Added Manager is removed Successfully");
        test.pass("Successfully verified ManagerTab");
    }
    @AfterTest
    public void afterTest() {
        extent.flush();
    }


}
