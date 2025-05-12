package Tests;

import PojoClasses.Booking;
import Utils.BookingUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateTest {
    Booking booking =new Booking();
    JsonPath jsonPath;
    String bookingId="1492";
    @Test
    public void UpdateBooking() {
        // Step 1: Get the old booking
        Response oldBookingResponse = BookingUtils.getBookingByIdTT(bookingId);
        Booking oldBooking = oldBookingResponse.as(Booking.class); //Deserialize

       // Step 2: Update only firstname and lastname
        oldBooking.setFirstname("nour");
        oldBooking.setLastname("is");

        // Step 3: Send the updated booking
        Response responseOfUpdate = BookingUtils.updateBooking(bookingId, oldBooking);
        jsonPath = responseOfUpdate.jsonPath();

         // Step 4: Assert
        Assert.assertEquals(jsonPath.getString("firstname"), "nour");
        Assert.assertEquals(responseOfUpdate.getStatusCode(), 200);

        System.out.println(jsonPath.getString("firstname"));
        System.out.println(bookingId);
    }
    @Test
    public void updateBookingWithNullFirstname() {
        // Step 1: Get the old booking
        Response oldBookingResponse = BookingUtils.getBookingByIdTT(bookingId);
        Booking oldBooking = oldBookingResponse.as(Booking.class);

        // Step 2: Set invalid firstname
        oldBooking.setFirstname(null);

        // Step 3: Send the updated booking
        Response responseOfUpdate = BookingUtils.updateBooking(bookingId, oldBooking);
        jsonPath = responseOfUpdate.jsonPath();

        // Step 4: Assert
        Assert.assertEquals(responseOfUpdate.getStatusCode(), 400); // نتوقع Bad Request
        System.out.println(responseOfUpdate.asString());
    }
    @Test
    public void updateBookingWithEmptyLastname() {
        // Step 1: Get the old booking
        Response oldBookingResponse = BookingUtils.getBookingByIdTT(bookingId);
        Booking oldBooking = oldBookingResponse.as(Booking.class);

        // Step 2: Set empty lastname
        oldBooking.setLastname("");

        // Step 3: Send the updated booking
        Response responseOfUpdate = BookingUtils.updateBooking(bookingId, oldBooking);
        jsonPath = responseOfUpdate.jsonPath();

        // Step 4: Assert
        Assert.assertEquals(responseOfUpdate.getStatusCode(), 400);
        System.out.println(responseOfUpdate.asString());
    }
    @Test
        public void updateBookingWithInvalidBookingId() {
            // Step 1: Get the old booking (استخدم المتغير بدلاً من النص)
            Response oldBookingResponse = BookingUtils.getBookingByIdTT(bookingId);
            Booking oldBooking = oldBookingResponse.as(Booking.class);

            // Step 2: Update data
            oldBooking.setFirstname("nour");
            oldBooking.setLastname("is");

            // Step 3: Send with invalid ID
            String invalidBookingId = "999999"; // ID غير موجود
            Response responseOfUpdate = BookingUtils.updateBooking(invalidBookingId, oldBooking);

            // Step 4: Assert
            Assert.assertEquals(responseOfUpdate.getStatusCode(), 405);
            System.out.println(responseOfUpdate.asString());
        }
    }





