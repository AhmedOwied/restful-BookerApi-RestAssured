package PojoClasses;

public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private PojoClasses.BookingDates bookingdates;
    private String additionalneeds;

    // Constructors empty
    public Booking() {}

    //Constructor With Param

    public Booking(String firstname, String lastname, int totalPrice, boolean depositPaid, BookingDates dates, String additionalNeeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalPrice;
        this.depositpaid = depositPaid;
        this.bookingdates = dates;
        this.additionalneeds = additionalNeeds;
    }



    // Getters and Setters

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }


    // ... (أضف باقي الـ Getters/Setters بنفس الطريقة)
}

