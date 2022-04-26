package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.baseClass.BaseClass;
import resources.utils.Utils;
import java.util.List;

public class Project extends BaseClass {
    String deleteSucc="/html/body/div[2]/div/div[1]";
    String deletePop="/html/body/div[2]/div/div[2]/div/button";
    String beforename="//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[";
    String aftername="]/td[2]";
    String beforeDelete="//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[";
    String afterDelete="]/td[5]/span";
    String table="//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/span";
    WebElement projectBtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div/ul/li[5]/a"));

    String project;
    String deleteMsg="Delete Successfull";
    String click="arguments[0].click();";
    String expected="Test Vacancy";

    public String viewProject(WebDriver driver) throws InterruptedException {
        projectBtn.click();
        String msg="";
        List<WebElement> rowVals = driver.findElements(By.xpath(table));
        int count= rowVals.size();
        System.out.println(count);
        for(int i=2;i<rowVals.size();i++){
            String actualXpath=beforename+i+aftername;
            WebElement element=driver.findElement(By.xpath(actualXpath));
            project=element.getText();
            if(project.equals(expected)){
                String deleteProject=beforeDelete+i+afterDelete;
                WebElement element1=driver.findElement(By.xpath(deleteProject));
                Utils.implicitWait(10);
                JavascriptExecutor js= (JavascriptExecutor)driver;
                js.executeScript(click, element1);
                Thread.sleep(20);
                WebElement deleteSuccess=driver.findElement(By.xpath(deleteSucc));
                Thread.sleep(20);
                msg=deleteSuccess.getText();
                Thread.sleep(2000);
                Utils.implicitWait(10);
                driver.findElement(By.xpath(deletePop)).click();
                Utils.refreshPage();
            }
        }
        return msg;
    }

}
