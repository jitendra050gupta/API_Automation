package WeatherAPIsTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetAPITest {
	
	@Test
	public void statusCodeTest() {
		
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/posts";
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/3");
		
		String body = response.body().asString();
		// -------------Printing Response body------------
		System.out.println(body);
		
		// ------------Verifying Status code of the response-----------
		System.out.println("Status code is "+response.getStatusCode());
		Assert.assertEquals(response.statusCode(), 200);
		
		// -----------Verifying Status line of the response-------------
		System.out.println("Status Line is "+response.statusLine());
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		//-------Verifying attribute value from the response body---------
		System.out.println("Id in response body is ---> "+response.jsonPath().get("id").toString());
		
		// ----------------Printing all headers----------		
		System.out.println("----------All headers are ------------");
		Headers allHeaders = response.headers();
		for(Header header:allHeaders) {
			System.out.println(header.getName()+"      "+header.getValue());			
			
		}
		
		// ------------------Verifying header value-------------------
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
		
		
		
	}
	
	
	

}
