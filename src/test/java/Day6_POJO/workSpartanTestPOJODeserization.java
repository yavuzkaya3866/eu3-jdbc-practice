package Day6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class workSpartanTestPOJODeserization {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("api/spartans/{id}");
        //response.prettyPrint();

        //how to convert json response to our spartan class
            //once sinifimizi yaziyoruz.(workSpartanTestPOJODeserization sinifini
           // olusturduktan sonra workSpartanPOJOsinifini olusturduk)
        WorkSpartanPOJO workSpartanPOJO1 = response.body().as(WorkSpartanPOJO.class);

        System.out.println(workSpartanPOJO1.toString());

        assertEquals(workSpartanPOJO1.getName(),"Meta");
        assertEquals(workSpartanPOJO1.getId(),15);
        assertEquals(workSpartanPOJO1.getGender(),"Female");
        assertEquals(workSpartanPOJO1.getPhone(),1938695106);

    }

    @Test
    public void gsonExample(){

        Gson gson= new Gson();

String myJsonBody = "{\n" +
        "    \"id\": 15,\n" +
        "    \"name\": \"Meta\",\n" +
        "    \"gender\": \"Female\",\n" +
        "    \"phone\": 1938695106\n" +
        "}";


// using gson mehtod to de serialize our json body

        WorkSpartanPOJO workSpartanPOJOMeta = gson.fromJson(myJsonBody,WorkSpartanPOJO.class);

        System.out.println(workSpartanPOJOMeta.toString());

        WorkSpartanPOJO workSpartanPOJO = new WorkSpartanPOJO(101,"Mike","Male",321342123l);

        //converting custom class to json(serialization)
        String jsonbody= gson.toJson(workSpartanPOJO);
        System.out.println(jsonbody);
    }

}
