import javax.swing.*;
import java.awt.*;

public class title extends JPanel {

    static final int width = Panel.width;
    static final int height = 120;

    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/title.png"));

    title() {
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Panel.blue);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(image, (width - 269) / 2, 20, 269, 100, this);
    }
}
