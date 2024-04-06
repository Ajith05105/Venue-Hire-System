package nz.ac.auckland.se281;
import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;


public class venue {
  
  
  private String venuName;
  private String venueCode;
  private String capacity;
  private String hireFee;

  public venue(String venuName, String venueCode, String capacity, String hireFee) {
    this.venuName = venuName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
   
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
