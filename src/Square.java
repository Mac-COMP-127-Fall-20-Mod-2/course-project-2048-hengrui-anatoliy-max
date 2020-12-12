import java.awt.Color;

import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Square extends GraphicsGroup {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final Color BEIGE_COLOR = new Color(224, 204, 177);
    public static final Color DARK_BEIGE_COLOR = new Color(235, 203, 162);
    public static final Color ORANGE_COLOR = new Color(255, 171, 61);
    public static final Color DARK_ORANGE_COLOR = new Color(232, 131, 42);
    public static final Color RED_ORANGE_COLOR = new Color(247, 114, 47);
    public static final Color REDDER_ORANGE_COLOR = new Color(237, 92, 19);
    public static final Color GOLD_COLOR = new Color(255, 255, 0);

    private int value;
    private Color color;
    private double x;
    private double y;

    public Square(double x, double y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;

        if (value == 2) {
            this.color = BEIGE_COLOR;
        }
        if (value == 4) {
            this.color = DARK_BEIGE_COLOR;
        }
        if (value == 8) {
            this.color = ORANGE_COLOR;
        }
        if (value == 16) {
            this.color = DARK_ORANGE_COLOR;
        }
        if(value == 32) {
            this.color = RED_ORANGE_COLOR;
        }
        if(value == 64) {
            this.color = REDDER_ORANGE_COLOR;
        }
        if(value >= 128) {
            this.color = GOLD_COLOR;
        }

        createBoxDrawing();
    }

    private void createBoxDrawing() {
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        rectangle.setFillColor(color);
        GraphicsText text = new GraphicsText(Integer.toString(value), 0, 0);

        if(value == 2 || value == 4 || value == 8) {
            text.setPosition(rectangle.getX() + 42, rectangle.getY() + 57);
        }
        else if(value == 16 || value == 32 || value == 64) {
            text.setPosition(rectangle.getX() + 34, rectangle.getY() + 57);

        }
        else if(value == 128 || value == 256 || value == 512) {
            text.setPosition(rectangle.getX() + 26, rectangle.getY() + 57);
        }
        else {
            text.setPosition(rectangle.getX() + 18, rectangle.getY() + 57);
        }


        text.setFont(FontStyle.BOLD, 25);
        add(rectangle);
        add(text);
    }

    public void move(String direction) {
        if(direction.equals("Up")&&y>60) {
            y=y-140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Down")&&y<=340) {
            y=y+140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Left")&&x>40) {
            x=x-140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Right")&&x<=320) {
            x=x+140;
            this.setCenter(x+50,y+50);
        }
    }

}