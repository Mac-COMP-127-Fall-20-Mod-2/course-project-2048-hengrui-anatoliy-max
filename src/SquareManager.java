import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import java.util.Map;
import java.util.HashMap;

import java.awt.Color;

/**
 * Represents a square manager that can influence all squares.
 */
public class SquareManager extends GraphicsGroup{

    private CanvasWindow canvas;
    public List<Square> squares;
    private Random rand;
    private boolean start;
    private List<Point> points = List.of(new Point(40,461),new Point(40,321),new Point(40,181),new Point(40,41),new Point(180,41),new Point(180,181),new Point(180,321),new Point(180,461),new Point(320,461),new Point(320,321),new Point(320,181),new Point(320,41),new Point(460,41),new Point(460,181),new Point(460,321),new Point(460,461));
    // private int[][] arr = new int[4][4];
    Map<Point,Integer> pointArrMap = new HashMap<>();

    /**
     * Constructs a square maanager from a canvas window.
     */
    public SquareManager(CanvasWindow canvas){
        squares= new ArrayList<Square>();
        rand = new Random();
        start = true;
        this.canvas=canvas;
        // put values into point-array relationship map
        pointArrMap.put(new Point(40,41),0);
        pointArrMap.put(new Point(180,41),0);
        pointArrMap.put(new Point(320,41),0);
        pointArrMap.put(new Point(460,41),0);

        pointArrMap.put(new Point(40,181),0);
        pointArrMap.put(new Point(180,181),0);
        pointArrMap.put(new Point(320,181),0);
        pointArrMap.put(new Point(460,181),0);

        pointArrMap.put(new Point(40,321),0);
        pointArrMap.put(new Point(180,321),0);
        pointArrMap.put(new Point(320,321),0);
        pointArrMap.put(new Point(460,321),0);

        pointArrMap.put(new Point(40,461),0);
        pointArrMap.put(new Point(180,461),0);
        pointArrMap.put(new Point(320,461),0);
        pointArrMap.put(new Point(460,461),0);
    }


    /**
     * Generates squares randomly only in free spaces.
     */
    public void generate(){
        if(start){
            int firstRandom = rand.nextInt(16);
            int secondRandom = rand.nextInt(16);
            if(firstRandom == secondRandom) {
                generate();
            }
        else{
            generateSquare(points.get(firstRandom).getX(), points.get(firstRandom).getY(), 2);
            generateSquare(points.get(secondRandom).getX(), points.get(secondRandom).getY(), 2);
            start=false;
            }
        }
        else if(squares.size()<16){
            int randomNumber = rand.nextInt(16);
            if(this.getElementAt(points.get(randomNumber)) != null){
                generate();
            }
            else {
                generateSquare(points.get(randomNumber).getX(), points.get(randomNumber).getY(), 2);
            }
        }
    }


    /**
     * Moves every square in the input string direction.
     */
    public void move(String direction){
        for (Square square : squares){
                square.move(direction);
                }
    }
        

    /**
     * Generates a square at the input x, y, and value.
     */
    private void generateSquare(double x, double y, int value) {
        Square square = new Square(x, y, value,this);
        pointArrMap.replace(new Point(x,y),value);
        this.add(square);
        squares.add(square);
    }
    /**
     * remove square at certain point
     */
    public void removeSquare(Point point){
        for (Square square : squares) {
            if(square.getPoint()==point){
                squares.remove(square);
            }
        }
    }
    
}
