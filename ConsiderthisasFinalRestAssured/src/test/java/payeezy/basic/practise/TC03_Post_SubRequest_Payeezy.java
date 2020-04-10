package payeezy.basic.practise;
/* this is payeezy apk capture request */

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;   //iMP manually import these static packages. else keywords won't work
import static org.hamcrest.Matcher.*;   	//iMP manually import these static packages. else keywords won't work
import static org.testng.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.*;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC03_Post_SubRequest_Payeezy {
	
	public static HashMap map= new HashMap();
	
	@BeforeClass
	public void payeezyTest(){
		map.put("merchant_ref", "Astonishing-Sale");
		map.put("transaction_tag", "4362881990");  //need to change before every run
		map.put("transaction_type", "capture");
		map.put("method", "credit_card");
		map.put("amount", "1299");
		map.put("currency_code", "USD");
		
		RestAssured.baseURI="https://api-cert.payeezy.com/v1/transactions";
		RestAssured.basePath="/ET152959";  //need to change before every run
	}
	
	@Test
	public void sendPayeezyRequest(){
		given()
		.contentType("application/json").body(map)
		.header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a")
		.header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6")
		.when().post()
		.then()
		.statusCode(201)
		.body("transaction_status", equalTo("approved"))
		.log().all();		
	}
}
