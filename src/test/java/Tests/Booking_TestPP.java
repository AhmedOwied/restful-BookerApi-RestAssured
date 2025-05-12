package Tests;

import PojoClasses.Booking;
import PojoClasses.BookingDates;
import Utils.BookingUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static Utils.Constants.ADDITIONAL_NEEDS;

public class Booking_TestPP {

    @Test
    public void createBooking(){
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, 5050, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        JsonPath jsonPath= BookingUtils.createBooking(booking).jsonPath();
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed");

    }


    @Test
    public void getBookingII() {
        // Arrange (create booking)
        Booking booking = new Booking(FIRST_NAME, LAST_NAME, 5050, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        JsonPath createJson = BookingUtils.createBooking(booking).jsonPath();
        String bookingId = createJson.getString("bookingid");

        // Act (retrieve booking)
        Response response = BookingUtils.getBookingByIdII(bookingId);

        // Assert
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"), "Ahmed");
        Assert.assertEquals(jsonPath.getString("lastname"), "ElSayed");
    }




    @Test
    public void testDeleteBooking() {
        // Step 1: Create booking
        Booking booking = new Booking(UPDATED_FIRST_NAME, LAST_NAME, 2050, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        JsonPath createJson = BookingUtils.createBooking(booking).jsonPath();
        String bookingId = createJson.getString("bookingid");

        // Step 2: Delete booking By Id
        Response deleteResponse = BookingUtils.deleteBooking(bookingId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 201); // أو 200 حسب الـ API

        // Step 3: Try to get deleted booking
        Response getResponse = BookingUtils.getBookingByIdII(bookingId);
        Assert.assertEquals(getResponse.getStatusCode(), 404); // مفروض يرجع Not Found
    }
}
