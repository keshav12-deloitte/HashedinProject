package pageObjects;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;

public class AdminLoginPage {

    public AdminLoginPage(WebDriver driver){
        this.driver=driver;
    }
    WebDriver driver;
    By username = By.xpath("//input[@id='username']");
    By password = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//input[@id='login']");

    public boolean loginBtnIsExist(){
        WebElement loginButton = driver.findElement(loginBtn);
        return (loginButton.isDisplayed());
    }

    public void adminSignin(int rowToChoose) throws IOException, InterruptedException {
        String excelFilePath = "src/main/java/resources/dataSheets/AdminLoginDetails.xlsx";//path of the excel File
        FileInputStream fis = new FileInputStream(excelFilePath);//reading the file by FileInputStream
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//opening workbook
        XSSFSheet sheet = workbook.getSheet("Sheet1");//opening sheet in workbook
        XSSFRow row = null;//initializing rows
        XSSFCell cell = null;//initializing columns
        String userName = null;//reading firstname from excel and storing in String variable
        String passWord = null;//reading lastname from excel and storing in String variable

        for (int i = rowToChoose; i <= rowToChoose; i++)//reading from row i
        {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++)//reading from column j
            {
                cell = row.getCell(j);
                if (j == 0) {
                    userName = cell.getStringCellValue();
                }
                if (j == 1) {
                    passWord = cell.getStringCellValue();

                }

            }
        }
        driver.findElement(username).sendKeys(userName);
        driver.findElement(password).sendKeys(passWord);
        WebElement loginButton = driver.findElement(loginBtn);
        loginButton.click();

    }

}
