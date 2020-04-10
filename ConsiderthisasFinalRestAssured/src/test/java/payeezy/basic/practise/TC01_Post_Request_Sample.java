package payeezy.basic.practise;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;   //iMP manually import these static packages. else keywords won't work
import static org.hamcrest.Matcher.*;   
import static org.hamcrest.core.IsEqual.*;

public class TC01_Post_Request_Sample {
	
	public static HashMap map=new HashMap(); //this is to define map

	@BeforeClass
	public void requestBody(){
		
	map.put("name", "VFC");
	map.put("salary", "31");
	map.put("age", "33");
	
	RestAssured.baseURI="http://dummy.restapiexample.com";
	RestAssured.basePath="/create";
	}
	
	@Test
	public void mainBody()
	{
		given().contentType("application/json").body(map)
		.when().post()
		.then().statusCode(405)
		.and()
		.body("name",equalTo("VFC"))
		.log().all();		
	}
}
 