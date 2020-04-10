package payeezy.datadriven;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payeezy.payloads.*;

public class PostCC_Void {
	
	String tid,ttag;
	
	@Test
	public void Post() throws IOException {

		// Read i/p sheet
		FileInputStream fis = new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\FinalTest\\Payloads\\AllPayloads.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		// Find Endpoint
		XSSFRow Endpoint = sheet.getRow(0);
		String FinalEndpoint = Endpoint.getCell(2).getStringCellValue();
		RestAssured.baseURI = FinalEndpoint;
		System.out.println("Hi");	
		
		// Creating Header Array List
		Header apikey = new Header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
		Header token = new Header("token", "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
		Header ctype = new Header("Content-type", "application/json");
		List<Header> list = new ArrayList<Header>();
		list.add(apikey);
		list.add(token);
		list.add(ctype);
		Headers hdr = new Headers(list);

		// Loop
		int row = sheet.getLastRowNum();
		System.out.println("Total no Of req found in " + workbook.getSheetName(0) + " are: " + row);

		for (int i = 2; i <= row; i++) {

			// Find Req
			Row reqrow = sheet.getRow(i);
			String Req = reqrow.getCell(0).getStringCellValue();

			// Send Request
			System.out.println("Posting Request " + i);
			Response res = given().headers(hdr).body(Req).log().uri().log().headers().log().body().when().post().then()
					.extract().response();
			String cres = res.asString();
			JsonPath jres = new JsonPath(cres);
			String txnstatus = jres.get("transaction_status");
			String vstatus = jres.get("validation_status");
			tid = jres.get("transaction_id");
		 ttag = jres.get("transaction_tag");
			int scode = res.getStatusCode();

			System.out.println(cres);
			/*
			 * assertEquals(scode, 201); assertEquals(txnstatus, "approved");
			 * assertEquals(vstatus, "success");
			 * System.out.println("Txn Approved & Passed");
			 */
			 reqrow.createCell(2).setCellValue(ttag); 
			 workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

			}
		
		  FileOutputStream os = new
		  FileOutputStream("E:\\Work\\WorkSpace-Do not delete or change\\FinalTest\\Payloads\\AllPayloads.xlsx"); 
		  workbook.write(os);
		 /* os.close(); workbook.close(); fis.close(); */
	}
	
	
	@AfterTest public void sPost() throws IOException, InterruptedException {
	  
	  // Read i/p sheet 
		  FileInputStream fis = new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\FinalTest\\Payloads\\AllPayloads.xlsx"); 
	  XSSFWorkbook workbook = new XSSFWorkbook(fis); 
	  XSSFSheet sheet = workbook.getSheet("Sheet1");
	  
	  // Find Endpoint 
	  XSSFRow Endpoint = sheet.getRow(0); 
	  String FinalEndpoint = Endpoint.getCell(2).getStringCellValue(); 
	  RestAssured.baseURI =FinalEndpoint;
	  
	  // Creating Header ArrayList
	  Header apikey = new Header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a"); 
	  Header token = new Header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
	  Header ctype = new Header("Content-type", "application/json"); 
	  List<Header> list = new ArrayList<Header>();
	  list.add(apikey); 
	  list.add(token); 
	  list.add(ctype);
	  Headers hdr = new Headers(list);
	  
	  // Loop 
	  int row = sheet.getLastRowNum();
	  System.out.println("Total no Of req found in " + workbook.getSheetName(0) +" are: " + row);
	  
	  for (int i = 2; i <= row; i++) {
	  
	  // Find Req
		  Row sreqrow = sheet.getRow(i); 
		  String sReq =sreqrow.getCell(1).getStringCellValue(); 
		  int value=sReq.indexOf("transaction_tag");
		  int beg=value+19;
		  int last=beg+10;
		  System.out.println(beg);
		  
		  
		  StringBuilder strreq=new StringBuilder(sReq);
		  strreq.replace(beg, last, ttag);
		  String freq=strreq.toString();
	  
	  Response sres=given().
			  headers(hdr).
			  body(freq).
	  log().uri().log().headers().log().body().
	  //basePath(ttag,tid). when().
	  post("/"+tid).
	  then().
	  extract().response(); //
	  int surescode=sres.getStatusCode();
	  //assertEquals(surescode, 201);
	  String srs=sres.asString(); 
	  //JsonPath jsrs=new JsonPath(srs); //jsrs.get("");
	  System.out.println(srs); 
	  }
	  FileOutputStream os = new FileOutputStream("E:\\Work\\WorkSpace-Do not delete or change\\FinalTest\\Payloads\\AllPayloads.xlsx");
	  workbook.write(os);
	  os.close(); 
	  workbook.close();
	  fis.close(); 
	 
	}
}