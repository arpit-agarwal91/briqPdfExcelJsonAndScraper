package excelOps;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExcelReader {

	public void reader() throws IOException {
		FileInputStream file = new FileInputStream(new File("C:\\Users\\arpit\\Downloads\\demo\\demo\\leads.xlsx"));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		JSONArray array = new JSONArray();
			for(int j=1;j<sheet.getLastRowNum();j++) {
				JSONObject obj = new JSONObject();
				for(int i=0;i<sheet.getRow(0).getLastCellNum();i++) {
				if(i<sheet.getRow(j).getLastCellNum() && sheet.getRow(j).getCell(i)!=null) {
					if(sheet.getRow(j).getCell(i).getCellType()==CellType.NUMERIC) {
						obj.put(sheet.getRow(0).getCell(i).getStringCellValue(), sheet.getRow(j).getCell(i).getNumericCellValue());
					}
					else {
						obj.put(sheet.getRow(0).getCell(i).getStringCellValue(), sheet.getRow(j).getCell(i).getStringCellValue());
					}
				}
				array.put(obj);
			}
		}
		System.out.println(array);
		File jsonFile = new File("ExcelToJsonData.json");
		FileWriter writer = new FileWriter(jsonFile);
		writer.write(array.toString());
	    writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExcelReader reader = new ExcelReader();
		reader.reader();
	}

}
