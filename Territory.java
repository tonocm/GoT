import java.util.*;
public class Territory{
  
  LinkedList<Territory> adjacent;
  String name;
  String occupiedBy;
  boolean available;
  int power;
  
  public Territory(String name){
    this.name = name;
    adjacent = new LinkedList<Territory>();
    occupiedBy = "";
    available = true;
    power = 0;
  }
}