public class Booking {
    private TourPackage tourPackage;
    private String customerName;
    private int peopleCount;

    public Booking(TourPackage tourPackage, String customerName, int peopleCount) {
        this.tourPackage = tourPackage;
        this.customerName = customerName;
        this.peopleCount = peopleCount;
    }

    public TourPackage getTourPackage() { return tourPackage; }
    public String getCustomerName() { return customerName; }
    public int getPeopleCount() { return peopleCount; }

    @Override
    public String toString() {
        return "Customer: " + customerName +
                " | Package: " + tourPackage.getName() +
                " | Location: " + tourPackage.getLocation() +
                " | Days: " + tourPackage.getDays() +
                " | People: " + peopleCount;
    }
}
