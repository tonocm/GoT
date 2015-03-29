public class Order{
  
  String name; //maybe not?
  int orderType; //1 = march/attack, 2 = defense, 3 = support, 4 = crown, 5 = raid
  int modifier; //-1, 0, 1, 2
  boolean star;
  
  public Order(String name, int modifier, boolean star){
    this.name = name.toLowerCase();
    this.modifier = modifier;
    this.star = star;
    
    /* Assigning order type based on order's name */
    switch((char)name.charAt(0)){
      case 'm':
      case 'a':
        orderType = 1;
      break;
      case 'd':
        orderType = 2;
      break;
      case 's':
        orderType = 3;
      break;
      case 'c':
        orderType = 4;
      break;
      case 'r':
        orderType = 5;
      break;
      default:
        System.err.println("An order token is reaching the default case in class Orders");
        System.exit(1);
      break;
    }
  }
  
}