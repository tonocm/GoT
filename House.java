import java.util.*;
public class House{
  
  String name;
  ArrayList<Land> lands;
  ArrayList<Sea> seas;
  ArrayList<Card> handCards;
  
  public House(String name, ArrayList<Land> lands, ArrayList<Sea> seas, ArrayList<Card> handCards){
    this.name = name;
    this.lands = lands;
    this.seas = seas;
    this.handCards = handCards;
  }
}