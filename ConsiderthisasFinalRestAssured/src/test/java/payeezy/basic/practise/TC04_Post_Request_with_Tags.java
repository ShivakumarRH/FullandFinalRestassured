package payeezy.basic.practise;
/* this is payeezy apk capture request */

import io.restassured.RestAssured;
import io.restassured.internal.http.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.*;   //iMP manually import these static packages. else keywords won't work
import static org.hamcrest.Matcher.*;   	//iMP manually import these static packages. else keywords won't work
import static org.testng.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.HasItemInArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.UserAdapter;

public class TC04_Post_Request_with_Tags {
	
	private static final String ResponseBody = null;
	public static HashMap map= new HashMap();
	
	
	@BeforeClass
	public void payeezyTest(){
	    HashMap card=new HashMap();  //creating hashmap to send multipart request.  
		card.put("type", "visa");
		card.put("cardholder_name", "SHIV");
		card.put("card_number", "4788250000028291");
		card.put("exp_date", "1020");
		card.put("cvv", "123");
	
		HashMap capture= new HashMap();
		capture.put("merchant_ref", "Astonishing-Sale");
		capture.put("transaction_tag", "4362881990");  //need to change before every run
		capture.put("transaction_type", "capture");
		capture.put("method", "credit_card");
		capture.put("amount", "1299");
		capture.put("currency_code", "USD");
		

		map.put("merchant_ref", "Astonishing-Sale");   /*Main body starts*/
		map.put("transaction_type", "authorize");
		map.put("method", "credit_card");
		map.put("amount", "1299");
		map.put("currency_code", "USD");
		map.put("credit_card", card);
		
//		RestAssured.baseURI="https://api-cert.payeezy.com/v1/transactions";
	}
	
	@Test(priority=1)
	public void sendPayeezyRequest(){
		Response response = given()
		.contentType("application/json").body(map)
		.header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a")
		.header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6")
		.baseUri("https://api-cert.payeezy.com/v1/transactions")
		.log().all()
		.when().post()
		.then()
		.assertThat().statusCode(201)
		.assertThat().body("transaction_status", equalTo("approved"))
		.and()
		.assertThat().body("validation_status", equalTo("success"))
		//.log().all()
		.extract().response();
		
		System.out.println("Transaction is:" +response);
	
//		JsonPath jsonPath = new JsonPath(ResponseBody);
//		int user_id = jsonPath.getInt("transaction_tag");
		
		String transaction_status = response.path("transaction_status");
		String transaction_id = response.path("transaction_id");
		String transaction_tag = response.path("transaction_tag");		

		
		

//	@Test(priority=2)
//	public void sendCaptureRequest(){
//		given()
//		.baseUri("https://api-cert.payeezy.com/v1/transactions")
//		.contentType("application/json").body(capture)
//		.header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a")
//		.header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6")
//		.when().post()
//		.then()
//		.statusCode(201)
//		.body("transaction_status", equalTo("approved"))
//		.log().all();
//		
//	}
	
	}
}