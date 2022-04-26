package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import resources.utils.Utils;
import java.io.File;
import static resources.baseClass.BaseClass.driver;
import static resources.baseClass.BaseClass.properties;

public class viewProfile {

    String click="arguments[0].click();";
    String ProfileFieldBtn="//*[@id=\"root\"]/div/div/div[1]/div/div/ul/li[9]/a";
    String updateBtn="//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/input";
    String confirm="//*[@class='btn btn-primary']";

    public String checkProfilePagebtn(){
        WebElement profileField= driver.findElement(By.xpath(ProfileFieldBtn));
        profileField.click();
        String profileUrl = driver.getCurrentUrl();
        return profileUrl;
    }


    public void pofileOption() {
        String profilepicpath = properties.getProperty("profilePic");
        WebElement updateProfileBtn= driver.findElement(By.xpath(updateBtn));
        WebElement confirmPic= driver.findElement(By.xpath(confirm));
        File file = new File(new File(profilepicpath).getAbsolutePath());
        String path = String.valueOf(file);
        Utils.implicitWait(10);
        updateProfileBtn.sendKeys(path);
        Utils.implicitWait(10);
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript(click, confirmPic);
    }
}