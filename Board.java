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
    houses.put(x.name, x);
  }
  public House getHouse(String x){
    return houses.get(x);
  }
  
  public boolean notOwned(Territory x, House y){
    int i=0;
    boolean out=true;
    Territory tmp=null;
    
    for(i=0; i < y.territories.size(); i++){
      tmp = y.territories.get(i);
      
      if(tmp.name.equals(x.name)){
        out = false;
        break;
      }
    }
    
    return out;
  }
  
  public void moveOrder(String x){
    House current = getHouse(x);
    int i=0, j=0, foundIndex=-1, shortDistance=0, bestShortDistance=4, shortIndexJ=-1, shortIndexI=-1;
    Territory tmp=null, bestTerritory=null;
    boolean found = false;
    
    for(i=0; i < current.territories.size(); i++){
      tmp = current.territories.get(i);
      
      if(tmp.power > 0 && tmp instanceof Land){
        
        for(j=0; j < tmp.adjacent.size(); j++){
          
          if(tmp.adjacent.get(j) instanceof Land){ //it is land, not sea
               
            if(((Land)tmp.adjacent.get(j)).mustering > 0 && tmp.adjacent.get(j).power == 0 && notOwned(tmp.adjacent.get(j), current)){ //it is a castle and no one is defending
              bestTerritory = tmp.adjacent.get(j);
              foundIndex = i;
              if(((Land)tmp.adjacent.get(j)).mustering == 2){
                found = true;
                break; //found best possible.
              }
              //System.out.println(tmp.adjacent.get(j).name + " Mustering: " + ((Land)tmp.adjacent.get(j)).mustering); 
            }
          }
          /*
           * Removing Else for sea movement, given time constrains and other factors
           else
           System.out.println("Sea: " + tmp.adjacent.get(j).name + ", ");
           */
        }
        if(found)
          break;
      }//else, there is no unit in that territory; can't move regardless.
    }
    
    if(foundIndex != -1){ //actually found something
      System.out.println("Moving from "+ current.territories.get(foundIndex).name+ " to " + bestTerritory.name);
      makeMove(current.territories.get(foundIndex),bestTerritory, current);
    }
    else{ //didn't find castle.
      found = false; //resetting var
      
      for(i=0; i < current.territories.size(); i++){
        tmp = current.territories.get(i);
        
        if(tmp.power > 0 && tmp instanceof Land){
          for(j=0; j < tmp.adjacent.size(); j++){
            if(tmp.adjacent.get(j) instanceof Land){ //it is land, not sea
              if(tmp.adjacent.get(j).power == 0 && notOwned(tmp.adjacent.get(j), current)){
                System.out.println("Moving from "+ tmp.name + " to " + tmp.adjacent.get(j).name + " no Castles Involved");
                makeMove(tmp, tmp.adjacent.get(j), current);
                found = true;
                break;
              }
            }
          }
        }
        if(found)
          break;
      }
    }
    
    if(!found && foundIndex == -1){
      for(i=0; i < current.territories.size(); i++){
        tmp = current.territories.get(i);
        
        if(tmp.power > 0 && tmp instanceof Land){
          for(j=0; j < tmp.adjacent.size(); j++){
            if(tmp.adjacent.get(j) instanceof Land && !notOwned(tmp.adjacent.get(j), current)){ //it is land, not sea. Also, it is not occupied.
              shortDistance = findDistance(((Land)tmp),((Land)tmp.adjacent.get(j)), 2);
              if(shortDistance < bestShortDistance){
                shortIndexJ = j;
                shortIndexI = i;
                bestShortDistance = shortDistance;
              }
            }
          }
        }
      }
      
      if(shortIndexI != -1){
        tmp = current.territories.get(shortIndexI); //reusing variable for better readability
        
        System.out.println("Moving from "+ tmp.name + " to " + tmp.adjacent.get(shortIndexJ).name + " special case with complex heuristic");
        
        if(tmp.footmen > 0){
          tmp.footmen = tmp.footmen-1;
          tmp.adjacent.get(shortIndexJ).footmen = tmp.adjacent.get(shortIndexJ).footmen+1;
          
          tmp.power = tmp.power-1;
          tmp.adjacent.get(shortIndexJ).power = tmp.adjacent.get(shortIndexJ).power+1;
          
        }
        else if(tmp.knights > 0){
          tmp.footmen = tmp.knights-1;
          tmp.adjacent.get(shortIndexJ).knights = tmp.adjacent.get(shortIndexJ).knights+1;
          
          tmp.power = tmp.power-2;
          tmp.adjacent.get(shortIndexJ).power = tmp.adjacent.get(shortIndexJ).power+2;
        }
        else
          System.out.println("Error in last heuristic case");
      }
      else
        System.out.println("Forfeit Turn. All adjacent territories occupied");
    }
  }
  
  public int findDistance(Land orig, Land x, int depth){
    int i=0, distance =4, temp;
    
    if(x.mustering > 0 && !orig.equals(x)){
      return depth-1;
    }
    
    if(depth <= 4){ //limited to 3 recursions
      
      for(i=0; i < x.adjacent.size(); i++){
        if(x.adjacent.get(i) instanceof Land){
          if(((Land)x.adjacent.get(i)).mustering > 0 && !orig.equals(((Land)x.adjacent.get(i)))) //found a castle, not the one I'm in, if any!!!
            return depth;
        }
      }
      
      /* Couldn't Find any castle directly adjacent to the adjacent territories */
      for(i=0; i < x.adjacent.size(); i++){
        if(x.adjacent.get(i) instanceof Land){
          temp = findDistance(orig, ((Land)x.adjacent.get(i)), depth+1);
          if(temp < distance){
           distance = temp; 
          } 
        }
      }
    }
    return distance;
  }
  
  /* Make move from 'from' to 'to'; always use least valuable token to move */
  public void makeMove(Territory from, Territory to, House currentHouse){
    
    int i=0, j=0;
    String[] houses = {"Baratheon", "Lannister", "Stark", "Martell", "Greyjoy", "Tyrell"};
    House tmp=null;
    boolean found = false;
    
    if(from.footmen > 0){
      from.footmen = from.footmen-1;
      to.footmen = to.footmen+1;
      
      from.power = from.power-1;
      to.power = to.power+1;
    }
    else if(from.knights > 0){
      from.knights = from.knights-1;
      to.knights = to.knights +1;
      
      from.power = from.power-2;
      to.power = to.power+2;
    }
    
    /* Removing territory from old House, if owned */
    for(i=0; i < houses.length; i++){
      tmp = getHouse(houses[i]);
      for(j=0; j < tmp.territories.size(); j++){
        if(tmp.territories.get(j).equals(to) && ((Land)to).mustering > 0){ //moving to territory with castle
          
          tmp.territories.remove(j);
          found = true;
          /* Updating Castles */
          tmp.castles = tmp.castles-1;
          break;
        }
        else if(tmp.territories.get(j).equals(to) && ((Land)to).mustering == 0){ //moving to territory without castle
          tmp.territories.remove(j);
          currentHouse.castles = currentHouse.castles-1; //to balance for the default adding 1 castle
          found = true;
          break;
        }
      }
      if(found)
        break;
    }
    
    currentHouse.territories.add(to);
    currentHouse.castles = currentHouse.castles+1;
  }
}