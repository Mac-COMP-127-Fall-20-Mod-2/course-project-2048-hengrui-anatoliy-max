
import java.util.Arrays;
import java.util.Random;
import edu.macalester.graphics.GraphicsGroup;

/**
 * Represents a square manager that can influence all squares.
 */
public class SquareManager extends GraphicsGroup {

    private Background background;
    private Random rand;
    private Square[][] square;
    final int target = 2048;
    int highest;
    int score;
    int highestScore;
    private boolean checkingAvailableMoves;
    public String state = "notStarted";

    /**
     * Constructs a square maanager from a canvas window.
     */
    public SquareManager(Background background) {
        square = new Square[4][4];
        rand = new Random();
        highest = 0;
        score = 0;
        this.background = background;
        generateSquare();
        generateSquare();
    }

    /**
     * Moves every square in the input string direction and allows merging between squares of the same
     * value.
     */
    public boolean move(int countDownFrom, int yChange, int xChange) {
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
                    if(checkingAvailableMoves){
                        return true;
                    }
                    square[nextRow][nextCol] = current;
                    square[row][col] = null;
                    row = nextRow;
                    col = nextCol;
                    nextRow += yChange;
                    nextCol += xChange;
                    moved = true;

                } else if (next.canMergeWith(current)) {
                    if(checkingAvailableMoves){
                        return true;
                    }
                    int value = next.mergeWith(current);
                    if (value > highest)
                        highest = value;
                    score += value;
                    background.updateScore(score, "currentScore");
                    if (score>=highestScore){
                        highestScore=score;
                        background.updateScore(highestScore, "bestScore");
                    }
                    square[row][col] = null;
                    break;
                } else {
                    break;
                }
            }
        }
        if (moved) {
            if (highest < target) {
                clearMerged();
                generateSquare();
                    if(!movesAvailable()){
                        state="over";
                    }
            }
            else if(highest == target){
                state="won";
            }
        }
        else{
            clearMerged();
        }
        return moved;
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
        if(state.equals("running")){
            this.removeAll();
            Square[][] newSquare = new Square[4][4];
            for (int i = 0; i < square.length; i++) {
                for (int j = 0; j < square[0].length; j++) {
                    newSquare[i][j] = square[i][j];
                }
            }
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
        }
    }

    boolean movesAvailable() {
        checkingAvailableMoves = true;
        boolean hasMoves = move(0, 0, -1) || move(15, 0, 1) || move(15, 1, 0) || move(0, -1, 0);
        checkingAvailableMoves = false;
        return hasMoves;
    }


    @Override
    public String toString() {
        return "highest=" + highest + ", rand=" + rand + ", score=" + score
            + ", square=" + Arrays.toString(square) + ", target=" + target + "]";
    }
}
