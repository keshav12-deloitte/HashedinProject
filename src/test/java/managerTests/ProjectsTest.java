package managerTests;

import com.aventstack.extentreports.ExtentTest;
import managerPageObjects.LoginClass;
import managerPageObjects.ProjectsClass;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;


public class ProjectsTest extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(managerTests.ProjectsTest.class);

    public ProjectsTest() {
        super();
    }

    @BeforeClass
    public void launchUrl() throws IOException {

       // test = extent.createTest("launchUrl");     // Creating right side test
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
        LoginClass loginClass = new LoginClass();
        loginClass.fillDetails(2);
        Utils.implicitWait(3);

    }

    @Test(priority = 1)
    public static void addVacancy() throws InterruptedException, IOException {
        ProjectsClass projectsClass = new ProjectsClass();
        projectsClass.addingVacancy();
    }

    @Test(priority =2)
    public static void removeVacancy() throws InterruptedException, IOException {
        ProjectsClass projectsClass = new ProjectsClass();
        projectsClass.removingVacancy();

    }

    @Test(priority =3)
    public static void acceptVacancy() throws InterruptedException, IOException {
        ProjectsClass projectsClass = new ProjectsClass();
        projectsClass.acceptingVacancy();
    }



}
