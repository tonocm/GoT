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
  public House getHouse(String x){
   return houses.get(x);
  }
  
  public void makeMove(String houseName){
   House current = getHouse(houseName);
   System.out.println(houseName);
   System.out.println(current.name);
   
  }
  
}