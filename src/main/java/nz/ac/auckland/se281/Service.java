package nz.ac.auckland.se281;

public abstract class Service {
  protected Booking booking;

  public Service(Booking booking) {
    this.booking = booking;
  }

  public abstract void addService();

  protected abstract Integer getServiceCost();

  protected abstract String getServiceName();

}
