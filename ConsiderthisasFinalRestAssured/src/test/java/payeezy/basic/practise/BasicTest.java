package payeezy.basic.practise;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicTest {

	@Test
	public void main() {
		
		RestAssured.baseURI="https://api-cert.payeezy.com/v1/transactions";
		
		String req="{\r\n" + 
				"  \"merchant_ref\": \"Astonishing-Sale\",\r\n" + 
				"  \"transaction_type\": \"authorize\",\r\n" + 
				"  \"method\": \"credit_card\",\r\n" + 
				"  \"amount\": \"1299\",\r\n" + 
				"  \"currency_code\": \"USD\",\r\n" + 
				"  \"credit_card\": {\r\n" + 
				"    \"type\": \"visa\",\r\n" + 
				"    \"cardholder_name\": \"John Smith\",\r\n" + 
				"    \"card_number\": \"4788250000028291\",\r\n" + 
				"    \"exp_date\": \"1020\",\r\n" + 
				"    \"cvv\": \"123\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		
		Response res=given().
			header("apikey","y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a").
			header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6").
			contentType("application/json").
			body(req).
			log().uri().log().headers().log().body().
		when().
			post().
		then().
			extract().response();
			String cres=res.asString(); 
			JsonPath jres=new JsonPath(cres); 
			String txnstatus=jres.get("transaction_status");
			String vstatus=jres.get("validation_status");
			int scode=res.getStatusCode();
			System.out.println(cres);
			assertEquals(scode, 201);
			assertEquals(txnstatus, "approved");
			assertEquals(vstatus, "success");	
			System.out.println("Txn Approved & Passed");
			
		
	}
}
