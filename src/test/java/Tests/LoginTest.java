package Tests;

import PojoClasses.Auth;
import Utils.LoginUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.PASSWORD;
import static Utils.Constants.USER_NAME;

public class LoginTest {
    String token="";

    @Test
    public void valid_Auth(){
        Response response = LoginUtils.Login(new Auth(USER_NAME,PASSWORD));

        //Extract data from response
        JsonPath jsonPath=response.jsonPath();
        token=jsonPath.getString("token");
    }

}
