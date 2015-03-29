public class Land{
 
  String name;
  String startingHouse; //can be null.
  String occupiedBy;
  boolean available; //available if no one has claimed it yet, or if it's being held by a coin.
  boolean playable; //if less than X players, this land is out of bounds.
  boolean coin;
  boolean crown;
  int mustering; //0 = no mustering, 1 = 1 mustering, 2 = 2 mustering
  int supply; //0 = no supplies, 1 = 1 supply, 2 = 2 supplies
  
  public Land(String name, String startingHouse, boolean playable, boolean crown, int mustering, int supply){
    this.name = name;
    this.startingHouse = startingHouse;
    this.playable = playable;
    this.crown = crown;
    this.mustering = mustering;
    this.supply = supply;
    
    /* Dealing with map constrains */
    if(startingHouse == null && playable == true)
      available = true;
    else
      available = false;
  }
}