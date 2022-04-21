package UserLoginTest;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ViewProfilePage;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;


public class TestRunner extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(TestRunner.class);

    public TestRunner() {
        super();
    }

    @BeforeClass
    public void launchUrl() throws IOException {

        test = extent.createTest("launchUrl");     // Creating right side test
        logInfo = test.createNode("Launching the URL");    // Creating node which will store the screenshots
        driver = initializeDriver();
        logger.info("Driver is initialized!!");

        String urlName = properties.getProperty("url");
        driver.get(urlName);
        logger.info("Navigating to the required url!!");
        Utils.implicitWait(2);
        Utils.maximizePage();
        //Utils.extentScreenShotCapture(logInfo,"Url Launched Successfully", By.xpath("//div[@class='container top-space-50']"));
        Utils.deleteAllCookies();

    }

    @Test(priority = 1)
    public void fillDetails() throws IOException {
        logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginPage loginPage = new LoginPage();
        loginPage.fillDetails();
    }
    @Test(priority = 2)
    public void ProfilePage() throws IOException {
        logger.info("Clicking on the View Profile Page Button");
        ViewProfilePage viewProfle = new ViewProfilePage();
        viewProfle.checkProfilePagebtn();
        logger.info("Successfully got Into View profile tab");

        viewProfle.selectUpdateProfilebtn();
        logger.info("Profile Pic updated Successfully");
    }


}
