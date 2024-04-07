package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class venue extends VenueHireSystem {

  private String venuName;
  private String venueCode;
  private String capacity;
  private String hireFee;
  private ArrayList<bookings> currentVenueBookings = new ArrayList<bookings>();

  public venue(String venuName, String venueCode, String capacity, String hireFee,
      ArrayList<bookings> currentVenueBookings) {
    this.venuName = venuName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.currentVenueBookings = currentVenueBookings;

  }

  public String getNextAvailableDate(String systemDate) {
    if (currentVenueBookings.isEmpty()) {
      return systemDate;
    }

    // Split the system date into day, month, and year.
    String[] dateParts = systemDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    // Check each day starting from the system date until an available date is
    // found.
    while (true) {
      boolean dateAvailable = true;

      // Check if there is a booking for the current date.
      for (bookings booking : currentVenueBookings) {
        String[] bookingDateParts = booking.getvenueDate().split("/");
        int bookingDay = Integer.parseInt(bookingDateParts[0]);
        int bookingMonth = Integer.parseInt(bookingDateParts[1]);
        int bookingYear = Integer.parseInt(bookingDateParts[2]);

        // If there's a booking for the current date, mark it as unavailable.
        if (day == bookingDay && month == bookingMonth && year == bookingYear) {
          dateAvailable = false;
          break; // No need to check further, as the date is already booked.
        }
      }

      // If the date is available, return it.
      if (dateAvailable) {
        return String.format("%02d/%02d/%d", day, month, year);
      }

      // Move to the next day.
      day++;
      if (day > 31) {
        day = 1;
        month++;
        if (month > 12) {
          month = 1;
          year++;
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

}
