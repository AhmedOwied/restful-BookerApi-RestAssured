package Tests;

import PojoClasses.Booking;
import PojoClasses.BookingDates;
import Utils.BookingUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Constants.*;

public class DeleteBookingTest {

    String bookingIdAreDeleted="3050";
    String bookingId;
    @BeforeTest
    public void CreateBooking()
    {
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        Response responseOfCreate = BookingUtils.createBooking(booking);  //createBooking -->This is a "Pojo Class"

        //Extract response body and Assert
        Assert.assertEquals(responseOfCreate.getStatusCode(),200);
        JsonPath jsonPath= responseOfCreate.jsonPath();
        jsonPath.prettyPrint();
        bookingId = jsonPath.getString("bookingid");

        Assert.assertEquals(responseOfCreate.getStatusCode(),200);
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed","firstName not found");

    }
    @Test
    public void DeleteTest()
    {
        Response responseOfDelete = BookingUtils.deleteBooking(bookingId);
        String responseBody=responseOfDelete.getBody().asString();
        Assert.assertTrue(responseBody.contains("Created"), "Response not contain message 'Created'");
        Assert.assertEquals(responseOfDelete.getStatusCode(),201);

    }
    @Test
    public void deleteAlreadyDeletedBooking() {
        Response responseOfDelete = BookingUtils.deleteBooking(bookingId);
        String responseBody=responseOfDelete.getBody().asString();
        Assert.assertTrue(responseBody.contains("Method Not Allowed"));
        Assert.assertEquals(responseOfDelete.getStatusCode(),405);
    }
    @Test
    public void DeleteBookingWithIdNotfound()
    {
        Response responseOfDelete = BookingUtils.deleteBooking(bookingIdAreDeleted);
        String responseBody=responseOfDelete.getBody().asString();
        Assert.assertTrue(responseBody.contains("Method Not Allowed"));
        Assert.assertEquals(responseOfDelete.getStatusCode(),405);

    }

}
