package apitests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 17982)
                .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);
        //assing response the jsonpath
       JsonPath jsonPath=response.jsonPath();
       String firstName= jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lasttName= jsonPath.getString("students.lastName[0]");
        System.out.println("lasttName = " + lasttName);

        String phone= jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        //assert city and zipCode

        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        assertEquals(city,"Chicago");


        String zipCode = jsonPath.getString("students.company[0].address.zipCode");
        System.out.println("zipcode = " + zipCode);
        assertEquals(zipCode,"60606");


        //----------------
        String firstname2 = jsonPath.getString("students.firstName");
        System.out.println("firstname2 = " + firstname2);

//        String firstname3 =response.path("students.firstName");
//        System.out.println("firstname3 = " + firstname3);

//        String zipCode2= response.path("students.company[0].address.zipCode");
//        System.out.println("zipCode2 = " + zipCode2);

    }
}