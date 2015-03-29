import java.util.*;
public class Territory{
  
  LinkedList<Territory> adjacent;
  String name;
  String occupiedBy;
  boolean available;
  
  public Territory(String name){
    this.name = name;
    occupiedBy = "";
    available = true;
    adjacent = new LinkedList<Territory>();
  }
}