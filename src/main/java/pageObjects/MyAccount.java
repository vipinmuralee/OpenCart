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

public class MyAccount extends utils{

	@FindBy(name ="search")
	private WebElement Search;
	@FindBy(id = "content")
	private WebElement SearchList;
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement SearchButton;
	@FindBy(id = "list-view")
	private WebElement ListView;
	@FindBy(name= "category_id")
	private WebElement Categories;
	@FindBy(name = "sub_category")
	private WebElement SubCategory;
	@FindBy(id = "button-search")
	private WebElement ButSearch;
	
	public MyAccount(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void searchProduct() throws IOException {
		Search.sendKeys(getCelldata("SearchProd",0,0));
		SearchButton.click();
		ListView.click();
	}
	public void selectCategories() throws IOException {
		Select cat = new Select(Categories);
		cat.selectByIndex(10);	
		SubCategory.click();
		ButSearch.click();
		saveFlatFile("monitor_searchFlatFile");
		cat.selectByIndex(18);	
	}
	
	public void saveFlatFile(String filename) throws IOException {
		File file = new File("D:\\Selenium\\SeleniumTopGear\\OpenCart\\" +filename+ ".txt");
		file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
		int value=SearchList.findElements(By.xpath("//div[@class='product-layout product-list col-xs-12']")).size();
		for (int i=1;i<value+1;i++) {
			String Product = SearchList.findElement(By.xpath("//div[@class='product-layout product-list col-xs-12']["+i+"]//div[@class='caption']//a")).getText();
			Reporter.log("\n" +Product+ " is retrived in search");
			bw.newLine();
	        bw.write("Product " +i+ " : " + Product);
		}
	    bw.newLine();
        bw.close();
	}
}
