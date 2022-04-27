package AdminFunctionalityTest;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.*;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;


public class TestOperation extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(TestOperation.class);

    public TestOperation() {
        super();
    }

    //@BeforeMethod
    @BeforeClass
    public void launchUrl() throws IOException {

//        test = extent.createTest("launchUrl");     // Creating right side test
//        logInfo = test.createNode("Launching the URL");    // Creating node which will store the screenshots
        driver = initializeDriver();
        logger.info("Driver is initialized!!");

        String urlName = properties.getProperty("url");
        driver.get(urlName);
        logger.info("Navigating to the required url!!");
        Utils.implicitWait(2);
        Utils.maximizePage();
//        Utils.extentScreenShotCapture(logInfo,"Url Launched Successfully", By.xpath("//div[@class='container top-space-50']"));
        Utils.deleteAllCookies();

    }

    @Test(priority = 5)
    public void loginOperation() throws IOException {
        //logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginPageAdmin2 loginPage = new LoginPageAdmin2();
        loginPage.fillDetails();
//        ViewProject viewProject=new ViewProject();
//        viewProject.SearchProject(driver);
    }

    @Test(priority=6)
    public void ViewProject() throws InterruptedException {
        //this.test = extent.createTest("verifyViewProjectTab");
        Project project=new Project();
        String msg=project.viewProject(driver);
        System.out.println(msg);
        Assert.assertEquals(msg,"Delete Successfull");
//        logger.info("Newly Added project is removed/deleted Successfully");
        //this.test.pass("Successfully verified viewProjectTab");

    }

    @Test(priority = 7)
    public void technologyOperations() throws InterruptedException {
        //this.test = extent.createTest("verifyaddTechnologyTab");
        addTechnology addtechnology=new addTechnology();
        String message=addtechnology.emptyTechnology();
        Utils.implicitWait(20);
        Assert.assertEquals(message,"Please fill out this field.");
        System.out.println(message);
        String msg=addtechnology.addNewTechnology();
        Utils.implicitWait(20);
        Assert.assertEquals(msg,"Technology Added");
        System.out.println(msg);
        String samemsg=addtechnology.sameTechnology();
        Utils.implicitWait(20);
        Assert.assertEquals(samemsg,"You can't add the Technology with same name");
        System.out.println(samemsg);
        String deletemsg=addtechnology.deleteTechnology();
        Utils.implicitWait(20);
        Assert.assertEquals(deletemsg,"Technology Deleted successfull");
        System.out.println(deletemsg);
    }

    @Test(priority=8)
    public void bandOperations() throws InterruptedException {
        //this.test = extent.createTest("verifyaddBandTab");
        addBand addband=new addBand();
        String message=addband.emptyBand();
        Utils.implicitWait(20);
        Assert.assertEquals(message,"Please fill out this field.");
        System.out.println(message);
        String msg=addband.addNewBand();
        Utils.implicitWait(20);
        Assert.assertEquals(msg,"Band Added");
        System.out.println(msg);
        String samemsg=addband.sameBand();
        Utils.implicitWait(20);
        Assert.assertEquals(samemsg,"You can't add the Band with same name");
        System.out.println(samemsg);
        String deletemsg=addband.deleteBand();
        Utils.implicitWait(20);
        Assert.assertEquals(deletemsg,"Band Removed successfully");
        System.out.println(deletemsg);
    }

    @Test(priority = 9)
    public void specializationOperations() throws InterruptedException {
       // this.test = extent.createTest("verifyaddSpecializationTab");
        addSpecialization addspecialization=new addSpecialization();
        String emptymsg=addspecialization.emptyField();
        Utils.implicitWait(20);
        Assert.assertEquals(emptymsg,"Please fill out this field.");
        System.out.println(emptymsg);
        String addmsg=addspecialization.addNewSpecialization();
        Utils.implicitWait(20);
        Assert.assertEquals(addmsg,"Specialization Added Succesfully");
        System.out.println(addmsg);
        String samemsg=addspecialization.sameSpecialization();
        Utils.implicitWait(20);
        Assert.assertEquals(samemsg,"You can't add the Specialization with same name");
        System.out.println(samemsg);
        String deletemsg=addspecialization.deleteSpecialization();
        Utils.implicitWait(20);
        Assert.assertEquals(deletemsg,"Specialization delete successful");
        System.out.println(deletemsg);

    }
    @Test(priority = 10)
    public void viewProfileOperations(){
        //this.test = extent.createTest("verifyViewProfileTab");
        viewProfile viewprofile=new viewProfile();
        Utils.implicitWait(20);
        viewprofile.profileOption();
    }
    //@AfterMethod
    @AfterClass
    public void afterTest() {
        //extent.flush();
        driver.close();
    }
}
