package utils;


import org.testng.annotations.DataProvider;

import config.ConfigReader;

public class TestDataProvider {

	@DataProvider(name = "loginData" , parallel = true)
	public static Object[][] loginData(){
		
		String source = ConfigReader.get("data.source");
		
		 if (source == null) {
		        throw new RuntimeException("data.source not found in config.properties");
		    }
		 
		if (source.equalsIgnoreCase("excel")) {
			
			return ExcelUtils.getExcelData(
					"testdata/login.xlsx", 
					"LoginData"
			);
			
		}else {
			
			return JsonDataReader.getJsonDataAs2DArray(
					"testdata/login.json"
					);
		}
	}
}