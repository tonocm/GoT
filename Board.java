import java.util.*;
public class Board{
  
  HashMap<String, Territory> board;
  
  public Board(){
    board = new HashMap<String, Territory>();
  }
  
  public void addTerritory(Territory x){
    board.put(x.name, x);
  }
}