import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Color bgColor = new Color(255, 20, 147); // DeepPink

    public RoundedButton(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 14));
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bgColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
        super.paintComponent(g);
    }
}
