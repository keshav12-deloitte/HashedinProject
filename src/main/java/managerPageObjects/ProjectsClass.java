package managerPageObjects;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProjectsClass extends BaseClass {
    public static Logger logger = Logger.getLogger(managerPageObjects.ProjectsClass.class);

    @FindBy(xpath = "//*[@icon='search']")
    WebElement prjctSrchBtn;

    @FindBy(xpath = "//button[contains(text(),'View Details')]")
    WebElement viewDetailsBtn;

    @FindBy(xpath = "//button[contains(text(),'Add New Vacancy')]")
    WebElement addNewVacancyBtn;

    @FindBy(name = "vacancyTechnology")
    WebElement drpTechnology;

    @FindBy(name = "vacancyDesignation")
    WebElement drpDesignation;

    @FindBy(name= "band")
    WebElement drpBand;

    @FindBy(xpath = "//button[contains(text(),'Add Vacancy')]")
    WebElement addVacancyBtn;

    @FindBy(xpath = "//*[@class='swal-text']")
    WebElement successTxt;

    @FindBy(xpath = "//*[@class='swal-button-container']/button")
    WebElement successOkBtn;

    @FindBy(xpath = "//button[contains(text(),'View Vacancies')]")
    WebElement viewVacanciesBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[1]/div[4]/div/div/table/tbody")
    WebElement webTable;

    @FindBy(xpath = "//*[@class='swal-button swal-button--confirm swal-button--danger']")
    WebElement confirmationOkBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/table/tbody")
    WebElement rqWebTable;




    public ProjectsClass() {
        PageFactory.initElements(driver, this);
    }

    public String excelData(String sheetno,int rowno,int colno) throws IOException{
        String excelFilePath = properties.getProperty("managerExcelPath"); //Locating Excel File path
        FileInputStream fis = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetno);
        String data = null;
        data = sheet.getRow(rowno).getCell(colno).getStringCellValue();
        return data;

    }

    public String handleBox(){
        Utils.implicitWait(3);
        String contentOfTerms = successTxt.getText();
        System.out.println(contentOfTerms);
        successOkBtn.click();
        Utils.implicitWait(3);
        return contentOfTerms;
    }


    public void addingVacancy() throws InterruptedException, IOException {
        JavascriptExecutor ex = (JavascriptExecutor)driver;

         Utils.implicitWait(3);
       // Thread.sleep(2000);
        //prjctSrchBtn.sendKeys("testproject"); //project name hardcoded

        Utils.implicitWait(3);
        viewDetailsBtn.click();
        Utils.implicitWait(10);

        addNewVacancyBtn.click();
        Utils.implicitWait(3);
        logger.info("taking technology from excel");
        String technology =excelData("sheet2",1,0); //taking technology from excel
        Select drpTech = new Select(drpTechnology) ;
        drpTech.selectByVisibleText(technology);
        logger.info("taking designation from excel manager sheet2");
        String designation =excelData("sheet2",1,1); //taking designation from excel manager sheet2
        Select drpRole = new Select(drpDesignation);
        drpRole.selectByVisibleText(designation);
        String band =excelData("sheet2",1,2);
        Select drpBnd = new Select(drpBand);
        drpBnd.selectByVisibleText(band);//taking band from excel manager sheet2
        Utils.implicitWait(9);
        WebElement elem2 = addVacancyBtn;

        ex.executeScript("arguments[0].click();",elem2);
        Utils.implicitWait(9);

        String actualAlert = handleBox();
        String expectedAlert = "Vacancy added successfully";
        Assert.assertEquals(expectedAlert, actualAlert);
        viewVacanciesBtn.click();
        WebDriverWait wait = new WebDriverWait(driver,30);
        Utils.takeScreenShot("Vacancy added");
        Utils.implicitWait(9);

    }

    public void removingVacancy() throws InterruptedException, IOException {

        String remTechnology =excelData("sheet2",1,0);
        String remDesignation =excelData("sheet2",1,1);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement htmltable=webTable;
        List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
        int flag=1;
        for(int rnum=0;rnum<rows.size();rnum++)
        {
            List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
            System.out.println("Number of columns:"+columns.size());

            for(int cnum=0;cnum<columns.size();cnum++)
            {
                String tech,designation;
                tech=columns.get(cnum).getText();
                logger.info("taking tech from excel manager sheet");
                if(tech.contains(remTechnology)){  //taking tech to remove
                    System.out.println(columns.get(cnum).getText());

                    designation=columns.get(cnum+1).getText();
                    System.out.println(columns.get(cnum+1).getText());
                    if(designation.contains(remDesignation)){//hardcoded
                        System.out.println(columns.get(cnum+3).getText());
                        List<WebElement> element = columns.get(cnum+2).findElements(By.xpath("//button[contains(text(),'Remove')]"));
                        //clicking remove button
                        WebElement elem = element.get(rnum);
                        js.executeScript("arguments[0].click();",elem);
                        Utils.implicitWait(9);


                        //handling confirmation dialog box
                        driver.manage().window();
                        Utils.implicitWait(9);
                        WebDriverWait wait = new WebDriverWait(driver,7);
                        confirmationOkBtn.click();
                        //handling removed vacancy success box
                        Utils.implicitWait(9);
                        String actualAlert = handleBox();
                        String expectedAlert = "Vacancy Removed Successfully";
                       // Assert.assertEquals(expectedAlert, actualAlert);
                        WebDriverWait wait2 = new WebDriverWait(driver,30);
                        Utils.takeScreenShot("Vacancy removed");

                        flag=0;
                        break;

                    }
                }

            }

            if(flag==0){
                break;
            }
        }
    }

    public void acceptEmployee(String command) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement requestTable = rqWebTable;
        List<WebElement> rowloop =requestTable.findElements(By.tagName("tr"));
        int flag=1;
        for(int rno=0;rno<rowloop.size();rno++)
        {
            List<WebElement> columnLoop=rowloop.get(rno).findElements(By.tagName("td"));
            System.out.println("Number of columns:"+columnLoop.size());
            for(int cno=0;cno<columnLoop.size();cno++)
            {
                String name;
                name=columnLoop.get(cno).getText();
                if(name.equals("sample")){ //hardcoded
                    if(command.equals("accept")){
                        List<WebElement> element = columnLoop.get(cno+3).findElements(By.xpath("//button[contains(text(),'Accept')]"));
                        WebElement elem = element.get(rno);
                        JavascriptExecutor ex = (JavascriptExecutor)driver;
                        ex.executeScript("arguments[0].click();",elem);
                        Utils.implicitWait(3);
                        Thread.sleep(2000);
                      //  handleBox();
                        Thread.sleep(1000);
                        flag=0;
                        break;
                    }
                    else if(command.equals("decline")){
                        List<WebElement> element = columnLoop.get(cno+3).findElements(By.xpath("//button[contains(text(),'Decline')]"));
                        WebElement elem = element.get(rno);
                        JavascriptExecutor ex = (JavascriptExecutor)driver;
                        ex.executeScript("arguments[0].click();",elem);
                        Utils.implicitWait(3);

                        driver.manage().window();
                        Thread.sleep(1000);
                        handleBox();
                        Thread.sleep(1000);
                        Utils.implicitWait(3);

                        flag=0;
                        break;
                    }

                }
            }
            if(flag==0){
                break;
            }
        }

    }

    public void acceptingVacancy() throws InterruptedException, IOException {

        String viewReqTech = excelData("sheet4",1,0);
        String viewReqDesignation = excelData("sheet4",1,1);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement htmltable=webTable;
        List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
        int flag=1;
        for(int rnum=0;rnum<rows.size();rnum++)
        {
            List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
            System.out.println("Number of columns:"+columns.size());

            for(int cnum=0;cnum<columns.size();cnum++)
            {
                String tech,designation;
                tech=columns.get(cnum).getText();
                if(tech.contains(viewReqTech)){  // view requests for specific technology and designation
                    System.out.println(columns.get(cnum).getText());

                    designation=columns.get(cnum+1).getText();
                    System.out.println(columns.get(cnum+1).getText());
                    if(designation.contains(viewReqDesignation)){
                        System.out.println(columns.get(cnum+2).getText());
                        List<WebElement> element = columns.get(cnum+2).findElements(By.xpath("//button[contains(text(),'View Requests')]"));
                        //clicking view request button
                        WebElement elem = element.get(rnum);
                        js.executeScript("arguments[0].click();",elem);
                        Utils.implicitWait(3);

                        acceptEmployee("accept");
                        flag=0;
                        break;

                    }
                }

            }

            if(flag==0){
                break;
            }
        }
    }

}



