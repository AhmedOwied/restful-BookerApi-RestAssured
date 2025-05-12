package Tests;

import ResponseModels_Deserlizations.GetSingleBooking_ResponseModels;
import Utils.BookingUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleUserTest {
    @Test
    public void getBookingById(){
        String id ="23";
        Response response = BookingUtils.getBookingByIdII(id);
        response.asPrettyString();
        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"),"John");
    }

    //TODO::Applied Deserialization
    @Test
    public void getBookingByIdWithDeserialization(){
        String id ="21";
        GetSingleBooking_ResponseModels responseModels= BookingUtils.getBookingById(id);
        Assert.assertEquals(responseModels.firstname,"Rachel");
    }
}
