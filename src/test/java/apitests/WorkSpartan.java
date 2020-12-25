package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import  static  org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class WorkSpartan {

    @BeforeClass
            public void setUpClass(){

        baseURI="http://54.174.126.24:8000";
    }

    String spartanBaseURL= "http://54.174.126.24:8000";
       String  url1= "https://reqres.in";
    @Test
    public void test1() {

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        System.out.println(response.statusCode());

        System.out.println(response.body().asString());

        System.out.println(response.body().prettyPrint());
    }

    @Test
            public void test2(){

        Response response = RestAssured.get(spartanBaseURL+"/api/users?page=2");

        System.out.println(response.statusCode());

        System.out.println(response.body().asString());

        System.out.println("@@@@@@@@@@@@@@@@@@@@##############@@@@@@@@@@@@@@@@@@@");
        System.out.println(response.prettyPrint());


    }

    @Test
    public void test3(){

        Response response = RestAssured.get(url1+"/api/unknown/2");

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        System.out.println(response.contentType());




    }

    /*when user send GET request to /api/spartans end point
    Then status code must be 200
    and body should contains Allen
     */


    @Test
    public void viewSpartanTest(){

         Response response = RestAssured.get(spartanBaseURL+"/api/spartans");

//        System.out.println(response.statusCode());
//        System.out.println(response.contentType());
//        System.out.println(response.body().asString());

        //verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        //verify body contains Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));

    }

    /*Given accep type is JSON
    when user sends a get request to spartanAllURL
    then response status code is 200
    And response body should be json format

     */

    @Test
    public void test4(){

     //   Response response = RestAssured.get(spartanBaseURL+"/api/spartans");


          Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseURL+"/api/spartans");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");


    }

    /*Given accep type is JSON
    And Id parameter value is 18
    when user sends get request to /api/spartans/{id}
    Then response status code should be 200
    And response content type : application/json
    And "Allen" should be in response payload
     */

    @Test
    public void test5(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                                        .and().pathParam("id", 18)
                                        .when().get("/api/spartans/{id}");

            assertEquals(response.statusCode(),200);
            assertEquals(response.contentType(),"application/json");
            assertTrue(response.body().asString().contains("Allen"));

    }
    /*Given accep type is JSON
    And Id parameter value is 500
    when user sends get request to /api/spartans/{id}
    Then response status code should be 404
    And response content type : application/json
    And "Spartan Not Found" message should be in response payload

     */

        @Test
    public void test6(){

            Response response = RestAssured.given().accept(ContentType.JSON)
                               .and().pathParam("id",500)
                               .when().get("/api/spartans/{id}");

          assertEquals(response.statusCode(),404);
          assertEquals(response.contentType(),"application/json");
          assertTrue(response.body().asString().contains("Spartan Not Found"));

       response.prettyPrint();


        }

        /*Given accep type is Json
        And qury parameter values are:
        gender|Female
        nameContains|J
        when user sends GET request to /api/spartan/search
        Then response status code should be 200
        And response content type : application/json
        And "Female" should be in response payload
         And "Janette" should be in response payload

         */

    @Test
    public void test7(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");



        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

        assertFalse(response.body().asString().contains("Male"));

        response.prettyPrint();

    }
        @Test
    public void test8(){

            Map<String,Object> paramsMap= new HashMap<>();
            paramsMap.put("gender","Female");
            paramsMap.put("nameContains","J");

            Response response = given().accept(ContentType.JSON)
                    .and().queryParams(paramsMap)
                    .when().get("/api/spartans/search");

            assertEquals(response.statusCode(),200);
            assertEquals(response.contentType(),"application/json");
            assertTrue(response.body().asString().contains("Female"));
            assertTrue(response.body().asString().contains("Janette"));

            assertFalse(response.body().asString().contains("Male"));

            response.prettyPrint();

        }

}
