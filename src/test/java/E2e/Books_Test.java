package E2e;

import PojoClasses.Auth;
import PojoClasses.Booking;
import PojoClasses.BookingDates;
import Utils.BookingUtils;
import Utils.LoginUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;


public class Books_Test {
    public String bookingId;
    public String token;

    @BeforeClass
    public void Auth(){
        Response response = LoginUtils.Login(new Auth(USER_NAME,PASSWORD));

        //Extract data from response
        JsonPath jsonPath=response.jsonPath();
        token=jsonPath.getString("token");
    }

    /*@Test
    public void getAllBooking(){
        Response response =given()
                   .contentType(ContentType.JSON)
                .when()
                        .get(BASE_URL+BOOKING_ENDPOINT)
                .then().log().all()
                        .statusCode(200).extract().response();

        System.out.println(response.prettyPrint());


    }*/


    @Test
    public void E2E_Scenario(){
       //TODO::CreateBooking
        Booking booking =new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, new BookingDates(CHECK_IN,CHECK_OUT), ADDITIONAL_NEEDS);
        Response responseOfCreate = BookingUtils.createBooking(booking);  //createBooking -->This is a "Pojo Class"

        //Extract response body and Assert
        Assert.assertEquals(responseOfCreate.getStatusCode(),200);
        JsonPath jsonPath= responseOfCreate.jsonPath();
        jsonPath.prettyPrint();
        bookingId = jsonPath.getString("bookingid");

        Assert.assertEquals(responseOfCreate.getStatusCode(),200);
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed","firstName not found");


        //System.out.println(responseOfCreate.prettyPrint());

        //TODO::UpdateBooking
         booking.setFirstname("Mona");
         booking.setLastname("Owied");
         Response responseOfUpdate= BookingUtils.updateBooking(bookingId,booking);
         jsonPath =responseOfUpdate.jsonPath();
         Assert.assertEquals(jsonPath.getString("firstname"),"Mona");
         Assert.assertEquals(responseOfUpdate.getStatusCode(),200);

        System.out.println(jsonPath.getString("firstname"));
        System.out.println(bookingId);
        //System.out.println(responseOfUpdate.prettyPrint());

        //TODO::DeleteBooking by Id
        Response responseOfDelete =BookingUtils.deleteBooking(bookingId);

        String responseBody=responseOfDelete.getBody().asString();
        //System.out.println(response.asString());
        Assert.assertTrue(responseBody.contains("Created"), "Response not contain message 'Created'");
        Assert.assertEquals(responseOfDelete.getStatusCode(),201);



    }

    /*@Test
    public void createBookingTest(){
        Response response= BookingUtils.createBooking();  //createBooking -->This is a "Pojo Class"

        //Extract response body and Assert
        JsonPath jsonPath= response.jsonPath();
        bookingId = jsonPath.getString("bookingid");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed","firstName not found");

        System.out.println(response.prettyPrint());


    }*/

    /*@Test(dependsOnMethods = "createBookingTest")
    public void updateBookingTest(){
        Response response= BookingUtils.updateBooking(bookingId);

        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"),"mona");
        Assert.assertEquals(response.getStatusCode(),200);

        System.out.println(jsonPath.getString("firstname"));
        System.out.println(bookingId);
        System.out.println(response.prettyPrint());
    }*/

    /*@Test(dependsOnMethods = "updateBookingTest")
    public void deleteBookingTest(){
        Response response =BookingUtils.deleteBooking(bookingId);

        String responseBody=response.getBody().asString();
        //System.out.println(response.asString());
        Assert.assertTrue(responseBody.contains("Created"), "Response not contain message 'Created'");
        Assert.assertEquals(response.getStatusCode(),201);

    }*/



}
