import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class App extends JFrame {
    App() throws IOException {
        this.setTitle("Ultimate Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/logo.png"));
        this.setIconImage(icon);

        JPanel parent = new JPanel();
        parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
        parent.add(new title());
        parent.add(new Panel());

        this.add(parent);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
    }
}
