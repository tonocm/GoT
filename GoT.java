import java.io.*;
import java.util.*;

class GoT{
  public static void main (String[] args) throws IOException{
    
    Board game = makeBoard();
    String[] ironTrack = {"Baratheon", "Lannister", "Stark", "Martell", "Greyjoy", "Tyrell"};
    initialize(game);
    boolean cont = true;
    int i=0, j=0, moves=0;

    //House baratheon, lannister, stark, martell, greyjoy, tyrell;
    
    while(cont){
      
      /* Baratheon
       * Lannister
       * Stark
       * Martell
       * Greyjoy
       * Tyrell */
      
      /* Move Orders */
      for(i=0; i < 3; i++){
        for(j=0; j < ironTrack.length; j++){
          System.out.println(ironTrack[j]); //house name
          
          
          //System.out.println(countCastles(game.getHouse(ironTrack[j])));
          
          if(countCastles(game.getHouse(ironTrack[j])) >= 7){
            cont = false; //game over.
            break;
          }
          
          game.moveOrder(ironTrack[j]);
          moves++;
        }
        //break;//remove this after testing
      }
      
      if(!cont)
        break; //game over.
      
      //break;//remove this after testing
    }
    System.out.println("House " + ironTrack[j]+ " won! in " + moves + " moves!");
  }
  
  public static int countCastles(House x){
    
    int out=0, i=0;
    
    for(i=0; i < x.territories.size(); i++){
      if(x.territories.get(i) instanceof Land){
        if(((Land)x.territories.get(i)).mustering > 0)
          out++;
      }
    }
    return out;
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
      
      /*foot = */makeList(territories, scan.next(), game, 1);
      
      /*knight = */makeList(territories, scan.next(), game, 2);
      
      /*ship = */makeList(territories, scan.next(), game, 3);
      
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
       * 3 - [Castle = 2, Stronghold = 1, 0 = Nothing]
       * 4 - Supplies [0, 1 or 2]
       * 5 - Crowns [0, 1 or 2]
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
  
  public static void makeList(LinkedList<Territory> out, String s, Board game, int type){
    /* Getting rid of quotes" */
    s = s.substring(1,s.length());
    s = s.substring(0,s.length()-1);
    
    StringTokenizer token = new StringTokenizer(s, ",");
    String tmp;
    
    while(token.hasMoreTokens()){
      tmp = token.nextToken().trim();
      
      if(!out.contains(game.board.get(tmp)))
        out.add(game.board.get(tmp));
      
      if(type == 1){
        game.board.get(tmp).footmen = game.board.get(tmp).footmen + 1;
        game.board.get(tmp).power = game.board.get(tmp).power + 1;
      }
      else if(type == 2){
        game.board.get(tmp).knights = game.board.get(tmp).knights + 1;
        game.board.get(tmp).power = game.board.get(tmp).power + 2;
      }
      else{
        game.board.get(tmp).ships = game.board.get(tmp).ships + 1;
        game.board.get(tmp).power = game.board.get(tmp).power + 1;
      }
      
      
    }
  }
}