package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class utils {
	public Properties prop;
	public String getReportConfigPath() throws IOException{
		prop= new Properties();
		FileInputStream fis=new FileInputStream("D:\\Selenium\\SeleniumTopGear\\OpenCart\\configs\\Configuration.properties");
		prop.load(fis);
		String reportConfigPath = prop.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public void verifyValue(WebElement strObj, String strValue) {
		String divText = strObj.getText();
		if(divText.contains(strValue)) {
			Reporter.log("\n The Text "+strValue+" is displayed in the page");
		} else {
		    Reporter.log("\n Expected: "+strValue+" Actual: "+divText);
		}
	}
	
	public void verifyText(WebElement strObj, String strValue) {
		String divText = strObj.getAttribute("value");
		if(divText.contains(strValue)) {
			Reporter.log("\n The Text "+strValue+" is displayed in the page");
		} else {
			Reporter.log("\n Expected: "+strValue+" Actual: "+divText);
		}
	}

	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileInputStream fis;
	
	public String getCelldata(String excelData, int rownum,int col) throws IOException	{
			fis =new FileInputStream("H:\\OpenCart\\exceldata\\" +excelData+ ".xlsx");
			wb=new XSSFWorkbook(fis);
			sheet=wb.getSheet("Data");
			row=sheet.getRow(rownum);
			cell = sheet.getRow(rownum).getCell(col);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
	}
	
}
