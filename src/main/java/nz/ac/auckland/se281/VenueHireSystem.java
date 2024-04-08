package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  protected ArrayList<Venue> allTheVenues = new ArrayList<Venue>();
  protected ArrayList<Booking> allBookings = new ArrayList<Booking>();
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
    int totalVenues = allTheVenues.size();
    if (totalVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    if (totalVenues == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", convertToText(totalVenues), "");
    } else if (totalVenues > 1 && totalVenues < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", convertToText(totalVenues), "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(totalVenues), "s");
    }

    for (Venue venue : allTheVenues) {
      if (systemDate == null) {
        MessageCli.CURRENT_DATE.printMessage("is not set");
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenuName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee());
      } else {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenuName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            venue.getNextAvailableDate(systemDate));
      }

    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    Venue venueBeingCreated = new Venue(venueName, venueCode, capacityInput, hireFeeInput, new ArrayList<>());

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    } else if (venueCode.isEmpty()) {
      System.out.println("Venue not created: venue code must not be empty.");
      return;
    }

    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenuName());
        return;
      }
    }

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
    String givenVenueCode = options[0];
    String givenDate = options[1];
    String givenEmail = options[2];
    String givenGuests = options[3];
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    String venueName = "";
    String venueCapacity = "";
    int venueCapacityInt = 0;
    int adjustedCapacity = 0;
    Venue requestedVenue = null;
    ArrayList<Booking> requestedVenueBookings = new ArrayList<Booking>();

    if (this.systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (allTheVenues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(givenVenueCode)) {
        requestedVenue = venue;
        break;
      }
    }

    if (requestedVenue == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(givenVenueCode);
      return;
    } else {
      venueName = requestedVenue.getVenuName();
      venueCapacity = requestedVenue.getCapacity();
      requestedVenueBookings = requestedVenue.getCurrentVenueBookings();
      venueCapacityInt = Integer.parseInt(venueCapacity);
      adjustedCapacity = venueCapacityInt / 4;
    }

    if (Integer.valueOf(givenGuests) < venueCapacityInt / 4) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          givenGuests, String.valueOf(adjustedCapacity), venueCapacity);
    }

    for (Booking existingBooking : requestedVenueBookings) {
      if (existingBooking.getVenueCode().equals(givenVenueCode)
          && existingBooking.getBookingDate().equals(givenDate)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, givenDate);
        return;
      }

    }

    Booking booking = new Booking(bookingReference, givenVenueCode, givenDate, givenEmail, givenGuests, venueName);

    requestedVenue.addBooking(booking);
    allBookings.add(booking);

    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, venueName, givenDate, givenGuests);
  }

  public String getSystemDate() {
    return this.systemDate;
  }

  public void printBookings(String venueCode) {
    Venue requestedVenue = null;
    ArrayList<Booking> requestedVenueBookings = new ArrayList<Booking>();

    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        requestedVenue = venue;
        break;
      }
    }

    if (requestedVenue == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    } else {
      requestedVenueBookings = requestedVenue.getCurrentVenueBookings();
    }

    if (requestedVenueBookings.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(requestedVenue.getVenuName());
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(requestedVenue.getVenuName());
      return;
    }
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(requestedVenue.getVenuName());
    for (Booking booking : requestedVenueBookings) {
      MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
          booking.getBookingReference(),
          booking.getBookingDate());
    }
  }

  private Venue getVenueByCode(String venueCode) {
    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        return venue;
      }
    }
    return null;
  }

  private Booking getBookingByReference(String bookingReference) {
    for (Booking booking : allBookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        return booking;
      }
    }
    return null;
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    Booking booking = getBookingByReference(bookingReference);

    if (booking != null) {
      CateringService cateringService = new CateringService(booking, cateringType);
      cateringService.addService();

      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(cateringType.toString(), bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    }

  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {

  }
}
