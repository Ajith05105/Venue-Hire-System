package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<ArrayList<String>> venueData;
  int bookings;
  private String dateInput;

  public VenueHireSystem() {
    venueData = new ArrayList<>();
  }

  public String convertToText(int number) {
    String[] ONES = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    if (number >= 1 && number <= 9) {
      return ONES[number];

    } else {
      return "Unsupported number";
    }
  }

  public void printVenues() {
    this.bookings = bookings;
    bookings = 0;

    for (int i = 0; i < venueData.size(); i++) {
      bookings++;
    }
    if (bookings == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", convertToText(bookings), "");
      for (ArrayList<String> venue : venueData) {
        String venueName = venue.get(0);
        String venueCode = venue.get(1);
        String capacity = venue.get(2);
        String hireFee = venue.get(3);

        MessageCli.VENUE_ENTRY.printMessage(venueName, venueCode, capacity, hireFee);
      }
    } else if (bookings > 1 && bookings < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", convertToText(bookings), "s");
      for (ArrayList<String> venue : venueData) {
        String venueName = venue.get(0);
        String venueCode = venue.get(1);
        String capacity = venue.get(2);
        String hireFee = venue.get(3);

        MessageCli.VENUE_ENTRY.printMessage(venueName, venueCode, capacity, hireFee);
      }
    } else if (bookings >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(bookings), "s");
      for (ArrayList<String> venue : venueData) {
        String venueName = venue.get(0);
        String venueCode = venue.get(1);
        String capacity = venue.get(2);
        String hireFee = venue.get(3);

        MessageCli.VENUE_ENTRY.printMessage(venueName, venueCode, capacity, hireFee);
      }

    } else {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    for (ArrayList<String> venue : venueData) {
      if (venueCode.equals(venue.get(1))) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.get(0));
        return; // Exit method if duplicate venue code found
      }
    }

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (venueCode.isEmpty()) {
      System.out.println("Venue not created: venue code must not be empty.");
      ;
    } else {
      try {
        int capacity = Integer.parseInt(capacityInput);
        int hireFee = Integer.parseInt(hireFeeInput);

        if (capacity < 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");

        } else if (hireFee < 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        } else {
          MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
          ArrayList<String> venueDataItem = new ArrayList<>();
          venueDataItem.add(venueName);
          venueDataItem.add(venueCode);
          venueDataItem.add(capacityInput);
          venueDataItem.add(hireFeeInput);
          venueData.add(venueDataItem);
        }
      } catch (NumberFormatException e) {
        if (!capacityInput.matches("\\d+")) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
        } else {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
        }
      }
    }
  }

  public void setSystemDate(String dateInput) {
    this.dateInput = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);

  }

  public void printSystemDate() {

    if (this.dateInput == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.dateInput);
    }
  }

  public void makeBooking(String[] options) {
    String gmail = options[2];
    String date = options[1];
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    String attendees = options[3];
    String venuName = null;
    for (ArrayList<String> venue : venueData) {
      if (options[0].equals(venue.get(1))) {
        venuName = venue.get(0);
        break;
      }
    }

    if (venueData.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    if (this.dateInput == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    if (venuName == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[1]);
      return;
    } else {
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, venuName, date, attendees);
    }

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
