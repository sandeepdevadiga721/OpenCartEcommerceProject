package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getDate() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)    //need only data up to 3nd column 
			{
				logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);
			}
		}
		return logindata;
		
	}
}