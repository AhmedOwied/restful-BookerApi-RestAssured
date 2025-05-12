package Utils;

import PojoClasses.Auth;
import endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static Utils.Constants.USER_NAME;
import static Utils.Constants.PASSWORD;
import static endpoints.Routes.AUTH_URL;
import static io.restassured.RestAssured.given;


public class LoginUtils {

    public static Response Login(Auth body){  //Object from PojoClass


        return given()
                  .contentType(ContentType.JSON)
                  .body(body)
                .when()
                   .post(AUTH_URL)
                .then()
                .extract().response();



    }
}
