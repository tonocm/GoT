import java.io.*;
import java.util.*;

class GoT{
  public static void main (String[] args) throws IOException{
    
    Board board = makeBoard();
    
  }
  
  public static Board makeBoard() throws IOException{
    int i;
    File board = new File("board.txt");
    Scanner file = new Scanner(board).useDelimiter("\t");
    
    int type, castles, supplies, crowns;
    String name, adjacent;
    
    while(file.hasNext()){
      
      /*
       * 1 - [Sea = 1, Land = 0]
       * 2 - Name
       * 3 - [Castle = 0, Stronghold = 1]
       * 4 - Supplies
       * 5 - Crowns
       * 6 - Adjacent Territories
       */
        /* 1 */ type = file.next();
        /* 2 */ name = file.next();
        /* 3 */ castles = file.next();
        /* 4 */ supplies = file.next();
        /* 5 */ crowns = file.next();
        /* 6 */ adjacent = file.next();
      
      if(type == 0){
          
        }
        else{
          
        }
      
    }
    return new Board();
  }
  
}