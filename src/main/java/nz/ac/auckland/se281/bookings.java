package nz.ac.auckland.se281;

public class bookings extends VenueHireSystem {

  private String venueCode;
  private String venueDate;
  private String customerEmail;
  private String numberOfGuests;
  private String bookingReference;
  private String venueName;

  public bookings(String bookingReference, String venueCode, String venueDate, String customerEmail,
      String numberOfGuests, String venueName) {
    this.venueCode = venueCode;
    this.venueDate = venueDate;
    this.numberOfGuests = numberOfGuests;
    this.customerEmail = customerEmail;
    this.bookingReference = bookingReference;
    this.venueName = venueName;
  }

  public String getVenueName() {
    return this.venueName;
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
