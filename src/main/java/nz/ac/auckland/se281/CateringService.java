package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private CateringType cateringType;

  public CateringService(Booking booking, CateringType cateringType) {
    super(booking);
    this.cateringType = cateringType;
  }

  @Override
  public void addService() {
    booking.addService(this);
  }

  @Override
  protected Integer getServiceCost() {
    return cateringType.getCostPerPerson();
  }

}
