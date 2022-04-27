package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.util.List;

public class addBand extends BaseClass {
    String addField="//*[@id=\"root\"]/div/div/div[2]/div[2]/form/input";
    String addBtn="//*[@id=\"root\"]/div/div/div[2]/div[2]/form/div/button";
    String addSucc="/html/body/div[2]/div/div[1]";
    String popBtn="/html/body/div[2]/div/div[2]/div/button";
    String table="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr";
    String beforeXpath="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[";
    String afterXpath="]/td[2]";
    String beforeXpath1="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[";
    String afterXpath1="]/td[3]/span";
    String deleteSucc="/html/body/div[2]/div/div[1]";
    String deletePop="/html/body/div[2]/div/div[2]/div/button";
    String samePop="/html/body/div[2]/div/div[2]";
    String sameOk="/html/body/div[2]/div/div[3]/div/button";
    String deletemsg;
    String emptypath="//*[@id=\"root\"]/div/div/div[1]/div/div/ul/li[7]/a";


    String Expected="php30";
    String successMsg="Band Added";
    String deleteMsg="Band Removed successfully";
    String sameMsg="You can't add the Band with same name";
    String emptyName="band";
    String Validate="validationMessage";
    String click="arguments[0].click();";

    public String addNewBand() throws InterruptedException {

        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        driver.findElement(By.xpath(addBtn)).click();
        Thread.sleep(2000);
        Utils.implicitWait(20);
        WebElement addedSuccess=driver.findElement(By.xpath(addSucc));
        Utils.takeScreenShot("Band added");
        Utils.implicitWait(20);
        String msg=addedSuccess.getText();
        Thread.sleep(2000);
        driver.findElement(By.xpath(popBtn)).click();
        Utils.refreshPage();
        return msg;
    }
    public String deleteBand() throws InterruptedException {

        String project;
        String path;
        List<WebElement> rowVals = driver.findElements(By.xpath(table));
        int row=rowVals.size();
        for(int i=1;i<=row;i++){
            String actualXpath=beforeXpath+i+afterXpath;
            WebElement element=driver.findElement(By.xpath(actualXpath));
            project=element.getText();
            if(project.equals(Expected)){
                path=beforeXpath1+i+afterXpath1;
                WebElement element1 = driver.findElement(By.xpath(path));
                JavascriptExecutor js= (JavascriptExecutor)driver;
                js.executeScript(click, element1);
                Thread.sleep(2000);
                Utils.implicitWait(10);
                WebElement deleteSuccess=driver.findElement(By.xpath(deleteSucc));
                Utils.takeScreenShot("Band deleted");
                Utils.implicitWait(20);
                deletemsg=deleteSuccess.getText();
                Thread.sleep(2000);
                Utils.implicitWait(10);
                driver.findElement(By.xpath(deletePop)).click();
                Utils.refreshPage();
            }
            else{
                continue;
            }
        }
        return deleteMsg;
    }
    public String emptyBand() throws InterruptedException {
        WebElement bandBtn=driver.findElement(By.xpath(emptypath));
        bandBtn.click();
        driver.findElement(By.xpath(addField)).click();
        Utils.implicitWait(10);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.takeScreenShot("Band empty");
        Thread.sleep(2000);
        String message=driver.findElement(By.name(emptyName)).getAttribute(Validate);
        Thread.sleep(2000);
        Utils.implicitWait(10);
        Utils.refreshPage();
        return message;
    }

    public String sameBand() throws InterruptedException {
        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        Utils.implicitWait(10);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.takeScreenShot("Same Band");
        Thread.sleep(2000);
        WebElement samePopUp=driver.findElement(By.xpath(samePop));
        Utils.implicitWait(20);
        String msg=samePopUp.getText();
        Thread.sleep(2000);
        Utils.implicitWait(10);
        driver.findElement(By.xpath(sameOk)).click();
        Utils.refreshPage();
        return msg;
    }
}
