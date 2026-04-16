import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {
    private TourManager manager = new TourManager();

    public MainUI() {
        setTitle("Village Tour Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image from src/images folder
        BackgroundPanel bg = new BackgroundPanel("/images/village-img.jpg");
        bg.setLayout(new BorderLayout());
        setContentPane(bg);

        // Title label
        JLabel title = new JLabel("🌸 Village Tour Management 🌸", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(new Color(25, 25, 112)); // Deep Blue
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        bg.add(title, BorderLayout.NORTH);

        // Panel for buttons
        JPanel cardsPanel = new JPanel();
        cardsPanel.setOpaque(false);
        cardsPanel.setLayout(new GridLayout(1, 4, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Buttons
        RoundedButton addPackageBtn = new RoundedButton("Add Tour Package");
        RoundedButton viewPackagesBtn = new RoundedButton("View Packages");
        RoundedButton bookTourBtn = new RoundedButton("Book a Tour");
        RoundedButton viewBookingsBtn = new RoundedButton("View Bookings");

        // Set button size 250x80
        Dimension btnSize = new Dimension(250, 80);
        addPackageBtn.setPreferredSize(btnSize);
        viewPackagesBtn.setPreferredSize(btnSize);
        bookTourBtn.setPreferredSize(btnSize);
        viewBookingsBtn.setPreferredSize(btnSize);

        // Add buttons to panel
        cardsPanel.add(addPackageBtn);
        cardsPanel.add(viewPackagesBtn);
        cardsPanel.add(bookTourBtn);
        cardsPanel.add(viewBookingsBtn);

        bg.add(cardsPanel, BorderLayout.CENTER);

        // Button actions
        addPackageBtn.addActionListener(e -> addPackageDialog());
        viewPackagesBtn.addActionListener(e -> viewPackagesDialog());
        bookTourBtn.addActionListener(e -> bookTourDialog());
        viewBookingsBtn.addActionListener(e -> viewBookingsDialog());
    }

    private void addPackageDialog() {
        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField daysField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] message = {
                "Package Name:", nameField,
                "Location:", locationField,
                "Number of Days:", daysField,
                "Price ($):", priceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Tour Package", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String location = locationField.getText();
                int days = Integer.parseInt(daysField.getText());
                double price = Double.parseDouble(priceField.getText());
                manager.addPackage(new TourPackage(name, location, days, price));
                JOptionPane.showMessageDialog(this, "Tour package added successfully!");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewPackagesDialog() {
        List<TourPackage> packages = manager.getPackages();
        if(packages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No packages available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(TourPackage tp : packages) sb.append(tp).append("\n");

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scroll, "Available Packages", JOptionPane.INFORMATION_MESSAGE);
    }

    private void bookTourDialog() {
        List<TourPackage> packages = manager.getPackages();
        if(packages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No packages available to book.");
            return;
        }

        String[] options = packages.stream().map(TourPackage::getName).toArray(String[]::new);
        JComboBox<String> packageList = new JComboBox<>(options);
        JTextField nameField = new JTextField();
        JTextField peopleField = new JTextField();

        Object[] message = {
                "Select Package:", packageList,
                "Your Name:", nameField,
                "Number of People:", peopleField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Book a Tour", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            try {
                String selectedPackage = (String) packageList.getSelectedItem();
                String customerName = nameField.getText();
                int people = Integer.parseInt(peopleField.getText());
                TourPackage tp = packages.stream().filter(p -> p.getName().equals(selectedPackage)).findFirst().orElse(null);
                if(tp != null) {
                    manager.addBooking(new Booking(tp, customerName, people));
                    JOptionPane.showMessageDialog(this, "Booking successful!");
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewBookingsDialog() {
        List<Booking> bookings = manager.getBookings();
        if(bookings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bookings yet.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(Booking b : bookings) {
            sb.append("Customer: ").append(b.getCustomerName())
              .append(", Package: ").append(b.getTourPackage().getName())
              .append(", Days: ").append(b.getTourPackage().getDays())
              .append(", People: ").append(b.getPeopleCount())
              .append("\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scroll, "All Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ui = new MainUI();
            ui.setVisible(true);
        });
    }
}
