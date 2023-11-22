import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Butterfly Catcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            ButterflyPanel panel = new ButterflyPanel();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}