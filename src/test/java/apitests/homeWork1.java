package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  static  org.testng.Assert.*;
import static io.restassured.RestAssured.*;


public class homeWork1 {

    @BeforeClass
    public void beforeclass() {


           baseURI = ConfigurationReader.get("hr_api_url");
    }

//    Feel free to use any  project and class for automation.
//
//    ORDS API:


//    Q1:
//            - Given accept type is Json
//- Path param value- US
//- When users sends request to /countries
//- Then status code is 200
//            - And Content - Type is Json
//- And country_id is US
//- And Country_name is United States of America
//- And Region_id is

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                                .pathParam("country_id","US")
                                .when().get("/countries/{country_id}");
       // response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");


        System.out.println("country_id = " + response.body().path("country_id").toString());
        System.out.println("countryName = " + response.body().path("country_name").toString());
        System.out.println("region_id = " + response.body().path("region_id").toString());

        response.prettyPrint();

        String country_id= response.path("country_id");
        String country_name= response.path("country_name");
        int region_id= response.path("region_id");

        System.out.println("country_id = " + country_id);
        System.out.println("country_name = " + country_name);
        System.out.println("region_id = " + region_id);

        assertEquals(country_id,"US");
        assertEquals(country_name,"United States of America");
        assertEquals(region_id,2);



    }
//
//    Q2:
//            - Given accept type is Json
//- Query param value - q={"department_id":80}
//- When users sends request to /employees
//- Then status code is 200
//            - And Content - Type is Json
//            - And all job_ids start with 'SA'
//            - And all department_ids are 80
//            - Count is 25
    @Test
        public void test2(){

        Response response = given().accept(ContentType.JSON)
                                .and().queryParam("q","{\"department_id\":80}")
                .when().get("/employees");

       // response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        List<String> JobIds = response.path("items.job_id");

        for (String jobId : JobIds) {
            assertEquals(jobId.substring(0,2), "SA");

            //or
            assertTrue(jobId.startsWith("SA"));
        }
        System.out.println(JobIds);

        List<Integer> DepIds = response.path("items.department_id");
        for (int depId : DepIds) {
            assertEquals(depId,80);
        }

        int count = response.path("count");
        System.out.println("count = " + count);

        boolean hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);

        int limit = response.path("limit");
        System.out.println("limit = " + limit);

        int offset = response.path("offset");
        System.out.println("offset = " + offset);

        assertEquals(count,25);

        response.prettyPrint();



    }

//    Q3:
//            - Given accept type is Json
//-Query param value q= region_id 3
//            - When users sends request to /countries
//- Then status code is 200
//            - And all regions_id is 3
//            - And count is 6
//            - And hasMore is false
//            - And Country_name are;
//    Australia,China,India,Japan,Malaysia,Singapore


    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                                   .and().queryParam("q","{\"region_id\":3}")
                                    .when().get("/countries");

        //response.prettyPrint();

        assertEquals(response.statusCode(),200);

        List<Integer> RegionIds = response.path("items.region_id");

        for (int regionId : RegionIds) {

            assertEquals(regionId,3);
        }

        int count = response.path("count");
        assertEquals(count,6);

        boolean hasMore=response.path("hasMore");
        assertEquals(hasMore,false);

        String [] countries ={"Australia","China","India","Japan","Malaysia","Singapore"};

        System.out.println(Arrays.toString(countries));

        List<String>  countryNames = response.path("items.country_name");
        for (String countryName : countryNames) {

            System.out.println(countryName);
            assertTrue(Arrays.toString(countries).contains(countryName));


        }


    }
}
