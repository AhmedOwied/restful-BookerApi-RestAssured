package Tests;

import PojoClasses.Booking;
import PojoClasses.BookingDates;
import ResponseModels_Deserlizations.GetSingleBooking_ResponseModels;
import Utils.BookingUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static endpoints.Routes.Booking_URL;
import static io.restassured.RestAssured.given;

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
    public void CreateBookingWithMissingFirstname(){
        Booking booking =new Booking(null, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);

        Response response = BookingUtils.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 500);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Internal Server Error"));

    }
    @Test
    public void createBooking_IllogicalDates_ShouldFail()
    {
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID,  new BookingDates("2023-01-10", "2023-01-05"), ADDITIONAL_NEEDS);

        Response response = BookingUtils.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 500); // Ideally should be 422 or 400


    }
    @Test
    public void createBooking_EmptyFirstname_ShouldFail() {
        Booking booking = new Booking("", "Elsayed", TOTAL_PRICE, true,
                new BookingDates("2023-01-01", "2023-01-05"), "Breakfast");

        Response response = BookingUtils.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void createBooking_NullBookingDates_ShouldFail() {
        Booking booking = new Booking("Ahmed", "Elsayed", TOTAL_PRICE, true,
                null, "Breakfast");

        Response response = BookingUtils.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 500);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Internal Server Error"));

    }
    @Test
    public void createBooking_InvalidTotalPrice_ShouldFail() {
        String invalidJson = "{\n" +
                "  \"firstname\": \"Ahmed\",\n" +
                "  \"lastname\": \"Elsayed\",\n" +
                "  \"totalprice\": \"invalid\",\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2023-01-01\",\n" +
                "    \"checkout\": \"2023-01-02\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(invalidJson)
                .when()
                .post(Booking_URL)
                .then().log().all()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 200); // لأنه فعليًا بيرجع 200

        JsonPath jsonPath = response.jsonPath();
        Integer totalPrice = jsonPath.get("booking.totalprice");  //Integer بنقبل ال null ,, بس الint مش بيقبل ال null
        System.out.println(totalPrice);
        Assert.assertNull(totalPrice, "Expected totalprice to be null when sending invalid input");
    }
    @Test
    public void CreateBookingWithStringINDateField(){
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, new BookingDates("not-a-date","123456"), ADDITIONAL_NEEDS);

        Response response = BookingUtils.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        String checkin = jsonPath.getString("booking.bookingdates.checkin");
        Assert.assertNull(checkin, "Expected checkin to be null for invalid input");


    }

}
