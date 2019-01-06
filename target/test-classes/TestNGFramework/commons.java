package TestNGFramework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class commons {
    protected static WebDriver driver;

    public static void main(String[] args) throws Exception {
        enterUrl();
        login();
        logout();
    }

    private static WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver","D://Setup//chromedriver//chromedriver.exe");
        return new ChromeDriver();
    }

    protected static void enterUrl() throws InterruptedException {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost/opencart/");
    }

    protected static void login() throws InterruptedException, IOException {
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(getCelldata(0));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(getCelldata(1));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    protected static void logout() {
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
        driver.quit();
    }

    private static String getCelldata(int rownum) throws IOException {
        String path=System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(path + "//src//test//java//TestNGFramework//Login.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Data");
        XSSFCell cell = sheet.getRow(rownum).getCell(1);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }

}
