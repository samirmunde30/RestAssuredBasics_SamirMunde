package Basics;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DeserializationJSonDemo {

	@Test
	public void createUser() {
		// https://reqres.in/api/users
		// create Request Specification
		RequestSpecification requestSpec = RestAssured.given();

		// specify URL
		requestSpec.baseUri("https://reqres.in");
		requestSpec.basePath("/api/users");

		// create request body
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Samir");
		jsonData.put("job", "QAAutomation");

		// perform post request
		Response response = requestSpec.contentType(ContentType.JSON).body(jsonData.toJSONString()).post();

		ResponseBody responseBody = response.getBody();

		// Deserialize responseBody i.e json resoponse body to class object

		// Class<T> is a generic form of any class of type T which is also referred to
		// as template class.

		DeserializationJSONPostRequestResponse responseClass = responseBody
				.as(DeserializationJSONPostRequestResponse.class);

		Assert.assertEquals(responseClass.name, "Samir", "check for name.");
		Assert.assertEquals(responseClass.job, "QAAutomation", "check for QAAutomation.");
		
        
	}
}
