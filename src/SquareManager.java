
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * Represents a square manager that can influence all squares.
 */
public class SquareManager extends GraphicsGroup {

    private CanvasWindow canvas;
    private Random rand;
    // private int[][] arr = new int[4][4];
    private Square[][] square;
    final int target = 2048;
    int highest;
    int score;

    /**
     * Constructs a square maanager from a canvas window.
     */
    public SquareManager(CanvasWindow canvas) {
        square = new Square[4][4];
        rand = new Random();
        highest = 0;
        score = 0;
        this.canvas = canvas;
        generateSquare();
        generateSquare();
    }

    /**
     * Moves every square in the input string direction and allows merging between squares of the same
     * value.
     */
    public void move(int countDownFrom, int yChange, int xChange) {
        boolean moved = false;
        for (int i = 0; i < 16; i++) {
            int j = Math.abs(countDownFrom - i);
            int row = j / 4;
            int col = j % 4;
            if (square[row][col] == null) {
                continue;
            }
            int nextRow = row + yChange;
            int nextCol = col + xChange;

            while (nextRow >= 0 && nextRow < 4 && nextCol >= 0 && nextCol < 4) {
                Square next = square[nextRow][nextCol];
                Square current = square[row][col];

                if (next == null) {
                    square[nextRow][nextCol] = current;
                    square[row][col] = null;
                    row = nextRow;
                    col = nextCol;
                    nextRow += yChange;
                    nextCol += xChange;
                    moved = true;

                } else if (next.canMergeWith(current)) {
                    int value = next.mergeWith(current);
                    if (value > highest)
                        highest = value;
                    score += value;
                    square[row][col] = null;
                    break;
                } else {
                    break;
                }
            }
        }
        if (moved) {
            if (highest < 2048) {
                clearMerged();
                generateSquare();
                // to see if you are dead
            }
        }
    }


    /**
     * Removes leftover squares after merging occurs.
     */
    public void clearMerged() {
        for (int i = 0; i < square[0].length; i++) {
            for (int j = 0; j < square.length; j++) {
                if (square[j][i] != null) {
                    square[j][i].setMerged(false);
                }
            }
        }
    }

    /**
     * Generates a square at a free random position on the board.
     */
    public void generateSquare() {
        int pos;
        int row, col;
        do {
            pos = rand.nextInt(16);
            row = pos / 4;
            col = pos % 4;
        } while (square[row][col] != null);
        square[row][col] = new Square(2);
    }


    /**
     * Refreshes the square layer so that the gameboard shows only the most updated squares.
     */
    public void reprint() {
        this.removeAll();
        Square[][] newSquare = new Square[4][4];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                newSquare[i][j] = square[i][j];
            }
        }
        System.out.println("Copy is done");
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (newSquare[col][row] != null) {
                    newSquare[col][row].createBoxDrawing(40 + col * 140, 41 + row * 140);// by calling createBoxDrawing
                                                                                         // we add rectangle and text
                                                                                         // into the 2d square
                    this.add(newSquare[col][row]);
                }
            }
        }
        System.out.println("Print is done");
        printOutArray(newSquare);
    }


    /**
     * Prints the array that the gameboard is based on, allowing for ease of debugging.
     */
    public void printOutArray(Square[][] squares) {
        System.out.println("--------");
        for (int y = 0; y < squares[0].length; y++) {
            for (int x = 0; x < squares.length; x++) {
                if (square[x][y] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(squares[x][y].getValue() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------");
    }
    ///eeeeddit

    @Override
    public String toString() {
        return "SquareManager [canvas=" + canvas + ", highest=" + highest + ", rand=" + rand + ", score=" + score
            + ", square=" + Arrays.toString(square) + ", target=" + target + "]";
    }
}
