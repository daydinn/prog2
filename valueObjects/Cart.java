package Valueobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {

  private List < cartItem > cartList = new ArrayList < cartItem > ();
  private List < Item > iList;

  public Cart(List < Item > iList) {
    this.iList = iList;
  }

  /**
   * Description: outputs the shopping cart in the console.
   */
  public void output() {
    for (cartItem ti: cartList) {
      System.out.println(ti);
    }
    if (cartList.isEmpty()) {
      System.out.println("Cart is empty!");
    }
  }
  /**
   * outputs the current shopping cart list
   * @return Cartlist
   */
  public List < cartItem > getCart() {
    return cartList;
  }

  /**
   * searches the shopping cart for a name
   * @param name
   * @return searchResult
   */
  public List < cartItem > searchByName(String name) {

    List < cartItem > searchResult = new ArrayList < cartItem > ();
    Iterator < cartItem > iter = cartList.iterator();

    while (iter.hasNext()) {
      cartItem ti = iter.next();
      if (ti.getItem().getName().equals(name)) {
        searchResult.add(ti);
      }
    }

    return searchResult;
  }

  /**
   * Description: empty the shopping cart
   */
  public void empty() {
    cartList.removeAll(cartList);
  }

  /**
   * Description: Adds an item to the shopping cart. It also checks  whether an article is already in the shopping cart
   * @param iNumber
   * @param iAmount
   */
  public void addItem(int iNumber, int iAmo) {
    Iterator < Item > iter = this.iList.iterator();
    cartItem ti;

    while (iter.hasNext()) {
      Item i = iter.next();
      if (i.getNumber() == iNumber) {
        if (cartList.contains(new cartItem(i, iAmo))) { //check whether the item is already in the shopping cart (uncorrect)
          ti = cartList.get(cartList.indexOf(i)); //returns an object from a list, (indexof gives  the place in a list of an object)

        } else {
          if (i.getStock() >= iAmo) {
            cartList.add(new cartItem(i, iAmo));

          } else {
            System.out.println("There are not enough items in storage");
          }
        }

      }
    }
  }

  /**
   * Description: deletes an item from the shopping cart, with itemnumber.
   * @param iNumber
   * @param iAmo
   */
  public void delItem(int iNumber, int iAmo) { //remove item from shopping cart
    Iterator < cartItem > iter = this.cartList.iterator();
    while (iter.hasNext()) {
      cartItem ti = iter.next();
      if (ti.getItem().getNumber() == iNumber) { //if Item is in the shopping cart
        if (ti.getAmount() == iAmo) { // if Item should be completely deleted
          ti.setAmount(0);
          cartList.remove(ti);
        } else if (ti.getAmount() > iAmo) { // The amount of Items in the shopping cart should be reduced
          ti.setAmount(ti.getAmount() - iAmo);
        } else {
          System.out.println("It is not possible to delete more items than are available.");
        }
      } else { //Item was not in the shopping cart at all
        System.out.println("This item is not in the shopping cart.");
      }
    }

  }
  /**
   * Description: Change the ammount of Items in  shopping cart, with itemnumber.
   * @param iNumber
   * @param iAmo
   */
  public void changeStockofItem(int iNumber, int iAmo) { //change items amount

    Iterator < cartItem > iter = this.cartList.iterator();

    while (iter.hasNext()) {
      cartItem ti = iter.next();

      if (ti.getItem().getNumber() == iNumber) { //if Item is in the shopping cart
        if ((ti.getItem().getStock() == iAmo || ti.getItem().getStock() > iAmo) && iAmo >= 0) { //The amount to be changed cant be bigger than the Item's Storage Stock.
          System.out.println("Item amount in Cart has been changed");
          ti.setAmount(iAmo);
          if (iAmo == 0) {
            cartList.remove(ti);
          }

        } else {
          System.out.println("It is not possible to change the amount of this item");
        }

      } else {
        System.out.println("it doesnt work.");
      }

    }

  }

  /**
   * Description: buys the Cart
   */
  public void buy() {

    for (cartItem ti: cartList) { //go through each item in the shopping cart
      if (iList.contains(ti.getItem())) { //check whether the items from the shopping cart are also in the item list
        Iterator < Item > iter = this.iList.iterator(); // search in the item list for the item that is selected in the for loop
        while (iter.hasNext()) {
          Item i = iter.next();
          if (i.getNumber() == ti.getItem().getNumber()) { //whether both have selected the same object
            i.setStock(i.getStock() - ti.getAmount()); //reduce the inventory in the Storage
            System.out.println(i);

          }
        }
      }
    }
  }

}