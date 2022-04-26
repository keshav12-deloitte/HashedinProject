package managerPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.util.List;



public class EditProjectClass extends BaseClass {

    @FindBy(xpath = "//button[contains(text(),'View Details')]")
    WebElement viewDetailsBtn;

    @FindBy(xpath = "//button[contains(text(),'Edit Project Details')]")
    WebElement editProjectBtn;

    @FindBy(xpath = "//input[@name='projectName']")
    WebElement prjctName;

    @FindBy(xpath = "//textarea[@name='projectDescription']")
    WebElement prjctDescription;

    @FindBy(xpath = "//input[@name ='projectStartDate']")
    WebElement prjctStartDate;

    @FindBy(xpath = "//input[@name ='projectEndDate']")
    WebElement prjctEndDate;

    @FindBy(xpath = "//*[@id='multiselectContainerReact']/div[1]")
    List<WebElement> cancelIcons;

    @FindBy(xpath = "//li[contains(text(),'CSS')]")
    WebElement skill1;

    @FindBy(xpath = "//li[contains(text(),'Python')]")
    WebElement skill2;

    @FindBy(xpath = "//*[@id='root']/div/div/div[2]/div[2]/div[2]")
    WebElement randomSide;

    @FindBy(xpath = "//button[contains(text(),'Update')]")
    WebElement updateBtn;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    WebElement okBtn;

    @FindBy(xpath = "//*[@class='swal-text']")
    WebElement successTxt;

    @FindBy(xpath = "//*[@class='swal-button-container']/button")
    WebElement successOkBtn;





    public EditProjectClass() {
        PageFactory.initElements(driver, this);
    }
    public void handleBox(){
        Utils.implicitWait(3);
        String contentOfTerms = successTxt.getText();
        System.out.println(contentOfTerms);
        successOkBtn.click();
        Utils.implicitWait(3);
    }
    public void editingProject() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        viewDetailsBtn.click();
        Utils.implicitWait(3);
        editProjectBtn.click();
        Utils.implicitWait(3);
        Thread.sleep(2000);
       // WebDriverWait wait = new WebDriverWait(driver,30);

        prjctName.clear();
        Thread.sleep(1000);
       // WebDriverWait wait2 = new WebDriverWait(driver,30);
        Utils.implicitWait(3);

        prjctName.sendKeys("testprooo");
        Thread.sleep(1000);
       // WebDriverWait wait3 = new WebDriverWait(driver,30);
        Utils.implicitWait(3);
        prjctDescription.clear();
        Thread.sleep(1200);
       // WebDriverWait wait4 = new WebDriverWait(driver,30);
        Utils.implicitWait(3);
        prjctDescription.sendKeys("this is edited now this is edited now this is edited now");
       // Thread.sleep(2000);
       // WebDriverWait wait5 = new WebDriverWait(driver,30);
        prjctStartDate.clear();
        Utils.implicitWait(3);
        prjctStartDate.sendKeys("04-25-2022"); //hardcoded

        prjctEndDate.clear();
        prjctEndDate.sendKeys("05-14-2022");
        Utils.implicitWait(3);

        for(int i=0;i<cancelIcons.size();i++){

            List<WebElement> imgs = cancelIcons.get(i).findElements(By.tagName("img"));
            for (int j=0;j<imgs.size();j++){
               WebElement em = imgs.get(i);
               js.executeScript("arguments[0].click();",em);
            }
        }
        Utils.implicitWait(3);
        js.executeScript("arguments[0].click();",skill1);
        Utils.implicitWait(3);
        js.executeScript("arguments[0].click();",skill2);
        Utils.implicitWait(3);
        randomSide.click();
        Utils.implicitWait(3);

        js.executeScript("arguments[0].click();",updateBtn);
        Thread.sleep(2000);
       // driver.manage().window();
        okBtn.click();
        Utils.implicitWait(6);
        Thread.sleep(2000);
        handleBox();

    }
}
