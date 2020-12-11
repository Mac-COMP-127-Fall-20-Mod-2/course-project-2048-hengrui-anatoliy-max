import java.awt.Color;
import java.awt.Font;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class Square extends GraphicsGroup {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int SPEED = 10;

    private int value;
    private Color background;
    private Color text;
    private Color color;
    private double x;
    private double y;

    public Square(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        createBoxDrawing();
    }

    private void createBoxDrawing() {
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        add(rectangle);
    }

}