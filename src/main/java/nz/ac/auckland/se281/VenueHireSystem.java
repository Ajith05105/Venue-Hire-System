package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<venue> allTheVenues = new ArrayList<venue>();
  private ArrayList<bookings> allTheBookings = new ArrayList<bookings>();
  private String systemDate;
  int bookings;

  public VenueHireSystem() {
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
    int bookings = allTheVenues.size(); // Update bookings count based on the size of allTheVenues
    if (bookings == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    if (bookings == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", convertToText(bookings), "");
    } else if (bookings > 1 && bookings < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", convertToText(bookings), "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(bookings), "s");
    }

    for (venue venue : allTheVenues) {
      MessageCli.VENUE_ENTRY.printMessage(venue.getVenuName(), venue.getVenueCode(), venue.getCapacity(),
          venue.getHireFee());
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    venue venueBeingCreated = new venue(venueName, venueCode, capacityInput, hireFeeInput);

    for (venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenuName());
        return;
      }
    }

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();

    } else if (venueCode.isEmpty()) {
      System.out.println("Venue not created: venue code must not be empty.");

    } else {
      try {
        int capacityInt = Integer.parseInt(capacityInput);
        int hireFeeInt = Integer.parseInt(hireFeeInput);

        if (capacityInt < 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        } else if (hireFeeInt < 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        } else {
          MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
          allTheVenues.add(venueBeingCreated);
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
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);

  }

  public void printSystemDate() {
    if (this.systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.systemDate);
    }

  }

  public void makeBooking(String[] options) {
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    bookings booking = new bookings(bookingReference, options[0], options[1], options[2], options[3]);

    if (this.systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (allTheVenues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;

    }
    for (venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(options[0])) {
        if (allTheBookings.size() == 0) {
          MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, venue.getVenuName(), options[1],
              options[3]);
          allTheBookings.add(booking);
          return;
        } else {
          for (bookings booking1 : allTheBookings) {
            if (booking1.getVenueCode().equals(options[0]) && booking1.getvenueDate().equals(options[1])) {
              MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venue.getVenuName(), options[1]);
              return;
            } else {
              MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, venue.getVenuName(), options[1],
                  options[3]);
              allTheBookings.add(booking);
              return;
            }
          }
        }
      } else {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
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
