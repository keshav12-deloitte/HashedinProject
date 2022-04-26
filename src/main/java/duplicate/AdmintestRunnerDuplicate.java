package duplicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.AddUserProfilePage;
import pageObjects.AdminLoginPage;
import pageObjects.EmployeePage;
import pageObjects.ManagerPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdmintestRunnerDuplicate {
    static WebDriver driver;
    String projectlink = "https://hashedin-frontend-urtjok3rza-wl.a.run.app/";

    @BeforeTest
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
        driver = new ChromeDriver(); //launch browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(projectlink);//open Url
        Thread.sleep(2000);
        driver.manage().window().maximize();// maximizing Window

    }

    @Test(priority = 1)
    public void verifyAdminLoginPage() throws IOException, InterruptedException {
        AdminLoginPage login = new AdminLoginPage(driver);
        Assert.assertTrue(login.loginBtnIsExist());
        login.adminSignin(1);
        //Thread.sleep(3000);
    }
    @Test(priority = 2)
    public void verifyAdduserProfileTab() throws InterruptedException, IOException {
        AddUserProfilePage signup = new AddUserProfilePage(driver);
        Assert.assertTrue(signup.addUserProfileBtnIsExist());
        String statusOfManager=signup.adminAddingManager(1);
        Assert.assertEquals(statusOfManager,"Added successfully");
        String statusOfEmployee=signup.adminAddingEmployee(1);
        Assert.assertEquals(statusOfEmployee,"Added successfully");
        String nameofManager = signup.manageraddedSuccessfully();
        Assert.assertEquals(nameofManager, "ramesh");
        String nameofEmployee = signup.employeeaddedSuccessfully();
        Assert.assertEquals(nameofEmployee, "keshav");
    }
    @Test(priority = 3)
    public void verifyEmployeeTab() throws InterruptedException {
        EmployeePage verify = new EmployeePage(driver);
        Assert.assertTrue(verify.employeeBtnExist());
        String statusOfDelEmployee=verify.viewAndDeleteEmployee();
        Assert.assertEquals(statusOfDelEmployee,"Removed Successfully");
        //Thread.sleep(200);
    }
    @Test(priority = 4)
    public void verifyManagerTab() throws InterruptedException {
        ManagerPage verify = new ManagerPage(driver);
        Assert.assertTrue(verify.managerTabIsExist());
        String statusOfDelManager=verify.viewAndDeleteManager();
        Assert.assertEquals(statusOfDelManager,"Removed Successfully");
    }

}
