package Day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class workSpartanJsonToCollection {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
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
                            .pathParam("id",11)
                            .when().get("api/spartans/{id}");

        //convert Json response to Java Collection(Map)

        Map <String, Object> spartanMap = response.body().as(Map.class);

        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("gender"));

        //one example verification one side map/ expected value

        assertEquals(spartanMap.get("name"),"Nona");

    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                            .when().get("/api/spartans");
        //response.prettyPrint();

        //convert full Jason body to list of maps
       List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

       //print all data of first spartan

        System.out.println("listOfSpartans.get(0) = " + listOfSpartans.get(0));

        Map<String,Object> firstSpartan= listOfSpartans.get(0);
        System.out.println(firstSpartan.get("name"));
        
        //iter//
        int counter = 1;
        for (Map<String, Object> listOfSpartan : listOfSpartans) {

            //System.out.println(listOfSpartan);
            //counter ekleyerek sira numarasi verebiliriz. bunun icin disariya bir integer counter ekliyoruz
            System.out.println(counter+" - spartan: " + listOfSpartan);
            counter++;
        }
        
        
        


    }

}


