package managerPageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.File;


public class ViewProfileClass extends BaseClass {
    @FindBy(xpath = "//a[normalize-space()='VIEW PROFILE']")
    WebElement profileField;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/input")
    WebElement updateProfileBtn;
    @FindBy(xpath = "/html/body/div[3]/div/div/div[3]/button")
    WebElement confirmPic;
    @FindBy(xpath ="//*[@ class=\"dropdown-toggle nav-link\"]")
    WebElement managerBtn;
    @FindBy(xpath ="//*[@ class=\"dropdown-item\"]")
    WebElement logout;
    public ViewProfileClass() {
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
        WebDriverWait webDriverWait = new WebDriverWait(driver, 8);
        confirmPic.click();
        WebDriverWait webDriverWait1 = new WebDriverWait(driver, 8);
    }
    public String  logoutbtn()
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 8);
        managerBtn.click();
        String btn =driver.getCurrentUrl();
        return btn;
    }
    public String Logout()
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 8);
        logout.click();
        String btm =driver.getCurrentUrl();
        return btm;
    }


}
