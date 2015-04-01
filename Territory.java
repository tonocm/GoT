import java.util.*;
public class Territory{
  
  LinkedList<Territory> adjacent;
  String name;
  String occupiedBy;
  boolean available;
  
  public Territory(String name){
    this.name = name;
    adjacent = new LinkedList<Territory>();
    occupiedBy = "";
    available = true;
  }
}