package Tests;

import PojoClasses.Booking;
import PojoClasses.BookingDates;
import ResponseModels_Deserlizations.GetSingleBooking_ResponseModels;
import Utils.BookingUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Constants.*;

public class BookingTest {
    @Test
    public void createBooking(){
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, 5050, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        JsonPath jsonPath= BookingUtils.createBooking(booking).jsonPath();
        int bookingId = Integer.parseInt(jsonPath.getString("bookingid"));
        System.out.println(bookingId);
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed");
    }

    @Test
    public void getBookingById(){
        String id ="23";
        Response response =BookingUtils.getBookingByIdII(id);
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
