package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.File;

public class ViewProfilePage extends BaseClass {

    @FindBy(xpath = "//a[normalize-space()='VIEW PROFILE']")
    WebElement profileField;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/input")
    WebElement updateProfileBtn;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement confirmPic;

    public ViewProfilePage() {
        PageFactory.initElements(driver,this);
    }

    public String checkProfilePagebtn(){
        profileField.click();
        String profileUrl = driver.getCurrentUrl();
        Utils.takeScreenShot("UserProfileUpdate");
        return profileUrl;
    }

    public void selectUpdateProfilebtn() throws InterruptedException {
        String profilepicpath = properties.getProperty("profilePic");
        Utils.wait(3000);
        File file = new File(new File(profilepicpath).getAbsolutePath());
        Utils.wait(3000);
        String path = String.valueOf(file);
        Utils.wait(3000);
        updateProfileBtn.sendKeys(path);
        Utils.wait(3000);
        Utils.takeScreenShot("UserUpdatedPic");
        confirmPic.click();
    }
}
