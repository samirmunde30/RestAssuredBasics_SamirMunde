package Basics;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POJONestedJsonPayload {

	@Test
	public void createUser() throws JsonProcessingException {

		/*
		 * 
{
  "firstName": "Suresh",
  "lastName": "Mehra",
  "gender": "Male",
  "age": 35,
  "salary:10000.56,
  "Address":{
	"Street": "Park Avenue",
	"City": "Vijaywada",
	"State": "Andhra Pradesh",
	"pin code":530012
        }
}
		 */

		POJONestedJsonEmployee emp1=new POJONestedJsonEmployee();

		emp1.setFirstname("Samir");
		emp1.setLastname("Munde");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000.56);


		POJOENestedmployeeAddress emp1Address =new POJOENestedmployeeAddress();

		emp1Address.setStreet("Nigdi Road");
		emp1Address.setCity("Pune");
		emp1Address.setState("Maharashtra");
		emp1Address.setPincode(412101);

		emp1.setAddress(emp1Address);

		//convert class object to json object as string

		ObjectMapper objetMapper = new ObjectMapper();

		String josnpayload = objetMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);


		RequestSpecification reqSpec = RestAssured.given();
		//specify url
				reqSpec.baseUri("http://httpbin.org/post");
				reqSpec.contentType(ContentType.JSON);
				reqSpec.body(josnpayload);
				
				//perform post request
				Response response = reqSpec.post();
				
				response.prettyPrint();
				
				
			//	System.out.println("jsonpayload : " + josnpayload);
	}


}
