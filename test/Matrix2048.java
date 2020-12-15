// import java.util.Random;
// import java.util.Scanner;

// public class Matrix2048 {
//     Tile[][] tile;
//     Random rand;
//     final int target = 2048;
//     int highest;
//     int score;
//     public Matrix2048(){
//         tile = new Tile[4][4];
//         rand = new Random();
//         generateRandomTile();
//         generateRandomTile();
//         highest=0;
//         score=0;
//         run();
//     }
//     public static void main(String[] args) {
//         Matrix2048 game = new Matrix2048();
//     }

//     public void printOutArray(){
//         System.out.println("--------");
//         for(int y =0;y<tile[0].length;y++){
//             for(int x=0;x<tile.length;x++){
//                 if(tile[x][y]==null){
//                     System.out.print("0 ");
//                 }
//                 else{
//                     System.out.print(tile[x][y].getValue()+" ");
//                 }
//             }
//             System.out.println();
//         }
//         System.out.println("--------");
//     }
    
//     public void generateRandomTile(){
//         int pos;
//         int row,col;
//         do{
//             pos = rand.nextInt(16);
//             row = pos/4;
//             col = pos%4;
//        }
//        while(tile[row][col]!=null);
//        tile[row][col]= new Tile(2);
//     }

    
//     private void move(int countDownFrom, int yChange, int xChange) {
//         boolean moved=false;
//         for (int i = 0; i < 16; i++) {
//             int j = Math.abs(countDownFrom-i);
//             int row = j / 4;
//             int col = j % 4;
//             if(tile [row] [col] == null)
//                 continue;
//             int nextRow = row + yChange;
//             int nextCol = col + xChange;

//             while (nextRow >= 0 && nextRow < 4 && nextCol >= 0 && nextCol < 4) {
//                 Tile next = tile [nextRow] [nextCol];
//                 Tile current = tile [row] [col];

//                 if (next == null) {
//                     tile [nextRow] [nextCol] = current;
//                     tile [row] [col] = null;
//                     row = nextRow;
//                     col = nextCol;
//                     nextRow += yChange;
//                     nextCol += xChange;
//                     moved = true;

//                 } else if(next.canMergeWith(current)) {
//                     int value = next.mergeWith(current);
//                     if (value > highest) 
//                         highest = value;
//                     score += value;
//                     tile [row] [col] = null;
//                     break;
//                 } else {
//                     break;
//                 }
//             }
//         }
//         if(moved){
//             if(highest<2048){
//                 clearMerged();
//                 generateRandomTile();
//                 //to see if you are dead
//             }
//         } 

//     }
//     public void run(){
//         printOutArray();
//         Scanner scan = new Scanner(System.in);
//         System.out.println("Where do you want to move?");
//         String input = scan.next();
//         if(input.equals("left")) {
//             move(0,-1,0);
//         }
//         else if(input.equals("right")) {
//             move(15,1,0);
//         }
//         else if(input.equals("up")) {
//             move(0,0,-1);
//         }
//         else if(input.equals("down")) {
//             move(15,0,1);
//         }
//         if(!input.equals("quit")){
//             System.out.println("Where do you want to move?");
//         }
//         run();
//     }
//     public void clearMerged(){
//         for(int i =0;i<tile[0].length;i++){
//             for(int j=0;j<tile.length;j++){
//                 if (tile[j][i] != null) {
//                     tile[j][i].setMerged(false);
//                 }
//             }
//         }
//     }
// }

// class Tile{
//     boolean merged;
//     int value;
//     public Tile(int value){
//         this.value=value;
//     }
//     public Integer getValue(){
//         return value;
//     }
//     public void setMerged(boolean b){
//         merged=b;
//     }
//     public boolean canMergeWith(Tile tile){
//         return !merged&&tile!=null&&!tile.merged&&value==tile.getValue();

//     }
//     public int mergeWith(Tile tile){
//         if(canMergeWith(tile)){
//             value*=2;
//             merged=true;
//             return value;
//         }
//         return -1;
//     }

// }
