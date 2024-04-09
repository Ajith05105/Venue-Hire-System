package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue extends VenueHireSystem {

  private String venuName;
  private String venueCode;
  private String capacity;
  private String hireFee;
  private ArrayList<Booking> currentVenueBookings = new ArrayList<>();

  public Venue(String venuName, String venueCode, String capacity, String hireFee,
      ArrayList<Booking> currentVenueBookings) {

    this.venuName = venuName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.currentVenueBookings = currentVenueBookings;
  }

  public String getNextAvailableDate(String systemDate) {
    String[] dateParts = systemDate.split("/");

    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    if (currentVenueBookings.size() == 0) {
      return systemDate;
    } else {
      while (true) {
        boolean dateAvailable = true;
        // go through the bookings and find the next available date
        for (Booking booking : currentVenueBookings) {
          String[] bookingDateParts = booking.getBookingDate().split("/");
          int bookingDay = Integer.parseInt(bookingDateParts[0]);
          int bookingMonth = Integer.parseInt(bookingDateParts[1]);
          int bookingYear = Integer.parseInt(bookingDateParts[2]);

          if (bookingDay == (day) && bookingMonth == (month) && bookingYear == (year)) {
            dateAvailable = false;
            break;
          }
        }

        if (dateAvailable) {
          return String.format("%02d/%02d/%d", day, month, year);
        }

        day++;
        if (day > 31) {
          day = 1;
          month++;
          if (month > 12) {
            month = 1;
            year++;
          }
        }

        if (day == Integer.parseInt(dateParts[0]) && month == Integer.parseInt(dateParts[1])
            && year == Integer.parseInt(dateParts[2])) {
          return "No available dates"; 
        }

      }

    }

  }

  public String getVenuName() {
    return this.venuName;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public String getCapacity() {
    return this.capacity;
  }

  public String getHireFee() {
    return this.hireFee;
  }

  public ArrayList<Booking> getCurrentVenueBookings() {
    return this.currentVenueBookings;
  }

  public void addBooking(Booking booking) {
    this.currentVenueBookings.add(booking);
  }

}
