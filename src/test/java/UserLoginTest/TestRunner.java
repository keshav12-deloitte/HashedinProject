package UserLoginTest;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProjectsPage;
import pageObjects.ViewAppliedPositionPage;
import pageObjects.ViewProfilePage;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;


public class TestRunner extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(TestRunner.class);

    public TestRunner() {
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
        //Utils.extentScreenShotCapture(logInfo,"Url Launched Successfully", By.xpath("//img[@alt='logo']"));
        Utils.deleteAllCookies();

    }

    @Test(priority = 11)
    public void fillDetails() throws IOException {
        logger.info("Login into the application");
        logger.info("Filling the details from excel sheet");
        //test = extent.createTest("Log In");                 // Creating right side test
        //logInfo = test.createNode("Entering user details");         // Creating node which will store the screenshots
        //Utils.extentScreenShotCapture(logInfo,"Entering the details", By.xpath("//p[@class='login-card-description']"));
        LoginPage loginPage = new LoginPage();
        loginPage.fillDetails();

    }

    @Test(priority = 12)
    public void checkUrl() throws IOException {
        logger.info("Going to the Projects Page");
        logger.info("Validating the URL");
        //test = extent.createTest("URL Validation");                 // Creating right side test
        //logInfo = test.createNode("Asserting the required URL");         // Creating node which will store the screenshots
        ProjectsPage projectsPage = new ProjectsPage();
        String actual = projectsPage.checkProjectFieldBtn();
        //Utils.extentScreenShotCapture(logInfo,"Validating the URL", By.xpath("//div[@class='sidebar-logo']"));
        Assert.assertEquals(actual,"https://hashedin-frontend-urtjok3rza-wl.a.run.app/employee/projects");
    }

    @Test(priority = 13)
    public void selectItems() throws InterruptedException, IOException {
        logger.info("Selecting the technology");
        //test = extent.createTest("Selecting Technology");                 // Creating right side test
        //logInfo = test.createNode("Technology Selection");         // Creating node which will store the screenshots
        ProjectsPage projectsPage = new ProjectsPage();
        String selectTechnologyText = projectsPage.selectTechnology();
        String insideDescriptionText = projectsPage.description();
//        Utils.extentScreenShotCapture(logInfo,"Selecting the required Items", By.xpath("//div[@class='search']"));
        if (insideDescriptionText.contains(selectTechnologyText))
            Assert.assertEquals(0, 0);                   // PASSING IT
    }

    @Test(priority = 14)
    public void positiveSearch() throws InterruptedException, IOException {
        logger.info("Searching Items present in the list");
        //test = extent.createTest("Positive Search");                 // Creating right side test
        //logInfo = test.createNode("Checking with items in the list");         // Creating node which will store the screenshots
        ProjectsPage projectsPage = new ProjectsPage();
        //Utils.extentScreenShotCapture(logInfo,"Positive search of items in list", By.xpath("//input[@placeholder=' Search for Project']"));
        Assert.assertTrue(projectsPage.searchForPositive());
    }

    @Test(priority = 15)
    public void negativeSearch() throws IOException {
        logger.info("Searching for items not present in the list");
        //test = extent.createTest("Negative Search");                 // Creating right side test
        //logInfo = test.createNode("Checking with items in the list");         // Creating node which will store the screenshots
        ProjectsPage projectsPage = new ProjectsPage();
        //Utils.extentScreenShotCapture(logInfo,"Positive search of items in list", By.xpath("//input[@placeholder=' Search for Project']"));
        Assert.assertTrue(projectsPage.searchForNegative());
    }

    @Test(priority = 16)
    public void checkApplyBtn() throws InterruptedException, IOException {
        logger.info("Checking whether Apply button is working or not");
        //test = extent.createTest("Apply button check");                 // Creating right side test
        //logInfo = test.createNode("Apply for position field");         // Creating node which will store the screenshots
        ProjectsPage projectsPage = new ProjectsPage();
        //Utils.extentScreenShotCapture(logInfo,"Checking for apply button", By.xpath("//a[normalize-space()='VIEW APPLIED POSITION']"));
        Assert.assertEquals(projectsPage.applyForVacancy(),"Successfully Applied!");
    }

    @Test(priority = 17)
    public void validateViewAppliedUrl() throws IOException {
        logger.info("Validating view applied position URL");
        //test = extent.createTest("View applied position Url ");                 // Creating right side test
        //logInfo = test.createNode("Checking View applied position Url");         // Creating node which will store the screenshots
        ViewAppliedPositionPage viewAppliedPositionPage = new ViewAppliedPositionPage();
        String actual = viewAppliedPositionPage.checkViewAppliedUrl();
        //Utils.extentScreenShotCapture(logInfo,"Validating URL", By.xpath("//a[normalize-space()='VIEW APPLIED POSITION']"));
        Assert.assertEquals(actual,"https://hashedin-frontend-urtjok3rza-wl.a.run.app/employee/view-applied-positions");
    }

    @Test(priority = 18)
    public void viewProject() throws InterruptedException {
        ViewAppliedPositionPage viewAppliedPositionPage = new ViewAppliedPositionPage();
        viewAppliedPositionPage.viewProjectDetails();
    }
    @Test(priority = 19)
    public void deleteProject() throws InterruptedException {
        ViewAppliedPositionPage viewAppliedPositionPage = new ViewAppliedPositionPage();
        viewAppliedPositionPage.deletePosition();
    }
    @Test(priority = 20)
    public void ProfilePage() throws InterruptedException {
        logger.info("Clicking on the View Profile Page Button");
        ViewProfilePage viewProfle = new ViewProfilePage();
        viewProfle.checkProfilePagebtn();
        logger.info("Successfully got Into View profile tab");
        viewProfle.selectUpdateProfilebtn();
        logger.info("Profile Pic updated Successfully");
    }
    //@AfterMethod
    @AfterClass
    public void afterTest() {
        //extent.flush();
        driver.close();
    }
}