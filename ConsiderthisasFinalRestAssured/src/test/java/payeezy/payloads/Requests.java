package payeezy.payloads;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Requests {

	
	public static String req(String ttag) throws IOException {
		
		// Read i/p sheet
		FileInputStream fis = new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\FinalTest\\Payloads\\AllPayloads.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row creq=sheet.getRow(2);
		String reqs=creq.getCell(3).getStringCellValue();
		
		String reqq= reqs;
		
		return reqq;
		
		
		
	}
}
