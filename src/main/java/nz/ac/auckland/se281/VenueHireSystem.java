package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<String> venuData;

  public VenueHireSystem() {
    venuData =  new ArrayList<>();
  }

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
              String data = String.format("%s %s %s %s",venueName,venueCode,capacityInput,hireFeeInput);
              venuData.add(data);

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
