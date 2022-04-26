package managerPageObjects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginClass extends BaseClass {

    @FindBy(xpath = "//input[@id='username']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginBtn;

    @FindBy(xpath = "//*[@class='swal-text']")
    WebElement successTxt;

    @FindBy(xpath = "//*[@class='swal-button-container']/button")
    WebElement successOkBtn;

    public LoginClass() {
        PageFactory.initElements(driver, this);
    }

    public String excelData(int rowno,int colno) throws IOException{
        String excelFilePath = properties.getProperty("managerExcelPath"); //Locating Excel File path
        FileInputStream fis = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        String data = null;

        int rowCount = sheet.getPhysicalNumberOfRows();       //Using Apache POI to fetch the data
        int columnCount = sheet.getRow(0).getLastCellNum();
        data = sheet.getRow(rowno).getCell(colno).getStringCellValue();
        return data;

    }

    public void fillDetails(int rowno) throws IOException {

        String emailValue = excelData(rowno,0);
        String passwordValue = excelData(rowno,1);
        usernameField.sendKeys(emailValue);
        Utils.implicitWait(3);
        passwordField.sendKeys(String.valueOf(passwordValue));
        loginBtn.click();
    }
    public void emptyDetails()  {

        loginBtn.click();
        Utils.implicitWait(6);
        driver.manage().window();
        Utils.implicitWait(8);
        String message = driver.findElement(By.name("password")).getAttribute("validationMessage");
        System.out.println(message);
        Utils.implicitWait(4);

    }


    public void handleBox(){

             Utils.implicitWait(3);
             String contentOfTerms = successTxt.getText();
             System.out.println(contentOfTerms);
             successOkBtn.click();
             usernameField.clear();
             passwordField.clear();
             Utils.implicitWait(2);
    }


}
