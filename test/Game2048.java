import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
 
public class Game2048 extends JPanel {
 
    enum State {
        start, won, running, over
    }
 
    final Color[] colorTable = {
        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
        new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
        new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
        new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)};
 
    final static int target = 2048;
 
    static int highest;
    static int score;
 
    private Color gridColor = new Color(0xBBADA0);
    private Color emptyColor = new Color(0xCDC1B4);
    private Color startColor = new Color(0xFFEBCD);
 
    private Random rand = new Random();
 
    private Tile[][] tiles;
    private int side = 4;
    private State gamestate = State.start;
    private boolean checkingAvailableMoves;
 
    public Game2048() {
        setPreferredSize(new Dimension(900, 700));
        setBackground(new Color(0xFAF8EF));
        setFont(new Font("SansSerif", Font.BOLD, 48));
        setFocusable(true);
 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
                repaint();
            }
        });
 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        break;
                }
                repaint();
            }
        });
    }
 
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
 
        drawGrid(g);
    }
    /**
     * if game is not running
     * reset all the things
     * make it running
     * create new tiles
     * add two tiles
     */
    void startGame() {
        if (gamestate != State.running) {
            score = 0;
            highest = 0;
            gamestate = State.running;
            tiles = new Tile[side][side];
            addRandomTile();
            addRandomTile();
        }
    }
    /**
     * Draw Grid
     * @param g Graphics Object
     * if game is running then
     * redraw every grids based on the tiles
     * if there is nothing fill it with empty colors 
     * else draw it based its location
     */
    void drawGrid(Graphics2D g) {
        g.setColor(gridColor);
        g.fillRoundRect(200, 100, 499, 499, 15, 15);
 
        if (gamestate == State.running) {
 
            for (int r = 0; r < side; r++) {
                for (int c = 0; c < side; c++) {
                    if (tiles[r][c] == null) {
                        g.setColor(emptyColor);
                        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
                    } else {
                        drawTile(g, r, c);
                    }
                }
            }
        } else {
            g.setColor(startColor);
            g.fillRoundRect(215, 115, 469, 469, 7, 7);
 
            g.setColor(gridColor.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 128));
            g.drawString("2048", 310, 270);
 
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
 
            if (gamestate == State.won) {
                g.drawString("you made it!", 390, 350);
 
            } else if (gamestate == State.over)
                g.drawString("game over", 400, 350);
 
            g.setColor(gridColor);
            g.drawString("click to start a new game", 330, 470);
            g.drawString("(use arrow keys to move tiles)", 310, 530);
        }
    }
    /**
     * Called by drawGrid
     * draw tiles
     * @param g
     * @param r
     * @param c
     */
    void drawTile(Graphics2D g, int r, int c) {
        int value = tiles[r][c].getValue();
 
        g.setColor(colorTable[(int) (Math.log(value) / Math.log(2)) + 1]);
        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
        String s = String.valueOf(value);
 
        g.setColor(value < 128 ? colorTable[0] : colorTable[1]);
 
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();
 
        int x = 215 + c * 121 + (106 - fm.stringWidth(s)) / 2;
        int y = 115 + r * 121 + (asc + (106 - (asc + dec)) / 2);
 
        g.drawString(s, x, y);
    }
 
    /**
     * Add random tile with random position
     * sometimes with value of two sometimes with value of four
     */
    private void addRandomTile() {
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos = (pos + 1) % (side * side);
            row = pos / side;
            col = pos % side;
        } while (tiles[row][col] != null);
 
        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = new Tile(val);
    }
    /**
     * set Moved to false first
     * @param countDownFrom
     * @param yIncr
     * @param xIncr
     * @return
     */
    private boolean move(int countDownFrom, int yIncr, int xIncr) {
        boolean moved = false;
 
        for (int i = 0; i < side * side; i++) {
            //countDownFrom decides where this for loop is going to start
            int j = Math.abs(countDownFrom - i);
 
            int r = j / side;
            int c = j % side;
            //if there is nothing on that block then continue
            if (tiles[r][c] == null)
                continue;
            //else mark next block's location
            int nextR = r + yIncr;
            int nextC = c + xIncr;
 
            while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
            //while nextBlock is inbound
                Tile next = tiles[nextR][nextC];
                Tile curr = tiles[r][c];
            //create two tiles
                if (next == null) {
            //if next one has not been created
                    if (checkingAvailableMoves)
                        return true;
            //next tile equals now tile
                    tiles[nextR][nextC] = curr;
            //now tile equals null
                    tiles[r][c] = null;
            //r equals nextR
            //c equals nextC
                    r = nextR;
                    c = nextC;
                    nextR += yIncr;
                    nextC += xIncr;
                    moved = true;
            //if nextTile can merge with current tile
                } else if (next.canMergeWith(curr)) {
                    
                    if (checkingAvailableMoves)
                        return true;
                    //return the value that the merge generates
                    int value = next.mergeWith(curr);
                    //try to see if that is the highest value
                    if (value > highest)
                        highest = value;
                    //add score
                    score += value;
                    //make sure the orginal tile is null
                    tiles[r][c] = null;
                    //make sure it moved
                    moved = true;
                    break;
                } else
                    break;
            }
        }
        //if it has been moved
        if (moved) {
            //if the game is not over
            if (highest < target) {
                //start a new turn by return the status of the merge to default
                clearMerged();
                //add Random tile
                addRandomTile();
                //if it is not able to move
                if (!movesAvailable()) {
                    //game over
                    gamestate = State.over;
                }
                //if it is 2048
            } else if (highest == target)
                gamestate = State.won;
        }
 
        return moved;
    }
 
    boolean moveUp() {
        return move(0, -1, 0);
    }
 
    boolean moveDown() {
        return move(side * side - 1, 1, 0);
    }
 
    boolean moveLeft() {
        return move(0, 0, -1);
    }
 
    boolean moveRight() {
        return move(side * side - 1, 0, 1);
    }
 
    void clearMerged() {
        for (Tile[] row : tiles)
            for (Tile tile : row)
                if (tile != null)
                    tile.setMerged(false);
    }
 
    boolean movesAvailable() {
        checkingAvailableMoves = true;
        boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
        checkingAvailableMoves = false;
        return hasMoves;
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("2048");
            f.setResizable(true);
            f.add(new Game2048(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
 
class Tile {
    private boolean merged;
    private int value;
 
    Tile(int val) {
        value = val;
    }
 
    int getValue() {
        return value;
    }
 
    void setMerged(boolean m) {
        merged = m;
    }
 
    boolean canMergeWith(Tile other) {
        return !merged && other != null && !other.merged && value == other.getValue();
    }
 
    int mergeWith(Tile other) {
        if (canMergeWith(other)) {
            value *= 2;
            merged = true;
            return value;
        }
        return -1;
    }
}
