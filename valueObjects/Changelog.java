package Valueobjects;

import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * 
 * Description: Changelog is the class for reports in console
 * typ= true --> Employee   typ= false -->Customer
 */
public class Changelog {

  private Employee e;
  private Customer c;
  private String message;
  private boolean typ; //true: Employee | false: Customer
  private String Time;

  /**
   * Description: Constructor for creating a new changelog for an customer
   * @param c
   * @param message
   * @param typ
   */
  public Changelog(Customer c, String message, boolean typ) {
    this.c = c;
    this.message = message;
    this.typ = typ;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String Time = dateFormat.format(date);
    this.Time = Time;
  }

  /**
   * Description: Constructor for creating a new changelog for an employee
   * @param e
   * @param message
   * @param typ
   */
  public Changelog(Employee e, String message, boolean typ) {
    this.e = e;
    this.message = message;
    this.typ = typ;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String Time = dateFormat.format(date);
    this.Time = Time;
  }

  /**
   * Description: Constructor for loading the changelog from a file.
   * @param e
   * @param message
   * @param typ
   * @param Time
   */
  public Changelog(Employee e, String message, boolean typ, String Time) {
    this.e = e;
    this.message = message;
    this.typ = typ;
    this.Time = Time;
  }

  /**
   * Description: Constructor for loading the changelog from a file.
   * @param c
   * @param message
   * @param typ
   * @param Time
   */
  public Changelog(Customer c, String message, boolean typ, String Time) {
    this.c = c;
    this.message = message;
    this.typ = typ;
    this.Time = Time;
  }

  /**
   * Description: This method specifies how a changelog should be output as a string in the console.
   */
  public String toString() {
    return ("report: " + message);
  }

  //getter Methoden

  public Employee getEmployee() {
    return this.e;
  }

  public Customer getCustomer() {
    return this.c;
  }

  public String getMessage() {
    return message;
  }

  public String getTime() {
    return this.Time;
  }

  public boolean getTyp() {
    return this.typ;
  }

}