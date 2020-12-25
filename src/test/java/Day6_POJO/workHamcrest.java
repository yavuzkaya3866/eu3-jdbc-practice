package Day6_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
public class workHamcrest {

    @BeforeClass
    public void setUpClass(){
        baseURI= "http://54.174.126.24:8000";
    }

//    Given accept type is json
//    And path param id is 15
//    When user sends a get request to "api/spartans/{id}"
//    Then status code is 200
//    And content-type is "application/json"
//
//    And json data has following:
//    "id": is 15,
//    "name": is "Meta",
//     "gender': "Female",
//     "phone": 1938695106


    @Test
    public void test1(){

        //request part.(

                 given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("api/spartans/{id}")   //normally we put ; here and initially write Response response =
                                                        //but we continiu now because this is chaining method.
                 .then().statusCode(200)
                 .and().assertThat().contentType("application/json");



    }
    @Test
    public void test2(){
                //request part
        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("api/spartans/{id}")
                    //response and assert part
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", Matchers.equalTo(15),
                "name",Matchers.equalTo("Meta"),
                "gender",Matchers.equalTo("Female"),
                "phone",Matchers.equalTo(1938695106)).log().all();




    }

}
