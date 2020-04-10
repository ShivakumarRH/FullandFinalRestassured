package payeezy.basic.practise;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher; 
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matcher.*;   
import static org.hamcrest.core.IsEqual.*;
import static io.restassured.RestAssured.given;

public class TC00_Get_Request {
 
	  @Test
	  public void logTest() {
		  
		  given()		  
		  .when()
		  .get("https://jsonplaceholder.typicode.com/todos/1"). /*enable 1st assert*/
//		  .get("https://reqres.in/api/users/2"). /*enable 2nd assert*/
		  then()
		  .log().all()
		  .assertThat().statusCode(200)
		  .and()
		  .assertThat().body("title", equalTo("delectus aut autem"));
//		  .assertThat().body("first_name", equalTo("Janet"));
		  
		  System.out.println("Test is passed");
		  
	}

}