package payeezy.basic.practise;
/* this is payeezy apk capture request */

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;   //iMP manually import these static packages. else keywords won't work
import static org.hamcrest.Matcher.*;   	//iMP manually import these static packages. else keywords won't work
import static org.testng.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.UserAdapter;

public class TC02_Post_Request_Payeezy {
	
	public static HashMap map= new HashMap();
	
	@BeforeClass
	public void payeezyTest(){
		//List cardDetails = new ArrayList();  / use below 3 when u need to pass [] instead {}]
		//Map card = new HashMap();
		//cardDetails.add(card);
	    HashMap card=new HashMap();  //creating hashmap to send multipart request.  
		card.put("type", "visa");
		card.put("cardholder_name", "SHIV");
		card.put("card_number", "4788250000028291");
		card.put("exp_date", "1020");
		card.put("cvv", "123");	

		/*Main body starts*/
		
		map.put("merchant_ref", "Astonishing-Sale");
		map.put("transaction_type", "authorize");
		map.put("method", "credit_card");
		map.put("amount", "1299");
		map.put("currency_code", "USD");
		map.put("credit_card", card);
		
		RestAssured.baseURI="https://api-cert.payeezy.com/v1/transactions";
	}
	
	@Test
	public void sendPayeezyRequest(){
		given()
		.contentType("application/json").body(map)
		.header("apikey", "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a")
		.header("token","fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6")
		.log().all()
		.when().post()
		.then()
		.assertThat().statusCode(201)
		.assertThat().body("transaction_status", equalTo("approved"))
		.and()
		.assertThat().body("validation_status", equalTo("success"))
		.log().all();
		
		System.out.println("Transactin is passed");
	}
}

