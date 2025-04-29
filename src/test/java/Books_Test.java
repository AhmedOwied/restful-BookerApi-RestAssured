
import Utils.BookingUtils;
import Utils.LoginUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Books_Test {
    public String bookingId;
    public String token;

    //@BeforeClass
    @Test
    public void Auth(){
        Response response = LoginUtils.Auth();

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
    public void createBookingTest(){
        Response response= BookingUtils.createBooking();  //createBooking is a Pojo Class

        /*Extract response body and Assert*/
        JsonPath jsonPath= response.jsonPath();
        bookingId = jsonPath.getString("bookingid");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed","firstName not found");

        System.out.println(response.prettyPrint());

    }

    @Test(dependsOnMethods = "createBookingTest")
    public void updateBookingTest(){
        Response response= BookingUtils.updateBooking(bookingId);

        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"),"mona");
        Assert.assertEquals(response.getStatusCode(),200);

        System.out.println(jsonPath.getString("firstname"));
        System.out.println(bookingId);
        System.out.println(response.prettyPrint());
    }

    @Test(dependsOnMethods = "updateBookingTest")
    public void deleteBookingTest(){
        Response response =BookingUtils.deleteBooking(bookingId);

        String responseBody=response.getBody().asString();
        //System.out.println(response.asString());
        Assert.assertTrue(responseBody.contains("Created"), "Response not contain message 'Created'");
        Assert.assertEquals(response.getStatusCode(),201);

    }



}
