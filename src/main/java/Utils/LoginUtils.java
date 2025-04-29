package Utils;

import endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static Utils.Constants.AUTH_ENDPOINT;
import static io.restassured.RestAssured.given;


public class LoginUtils {

    public static Response  Auth(){
        HashMap<String,String> body =new HashMap<>();
        body.put("username","admin");
        body.put("password","password123");

        return given()
                  .contentType(ContentType.JSON)
                  .body(body)
                .when()
                   .post(Routes.BASE_URL +AUTH_ENDPOINT)
                .then()
                .extract().response();



    }
}
