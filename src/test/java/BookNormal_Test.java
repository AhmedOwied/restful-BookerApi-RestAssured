
import static Utils.Constants.*;
import static io.restassured.RestAssured.given;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
/*
public class BookNormal_Test {
    public String bookingId;
    public String token;

    @BeforeClass
    public void Auth(){
        HashMap<String,String> body =new HashMap<>();
        body.put("username","admin");
        body.put("password","password123");

        Response response =given()
                .contentType(ContentType.JSON)
                //add Auth
                .body(body)
                .when()
                   .post(BASE_URL+AUTH_ENDPOINT)
                .then()
                .statusCode(200).extract().response();

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


    }

    @Test
    public void createBooking(){
        HashMap<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        HashMap<String,Object> body =new HashMap<>();
        body.put("firstname","Ahmed");
        body.put("lastname","Elsayed");
        body.put("totalprice",2020);
        body.put("depositpaid",bookingDates);
        body.put("additionalneeds","Breakfast1");



        Response response =given()
                    .contentType(ContentType.JSON)  //add Auth
                .body(body)
                .when()
                    .post(BASE_URL+BOOKING_ENDPOINT)
                .then()
                .statusCode(200).extract().response();

        //Extract response body and Assert
        JsonPath jsonPath= response.jsonPath();
        bookingId = jsonPath.getString("bookingid");
        System.out.println(bookingId);
        //Assert.assertEquals(jsonPath.getString("booking.firstname"),"Ahmed","firstName not found");

        System.out.println(response.prettyPrint());

    }

    @Test(dependsOnMethods = "createBooking")
    public void updateSingleBooking(){
        Response response =given()
                     .contentType(ContentType.JSON)
                     .accept("application/json")
                     //.cookie("token", token)
                     .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=") //or
                     .body("{\n" +
                        "    \"firstname\": \"Mona\",\n" +
                        "    \"totalprice\": 1515\n" +
                        "}")
                .when()
                      .patch(Booking_Url +"/"+bookingId)
                .then().log().all()
                .statusCode(200).extract().response();

        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"),"Mona");
        System.out.println(jsonPath.getString("firstname"));

        System.out.println(response.prettyPrint());
    }


    @Test(dependsOnMethods = "updateSingleBooking")
    public void getSingleBooking(){
        Response response =given()
                    .contentType(ContentType.JSON)
                .when()
                    .get(BASE_URL+BOOKING_ENDPOINT+"/"+bookingId)
                .then().log().all()
                .statusCode(200).extract().response();

        //System.out.println(response.prettyPrint());


    }



}
*/