package managerTests;

import com.aventstack.extentreports.ExtentTest;
import managerPageObjects.LoginClass;
import managerPageObjects.ViewProfileClass;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;

public class ViewProfileTest extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(UserLoginTest.TestRunner.class);

    public ViewProfileTest() {
        super();
    }

    //@BeforeMethod
    @BeforeClass
    public void launchUrl() throws IOException {

        //test = extent.createTest("launchUrl");     // Creating right side test
        //logInfo = test.createNode("Launching the URL");    // Creating node which will store the screenshots
        driver = initializeDriver();
        logger.info("Driver is initialized!!");

        String urlName = properties.getProperty("url");

        driver.get(urlName);
        logger.info("Navigating to the required url!!");
        Utils.implicitWait(2);
        Utils.maximizePage();
        //Utils.extentScreenShotCapture(logInfo,"Url Launched Successfully", By.xpath("//div[@class='container top-space-50']"));
        Utils.deleteAllCookies();
        LoginClass loginClass = new LoginClass();
        loginClass.fillDetails(2);
        Utils.implicitWait(3);

    }
    @Test(priority = 29)
    public void ProfilePage() throws IOException {
        logger.info("Clicking on the View Profile Page Button");
        ViewProfileClass viewProfle = new ViewProfileClass();
        viewProfle.checkProfilePagebtn();
        logger.info("Successfully got Into View profile tab");
        viewProfle.selectUpdateProfilebtn();
        logger.info("Profile Pic updated Successfully");
        Utils.implicitWait(20);
        viewProfle.logoutbtn();
        logger.info("click on manager button");
        Utils.implicitWait(20);
        viewProfle.Logout();
        Utils.implicitWait(20);
        logger.info("logout successfully");
    }
    //@AfterMethod
    @AfterClass
    public void afterTest() {
        //extent.flush();
        driver.close();
    }
}
