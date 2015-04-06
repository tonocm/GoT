import java.util.*;
public class Board{
  
  HashMap<String, Territory> board;
  HashMap<String, House> houses;
  
  public Board(){
    board = new HashMap<String, Territory>();
    houses = new HashMap<String, House>();
  }
  
  public void addTerritory(Territory x){
    board.put(x.name, x);
  }
  
  public void addHouse(House x){
    
  }
}