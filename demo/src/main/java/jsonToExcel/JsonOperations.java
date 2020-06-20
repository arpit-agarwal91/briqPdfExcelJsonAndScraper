package jsonToExcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.*; 

public class JsonOperations {

	public static void main(String[] args) {
		try {
			StringBuilder result = new StringBuilder();
		      URL url = new URL("https://data.sfgov.org/resource/p4e4-a5a7.json");
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		      
		      Workbook workbook = new XSSFWorkbook();
		      FileOutputStream outputStream = null;
	    	  Sheet sheet = workbook.createSheet("JsonToExcel");
		      JSONArray jsonArray = new JSONArray(result.toString());
		      for(int i=0;i<jsonArray.length();i++) {
		    	  JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
		    	  Iterator itr = jsonObject.keys();
		    	  if(i==0) {
			    	  Row header = sheet.createRow(0);
			    	  Row data = sheet.createRow(1);
			    	  
			    	  int j=0;
			    	  while(itr.hasNext()) {
			    		  String column = (String)itr.next();
			    		  Cell headerCell = header.createCell(j);
			    		  headerCell.setCellValue(column);
			    		  Cell dataCell = data.createCell(j++);
			    		  dataCell.setCellValue(jsonObject.get(column).toString());
			    	  }
		    	  }
		    	  else {
		    		  Row data = sheet.createRow(sheet.getLastRowNum()+1);
		    		  int j=0;
		    		  while(itr.hasNext()) {
		    			  if(sheet.getRow(0).getCell(j)!=null) {
		    				  String column = sheet.getRow(0).getCell(j).getStringCellValue();
				    		  Cell dataCell = data.createCell(j++);
				    		  if(jsonObject.has(column)) {
				    			  dataCell.setCellValue(jsonObject.get(column).toString());
				    		  }
		    			  }
		    			  itr.next();
		    		  }
		    	  }
		    	  	    	  
		      }
		      File currDir = new File(".");
	    	  String path = currDir.getAbsolutePath();
	    	  String fileLocation = path.substring(0, path.length() - 1) + "temp1.xlsx";
	    	   
	    	  outputStream = new FileOutputStream(fileLocation);
	    	  workbook.write(outputStream);
	    	  workbook.close();
	    	  outputStream.close();
		      
		} catch (Exception e) {
		    e.printStackTrace();
		} 

	}

}
