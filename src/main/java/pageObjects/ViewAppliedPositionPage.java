package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;

public class ViewAppliedPositionPage extends BaseClass {

    @FindBy(xpath = "//a[normalize-space()='VIEW APPLIED POSITION']")
    WebElement viewAppliedBtn;

    public ViewAppliedPositionPage(){
        PageFactory.initElements(driver,this);
    }

}
