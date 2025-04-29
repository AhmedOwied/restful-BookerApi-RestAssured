package Utils;

import PojoClasses.Booking;
import PojoClasses.BookingDates;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Utils.Constants.*;
import static endpoints.Routes.Booking_Url;
import static io.restassured.RestAssured.given;

public class BookingUtils {

    public String bookingId;
    public String token;

    public static Response createBooking() {
        /*PojoClass*/
        BookingDates dates = new BookingDates(CHECK_IN, CHECK_OUT);

        Booking booking = new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, dates, ADDITIONAL_NEEDS);

        return given()
                     .contentType(ContentType.JSON)
                     .accept("application/json")
                     .body(booking)
                .when()
                    .post(Booking_Url)
                .then().log().all()
                .extract().response();
    }

    public static Response updateBooking(String bookingId) {

        BookingDates dates = new BookingDates(CHECK_IN, CHECK_OUT);

        //Update firstName only
        Booking booking = new Booking(UPDATED_FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, dates, ADDITIONAL_NEEDS);

        return given()
                   .contentType(ContentType.JSON)
                   .accept("application/json")
                   .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                   .body(booking)
                .when()
                   .put(Booking_Url+"/"+bookingId)
                .then().log().all()
                .extract().response();
    }

    public static Response deleteBooking(String bookingId){
        return given()
                  .contentType(ContentType.JSON)
                  .accept("application/json")
                  .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                   .delete(Booking_Url+"/"+bookingId)
                .then().log().all()
                  .extract().response();
    }


}
