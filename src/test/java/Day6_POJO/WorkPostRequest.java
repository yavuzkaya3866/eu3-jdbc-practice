package Day6_POJO;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import oracle.jdbc.proxy.annotation.Post;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class WorkPostRequest {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void PostWithString(){

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"name\":\"Cakarcakmaz\",\n" +
                        "    \"phone\":1212396360\n" +
                        "}")
                .when().post("/api/spartans/");

        response.prettyPrint();

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify succes message
        assertEquals(response.path("success"),"A Spartan is Born!");

        JsonPath jsonPath= response.jsonPath();

        assertEquals(jsonPath.getString("data.name"),"Cakarcakmaz");
        assertEquals(jsonPath.getString("data.gender"),"Male");
        //assertEquals(jsonPath.getString("data.phone"),1212396360L);



    }




}
