package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends Service {
  private FloralType floralType;

  public FloralService(Booking booking, FloralType floralType) {
    super(booking);
    this.floralType = floralType;
  }

  @Override
  public void addService() {
    booking.addService(this);
  }

  @Override
  protected Integer getServiceCost() {
    return floralType.getCost() ;
  }

  @Override
  public String getServiceName() {
    return floralType.getName();
  }

}
