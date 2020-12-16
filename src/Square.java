import java.awt.Color;

import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

/**
 * Represents a square that can be combined with other squares to reach the fabled 2048.
 */
public class Square extends GraphicsGroup {
    // square is a graphics group we have array of square in the square array with only value that is
    // needed
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
    private boolean merged;


    /**
     * Constructs a square from a value that exists on the 2048 gameboard.
     */
    public Square(int value) {
        this.value = value;
        this.merged = false;
    }

    /**
     * Creates a rectangle and number value based on the square's held value to graphically represent
     * the square. The number's position stays centered no matter its value and the color is changed
     * based on the square's value.
     */
    public void createBoxDrawing(int x, int y) {
        this.removeAll();
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        GraphicsText text = new GraphicsText(Integer.toString(value), 0, 0);

        if (value == 2 || value == 4 || value == 8) {
            text.setPosition(rectangle.getX() + 42, rectangle.getY() + 57);
        } else if (value == 16 || value == 32 || value == 64) {
            text.setPosition(rectangle.getX() + 34, rectangle.getY() + 57);

        } else if (value == 128 || value == 256 || value == 512) {
            text.setPosition(rectangle.getX() + 26, rectangle.getY() + 57);
        } else {
            text.setPosition(rectangle.getX() + 18, rectangle.getY() + 57);
        }
        text.setFont(FontStyle.BOLD, 25);

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
        if (value == 32) {
            this.color = RED_ORANGE_COLOR;
        }
        if (value == 64) {
            this.color = REDDER_ORANGE_COLOR;
        }
        if (value >= 128) {
            this.color = GOLD_COLOR;
        }
        rectangle.setFillColor(color);
        add(rectangle);
        add(text);
    }


    /**
     * Returns the given square's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * If a given square is able to merge with a neighboring square, return true.
     */
    public boolean canMergeWith(Square square) {
        return !merged && square != null && !square.merged && value == square.getValue();
    }

    /**
     * Doubles the value of the merging square.
     */
    public int mergeWith(Square square) {
        if (canMergeWith(square)) {
            value *= 2;
            merged = true;
            return value;
        }
        return -1;
    }

    /**
     * Sets the merged boolean according to the input boolean.
     */
    public void setMerged(boolean b) {
        merged = b;
    }

    @Override
    public String toString() {
        return "Square [color=" + color + ", merged=" + merged + ", value=" + value + "]";
    }

}

