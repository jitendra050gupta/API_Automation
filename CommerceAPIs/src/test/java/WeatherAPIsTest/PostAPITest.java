package WeatherAPIsTest;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostAPITest {

	@Test
	public void createUserTest() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification httpRequest = RestAssured.given();

		Random rand = new Random();
		int num = rand.nextInt(100000);
		String userName = num + "user";
		String email = num + "@test.com";

		// Creating JSON object to create request body in JSON
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("FirstName", "Jitendra");
		jsonBody.put("LastName", "Gupta");
		jsonBody.put("UserName", userName);
		jsonBody.put("Password", "12334566");
		jsonBody.put("Email", email);

		// Defining header type inside the header
		httpRequest.header("Content-Type", "application/json");

		// Adding request body into request specification object
		httpRequest.body(jsonBody.toJSONString());

		// hitting the request and storing the response into response object
		Response rsponse = httpRequest.request(Method.POST, "/register");

		// ---------Printing Response Body-----------
		System.out.println("--------------Response Body ----------------");
		System.out.println(rsponse.body().asString());

		// ------------Verifying Status code of the response-----------
		System.out.println("Status code is " + rsponse.getStatusCode());
		Assert.assertEquals(rsponse.statusCode(), 201);

		// ---------Verifying Response Body attributes-----------
		Assert.assertEquals(rsponse.jsonPath().getString("SuccessCode"), "OPERATION_SUCCESS");
		
		// ----------Verifying Header values-----------------
		
		Assert.assertEquals(rsponse.header("Content-Type"), "application/json");
		
		Assert.assertEquals(rsponse.header("Content-Encoding"), "gzip");

	}

}
