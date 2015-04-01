import java.io.*;
import java.util.*;

class GoT{
  public static void main (String[] args) throws IOException{
    
    Board game = makeBoard();
    
    
    System.out.println(game.board.get("Riverrun").adjacent);
    
  }
  
  public static Board makeBoard() throws IOException{
    int i;
    File file = new File("board.txt");
    Scanner scan = new Scanner(file).useDelimiter("\t");
    Board out = new Board();
    
    int type, castles, supplies, crowns;
    String name, adjacentRaw;
    
    while(scan.hasNext()){
      /*
       * Given the file format, total tokens will always be a multiple of 5.
       * 1 - [Sea = 1, Land = 0]
       * 2 - Name
       * 3 - [Castle = 0, Stronghold = 1]
       * 4 - Supplies
       * 5 - Crowns
       */
      
      /* Using trim to avoid String -> Int conflicts */
      
      /* 1 */ type = Integer.parseInt(scan.next().trim());
      /* 2 */ name = scan.next();
      /* 3 */ castles = Integer.parseInt(scan.next().trim());
      /* 4 */ supplies = Integer.parseInt(scan.next().trim());
      /* 5 */ crowns = Integer.parseInt(scan.next().trim());
      
      if(type == 0)
        out.addTerritory(new Land(name, crowns, castles, supplies));
      else
        out.addTerritory(new Sea(name));
    }
    /* Closing scanner for "board.txt" */
    scan.close();
    
    /* Opening scanner for "adjacents.txt" */
    file = new File("adjacents.txt");
    scan = new Scanner(file).useDelimiter("\t");
    Territory temp;
    
    while(scan.hasNext()){
      /*
       * Given the file format, total tokens will always be a multiple of 2.
       * 1 - Name
       * 2 - Adjacents
       */
      
      /* 1 */ name = scan.next().trim();
      /* 2 */ adjacentRaw = scan.next();
      out.board.get(name).adjacent = makeAdjacent(out, name, adjacentRaw);
    }
    scan.close();
    
    return out;
  }
  
  public static LinkedList<Territory> makeAdjacent(Board board, String name, String s){
    /* Getting rid of quotes" */
    s = s.substring(1,s.length());
    s = s.substring(0,s.length()-1);

    LinkedList<Territory> out = new LinkedList<Territory>();
    StringTokenizer token = new StringTokenizer(s, ",");
    
    while(token.hasMoreTokens())
      out.add(board.board.get(token.nextToken().trim()));
    
    return out;
  }
}