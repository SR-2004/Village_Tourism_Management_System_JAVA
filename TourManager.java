import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private List<TourPackage> packages = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public void addPackage(TourPackage tp) {
        packages.add(tp);
    }

    public List<TourPackage> getPackages() {
        return packages;
    }

    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
