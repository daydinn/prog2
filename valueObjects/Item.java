package Valueobjects;

public class Item {

  private String name;
  private int number;
  private double price;
  private int stock;
  private int minimumStock;
  private int bulk;

  public Item(String name, int number, double price, int stock, int minimumStock, int bulk) {
    this.number = number;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.minimumStock = minimumStock;
    this.bulk = bulk;
  }

  /**
   * Description: This method specifies how an item should be output as a string in the Consol.
   */
  public String toString() {
    return ("Name: " + name + " | Number: " + number + " | Price: " + price + " | Stock: " + stock + " | Bulk: " + bulk);
  }

  /**
   * Description: Compares two items, if the other item is an instance of the item, the item number then the numbers are equated,otherwise it gives false.
   * 
   */
  public boolean euqals(Object otherItem) {
    if (otherItem instanceof Item) {
      return (this.number == ((Item) otherItem).number) &&
        (this.name.equals(((Item) otherItem).name));
    } else {
      return false;
    }

  }

  //getter und Setter

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

  public double getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }

  public int getminimumStock() {
    return minimumStock;
  }

  public int getBulk() {
    return bulk;
  }

  public void setStock(int newStock) {
    this.stock = newStock;
  }

}