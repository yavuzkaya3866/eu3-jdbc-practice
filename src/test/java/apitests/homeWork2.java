package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  static  org.testng.Assert.*;
import static io.restassured.RestAssured.*;


public class homeWork2 {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


//    Feel free to use any project and class for automation.
//
//            SPARTAN API
//    Q1:
//    Given accept type is json
//    And path param id is 20
//    When user sends a get request to "/api/spartans/{id}"
//    Then status code is 200
//    And content-type is "application/json"
//    And response header contains Date
//    And Transfer-Encoding is chunked
//    And response payload values match the following:
//    id is 20,
//    name is "Lothario",
//    gender is "Male",
//    phone is 7551551687


    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.headers().toString().contains("Date"));

        String TE = response.headers().getValues("Transfer-Encoding").toString().substring(1,8);
        System.out.println(response.headers().getValues("Transfer-Encoding").toString().substring(1, 8));
        assertEquals(TE,"chunked");


        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());

        //save json key values
        int id =  response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        //assert one by one
        assertEquals(id,20);
        assertEquals(name,"Lothario");
        assertEquals(gender,"Male");
        assertEquals(phone,7551551687l);



    }
//
//    Q2:
//    Given accept type is json
//    And query param gender = Female
//    And queary param nameContains = r
//    When user sends a get request to "/api/spartans/search"
//    Then status code is 200
//    And content-type is "application/json"
//    And all genders are Female
//    And all names contains r
//    And size is 20
//    And totalPages is 1
//    And sorted is false

        @Test
    public void test2() throws InterruptedException {

        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("gender","Female")
                            .and().queryParam("nameContains","r")
                            .when().get("/api/spartans/search");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

            List<String> Gender = response.path("content.gender");
            for (String gender : Gender) {

               // System.out.println(gender);

                assertEquals(gender,"Female");

            }
            List<String> NameR = response.path("content.name");

            for (String names : NameR) {
               System.out.println(names);
                assertTrue(names.contains("r")||names.contains("R"));
            }


            int Size = response.path("size");
            System.out.println("Size = " + Size);
            assertEquals(Size,20);

            int totalPages = response.path("totalPages");
            System.out.println("totalPages = " + totalPages);
            assertEquals(totalPages,1);

            System.out.println("###########################");

            boolean sorted= response.path("sort.sorted");
            System.out.println("sorted = " + sorted);
            assertEquals(sorted,false);


        }
}
