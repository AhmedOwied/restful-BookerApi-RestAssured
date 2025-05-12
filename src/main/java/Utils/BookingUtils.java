package Utils;

import PojoClasses.Booking;

import PojoClasses.BookingDates;
import ResponseModels_Deserlizations.GetSingleBooking_ResponseModels;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Utils.Constants.*;
import static endpoints.Routes.Booking_URL;
import static io.restassured.RestAssured.given;

public class BookingUtils {

    public String bookingId;
    public String token;

    public static Response createBooking(Booking booking) {
        /*PojoClass*/
        //BookingDates dates = new BookingDates(CHECK_IN, CHECK_OUT);

        //Booking booking = new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, dates, ADDITIONAL_NEEDS);

        return given().log().all()
                     .contentType(ContentType.JSON)
                     .accept("application/json")
                     .body(booking)
                .when()
                    .post(Booking_URL)
                .then().log().all()
                .extract().response();
    }
   //TODO::Applied Deserialization
    public static GetSingleBooking_ResponseModels getBookingById(String id){
        return given()
                   .contentType(ContentType.JSON)
                   .accept("application/json")
                .when()
                   .get(Booking_URL+"/"+id)
                .then().log().all().extract().response().as(GetSingleBooking_ResponseModels.class);
    }


    public static Response getBookingByIdTT(String id){
        return given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .when()
                .get(Booking_URL+"/"+id)
                .then().log().all().extract().response();
    }


    //TODO::Without Deserialization
    public static Response getBookingByIdII(String id){
        return given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .when()
                .get(Booking_URL+"/"+id)
                .then().log().all().extract().response();
    }

    public static Response updateBooking(String bookingId,Booking booking) {

      // BookingDates dates = new BookingDates(CHECK_IN, CHECK_OUT);

        //Update firstName only
        //Booking booking = new Booking(UPDATED_FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, dates, ADDITIONAL_NEEDS);

        return given()
                   .contentType(ContentType.JSON)
                   .accept("application/json")
                   .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                   .body(booking)
                .when()
                   .put(Booking_URL+"/"+bookingId)
                .then().log().all()
                .extract().response();
    }


    public static Response deleteBooking(String bookingId){
        return given()
                  .contentType(ContentType.JSON)
                  .accept("application/json")
                  .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                   .delete(Booking_URL+"/"+bookingId)
                .then().log().all()
                  .extract().response();
    }


}
