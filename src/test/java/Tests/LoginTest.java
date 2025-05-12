package Tests;

import PojoClasses.Auth;
import Utils.LoginUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
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

    @Test
    public void WriteWrongPassword()
    {
        Response response = LoginUtils.Login(new Auth(USER_NAME,"Psasd"));

        //Extract data from response
        JsonPath jsonPath=response.jsonPath();
        String reason = jsonPath.getString("reason");
        token=jsonPath.getString("token");

        Assert.assertEquals(reason, "Bad credentials");
        Assert.assertEquals(response.getStatusCode(),401); //Unauthorized


    }

    @Test
    public void WriteWrongEmail()
    {
        Response response = LoginUtils.Login(new Auth("Email",PASSWORD));

        //Extract data from response
        JsonPath jsonPath=response.jsonPath();
        String reason = jsonPath.getString("reason");
        token=jsonPath.getString("token");

        Assert.assertEquals(reason, "Bad credentials");
        Assert.assertEquals(response.getStatusCode(),401); //Unauthorized


    }
    @Test
    public void testLoginWithEmptyUsernameAndPassword()
    {
        Response response = LoginUtils.Login(new Auth("",""));

        //Extract data from response
        JsonPath jsonPath=response.jsonPath();
        String reason = jsonPath.getString("reason");
        token=jsonPath.getString("token");

        Assert.assertEquals(reason, "Bad credentials");
        Assert.assertEquals(response.getStatusCode(),401); //Unauthorized


    }

}
