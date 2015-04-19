import java.util.*;
public class Territory{
  
  LinkedList<Territory> adjacent;
  String name;
  String occupiedBy;
  boolean available;
  int power;
  int footmen, knights, ships, sieges;
    
  public Territory(String name){
    this.name = name;
    adjacent = new LinkedList<Territory>();
    occupiedBy = "";
    available = true;
    footmen = 0;
    knights = 0;
    ships = 0;
    sieges = 0;
    power = 0;
  }
}