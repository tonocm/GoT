import java.util.*;
public class Board{
  
  HashMap board;
  
  public Board(){
    board = new HashMap();
  }
  
  public void addTerritory(Territory x){
    board.put(x.name, x);
  }
}