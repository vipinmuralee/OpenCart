package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import resources.utils;

public class WishListPage extends utils{
	@FindBy(id = "wishlist-total")
	private WebElement WishList;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']")
	private WebElement WishTable;
	@FindBy(xpath = "//button[@class='btn btn-link dropdown-toggle']")
	private WebElement Currency;
	@FindBy(xpath = "//button[contains(text(), '£ Pound Sterling')]")
	private WebElement Pound;
	@FindBy(xpath = "//button[contains(text(), '€ Euro')]")
	private WebElement Euro;
	@FindBy(xpath = "//button[contains(text(), '$ US Dollar')]")
	private WebElement Dollar;
	@FindBy(xpath = "//button[@class='close']")
	private WebElement Close;
	@FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement Continue;
	
	public WishListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	public void verifyWishList(String strProduct) {
		verifyValue(WishList, "Wish List (1)");
		WishList.click();

		int rowcount = WishList.findElements(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr")).size();
		for(int i=0;i<rowcount;i++)
			{
				String value=WishList.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[2]")).getText();
				if (value.contains(strProduct)) {
					Reporter.log("\n" +strProduct+ " is displayed successfully in the Table");
				}
				else {
					Reporter.log("\n Count of table exceeds expected count");
				}
			}
	}
	
	public void getCurrency() throws IOException, InterruptedException {
		Currency.click();
		Pound.click();
		saveFlatFile("Pound");
		Thread.sleep(3000);
		Currency.click();
		Euro.click();
		saveFlatFile("Euro");
		Thread.sleep(3000);
		Currency.click();
		Dollar.click();
		saveFlatFile("Dollar");
		WishTable.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(2000);
		Close.click();
		WishTable.findElement(By.xpath("//a[@class='btn btn-danger']")).click();
		Continue.click();
	}
	
	public void saveFlatFile(String curr) throws IOException {
		File file = new File("D:\\Selenium\\SeleniumTopGear\\OpenCart\\currency_flatfile.txt");
		file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
		String value=WishList.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[5]")).getText();
			Reporter.log("\n" +value+ " is displayed successfully in the Table");
	        bw.write(curr+ " : " + value);
        bw.newLine();
        bw.close();
	}
}
