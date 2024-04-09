package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  protected ArrayList<Venue> allTheVenues = new ArrayList<Venue>();
  protected ArrayList<Booking> allBookings = new ArrayList<Booking>();
  private String systemDate;
  int bookings;

  public VenueHireSystem() {}

  // method to convert a number to text while printing the number of venues
  public String convertToText(int number) {
    String[] ONES = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    if (number >= 1 && number <= 9) {
      return ONES[number];

    } else {
      return "Unsupported number";
    }
  }

  // method to print the venues
  public void printVenues() {
    int totalVenues = allTheVenues.size();
    if (totalVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }
    // validating the number of venues
    if (totalVenues == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", convertToText(totalVenues), "");
    } else if (totalVenues > 1 && totalVenues < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", convertToText(totalVenues), "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(totalVenues), "s");
    }
    // printing the venues
    // checking if the system date is set
    for (Venue venue : allTheVenues) {
      if (systemDate == null) {
        MessageCli.CURRENT_DATE.printMessage("is not set");
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenuName(), venue.getVenueCode(), venue.getCapacity(), venue.getHireFee());
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

  // method to create a venue

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // creating a new venue object

    Venue venueBeingCreated =
        new Venue(venueName, venueCode, capacityInput, hireFeeInput, new ArrayList<>());
    // validating the venue name and code

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    } else if (venueCode.isEmpty()) {
      System.out.println("Venue not created: venue code must not be empty.");
      return;
    }

    // checking if the venue code already exists

    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenuName());
        return;
      }
    }
    // validating the capacity and hire fee
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

  // method to set the system date
  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  // method to print the system date

  public void printSystemDate() {
    if (this.systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.systemDate);
    }
  }

  // method to make a booking

  public void makeBooking(String[] options) {
    // getting the venue code, date, email and number of guests from options
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
    // creating an array list of bookings for each venue
    ArrayList<Booking> requestedVenueBookings = new ArrayList<Booking>();

    // validating the date, venue code and number of guests

    if (this.systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (allTheVenues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // getting the venue object from the venue code

    requestedVenue = getVenueByCode(givenVenueCode);

    // checking if the venue exists

    if (requestedVenue == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(givenVenueCode);
      return;
    } else {
      // getting the venue name, capacity and bookings out of the found venue
      venueName = requestedVenue.getVenuName();
      venueCapacity = requestedVenue.getCapacity();
      requestedVenueBookings = requestedVenue.getCurrentVenueBookings();
      venueCapacityInt = Integer.parseInt(venueCapacity);
      adjustedCapacity = venueCapacityInt / 4;
    }
    // validating the number of guests so that they are more than a quatert of the
    // venue capacity
    if (Integer.valueOf(givenGuests) < venueCapacityInt / 4) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          givenGuests, String.valueOf(adjustedCapacity), venueCapacity);
    }
    // checking if the venue is already booked on the given date
    for (Booking existingBooking : requestedVenueBookings) {
      if (existingBooking.getVenueCode().equals(givenVenueCode)
          && existingBooking.getBookingDate().equals(givenDate)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, givenDate);
        return;
      }
    }
    // creating a new booking object

    Booking booking =
        new Booking(
            bookingReference, givenVenueCode, givenDate, givenEmail, givenGuests, venueName);
    // adding the booking to the venue and all bookings array list
    requestedVenue.addBooking(booking);
    allBookings.add(booking);

    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, venueName, givenDate, givenGuests);
  }

  // method to print the bookings
  public void printBookings(String venueCode) {
    // getting the venue object from the venue code, and setting it null initially
    Venue requestedVenue = null;
    // creating an array list of bookings for each venue
    ArrayList<Booking> requestedVenueBookings = new ArrayList<Booking>();

    // getting the required venue and its bookings
    requestedVenue = getVenueByCode(venueCode);
    if (requestedVenue == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    } else {
      requestedVenueBookings = requestedVenue.getCurrentVenueBookings();
    }
    // checking if the venue has any bookings
    if (requestedVenueBookings.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(requestedVenue.getVenuName());
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(requestedVenue.getVenuName());
      return;
    }
    // printing the bookings
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(requestedVenue.getVenuName());
    for (Booking booking : requestedVenueBookings) {
      MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
          booking.getBookingReference(), booking.getBookingDate());
    }
  }

  // method to find a venue by using the venue code

  private Venue getVenueByCode(String venueCode) {
    for (Venue venue : allTheVenues) {
      if (venue.getVenueCode().equals(venueCode)) {
        return venue;
      }
    }
    return null;
  }

  // method to find a booking by using the booking reference
  private Booking getBookingByReference(String bookingReference) {
    for (Booking booking : allBookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        return booking;
      }
    }
    return null;
  }

  // method to add a catering service

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // getting the booking object from the booking reference
    Booking booking = getBookingByReference(bookingReference);
    // creating a catering message
    String cateringMessage = String.format("Catering (%s)", cateringType.getName());
    // checking if the booking exists
    if (booking != null) {
      CateringService cateringService = new CateringService(booking, cateringType);
      cateringService.addService();
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(cateringMessage, bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    }
  }

  // method to add a music service
  public void addServiceMusic(String bookingReference) {
    // getting the booking object from the booking reference
    Booking booking = getBookingByReference(bookingReference);
    // checking if the booking exists
    if (booking != null) {
      // creating a music service object
      MusicService musicService = new MusicService(booking);
      musicService.addService();
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
    }
  }

  // method to add a floral service
  public void addServiceFloral(String bookingReference, FloralType floralType) {
    Booking booking = getBookingByReference(bookingReference);
    String floralMessage = String.format("Floral (%s)", floralType.getName());
    if (booking != null) {
      FloralService floralService = new FloralService(booking, floralType);
      floralService.addService();
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(floralMessage, bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
    }
  }

  //  method to view an invoice
  public void viewInvoice(String bookingReference) {
    Booking booking = getBookingByReference(bookingReference);
    // checking if the booking exists and grabbing the details
    if (booking != null) {
      String customerEmail = booking.getCustomerEmail();
      String bookingDate = systemDate;
      String partyDate = booking.getBookingDate();
      String numberOfGuests = booking.getNumberOfGuests();
      String venueName = booking.getVenueName();
      Integer venueFee = 0;
      Integer cateringFee = 0;
      Integer musicFee = 0;
      Integer floralFee = 0;
      Integer totalServicesCost = 0;

      // getting the cost of each service

      for (Service service : booking.getServices()) {
        if (service instanceof CateringService) {
          cateringFee += service.getServiceCost();
        } else if (service instanceof MusicService) {
          musicFee += service.getServiceCost();
        } else if (service instanceof FloralService) {
          floralFee += service.getServiceCost();
        }
      }

      // getting the venue fee and the total cost of all services

      venueFee = Integer.parseInt(getVenueByCode(booking.getVenueCode()).getHireFee());
      totalServicesCost = cateringFee + musicFee + floralFee + venueFee;

      // printing the invoice

      MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
          bookingReference, customerEmail, bookingDate, partyDate, numberOfGuests, venueName);

      MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(String.valueOf(venueFee));
      if (cateringFee > 0) {
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            booking.getServices().get(0).getServiceName(), String.valueOf(cateringFee));
      }
      if (musicFee > 0) {
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(musicFee));
      }
      if (floralFee > 0) {
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            booking.getServices().get(0).getServiceName(), String.valueOf(floralFee));
      }

      MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(totalServicesCost));
    } else {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
    }
  }
}
