import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;


/**
 * Represents the background with gridlines for the squares and scoreboards to exist on.
 */
public class Background extends GraphicsGroup {

    private double margin;
    private double x;
    private double y;
    private CanvasWindow canvas;
    private GraphicsText currentScore;
    private GraphicsText bestScore;
    public static final Color SCOREBOARD_COLOR = new Color(183, 201, 186);

    /**
     * Constructs the background on the specified canvas.
     */
    public Background(CanvasWindow canvas) {
        this.canvas = canvas;
        this.margin = 40;
        this.x = margin;
        this.y = canvas.getHeight() * 0.068;
        this.draw();
    }

    /**
     * Draws rectangles and squares that serve as the background for the 2048 game, also
     * adds relevant text displaying the score and the highscore.
     */
    private void draw() {
        rectangleHelper(0, 0, 800, 600, Color.darkGray);
        textHelper("2048", this.getWidth() * 0.75, this.getHeight() * 0.20, 80);
        textHelper("Current Score", this.getWidth() * 0.75, 181, 18);
        textHelper("Best Score", this.getWidth() * 0.75, 321, 18);
        rectangleHelper(this.getWidth() * 0.75, 181, 175, 100, SCOREBOARD_COLOR);
        rectangleHelper(this.getWidth() * 0.75, 321, 175, 100, SCOREBOARD_COLOR);

        currentScore = new GraphicsText("0", this.getWidth() * 0.75, 255);
        currentScore.setFont(FontStyle.BOLD,60);
        currentScore.setFillColor(Color.WHITE);
        this.add(currentScore);

        bestScore = new GraphicsText("0", this.getWidth() * 0.75, 395);
        bestScore.setFont(FontStyle.BOLD,60);
        bestScore.setFillColor(Color.WHITE);
        this.add(bestScore);
        
        for (int i = 0; i < 16; i++) {                      // creates a row of bricks
            Rectangle square = new Rectangle(x, y, 100, 100);
            square.setFillColor(Color.lightGray);
            this.add(square);
            if (x < 600 - 100 - margin) {
                x = x + 100 + margin;
            } 
            else {                                        // multiplies of the currently existent row of bricks 
                x = margin;
                y = y + 100 + margin;
            }
        }
    }

    /**
     * Creates a rectangle at an x and y position, of specified width, height and color and adds
     * it to the background.
     */
    private void rectangleHelper(double x, double y, int width, int height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFillColor(color);
        this.add(rectangle);
    }

    /**
     * Creates white colored text of a specified string, x and y position, and a specified size.
     */
    private void textHelper(String string, double x, double y, int size) {
        GraphicsText text = new GraphicsText(string, x, y);
        text.setFont(FontStyle.BOLD, size);
        text.setFillColor(Color.WHITE);
        this.add(text);
    }

    /**
     * Updates the score text or the best score text to an input value depending on an input string.
     */
    public void updateScore(int value, String string) {
        if(string.equals("currentScore")){
            currentScore.setText(Integer.toString(value));
        }
        if(string.equals("bestScore")){
            bestScore.setText(Integer.toString(value));
        }
    }
    
    @Override
    public String toString() {
        return "Background [bestScore=" + bestScore + ", canvas=" + canvas + ", currentScore=" + currentScore
            + ", margin=" + margin + ", x=" + x + ", y=" + y + "]";
    }
}
