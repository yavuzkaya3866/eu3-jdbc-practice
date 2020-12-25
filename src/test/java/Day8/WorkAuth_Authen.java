package Day8;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
public class WorkAuth_Authen {

    @BeforeClass
    public void before(){
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com";


    }
    //email = emaynell8f@google.es
    //password = besslebond
    String accesToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @Test
    public void test1(){

         Response response = given().header("Authorization", accesToken)
                .when().get("/api/campuses");

         assertEquals(response.statusCode(),200);
         response.prettyPrint();



    }



}
