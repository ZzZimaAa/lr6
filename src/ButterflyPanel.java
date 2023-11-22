import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButterflyPanel extends JPanel {
    private Butterfly butterfly;
    private Net net;
    private Timer timer;
    private int hits;
    private int misses;
    private JButton startButton;
    private JButton stopButton;

    public ButterflyPanel() {
        ImageIcon butterflyImage = new ImageIcon("src/butterfly.png");
        ImageIcon netImage = new ImageIcon("src/net.png");

        // Уменьшаем размерчики изображений
        butterflyImage = resizeImageIcon(butterflyImage, 50, 50);
        netImage = resizeImageIcon(netImage, 50, 50);

        butterfly = new Butterfly(butterflyImage, 100, 100);
        net = new Net(netImage, 400, 400);
        hits = 0;
        misses = 0;

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                JOptionPane.showMessageDialog(null, "Hits: " + hits + ", Misses: " + misses);
            }
        });

        add(startButton);
        add(stopButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= butterfly.getX() && mouseX <= butterfly.getX() + butterfly.getImage().getIconWidth() &&
                        mouseY >= butterfly.getY() && mouseY <= butterfly.getY() + butterfly.getImage().getIconHeight()) {
                    hits++;
                } else {
                    misses++;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                net.setX(mouseX);
                net.setY(mouseY);
                repaint();
            }
        });
    }

    private void resetCounters() {
        hits = 0;
        misses = 0;
    }
    private void startTimer() {
        if (timer == null || !timer.isRunning()) {
            resetCounters();
            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveButterflyAsync();
                }
            });
            timer.start();
        }
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void moveButterflyAsync() {
        new Thread(() -> {
            int randomX = (int) (Math.random() * getWidth());
            int randomY = (int) (Math.random() * getHeight());
            butterfly.setX(randomX);
            butterfly.setY(randomY);
            repaint();
        }).start();
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        butterfly.getImage().paintIcon(this, g, butterfly.getX(), butterfly.getY());
        net.getImage().paintIcon(this, g, net.getX(), net.getY());
    }
}