package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  static  org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class workJsonPath {
    @BeforeClass
    public void setUpClass() {
        baseURI = "http://54.174.126.24:8000";

    }

    /*Given accep type is Json
    And path param id is 11
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And content type is Json
    And         "id": 11,
                "name": "Nona",
                "gender": "Female",
                "phone": 7959094216
     */


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                            .and().pathParam("id",11)
                            .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertTrue(response.contentType().contains("json"));

       int id = response.path("id");
        System.out.println("id = " + id);

        //How read value with Json path?

        JsonPath jsonData =response.jsonPath();
        int id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }
}
