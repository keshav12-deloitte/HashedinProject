package pageObjects;

import org.openqa.selenium.*;
import resources.baseClass.BaseClass;
import resources.utils.Utils;
import java.util.List;

public class addTechnology extends BaseClass {
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
    String techBtn="//*[@id=\"root\"]/div/div/div[1]/div/div/ul/li[6]/a";

    String msg;
    String Expected="Angular24";
    String successMsg="Technology Added";
    String deleteMsg="Technology Deleted successfull";
    String sameMsg="You can't add the Technology with same name";
    String emptyName="technology";
    String Validate="validationMessage";
    String click="arguments[0].click();";

    public String addNewTechnology() throws InterruptedException {
        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        Utils.implicitWait(20);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.implicitWait(20);
        WebElement addedSuccess=driver.findElement(By.xpath(addSucc));
        Thread.sleep(2000);
        Utils.implicitWait(20);
        String msg=addedSuccess.getText();
        Thread.sleep(2000);
        driver.findElement(By.xpath(popBtn)).click();
        Utils.refreshPage();
        return msg;
    }
    public String deleteTechnology() throws InterruptedException {
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
                Utils.implicitWait(20);
                JavascriptExecutor js= (JavascriptExecutor)driver;
                js.executeScript(click, element1);
                Utils.implicitWait(20);
                WebElement deleteSuccess=driver.findElement(By.xpath(deleteSucc));
                Thread.sleep(2000);
                Utils.implicitWait(20);
                msg=deleteSuccess.getText();
                Thread.sleep(2000);
                driver.findElement(By.xpath(deletePop)).click();
                Utils.refreshPage();
            }
            else{
                continue;
            }
        }
        return msg;
    }

    public String emptyTechnology() throws InterruptedException {
        WebElement technologyBtn = driver.findElement(By.xpath(techBtn));
        technologyBtn.click();
        driver.findElement(By.xpath(addField)).click();
        Utils.implicitWait(20);
        driver.findElement(By.xpath(addBtn)).click();
        Thread.sleep(2000);
        String message=driver.findElement(By.name(emptyName)).getAttribute(Validate);
        Thread.sleep(2000);
        Utils.refreshPage();
        return message;
    }

    public String sameTechnology() throws InterruptedException {
        driver.findElement(By.xpath(addField)).sendKeys(Expected);
        Utils.implicitWait(20);
        driver.findElement(By.xpath(addBtn)).click();
        Utils.implicitWait(20);
        WebElement samePopUp=driver.findElement(By.xpath(samePop));
        Thread.sleep(2000);
        Utils.implicitWait(20);
        String samemsg=samePopUp.getText();
        Thread.sleep(3000);
        driver.findElement(By.xpath(sameOk)).click();
        Utils.refreshPage();
        return samemsg;
    }
}
