package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private String venueCode;
  private String venuName;

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
    MessageCli.NO_VENUES.printMessage();
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
        this.venueCode = venueCode;
        this.venuName = venueName ;
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode);
    
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
