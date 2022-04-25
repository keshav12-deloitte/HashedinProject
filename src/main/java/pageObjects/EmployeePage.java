package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.time.Duration;

public class EmployeePage extends BaseClass {
    WebDriver driver;

    public EmployeePage(WebDriver driver) {
        this.driver=driver;
    }
    By employeetab = By.xpath("//a[contains(text(),'Employees')]");
    By removeProfileloc=By.xpath("//ul[@class='profile-social-links']/child::li[2]/child::a[@href='mailto:"+AddUserProfilePage.emailOfEmployee+"']/parent::*/parent::*/following-sibling::button");
    By confirmDelEmpLoc=By.xpath("//button[contains(text(),'OK')]");
    public boolean employeeBtnExist(){
        WebElement employee = driver.findElement(employeetab);
        return (employee.isDisplayed());
    }
    public String  viewAndDeleteEmployee() throws InterruptedException {
        WebElement employee = driver.findElement(employeetab);
        employee.click();
        WebElement element = driver.findElement(removeProfileloc);
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmDelEmpLoc));
        WebElement deletingEmp=driver.findElement(confirmDelEmpLoc);
        String deltedSuccessfully=driver.findElement(By.xpath("//div[@class='swal-text']")).getText();
        deletingEmp.click();
        return deltedSuccessfully;

    }

}
