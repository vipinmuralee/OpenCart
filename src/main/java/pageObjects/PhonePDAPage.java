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
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import resources.utils;

public class PhonePDAPage extends utils{
	
	@FindBy(xpath = "//a[contains(text(),'Phones & PDAs')]")
	private WebElement PhoneTAB;
	@FindBy(xpath = "//a[contains(text(),'product comparison')]")
	private WebElement Comparison;
	@FindBy(id = "input-sort")
	private WebElement Sort;
	@FindBy(id = "list-view")
	private WebElement ListView;
	@FindBy(id = "content")
	private WebElement SearchList;
	@FindBy(xpath = "//strong[contains(text(),'Palm Treo Pro')]")
	private WebElement PalmTreoPro;
	@FindBy(className = "tab-content")
	private WebElement Features;
	@FindBy(id = "button-cart")
	private WebElement AddCart;
	
	public PhonePDAPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void phoneTab() throws IOException, InterruptedException {
		PhoneTAB.click();
		Select sort = new Select(Sort);
		sort.selectByIndex(5);
		ListView.click();
		for (int i=1;i<4;i++) {
			Thread.sleep(2000);
			SearchList.findElement(By.xpath("//div[@class='product-layout product-list col-xs-12']["+i+"]//button[@data-original-title='Compare this Product']")).click();
		}
		Thread.sleep(2000);
		Comparison.click();
		Thread.sleep(2000);
		PalmTreoPro.click();
		Thread.sleep(5000);
	}
	
	public void saveFlatFile(String filename) throws IOException {
		File file = new File("D:\\Selenium\\SeleniumTopGear\\OpenCart\\" +filename+ ".txt");
		file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
			String prodFeature = Features.findElement(By.xpath("//div[@id=\"tab-description\"]/ul/li[5]")).getText();
			if (prodFeature != "") {
				Reporter.log("\n" +prodFeature+ " is the 5th Feature in the Product");
				bw.newLine();
		        bw.write("Product Feature : " + prodFeature);
			}
			else {
				Reporter.log("\n" +prodFeature+ " is the 5th Feature in the Product");
				bw.newLine();
		        bw.write("Product Feature : No Description");
			}
	    bw.newLine();
        bw.close();
	}

}
