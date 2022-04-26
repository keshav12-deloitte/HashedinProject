package pageObjects;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;

public class AddUserProfilePage {
    WebDriver driver;

    public AddUserProfilePage(WebDriver driver){
        this.driver=driver;
    }
    By addUserprofileBtn = By.xpath("//a[@href='/admin/add-profile']");
    By addname = By.xpath("//input[@name='name']");
    By addusername = By.xpath("//input[@name='username']");
    By addpassword = By.xpath("//input[@name='password']");
    By addemail = By.xpath("//input[@name='email']");
    By addphonenumber = By.xpath("//input[@name='phoneNumber']");
    By addaddress = By.xpath("//input[@name='address']");
    By dropDownRole = By.xpath("//select[@name='roles']");
    By specializationDropdown = By.xpath("//select[@name='designation']");
    By bandDropdown = By.xpath("//select[@name='band']");
    By skillsDropdown = By.xpath("//input[@id='search_input']");
    By addingSkill = By.xpath("//li[contains(text(),'React JS')]");
    By submit = By.xpath("//button[contains(text(),'Add Profile')]");
    By addedbtn = By.xpath("//button[contains(text(),'OK')]");
    By employeetab = By.xpath("//a[contains(text(),'Employees')]");
    By managertab=By.xpath("//a[@href='/admin/managers']");
    public static String emailOfManager=null;
    public static String emailOfEmployee=null;
    public static String nameOfManager=null;
    public static String nameOfEmployee=null;



    public boolean addUserProfileBtnIsExist(){
        WebElement userProfileBtn = driver.findElement(addUserprofileBtn);
        return (userProfileBtn.isDisplayed());
    }

    public String adminAddingManager(int rowToChoose) throws InterruptedException, IOException {
        Thread.sleep(3000);
        WebElement userProfileBtn = driver.findElement(addUserprofileBtn);
        userProfileBtn.click();
        String excelFilePath = "src/main/java/resources/dataSheets/employeeAndManagerSignup.xlsx";//path of the excel File
        FileInputStream fis = new FileInputStream(excelFilePath);//reading the file by FileInputStream
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//opening workbook
        XSSFSheet sheet = workbook.getSheet("Sheet1");//opening sheet in workbook
        XSSFRow row = null;//initializing rows
        XSSFCell cell = null;//initializing columns
        //String name = null;//reading firstname from excel and storing in String variable
        String userName = null;//reading lastname from excel and storing in String variable
        String password = null;
        String City = null;

        int ContactNumber = 0;
        for (int i = rowToChoose; i <= rowToChoose; i++)//reading from row i
        {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++)//reading from column j
            {
                cell = row.getCell(j);
                if (j == 0) {
                    nameOfManager = cell.getStringCellValue();
                }
                if (j == 1) {
                    userName = cell.getStringCellValue();

                }
                if (j == 2) {
                    password = cell.getStringCellValue();
                }
                if (j == 3) {
                    emailOfManager = cell.getStringCellValue();

                }
                if (j == 4) {
                    ContactNumber = (int) cell.getNumericCellValue();

                }
                if (j == 5) {
                    City = cell.getStringCellValue();

                }

            }
        }
        // Thread.sleep(3000);
        driver.findElement(addname).sendKeys(nameOfManager);
        // Thread.sleep(3000);
        driver.findElement(addusername).sendKeys(userName);
        //  Thread.sleep(3000);
        if(rowToChoose==2){
            driver.findElement(addpassword).sendKeys("");
        }
        else
            driver.findElement(addpassword).sendKeys(password);


        //  Thread.sleep(3000);
        WebElement dropRole = driver.findElement(dropDownRole);
        Select Role = new Select(dropRole);
        Role.selectByVisibleText("Manager");
        //  Thread.sleep(3000);
        WebElement skills = driver.findElement(skillsDropdown);
        skills.click();
        // Thread.sleep(3000);
        WebElement addingSkills = driver.findElement(addingSkill);
        addingSkills.click();
        //   Thread.sleep(3000);
        WebElement DesignationDropdown = driver.findElement(specializationDropdown);
        Select Designation = new Select(DesignationDropdown);
        Designation.selectByVisibleText("Backend");
        //   Thread.sleep(3000);
        WebElement band = driver.findElement(bandDropdown);
        Select bandType = new Select(band);
        bandType.selectByVisibleText("B5");
        driver.findElement(addemail).sendKeys(emailOfManager);
        // Thread.sleep(3000);
        driver.findElement(addphonenumber).sendKeys(String.valueOf(ContactNumber));
        //driver.findElement(addphonenumber).sendKeys();
        //  Thread.sleep(3000);
        driver.findElement(addaddress).sendKeys(City);
        Thread.sleep(4000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Profile')]")));
        WebElement submitBtn = driver.findElement(submit);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitBtn);
        //submitBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addedbtn));
        WebElement okBtn = driver.findElement(addedbtn);
        Thread.sleep(2000);
        String managerAddedSuccessfully = driver.findElement(By.xpath("//div[@class='swal-text']")).getText();
        okBtn.click();
        return managerAddedSuccessfully;


    }

    public String adminAddingEmployee(int rowToChoose) throws InterruptedException, IOException {

        //Thread.sleep(3000);
        WebElement userProfileBtn = driver.findElement(addUserprofileBtn);
        userProfileBtn.click();
        String excelFilePath = "src/main/java/resources/dataSheets/employeeAndManagerSignup.xlsx";//path of the excel File
        FileInputStream fis = new FileInputStream(excelFilePath);//reading the file by FileInputStream
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//opening workbook
        XSSFSheet sheet = workbook.getSheet("Sheet2");//opening sheet in workbook
        XSSFRow row = null;//initializing rows
        XSSFCell cell = null;//initializing columns
        //String name = null;//reading firstname from excel and storing in String variable
        String userName = null;//reading lastname from excel and storing in String variable
        String password = null;
        String City = null;

        int ContactNumber = 0;
        for (int i = rowToChoose; i <= rowToChoose; i++)//reading from row i
        {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++)//reading from column j
            {
                cell = row.getCell(j);
                if (j == 0) {
                    nameOfEmployee = cell.getStringCellValue();
                }
                if (j == 1) {
                    userName = cell.getStringCellValue();

                }
                if (j == 2) {
                    password = cell.getStringCellValue();
                }
                if (j == 3) {
                    emailOfEmployee = cell.getStringCellValue();

                }
                if (j == 4) {
                    ContactNumber = (int) cell.getNumericCellValue();
                    //ContactNumber=cell.getStringCellValue();
                }
                if (j == 5) {
                    City = cell.getStringCellValue();

                }

            }
        }
        // Thread.sleep(3000);
        driver.findElement(addname).sendKeys(nameOfEmployee);
        //  Thread.sleep(3000);
        driver.findElement(addusername).sendKeys(userName);
        //  Thread.sleep(3000);
        if(rowToChoose==2){
            driver.findElement(addpassword).sendKeys("");
        }
        else
            driver.findElement(addpassword).sendKeys(password);
        // Thread.sleep(3000);
        WebElement dropRole = driver.findElement(dropDownRole);
        Select Role = new Select(dropRole);
        Role.selectByVisibleText("Employee");
        // Thread.sleep(3000);
        WebElement skills = driver.findElement(skillsDropdown);
        skills.click();
        Thread.sleep(3000);
        WebElement addingSkills = driver.findElement(addingSkill);
        addingSkills.click();
        //   Thread.sleep(3000);
        WebElement DesignationDropdown = driver.findElement(specializationDropdown);
        Select Designation = new Select(DesignationDropdown);
        Designation.selectByVisibleText("Backend");
        // Thread.sleep(3000);
        WebElement band = driver.findElement(bandDropdown);
        Select bandType = new Select(band);
        bandType.selectByVisibleText("B5");
        driver.findElement(addemail).sendKeys(emailOfEmployee);
        //  Thread.sleep(3000);
        driver.findElement(addphonenumber).sendKeys(String.valueOf(ContactNumber));
        //  Thread.sleep(3000);
        driver.findElement(addaddress).sendKeys(City);
        Thread.sleep(4000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Profile')]")));
        WebElement submitBtn = driver.findElement(submit);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitBtn);
        //submitBtn.click();
        Thread.sleep(3000);
        String employeeAddedSuccessfully = driver.findElement(By.xpath("//div[@class='swal-text']")).getText();
        WebElement okBtn = driver.findElement(addedbtn);
        okBtn.click();
        return employeeAddedSuccessfully;
    }

    public String employeeaddedSuccessfully() {
        WebElement employee = driver.findElement(employeetab);
        employee.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+nameOfEmployee+"')]")));
        String employeename = driver.findElement(By.xpath("//div[contains(text(),'"+nameOfEmployee+"')]")).getText();
        System.out.println(employeename);
        return employeename;
    }

    public String manageraddedSuccessfully() {
        WebElement manager = driver.findElement(managertab);
        manager.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+nameOfManager+"')]")));
        String managername = driver.findElement(By.xpath("//div[contains(text(),'"+nameOfManager+"')]")).getText();
        return managername;
    }
}
