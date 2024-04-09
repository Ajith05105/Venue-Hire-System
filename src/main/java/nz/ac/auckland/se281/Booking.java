package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking extends VenueHireSystem {

  private String venueCode;
  private String venueDate;
  private String customerEmail;
  private String numberOfGuests;
  private String bookingReference;
  private String venueName;
  private ArrayList<Service> services = new ArrayList<Service>();

  public Booking(String bookingReference, String venueCode, String venueDate, String customerEmail,
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

  public String getBookingDate() {
    return this.venueDate;
  }

  public String getNumberOfGuests() {
    return this.numberOfGuests;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public void addService(Service service) {
    services.add(service);
  }

  public ArrayList<Service> getServices() {
    return services;
  }

}