package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
    MessageCli.NO_VENUES.printMessage();
  }

 
  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
      
  if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
  } else {
      try {
          int capacity = Integer.parseInt(capacityInput);
          int hireFee = Integer.parseInt(hireFeeInput);
          
          if (capacity < 0) {
              MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
          } else if (hireFee < 0) {
              MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "positive");
          } else {
              MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
          }
      } catch (NumberFormatException e) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      }
  }
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
