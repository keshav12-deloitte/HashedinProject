package managerPageObjects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class AddProjectClass extends BaseClass {
    @FindBy(xpath = "//a[normalize-space()='ADD NEW PROJECT']")
    WebElement addProjectField;

    @FindBy(xpath = "//input[@name='projectName']")
    WebElement prjctName;

    @FindBy(xpath = "//textarea[@name='projectDescription']")
    WebElement prjctDescription;

    @FindBy(xpath = "//input[@name ='projectStartDate']")
    WebElement prjctStartDate;

    @FindBy(xpath = "//input[@name ='projectEndDate']")
    WebElement prjctEndDate;

    @FindBy(xpath = "//*[@id='multiselectContainerReact']")
    WebElement multiSelector;

    @FindBy(xpath = "//li[contains(text(),'java')]")
    WebElement skill1;

    @FindBy(xpath = "//li[contains(text(),'React JS')]")
    WebElement skill2;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div")
    WebElement randomSide;

    @FindBy(xpath = "//button[contains(text(),'Add Project')]")
    WebElement addNewProjectBtn;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    WebElement okBtn;

    @FindBy(xpath = "//*[@class='swal-text']")
    WebElement successTxt;

    @FindBy(xpath = "//*[@class='swal-button-container']/button")
    WebElement successOkBtn;





    public AddProjectClass() {
        PageFactory.initElements(driver, this);
    }
    public void handleBox(){
        Utils.implicitWait(3);
        String contentOfTerms = successTxt.getText();
        System.out.println(contentOfTerms);
        successOkBtn.click();
        Utils.implicitWait(3);
    }
    public String excelData(int rowno,int colno) throws IOException {
        String excelFilePath = properties.getProperty("managerExcelPath"); //Locating Excel File path
        FileInputStream fis = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet3");
        String data = null;
        int numData=0;
        if(colno==0 || colno == 1){
            data = sheet.getRow(rowno).getCell(colno).getStringCellValue();
            return data;
        }else{
            numData = (int) sheet.getRow(rowno).getCell(colno).getNumericCellValue();
            data = Integer.toString(numData);
            return data;
        }

    }

    public void addingProject() throws InterruptedException, IOException {

        addProjectField.click();
        String  projectName = excelData(1,0);
        String  projectDescription= excelData(1,1);
        String  startDate = excelData(1,2);
        String  endDate = excelData(1,3);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        Utils.implicitWait(3);
        Thread.sleep(2000);
        // WebDriverWait wait = new WebDriverWait(driver,30);
        prjctName.sendKeys(projectName);
        Thread.sleep(1000);
        // WebDriverWait wait4 = new WebDriverWait(driver,30);
        Utils.implicitWait(3);
        prjctDescription.sendKeys(projectDescription);
        // Thread.sleep(2000);
        // WebDriverWait wait5 = new WebDriverWait(driver,30);

        Utils.implicitWait(3);
        prjctStartDate.sendKeys(startDate); //hardcoded
        prjctEndDate.sendKeys(endDate);
        Utils.implicitWait(3);
        js.executeScript("arguments[0].click();",multiSelector);
        Utils.implicitWait(3);
        js.executeScript("arguments[0].click();",skill1);
        Utils.implicitWait(3);
        js.executeScript("arguments[0].click();",skill2);
        Utils.implicitWait(3);
        randomSide.click();
        Utils.implicitWait(3);

        js.executeScript("arguments[0].click();",addNewProjectBtn);
        Thread.sleep(2000);
        // driver.manage().window();
        okBtn.click();
        Utils.implicitWait(6);
        Thread.sleep(2000);
        handleBox();

    }
}
