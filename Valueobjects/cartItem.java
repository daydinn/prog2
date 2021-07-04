package Valueobjects;

public class cartItem {

  //private double preis;
  //private double zwischenpreis;
  private int amount;
  private Item i;
  
  /**
   * Description: a TempItemcontains an item and a number. The number indicates how often this is in the shopping cart.
   * @param i
   * @param amount
   */
  
  
  public cartItem(Item i, int amount) {
    this.amount = amount;
    this.i = i;
  }

  
  
  
  
  /**
   * Description : Compares 2 items, if the other item is an instance of the item, then the numbers are equated, otherwise it gives false.
   * 
   */
  
  
  public boolean euqals(Object o) {
    System.out.println("call");
    if (o instanceof cartItem) {
      System.out.println("equals");
      return (this.i.getNumber() == ((cartItem) o).getItem().getNumber());
    } else {
      return false;
    }

  }

  /**
   * This method specifies how an item should be output as a string in the Consol. 
   * 
   **/
  public String toString() {
    double gPrice;
    gPrice = (this.i.getPrice() * this.amount);
    return (this.i.toString() + " | Amount: " + amount + " | Unitprice: " + this.i.getPrice() + "€ | Price: " + gPrice + "€");
  }

  //Getter and Setter
  public Item getItem() {
    return this.i;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int number) {
    this.amount = number;
  }

}