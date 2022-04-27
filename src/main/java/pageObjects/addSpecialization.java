package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import resources.baseClass.BaseClass;
import resources.utils.Utils;
import java.util.List;

public class addSpecialization extends BaseClass {

    String addField="//*[@id=\"root\"]/div/div/div[2]/div[2]/form/input";
    String addBtn="//*[@id=\"root\"]/div/div/div[2]/div[2]/form/div/button";
    String addSucc="/html/body/div[2]/div/div[1]";
    String popBtn="/html/body/div[2]/div/div[2]/div/button";
    String table="//*[@id='root']/div/div/div[2]/div[2]/div/div/table/tbody/tr";
    String beforeXpath="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[";
    String afterXpath="]/td[2]";
    String beforeXpath1="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[";
    String afterXpath1="]/td[3]/span";
    String deleteSucc="/html/body/div[2]/div/div[1]";
    String deletePop="/html/body/div[2]/div/div[2]/div/button";
    String samePop="/html/body/div[2]/div/div[2]";
    String sameOk="/html/body/div[2]/div/div[3]/div/button";
    String Btn="//*[@id='root']/div/div/div[1]/div/div/ul/li[8]";



    String deletemsg;
    String Expected="php20";
    String successMsg="Specialization Added Succesfully";
    String deleteMsg="Specialization delete successful";
    String sameMsg="You can't add the Specialization with same name";
    String emptyName="designation";
    String Validate="validationMessage";
    String click="arguments[0].click();";

    public String addNewSpecialization() throws InterruptedException {

        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        Utils.implicitWait(10);
        driver.findElement(By.xpath(addBtn)).click();
        Thread.sleep(2000);
        Utils.implicitWait(20);
        WebElement addedSuccess=driver.findElement(By.xpath(addSucc));
        Utils.takeScreenShot("Specialization added");
        Utils.implicitWait(20);
        String addmsg=addedSuccess.getText();
        Thread.sleep(1500);
        driver.findElement(By.xpath(popBtn)).click();
        Utils.refreshPage();
        return  addmsg;
    }
    public String deleteSpecialization() throws InterruptedException {
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
                Utils.takeScreenShot("Specialization deleted");
                Thread.sleep(2000);
                Utils.implicitWait(10);
                WebElement deleteSuccess=driver.findElement(By.xpath(deleteSucc));
                Utils.implicitWait(20);
                deletemsg=deleteSuccess.getText();
                Thread.sleep(1500);
                driver.findElement(By.xpath(deletePop)).click();
                Utils.refreshPage();
            }
            else{
                continue;
            }
        }
        return deletemsg;
    }
    public String emptyField() throws InterruptedException {
        WebElement SpecializationBtn= driver.findElement(By.xpath(Btn));
        SpecializationBtn.click();
        driver.findElement(By.xpath(addField)).click();
        Utils.implicitWait(10);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.takeScreenShot("Empty Specialization");
        Thread.sleep(2000);
        String message=driver.findElement(By.name(emptyName)).getAttribute(Validate);
        Thread.sleep(1500);
        Utils.refreshPage();
        return message;
    }

    public String sameSpecialization() throws InterruptedException {
        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        Utils.implicitWait(10);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.takeScreenShot("Same Specialization");
        Utils.implicitWait(10);
        WebElement samePopUp=driver.findElement(By.xpath(samePop));
        Thread.sleep(2000);
        Utils.implicitWait(20);
        String samemsg=samePopUp.getText();
        Thread.sleep(2000);
        driver.findElement(By.xpath(sameOk)).click();
        Utils.refreshPage();
        return samemsg;
    }
}
