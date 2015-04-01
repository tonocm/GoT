import java.util.LinkedList;
public class Land extends Territory{
 
  //String name;
  //String occupiedBy;
  //boolean available; //available if no one has claimed it yet, or if it's being held by a coin.
  String startingHouse; //can be null.
  boolean playable; //if less than X players, this land is out of bounds.
  boolean coin;
  int crown;
  int mustering; //0 = no mustering, 1 = 1 mustering, 2 = 2 mustering
  int supply; //0 = no supplies, 1 = 1 supply, 2 = 2 supplies
  
  public Land(String name, /*String startingHouse, boolean playable,*/ int crown, int mustering, int supply){
    super(name);  
    //this.startingHouse = startingHouse;
    //this.playable = playable;
    playable = true;
    this.crown = crown;
    this.mustering = mustering;
    this.supply = supply;
    
    /* Dealing with map constrains
    if(startingHouse == null && playable == true)
      available = true;
    else
      available = false; */
  }
  /* For testing */
  public Land(String name, LinkedList<Territory> adjacent){
    super(name);
  }
}