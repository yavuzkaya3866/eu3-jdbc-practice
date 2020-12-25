package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  static  org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class workPathMethod {

    @BeforeClass
    public void setUpClass() {
        baseURI = "http://54.174.126.24:8000";

    }

    /*Given accep type is Json
    And path param id is 10
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And content type is "application/json"
    And response payload values match the following:
               id is 10,
               name is "Lorenza",
               gender is "Female",
               phone is 3312820936
     */

    @Test
    public void test1(){

        Response response= RestAssured.given().accept(ContentType.JSON)
                            .and().pathParam("id",10)
                            .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        System.out.println("ID = " + response.body().path("id").toString());
        System.out.println("Name = " + response.body().path("name").toString());
        System.out.println("Gender = " + response.body().path("gender").toString());
        System.out.println("Phone = " + response.body().path("phone").toString());

        int id= response.path("id");
        String name= response.path("name");
        String gender= response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);


    }

    @Test
    public void test2(){

        Response response = get("/api/spartans");

        //extract to first id
        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);

        //extract first name
        String fist1stName= response.path("name[0]");
        System.out.println("fist1stName = " + fist1stName);

        //extract last firstName

        String last1stName= response.path("name[-1]");
        System.out.println("last1stName = " + last1stName);

        //extract all firstNames and print them

        List<String> names = response.path("name");

        System.out.println(names);
        System.out.println("names.size() = " + names.size());

        List<Object> phoneNumbers = response.path("phone");

        for (Object phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);

        }


    }
}
