import java.util.*;
public class House{
  
  String name;
  LinkedList<Territory> territories;
  int castles;
  //ArrayList<Land> lands;
  //ArrayList<Sea> seas;
  //ArrayList<Card> handCards;
  
  public House(String name, LinkedList<Territory> territories /* ArrayList<Land> lands, ArrayList<Sea> seas, ArrayList<Card> handCards*/){
    this.name = name;
    this.territories = territories;
    castles = 0;
    //this.lands = lands;
    //this.seas = seas;
    //this.handCards = handCards;
  }
}