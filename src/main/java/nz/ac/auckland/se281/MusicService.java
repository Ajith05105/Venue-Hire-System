package nz.ac.auckland.se281;

public class MusicService extends Service{

    public MusicService(Booking booking) {
        super(booking);
    }

    @Override
    public void addService() {
        booking.addService(this);
    }

    @Override
    protected Integer getServiceCost() {
        return 500 ;
    }

    @Override
    public String getServiceName() {
        return "Music";
    }

}
