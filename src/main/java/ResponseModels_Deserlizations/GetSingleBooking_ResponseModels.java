package ResponseModels_Deserlizations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSingleBooking_ResponseModels {


        @JsonProperty("firstname")
        public String firstname;
        @JsonProperty("lastname")
        public String lastname;
        @JsonProperty("totalprice")
        public Integer totalprice;
        @JsonProperty("depositpaid")
        public Boolean depositpaid;
        @JsonProperty("bookingdates")
        public Bookingdates bookingdates;
        @JsonProperty("additionalneeds")
        public String additionalneeds;

        //Inner Class
        public class Bookingdates {

            @JsonProperty("checkin")
            public String checkin;
            @JsonProperty("checkout")
            public String checkout;

        }


}
