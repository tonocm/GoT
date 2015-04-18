import java.io.*;
import java.util.*;

class GoT{
  public static void main (String[] args) throws IOException{
    
    Board game = makeBoard();
    initialize(game);
    boolean cont = true;
    int i=0;
    
    System.out.println(game.houses);
    
    //House baratheon, lannister, stark, martell, greyjoy, tyrell;
    
    //while(continue){
      
      /* Baratheon
       * Lannister
       * Stark
       * Martell
       * Greyjoy
       * Tyrell */
      
      /* Turns */
      //baratheon = game.getHouse("Baratheon");
      
      
    //}
    
  }
  
  public static void initialize(Board game) throws IOException{
    File file = new File("init.txt");
    Scanner scan = new Scanner(file).useDelimiter("\t");
    
    while(scan.hasNext()){
      
      String name;
      LinkedList<Territory> /*foot, knight, ship,*/ territories = new LinkedList<Territory>();
      int iron, fiefdom, court, supply, victory;
      /*
       * Given the file format, total tokens will always be a multiple of 5.
       * 1 - House Name
       * 2 - Footmen Placement
       * 3 - Knights Placement
       * 4 - Ships Placement
       * 5 - Iron Throne Track
       * 6 - Fiefdom Track
       * 7 - King's Court Track
       * 8 - Supply
       * 9 - Victory
       */
      
      /* Using trim to avoid String -> Int conflicts */
      
      name = scan.next().trim();
      
      /* Hack to account for Aries' excel instead of txt file */
      if(!scan.hasNext())
        break;
      
      /*foot = */makeList(territories, scan.next(), game);
      
      /*knight = */makeList(territories, scan.next(), game);
      
      /*ship = */makeList(territories, scan.next(), game);
      
      iron = Integer.parseInt(scan.next().trim());
      
      fiefdom= Integer.parseInt(scan.next().trim());
      
      court = Integer.parseInt(scan.next().trim());
      
      supply = Integer.parseInt(scan.next().trim());
      
      victory = Integer.parseInt(scan.next().trim());
      
      
      game.addHouse(new House(name, territories));
    }
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
  
  public static void makeList(LinkedList<Territory> out, String s, Board game){
    /* Getting rid of quotes" */
    s = s.substring(1,s.length());
    s = s.substring(0,s.length()-1);

    StringTokenizer token = new StringTokenizer(s, ",");
    String tmp;
    
    while(token.hasMoreTokens()){
      tmp = token.nextToken().trim();
      out.add(game.board.get(tmp));
      game.board.get(tmp).power = game.board.get(tmp).power + 1;
    }
  }
}