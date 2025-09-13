package Basics;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationDemo {

	@Test
	public void BearerToken() {
		// https://gorest.co.in/public/v2/users
		// create requsest specification
		RequestSpecification requestSpec = RestAssured.given();

		requestSpec.baseUri("https://gorest.co.in");
		requestSpec.basePath("/public/v2/users");


		JSONObject payload = new JSONObject();
		payload.put("name", "SamirMunde");
		payload.put("gender", "Male");
		payload.put("email", "SamirMunde123@gmail.com");
		payload.put("status", "Active");

		String AuthToken = "Bearer ad064e03d516b7f06f7aef8ad5807a5bcd1a2576bb8edfcd8e259d77c976b914";

		requestSpec.headers("Authorization", AuthToken).contentType(ContentType.JSON).body(payload.toJSONString());

		// perform post request
		Response response = requestSpec.post();

		// validate status code
		Assert.assertEquals(response.statusCode()/* actual */, 201/* expected */, "check for status code");

		// print status line & response boy
		System.out.println("Responsne status line:" + response.statusLine());
		System.out.println("Response body:" + response.body().asString());

	}
}