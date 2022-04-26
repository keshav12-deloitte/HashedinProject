package duplicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MAnagerPageduplicate {
//    WebDriver driver;
//
//    public ManagerPage(WebDriver driver) {
//        this.driver=driver;
//    }
//
//    By managertab=By.xpath("//a[@href='/admin/managers']");
//    By removeProfileloc=By.xpath("//ul[@class='profile-social-links']/child::li[2]/child::a[@href='mailto:ramesh']/parent::*/parent::*/following-sibling::button");
//    By confirmDelManagerLoc=By.xpath("//button[contains(text(),'OK')]");
//    public boolean managerTabIsExist()
//    {
//        WebElement employee = driver.findElement(managertab);
//        return (employee.isDisplayed());
//
//    }
//    public String viewAndDeleteManager() throws InterruptedException {
//        WebElement employee = driver.findElement(managertab);
//        employee.click();
//        //Thread.sleep(3000);
//        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
//        WebElement element = driver.findElement(removeProfileloc);
//        JavascriptExecutor js= (JavascriptExecutor)driver;
//        js.executeScript("arguments[0].click();", element);
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmDelManagerLoc));
//        WebElement deletingEmp=driver.findElement(confirmDelManagerLoc);
//        String deltedSuccessfully=driver.findElement(By.xpath("//div[@class='swal-text']")).getText();
//        deletingEmp.click();
//        return deltedSuccessfully;
//
//    }


}
