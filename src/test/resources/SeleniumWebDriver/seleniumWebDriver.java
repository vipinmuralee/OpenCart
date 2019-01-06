package SeleniumWebDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import TestNGFramework.commons;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class seleniumWebDriver extends commons {

    public static void main(String[] args) throws InterruptedException, IOException {
        enterUrl();
        login();
        searchSort();
        productCompare();
        addtoCart();
        checkOut();
        orderHistory();
        logout();
    }

    private static void searchSort() {
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Samsung");
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.ENTER);
        Select ele = new Select (driver.findElement(By.cssSelector("select[name*=category_id]")));
        ele.selectByVisibleText("      Monitors");//selecting Monitors from the drop down
        driver.findElement(By.cssSelector("input[name=sub_category]")).click();
        driver.findElement(By.id("button-search")).click();//step5- search button clicked
        driver.findElement(By.xpath("//a[contains(@href,'path=24')]")).click();//click on phones and PDA's
        Select sort = new Select (driver.findElement(By.id("input-sort")));//sort from the price high>low
        sort.selectByVisibleText("Price (High > Low)");
    }

    private static void productCompare() throws InterruptedException, IOException {
        //  adding first three phones for comparison
        for (int i = 1; i < 4; i++){
            Thread.sleep(3000);
            WebElement product = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[" + i + "]"));
            product.findElement(By.cssSelector("button[data-original-title='Compare this Product']")).click();
        }
        //click on product compare
        driver.findElement(By.id("compare-total")).click();
        driver.findElement(By.xpath("//strong[contains(text(),'Palm Treo Pro')]")).click();
        saveFlatFile();
    }

    private static void addtoCart() throws InterruptedException {
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title=('Shopping Cart')]")).click();
    }

    private static void checkOut() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
            Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
            Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='button-shipping-address']")).click();
            Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='button-shipping-method']")).click();
            Thread.sleep(2000);
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@id='button-payment-method']")).click();
            Thread.sleep(2000);
        driver.findElement(By.id("button-confirm")).click();
            Thread.sleep(2000);
        driver.switchTo().alert().accept();
        driver.findElement(By.id("button-confirm")).click();
            Thread.sleep(2000);
        System.out.println(driver.findElement(By.cssSelector("div>h1")).getText());
            Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title=('Shopping Cart')]")).click();//navigate back to shopping cart
            Thread.sleep(2000);
        System.out.println(driver.findElement(By.cssSelector("#content > p")).getText());
    }

    private static void orderHistory() throws InterruptedException {
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Order History')]")).click();
            Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(text(),'Newsletter')]")).click();
            Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Specials')]")).click();
            Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
    }

    private static void saveFlatFile() throws IOException {
        //store text from description to a text file.
        String time = new SimpleDateFormat("dd.mm.yyyy - hh.mm.ss").format(new Date());
        File f = new File("Vipin - Feature - " + time);
        String store = driver.findElement(By.xpath("//*[@id='tab-description']/ul/li[5]")).getText();
        System.out.println(store);
        FileWriter fw = new FileWriter(f, true);
        fw.write(store);
        fw.flush();
        fw.close();
    }

}
