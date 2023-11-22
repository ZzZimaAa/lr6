import javax.swing.*;

public class Butterfly {
    private ImageIcon image;
    private int x;
    private int y;

    public Butterfly(ImageIcon image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}