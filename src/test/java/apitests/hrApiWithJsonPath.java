package apitests;

import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }
        @Test
                public void test1() {
            Response response = get("/countries");

            JsonPath jsonPath = response.jsonPath();

            String secondCountryName = jsonPath.getString("items.country_name[1]");
            System.out.println("secondCountryName = " + secondCountryName);

            //GET ALL COUNTRY IDS
            List<String> countryIDs= jsonPath.getList("items.country_id");
            System.out.println(countryIDs);

            //get all country names where their region id is equal to 2
           List<String> countryNamesWithRegionId2 =jsonPath.getList("items.findAll {it.region_id==2}.country_name");
           System.out.println(countryNamesWithRegionId2);

            System.out.println("====================================");

           // //get all country id where their region id is equal to 3
            List<String> countryNamesWithRegionId3 =jsonPath.getList("items.findAll {it.region_id==3}.country_id");
            System.out.println(countryNamesWithRegionId3);

        }

        @Test
    public void test2(){

        Response response = given().queryParam("limit",107)
                                   .when().get("/employees");

            JsonPath jsonPath = response.jsonPath();

            //get me all firstname of employees who is working as IT_PROG

            List<String> firstNames = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
            System.out.println(firstNames);

            //get me all email of employees who is working as IT_PROG

            List<String> emailIT = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
            System.out.println(emailIT);


            System.out.println("========================");

            //get me all firstname of emplyoees who is making more than 10000

            List<String> firstName10k = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
            System.out.println(firstName10k);

            System.out.println("========================");

            //get me first name of who is making highest salary
            String firstNameMaxSalary = jsonPath.getString("items.max {it.salary}.first_name");
            System.out.println("firstNameMaxSalary = " + firstNameMaxSalary);

            //get me first name of who is making Lowest salary

            String firstNameLS = jsonPath.getString("items.min {it.salary}.first_name");
            System.out.println("firstNameLS = " + firstNameLS);
            

        }

}
