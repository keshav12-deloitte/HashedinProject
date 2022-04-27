package managerTests;
import managerPageObjects.LoginClass;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;


public class LoginTest extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(UserLoginTest.TestRunner.class);

    public LoginTest() {
        super();
    }

    //@BeforeMethod
    @BeforeClass
    public void launchUrl() throws IOException {

       // test = extent.createTest("launchUrl");     // Creating right side test
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

    }
    @Test(priority = 21)

    private static void invalidLogin() throws IOException, InterruptedException {
        logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginClass loginClass = new LoginClass();
        loginClass.fillDetails(1); //row 1 of excel contains wrong login credentials
        loginClass.handleBox();
    }
    @Test(priority = 22)
    public void emptyLogin() throws IOException {
        logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginClass loginClass = new LoginClass();
        loginClass.emptyDetails();
    }

    @Test(priority = 23)
    public void validLogin() throws IOException {
        logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginClass loginClass = new LoginClass();
        loginClass.fillDetails(2);
    }
    //@AfterMethod
    @AfterClass
    public void afterTest() {
        //extent.flush();
        driver.close();
    }





}