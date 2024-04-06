package nz.ac.auckland.se281;

public class bookings {

  private String venueCode;
  private String venueDate;
  private String customerEmail;
  private String numberOfGuests;
  private String bookingReference;

  public bookings(String bookingReference, String venueCode, String venueDate, String customerEmail,
      String numberOfGuests) {
    this.venueCode = venueCode;
    this.venueDate = venueDate;
    this.numberOfGuests = numberOfGuests;
    this.customerEmail = customerEmail;
    this.bookingReference = bookingReference;
  }

  public String getBookingReference() {
    return this.bookingReference;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public String getvenueDate() {
    return this.venueDate;
  }

  public String getNumberOfGuests() {
    return this.numberOfGuests;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

}
