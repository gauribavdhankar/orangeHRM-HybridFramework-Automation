package utils;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static Object[][] getExcelData(String fileName, String sheetName ){
		
		Object [][] data = null;
		
		try {
			//Load file from resources we use classloader to prevent hardcoded path issues in maven ci jar execution and jenkins
			InputStream is = ExcelUtils.class
					.getClassLoader()
					.getResourceAsStream(fileName);

			if (is == null) {
			    throw new RuntimeException("File not found in resources: " + fileName);
			}
			
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheet(sheetName);
			
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();
			
			//Dataformattre will handle all type of data so i remove toString()
			DataFormatter formatter = new DataFormatter();
			
			data = new Object[rows - 1][cols];
			
			for (int i = 1; i < rows; i++) {
				
				Row row = sheet.getRow(i);
				
				for (int j = 0; j < cols; j++) {
					
					if (row != null && row.getCell(j) != null) {
					data[i-1][j] = formatter.formatCellValue(row.getCell(j));
				} else {
					data[i - 1][j] = "";
				}
			}
		}
			
			workbook.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
