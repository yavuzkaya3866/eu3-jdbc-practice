package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class worksheet {

    String cturl = "http://api.cybertektraining.com/teacher/10746";

    @Test
    public void test1() {

        Response response = RestAssured.get(cturl);

        //print the status code
        System.out.println(response.statusCode());

       
        //print the LSON body
        System.out.println(response.prettyPrint());
    }



    }
