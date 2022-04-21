package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;

import java.io.File;
public class ViewProfilePage extends BaseClass {

    @FindBy(xpath = "//a[normalize-space()='VIEW PROFILE']")
    WebElement profileField;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/input")
    WebElement updateProfileBtn;
    @FindBy(xpath = "/html/body/div[3]/div/div/div[3]/button")
    WebElement confirmPic;

    public ViewProfilePage() {
        PageFactory.initElements(driver,this);
    }

    public String checkProfilePagebtn(){
        profileField.click();
        String profileUrl = driver.getCurrentUrl();
        return profileUrl;
    }

    public void selectUpdateProfilebtn() {
        String profilepicpath = properties.getProperty("profilePic");
        File file = new File(new File(profilepicpath).getAbsolutePath());
        String path = String.valueOf(file);
        updateProfileBtn.sendKeys(path);
        confirmPic.click();
    }
}
