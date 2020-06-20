package pdf;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

public class PdfOperations {

	public void readerPdf() throws FileNotFoundException, DocumentException {

		try {
			

			
			
			PdfReader reader = new PdfReader("C:\\Users\\arpit\\Downloads\\demo\\demo\\bids for transport.pdf");

			// pageNumber = 1
			int pages = reader.getNumberOfPages();

			String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1,new SimpleTextExtractionStrategy());
			String column1 = textFromPage.split("\n")[2];
			String column2 = textFromPage.split("\n")[3]+" "+textFromPage.split("\n")[4];
			String column3 = textFromPage.split("\n")[5].split("\t")[0]+" "+textFromPage.split("\n")[5].split("\t")[1];
			String column4 = textFromPage.split("\n")[5].split("\t")[2]+" "+textFromPage.split("\n")[5].split("\t")[3];
			String column5 = textFromPage.split("\n")[5].split("\t")[4]+" "+textFromPage.split("\n")[5].split("\t")[5]+" "+textFromPage.split("\n")[6];
			String column6 = textFromPage.split("\n")[7]+" "+textFromPage.split("\n")[8];
			String column7 = textFromPage.split("\n")[9].split("\t")[0];
			String column8 = textFromPage.split("\n")[9].split("\t")[1]+" "+textFromPage.split("\n")[10];
			String column9 = textFromPage.split("\n")[11]+" "+textFromPage.split("\n")[12];
			String column10 = textFromPage.split("\n")[13]+textFromPage.split("\n")[14]+textFromPage.split("\n")[15];
			String column11 = textFromPage.split("\n")[16]+textFromPage.split("\n")[17]+textFromPage.split("\n")[18]+textFromPage.split("\n")[19]+textFromPage.split("\n")[20];
			String column12 = textFromPage.split("\n")[21];
			Workbook workbook = new XSSFWorkbook();
			FileOutputStream outputStream = null;
			Sheet sheet = workbook.createSheet("PdfToExcel");
			Row header = sheet.createRow(0);
			Cell headerCell = header.createCell(0);
			headerCell.setCellValue(column1);
			Cell headerCell1 = header.createCell(1);
			headerCell1.setCellValue(column2);
			Cell headerCell2 = header.createCell(2);
			headerCell2.setCellValue(column3);
			Cell headerCell3 = header.createCell(3);
			headerCell3.setCellValue(column4);
			Cell headerCell4 = header.createCell(4);
			headerCell4.setCellValue(column5);
			Cell headerCell5 = header.createCell(5);
			headerCell5.setCellValue(column6);
			Cell headerCell6 = header.createCell(6);
			headerCell6.setCellValue(column7);
			Cell headerCell7 = header.createCell(7);
			headerCell7.setCellValue(column8);
			Cell headerCell8 = header.createCell(8);
			headerCell8.setCellValue(column9);
			Cell headerCell9 = header.createCell(9);
			headerCell9.setCellValue(column10);
			Cell headerCell10 = header.createCell(10);
			headerCell10.setCellValue(column11);
			Cell headerCell11 = header.createCell(11);
			headerCell11.setCellValue(column12);
			String tableData = textFromPage.split(column12)[1];
			String rows[] = tableData.split("\\n[\\d]{1,2}\\t\\s");
			sheet = setStringRowsToExcelColumnData(rows, column1, sheet);
			for(int j=2;j<pages;j++) {
				textFromPage = PdfTextExtractor.getTextFromPage(reader, j,new SimpleTextExtractionStrategy());
				tableData = textFromPage.split(column12)[1];
				rows = tableData.split("\\n[\\d]{1,2}\\t\\s");
				//System.out.println(j);
				sheet = setStringRowsToExcelColumnData(rows, column1, sheet);
			}
			
			  File currDir = new File(".");
	    	  String path = currDir.getAbsolutePath();
	    	  String fileLocation = path.substring(0, path.length() - 1) + "pdfToExcel.xlsx";
	    	  System.out.println(fileLocation);
	    	  outputStream = new FileOutputStream(fileLocation);
	    	  workbook.write(outputStream);
	    	  workbook.close();
	    	  outputStream.close();
			
			reader.close();
//			PDFParser pdfParser = new PDFParser( new RandomAccessBufferedFileInputStream("C:\\Users\\arpit\\Downloads\\demo\\demo\\bids for transport.pdf"));
//            pdfParser.parse();
//            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
//            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
//            String out = pdfTextStripper.getText(pdDocument);
//            System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static int cnt = 1;
	public Sheet setStringRowsToExcelColumnData(String rows[], String column1, Sheet sheet) {
		for(int i=1;i<rows.length;i++) {
			try {

				int j=0;
				Row data = sheet.createRow(cnt);
				//System.out.println(cnt);
				String data2 = String.valueOf(cnt++);
				String data3 = rows[i].split("MRH")[0];
				String data4 = "";
				if(rows[i].split("MRH").length>=2 && rows[i].split("MRH")[1].split("\n").length>=2) {
					data4 = ("MRH"+rows[i].split("MRH")[1].split("\n")[0]+rows[i].split("MRH")[1].split("\n")[1]).replaceAll("\t","");
					if(rows[i].split("MRH").length>2) {
						data4 = ("MRH"+rows[i].split("MRH")[rows[i].split("MRH").length-1]);
					}
				}
				else {
					if(rows[i].split("MRH").length>1) {
						data4 = ("MRH"+rows[i].split("MRH")[1].split("\n")[0]).replaceAll("\t","");
					}
					else {
						data4  = ("MRH"+rows[i].split("MRH")[0].split("\n")[0]).replaceAll("\t","");
					}
					i++;
				}
				System.out.println(rows[i].split("MRH")[1]);
				String data5 = data4.split("/")[3];
				if(data4.split("/")[3].equals("IC")) {
					data5 = "Individual Consultant";
				}
				else if(data4.split("/")[3].equals("SHP")) {
					data5 = "Shopping";
				}
				String data6 = "";
				if(data5.equals("Individual Consultant")) {
					//i++;
					data6 = rows[i].split("Consultant")[rows[i].split("Consultant").length-1].split("Box")[0];
				}
				else {
					
					data6 = rows[i].split(data5)[rows[i].split(data5).length-1].split("\\s\\t\\n")[0].split("\\t\\n")[0];
					if(data6.length()<6) {
						data6 = rows[i].split(data5)[rows[i].split(data5).length-1].split("Box")[0];
					}
				}
				String data7 = rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\n\\t")[0].split("[\\d]{1,},[\\d]{1,}")[0].split(data3)[0];
				String data8 = rows[i].split(data7)[rows[i].split(data7).length-1].split("\\t\\n")[0];
				String data9 = rows[i].split(data7)[rows[i].split(data7).length-1].split("\\t\\s")[rows[i].split(data7)[rows[i].split(data7).length-1].split("\\t\\s").length-1].split("GHS")[0].split("US$")[0];
				String data10 = rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s")[rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s").length-4];
				String data11 = rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s")[rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s").length-3];
				String data12 = rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s")[rows[i].split(data6)[rows[i].split(data6).length-1].split("\\t\\s").length-1];
				Cell dataCell = data.createCell(j++);
				dataCell.setCellValue(column1);
				Cell dataCell2 = data.createCell(j++);
				dataCell2.setCellValue(data2);
				Cell dataCell3 = data.createCell(j++);
				dataCell3.setCellValue(data3);
				Cell dataCell4 = data.createCell(j++);
				dataCell4.setCellValue(data4);
				Cell dataCell5 = data.createCell(j++);
				dataCell5.setCellValue(data5);
				Cell dataCell6 = data.createCell(j++);
				dataCell6.setCellValue(data6);
				Cell dataCell7 = data.createCell(j++);
				dataCell7.setCellValue(data7);
				Cell dataCell8 = data.createCell(j++);
				dataCell8.setCellValue(data8);
				Cell dataCell9 = data.createCell(j++);
				dataCell9.setCellValue(data9);
				Cell dataCell10 = data.createCell(j++);
				dataCell10.setCellValue(data10);
				Cell dataCell11 = data.createCell(j++);
				dataCell11.setCellValue(data11);
				Cell dataCell12 = data.createCell(j++);
				dataCell12.setCellValue(data12);
			
			}
			catch(Exception e){
				continue;
			}
		}
		
		return sheet;
	}
	

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated method stub
		PdfOperations pdfOps = new PdfOperations();
		pdfOps.readerPdf();

	}

}
