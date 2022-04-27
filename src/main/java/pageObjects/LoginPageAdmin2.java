package pageObjects;

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
import java.util.List;

public class LoginPageAdmin2 extends BaseClass {

    @FindBy(xpath = "//input[@id='username']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginBtn;

    public LoginPageAdmin2() {
        PageFactory.initElements(driver, this);
    }

    public void fillDetails() throws IOException {
        String excelFilePath = properties.getProperty("adminExcelPath"); //Locating Excel File path
        FileInputStream fis = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        String emailValue = null;
        String passwordValue = null;

        int rowCount = sheet.getPhysicalNumberOfRows();       //Using Apache POI to fetch the data
        int columnCount = sheet.getRow(0).getLastCellNum();
        for (int row = 1; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (column == 0)
                    emailValue = sheet.getRow(row).getCell(column).getStringCellValue();
                if (column == 1)
                    passwordValue = sheet.getRow(row).getCell(column).getStringCellValue();
            }
        }


        usernameField.sendKeys(emailValue);
        Utils.implicitWait(3);
        passwordField.sendKeys(String.valueOf(passwordValue));
        loginBtn.click();

    }
}
