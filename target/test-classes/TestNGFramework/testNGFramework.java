package TestNGFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testNGFramework {

    private static WebDriver driver = null;

    @BeforeTest
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver","D://Setup//chromedriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    private static void login() throws IOException, InterruptedException {
        driver.get("http://localhost/opencart/");
        WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='My Account']")));
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vipin.m33@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vipin1234");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        canon();
        condition();
    }


    private static void canon() throws IOException, InterruptedException{
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Canon");//search Canon
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.ENTER);//Canon products displayed  with details
            Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='image']/a/img[@alt='Canon EOS 5D']")).click();//CLicking on Canon image
            Thread.sleep(2000);
        Select sel = new Select(driver.findElement(By.xpath("//select[@id='input-option226']")));
            sel.selectByVisibleText("Blue");
        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("2");//User changed the no of quantity to 2
        driver.findElement(By.xpath("//button[@id='button-cart']")).click();//CLicking on add to cart button
            Thread.sleep(2000);
            System.out.println((driver.findElement(By.xpath("//*[@id='product-product']/div[1]")).getText()));// success message after adding items to cart
        driver.findElement(By.xpath("//*[@id='product-product']/div[1]/a[2]")).click();//Clicking on "Shopping Cart" displayed on ribbon message
        saveFlatFile();
    }

    private static void condition() {
        String p = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]")).getText();
        String s = p.substring(1);
        System.out.println(s);
        int j = s.indexOf('.');
        StringBuffer se = new StringBuffer(s);
        se = se.delete(j,s.length());;
        int z = Integer.parseInt(String.valueOf(se));
        if (z < 200) {
            driver.findElement(By.xpath("//*[@id='content']/div[3]/div[1]/a")).click();// click on the Continue Shopping button
        }
        else {
            driver.findElement(By.cssSelector("a[title='My Account']")).click();
            driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
        }
    }

    @AfterTest
    public static void browserClose(){
        driver.quit();
    }

    private static void saveFlatFile() throws IOException {
        //store text from description to a text file.
        String time = new SimpleDateFormat("dd.mm.yyyy - hh.mm.ss").format(new Date());
        File f = new File("Vipin - TotalAmount - " + time);
        String store = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]")).getText();
        System.out.println(store);
        FileWriter fw = new FileWriter(f, true);
        fw.write(store);
        fw.flush();
        fw.close();
    }

}
