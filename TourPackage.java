public class TourPackage {
    private static int counter = 1; // auto ID generator
    private int id;
    private String name;
    private String location;
    private int days;
    private double price;

    public TourPackage(String name, String location, int days, double price) {
        this.id = counter++;
        this.name = name;
        this.location = location;
        this.days = days;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getDays() { return days; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + location + " | " + days + " days | ₹" + price;
    }
}
