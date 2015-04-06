import java.util.LinkedList;
public class Land extends Territory{
 
  String startingHouse; //can be null.
  boolean playable; //if less than X players, this land is out of bounds.
  boolean coin;
  int crown;
  int mustering; //0 = no mustering, 1 = 1 mustering, 2 = 2 mustering
  int supply; //0 = no supplies, 1 = 1 supply, 2 = 2 supplies
  
  public Land(String name, int crown, int mustering, int supply){
    super(name);
    
    playable = true;
    
    this.crown = crown;
    this.mustering = mustering;
    this.supply = supply;  
  }
}