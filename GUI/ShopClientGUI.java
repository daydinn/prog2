package GUI;

//package CUI;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Domain.ChangelogManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import Exceptions.IncorrectLoginDataException;
import Exceptions.InvalidItemNameException;
import Exceptions.InvalidItemNumberException;
import Exceptions.InvalidCustomerNumberException;
import Exceptions.InvalidEmployeeNameException;
import Exceptions.InvalidEmployeeNumberException;
import Exceptions.InvalidNameChangelogException;
import Exceptions.InvalidNumberChangelogException;
import LoginFunctions.LoginCustomer;
import LoginFunctions.LoginEmployee;
import Exceptions.InvalidCartException;
import Exceptions.InvalidCartNameException;
import Exceptions.InvalidCustomerNameException;
import Valueobjects.Cart;
import Valueobjects.Changelog;
import Valueobjects.Customer;
import Valueobjects.CustomerManagement;
import Valueobjects.Employee;
import Valueobjects.EmployeeManagement;
import Valueobjects.Item;
import Valueobjects.Storage;
import Valueobjects.cartItem;

public class ShopClientGUI extends JFrame {

  private static Employee system;

  private int currentCustomer;
  private int currentEmployee;

  private List < Item > ilist;
  private List < Customer > clist;
  private List < Employee > elist;
  static List < String > log = new ArrayList < String > ();

  private static Storage storage;
  private static EmployeeManagement employeeManagement;
  private static CustomerManagement customerManagement;
  private static ChangelogManager logmanager;
  private static Cart cart;

  private JFrame getMenuFrame;
  private JFrame loginFrame;
  private JFrame shopCustomerRegistrationFrame;
  private JFrame shopEmployeeRegistrationFrame;
  private JFrame shopLoginEmployeeFrame;
  private JFrame shopLoginCustomerFrame;
  private JFrame employeeMenuFrame;
  private JFrame addItemMenuFrame;
  private JFrame CustomerMenuFrame;
  private JFrame DeleteItemMenuFrame;
  private JFrame searchItemFrame;
  private JFrame nameSearchFrame;
  private JFrame searchEmployeeNameFrame;
  private JFrame searchEmployeeNrFrame;
  private JFrame deleteCustomerFrame;
  private JFrame searchCustomerNrFrame;
  private JFrame searchCustomerNameFrame;
  private JFrame deleteEmployeeFrame;
  private JFrame BillFrame;

  private JTable table;
  private JTable itemTable;
  private JTable cartItemTable;
  private JTable tableCustomer;
  private JTable tableEmployee;
  private JTable tableChangelog;

  private JScrollPane ScrollTotalPrice;

  private JTextField CustomerNrText;
  private JTextField employeeNrText;
  private JTextField customerNumberText;
  private JTextField firstnametext;
  private JTextField textName2;
  private JTextField lastnametext;
  private JTextField Adresstext;
  private JTextField usernametext;
  private JTextField textID;
  private JTextField textItem;
  private JTextField textItem4;
  private JTextField textNumber;
  private JTextField textPrice;
  private JTextField textStock;
  private JTextField textMinimumStock;
  private JTextField ItemNumbertext;
  private JTextField itemNrtext;
  private JTextField ItemNrtext1;
  private JTextField amounttext1;
  private JTextField textBulk;
  private JTextField ItemNrText;
  private JTextField textEmployeeNr;
  private JPasswordField Passwordtext;

  private JLabel labelTotalprice;
  private JLabel InvalidCustomerNr;
  private JLabel InvalidEmployeeNr;
  private JLabel wrongIdorPw;
  private JLabel falscherArtikel;
  private JLabel wrongEntrylbl;
  private JLabel totalPriceNumberlbl;
  private JLabel Datelbl;
  private JLabel Editorlbl;
  private JLabel Namelbl;
  private JLabel Emaillbl;
  private JLabel nameofEmaillbl;
  private JLabel CustomerNrlbl;
  private JLabel Nrlbl;
  private JLabel totalPricelbl;
  private JLabel Pricelbl;
  private JLabel Thankslbl;
  private JLabel Kindregardslbl;
  private JLabel customerNamelbl;
  private JLabel Adresslbl;
  private JLabel labelAdresse1;
  private Object tabelleFeld;

  /**
   * Description: starts the program. Creates the GUI. Saves all lists before the program is closed.
   * 
   */
  public static void main(String[] args) {

    ShopClientGUI gui;

    gui = new ShopClientGUI("It", "Emp", "Cus", "Log");

    gui.getMenu();

    // creates the date with today's time

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String strDate = dateFormat.format(date);
    System.out.println("Converted String: " + strDate);

    // system user for the changelog
    employeeManagement.deleteEmployee(1);
    system = employeeManagement.addAnEmployee("system", "123456789", "Admin", "Admin", "Admin", 1);

    //saves all data before exiting the program
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        System.out.println("test");
        try {
          logmanager.writeData("Log");
          System.out.println("Data has been written");
        } catch (IOException e1) {
          e1.printStackTrace();
          System.out.println("Data has not been written correctly");
        }
        try {
          storage.writeItems();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          customerManagement.writeCustomers();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          employeeManagement.writeEmployees();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }, "Shutdown-thread"));

  }

  /**
   * 
   * Used by: The Main Method
   * Description: The construcot of GUI creates the necessary data structure classes and their Manager classes
   * 
   * @param dItem is the file for Items
   * @param dEmployee is the file for employees
   * @param dCustomer is the file customers
   * @param dLog is the file for logs
   */
  public ShopClientGUI(String dItem, String dEmployee, String dCustomer, String dLog) {

    try {
      storage = new Storage(dItem);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      employeeManagement = new EmployeeManagement(dEmployee);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      customerManagement = new CustomerManagement(dCustomer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    logmanager = new ChangelogManager();

    try {
      logmanager.readData("Log");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    elist = employeeManagement.getAllEmployees();

    clist = customerManagement.getAllCustomers();

    ilist = storage.getAllItems();

    cart = new Cart(ilist);

  }

  /**
   *  Description: Outputs the first Menu in which you can either log in or register
   * 
   */
  public void getMenu() {

    // create a new Window

    getMenuFrame = new JFrame();
    getMenuFrame.setTitle("E-Shop");
    getMenuFrame.setBounds(800, 200, 500, 500);
    getMenuFrame.getContentPane().setBackground(new Color(255, 255, 204));
    getMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getMenuFrame.getContentPane().setLayout(null);
    getMenuFrame.setVisible(true);

    //Label Welcome

    JLabel Welcome = new JLabel("Welcome to our E-Shop, its great to meet you!");
    Welcome.setFont(new Font("Serif", Font.PLAIN, 16));
    Welcome.setBounds(95, -10, 312, 200);
    getMenuFrame.getContentPane().add(Welcome);

    JLabel Welcome2 = new JLabel("Here you'll find everything you want...");
    Welcome2.setFont(new Font("Dialog", Font.PLAIN, 10));
    Welcome2.setPreferredSize(getMaximumSize());
    Welcome2.setBounds(152, 30, 312, 200);
    Welcome2.setForeground(Color.gray);
    getMenuFrame.getContentPane().add(Welcome2);

    //Label Login or Register

    JLabel LoR = new JLabel("Login or Register ?");
    LoR.setBounds(148, 145, 241, 250);
    LoR.setFont(new Font("SansSerif", Font.PLAIN, 22));
    LoR.setForeground(new Color(102, 51, 0));
    getMenuFrame.getContentPane().add(LoR);

    //Button for Login

    JButton Login = new JButton("Login");
    Login.setBackground(new Color(255, 204, 153));
    Login.setFont(new Font("Sans", Font.PLAIN, 17));
    Login.setForeground(new Color(102, 51, 0));
    Login.addActionListener(new ActionListener() {

      // function to load the next window "registration" and it closes the old window

      public void actionPerformed(ActionEvent e) {

        getMenuFrame.setVisible(false);
        login();

      }
    });
    Login.setBounds(98, 360, 105, 50);
    getMenuFrame.getContentPane().add(Login);

    // button registration

    JButton Register = new JButton("Register");
    Register.setFont(new Font("Sans", Font.PLAIN, 17));
    Register.setForeground(new Color(102, 51, 0));
    Register.setBackground(new Color(255, 204, 153));
    Register.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // funktion zum schließen dess alten fensters und öffnet das registrierungsfenster 	
        getMenuFrame.setVisible(false);
        shopCustomerRegistration(false);

      }
    });

    Register.setBounds(280, 360, 105, 50);
    getMenuFrame.getContentPane().add(Register);

  }

  /**
   *  Used by: shopLoginEmployee
   *  Beschriebung: Generates the GUI for the employee window. All tabs are created here and the functions for the employee menu are also called up.
   */
  public void employeeMenu() {

    // Employee Menu ( with title, window size, closing the window and layout) 

    employeeMenuFrame = new JFrame();
    employeeMenuFrame.setVisible(true);
    employeeMenuFrame.setTitle("Menü for Employee");
    employeeMenuFrame.setBounds(125, 20, 1300, 1000);
    employeeMenuFrame.getContentPane().setBackground(new Color(255, 255, 204));
    employeeMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    employeeMenuFrame.getContentPane().setLayout(null);

    // main tab created

    JTabbedPane Maintab = new JTabbedPane(JTabbedPane.TOP);
    Maintab.setBounds(40, 20, 1250, 900);
    employeeMenuFrame.getContentPane().add(Maintab);

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Storage tab created

    JPanel StorageTab = new JPanel();

    Maintab.addTab("Storage", null, StorageTab, null);
    StorageTab.setBackground(new Color(255, 255, 230));
    StorageTab.setLayout(null);

    //creates the layout where the table is 

    JScrollPane Layout = new JScrollPane();
    Layout.setBounds(270, 158, 940, 600);
    StorageTab.add(Layout);

    // create the table
    itemTable = new JTable();
    itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "Name",
      "Number",
      "Price",
      "Stock",
      "Minimumstock",
      "Bulk"
    }) {});
    itemTable.setGridColor(new Color(102, 102, 0));
    itemTable.setBackground(Color.white);
    itemTable.getTableHeader().setBackground(new Color(179, 179, 0));
    itemTable.setRowHeight(30);

    // sets the size of the column of the window with the names

    itemTable.getColumnModel().getColumn(0).setPreferredWidth(45);
    itemTable.getColumnModel().getColumn(1).setPreferredWidth(55);
    itemTable.getColumnModel().getColumn(2).setPreferredWidth(40);
    itemTable.getColumnModel().getColumn(3).setPreferredWidth(57);
    itemTable.getColumnModel().getColumn(4).setPreferredWidth(90);
    itemTable.getColumnModel().getColumn(5).setPreferredWidth(57);
    Layout.setViewportView(itemTable);

    //Fill the table for the first time, calls the method above
    updateTabelle(storage.getAllItems());

    //creates a button "add item"

    JButton addItembtn = new JButton("Add Item");
    addItembtn.addActionListener(new ActionListener() {

      // Function to open a new window in order to be able to add Items

      public void actionPerformed(ActionEvent e) {

        addItemMenuFrame = new JFrame();
        addItemMenuFrame.setTitle("Add an Item");
        addItemMenuFrame.setVisible(true);
        addItemMenuFrame.setBounds(1390, 280, 500, 500);
        addItemMenuFrame.getContentPane().setBackground(new Color(255, 255, 230));
        addItemMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemMenuFrame.getContentPane().setLayout(null);

        // creates label item name

        JLabel Itemnamelbl = new JLabel("Itemname :");
        Itemnamelbl.setForeground(new Color(102, 51, 0));
        Itemnamelbl.setBounds(37, 64, 96, 14);
        addItemMenuFrame.getContentPane().add(Itemnamelbl);

        // create a text entry for the item name

        textItem = new JTextField(null);
        textItem.setBounds(37, 89, 96, 20);
        addItemMenuFrame.getContentPane().add(textItem);
        textItem.setColumns(10);

        // creates a label for the item number 

        JLabel lblArtikelnummer = new JLabel("Itemnumber :");
        lblArtikelnummer.setBounds(37, 120, 96, 14);
        lblArtikelnummer.setForeground(new Color(102, 51, 0));
        addItemMenuFrame.getContentPane().add(lblArtikelnummer);

        // creates the text entry for the item number

        textNumber = new JTextField(null);
        textNumber.setColumns(10);
        textNumber.setBounds(37, 145, 37, 20);
        addItemMenuFrame.getContentPane().add(textNumber);

        // creates label for the price

        JLabel Pricelbl = new JLabel("Price :");
        Pricelbl.setBounds(187, 64, 96, 14);
        Pricelbl.setForeground(new Color(102, 51, 0));
        addItemMenuFrame.getContentPane().add(Pricelbl);

        // creates the text entry for the price

        textPrice = new JTextField(null);
        textPrice.setColumns(10);
        textPrice.setBounds(187, 89, 96, 20);
        addItemMenuFrame.getContentPane().add(textPrice);

        // creates the label for Stock

        JLabel Stocklbl = new JLabel("Stock");
        Stocklbl.setBounds(187, 120, 96, 14);
        Stocklbl.setForeground(new Color(102, 51, 0));
        addItemMenuFrame.getContentPane().add(Stocklbl);

        // creates a text entry for the stock

        textStock = new JTextField(null);
        textStock.setColumns(10);
        textStock.setBounds(187, 145, 96, 20);
        addItemMenuFrame.getContentPane().add(textStock);

        // creates label for minimum stock

        JLabel minimumStocklbl = new JLabel("Minimumstock :");
        minimumStocklbl.setBounds(37, 176, 110, 14);
        minimumStocklbl.setForeground(new Color(102, 51, 0));
        addItemMenuFrame.getContentPane().add(minimumStocklbl);

        // creates text entry for minimum stock

        textMinimumStock = new JTextField(null);
        textMinimumStock.setColumns(10);
        textMinimumStock.setBounds(37, 201, 96, 20);
        addItemMenuFrame.getContentPane().add(textMinimumStock);

        // creates label bulk

        JLabel Bulklbl = new JLabel("Bulk :");
        Bulklbl.setBounds(37, 225, 96, 14);
        Bulklbl.setForeground(new Color(102, 51, 0));
        addItemMenuFrame.getContentPane().add(Bulklbl);

        // creates text entry for bulk 

        textBulk = new JTextField(null);
        textBulk.setColumns(10);
        textBulk.setBounds(37, 250, 96, 20);
        addItemMenuFrame.getContentPane().add(textBulk);

        // if you haven't filled out all fields, this label will appear

        falscherArtikel = new JLabel("");
        falscherArtikel.setBounds(37, 350, 170, 14);
        falscherArtikel.setForeground(new Color(255, 128, 128));
        falscherArtikel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        addItemMenuFrame.getContentPane().add(falscherArtikel);

        // created button add + function to add the articles

        JButton addInsidebtn = new JButton("Add");

        addInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            // the variables for the input fields and reading out the input fields in the GUI
            String itemName = "";
            String itemNumber = "";
            String itemPrice = "";
            String itemStock = "";
            String itemMinimumStock = "";
            String itemBulk = "";

            int iNum;
            double iPri;
            int iSto;
            int iMSto;
            int iBulk;

            itemName = textItem.getText();
            System.out.println(itemName);

            itemNumber = textNumber.getText();
            System.out.println(itemNumber);
            iNum = Integer.parseInt(itemNumber);

            itemPrice = textPrice.getText();
            System.out.println(itemPrice);
            iPri = Double.parseDouble(itemPrice);

            itemStock = textStock.getText();
            System.out.println(itemStock);
            iSto = Integer.parseInt(itemStock);

            itemMinimumStock = textMinimumStock.getText();
            System.out.println(itemMinimumStock);
            iMSto = Integer.parseInt(itemMinimumStock);

            itemBulk = textBulk.getText();
            iBulk = Integer.parseInt(itemBulk);

            try {
              for (Item i: storage.getAllItems()) { // goes through all items
                if (i.getNumber() == iNum) { // if the item number already exists
                  falscherArtikel.setForeground(Color.RED); //red color for error message
                  falscherArtikel.setText("The item number already exists!"); //error message
                  logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "you tried to insert an existing item!", true));
                  textNumber.setText(null); //clears the field with the article number
                  throw new InvalidItemNumberException(); //throw the exception

                }
              }
            } catch (InvalidItemNumberException ex) {
              System.out.println(ex.getMessage()); // catches the exception and displays it in the console
            }

            if (!textNumber.getText().isEmpty()) { // if all fields are filled correctly
              storage.addAnItem(itemName, iNum, iPri, iSto, iMSto, iBulk); // add new item to the Storage
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item: " + itemName + " has been added.", true));
              try {
                storage.writeItems(); // saves the new stock in the txt file
              } catch (IOException e1) {

                e1.printStackTrace();
              }

              addItemMenuFrame.setVisible(false); // closes the menu
              updateTabelle(storage.getAllItems()); // updates the table for items
            }

          }
        });
        addInsidebtn.setBounds(184, 200, 126, 23);
        addInsidebtn.setForeground(new Color(102, 51, 0));
        addInsidebtn.setBackground(new Color(255, 204, 153));
        addItemMenuFrame.getContentPane().add(addInsidebtn);

      }
    });
    addItembtn.setBounds(55, 250, 126, 23);
    addItembtn.setBackground(new Color(255, 204, 153));

    StorageTab.add(addItembtn);

    // created button "delete item"

    JButton deleteItembtn = new JButton("Delete");
    deleteItembtn.addActionListener(new ActionListener() {

      //Function to open a new window in order to be able to delete iteks

      public void actionPerformed(ActionEvent e) {

        // erstellt neues fenster für  artikel löschen 

        DeleteItemMenuFrame = new JFrame();
        DeleteItemMenuFrame.setTitle("Deleting an Item");
        DeleteItemMenuFrame.setVisible(true);
        DeleteItemMenuFrame.setBounds(970, 100, 300, 300);
        DeleteItemMenuFrame.getContentPane().setBackground(new Color(255, 255, 230));
        DeleteItemMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DeleteItemMenuFrame.getContentPane().setLayout(null);

        // creates label for deleting an item

        JLabel deleteItemlbl2 = new JLabel("What would you delete?");
        deleteItemlbl2.setForeground(new Color(102, 51, 0));
        deleteItemlbl2.setBounds(68, 11, 160, 31);
        DeleteItemMenuFrame.getContentPane().add(deleteItemlbl2);

        // creates label for false input

        JLabel iNumNotAssignedlbl = new JLabel("  ");
        iNumNotAssignedlbl.setForeground(Color.RED);
        iNumNotAssignedlbl.setBounds(40, 215, 280, 14);
        DeleteItemMenuFrame.getContentPane().add(iNumNotAssignedlbl);

        // creates label for enter an item number

        JLabel enter3 = new JLabel("Please enter the number of the item :");
        enter3.setBounds(50, 78, 282, 14);
        enter3.setFont(new Font("Dialog", Font.PLAIN, 11));
        DeleteItemMenuFrame.getContentPane().add(enter3);

        // text input for the item number that you enter there

        ItemNumbertext = new JTextField();
        ItemNumbertext.setBounds(107, 113, 54, 20);
        ItemNumbertext.setColumns(10);
        DeleteItemMenuFrame.getContentPane().add(ItemNumbertext);

        // creates the delete button to delete items

        JButton deleteinsidebtn = new JButton("Delete");
        deleteinsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String itemNumber = "";
            int iNum;

            itemNumber = ItemNumbertext.getText();
            System.out.println(itemNumber);
            iNum = Integer.parseInt(itemNumber);

            try { // try and catch for the correctness of the item number
              if (checkNumber(iNum)) { //if yes delete item
                storage.deleteItem(iNum); //deletes the item from the storage
                logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item with number : " + iNum + " has been deleted!", true));
                DeleteItemMenuFrame.setVisible(false); //closes the delete window
                updateTabelle(storage.getAllItems()); //updates the table of items
                try {
                  storage.writeItems(); //saves the items in the TXT file
                } catch (IOException e1) {
                  e1.printStackTrace();
                }
              }
            } catch (InvalidItemNumberException ex) { // if not, catch the exception and give an error message
              System.out.println(ex.getMessage()); // outputs the error message in the console
              iNumNotAssignedlbl.setText("Please enter a valid Number!"); // error message if the item number is wrong in the GUI
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage(), true)); //schriebt den Fehler im Changelog
              ItemNumbertext.setText(null); // clears the input field for the item number
            }

          }
        });
        deleteinsidebtn.setBounds(81, 153, 104, 32);
        deleteinsidebtn.setBackground(new Color(255, 204, 153));
        deleteinsidebtn.setForeground(new Color(102, 51, 0));
        DeleteItemMenuFrame.getContentPane().add(deleteinsidebtn);
      }
    });
    deleteItembtn.setBounds(55, 350, 126, 23);
    deleteItembtn.setBackground(new Color(255, 204, 153));

    StorageTab.add(deleteItembtn);

    // created button "search item"

    JButton searchItembtn = new JButton("Search Items");
    searchItembtn.setBackground(new Color(255, 204, 153));
    searchItembtn.addActionListener(new ActionListener() {

      // Function to open a new window to search for items

      public void actionPerformed(ActionEvent e) {

        // created a window to search for an item

        searchItemFrame = new JFrame();
        searchItemFrame.setTitle("Search Items");
        searchItemFrame.setVisible(true);
        searchItemFrame.setBounds(970, 120, 300, 300);
        searchItemFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchItemFrame.getContentPane().setLayout(null);

        // creates a label for which item you are looking for

        JLabel searchItemlbl = new JLabel("Which Item are you looking for?");
        searchItemlbl.setForeground(new Color(102, 51, 0));
        searchItemlbl.setBounds(50, 11, 193, 31);
        searchItemFrame.getContentPane().add(searchItemlbl);

        // creates a label for the name of the item

        JLabel itemNamelbl = new JLabel("Please enter the name of Item :");
        itemNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        itemNamelbl.setBounds(63, 70, 282, 14);
        searchItemFrame.getContentPane().add(itemNamelbl);

        // creates a text entry where you can write the name in

        textItem4 = new JTextField();
        textItem4.setBounds(81, 93, 104, 20);
        searchItemFrame.getContentPane().add(textItem4);
        textItem4.setColumns(10);

        // errormessage if you entered a wrong name 

        JLabel WrongItemlbl = new JLabel("");
        WrongItemlbl.setForeground(Color.RED);
        WrongItemlbl.setBounds(98, 186, 240, 14);
        searchItemFrame.getContentPane().add(WrongItemlbl);

        // created button search and a function to search for an Item

        JButton searchbtn = new JButton("Suchen");
        searchbtn.setBackground(new Color(255, 204, 153));
        searchbtn.setForeground(new Color(102, 51, 0));
        searchbtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String itemName = "";

            itemName = textItem4.getText();

            try { // try and catch whether the name is correct
              if (checkName(itemName)) { // if so, look for it
                updateTabelle(storage.searchByName(itemName)); // updates the table with the search result
                searchItemFrame.setVisible(false); // close the window
              }
            } catch (InvalidItemNameException ex) { // if not, catch the exception and give an error message
              System.out.println(ex.getMessage()); // outputs the error message in the console
              WrongItemlbl.setText("Invalid name!"); // error message in the gui
              textItem4.setText(null); // empties the input field for the Item name
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage() + "in the search", true));
            }

          }
        });
        searchbtn.setBounds(81, 143, 104, 32);
        searchItemFrame.getContentPane().add(searchbtn);

      }
    });
    searchItembtn.setBounds(55, 300, 126, 23);
    StorageTab.add(searchItembtn);

    // create button "show item and function so that the table is updated""

    JButton showitemsbtn = new JButton("Show Items");
    showitemsbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        updateTabelle(storage.getAllItems()); // updates the table that contains all items 

      }
    });
    showitemsbtn.setBounds(55, 400, 130, 23);
    showitemsbtn.setBackground(new Color(255, 204, 153));

    StorageTab.add(showitemsbtn);

    // creates button "sort item number"

    JButton ItemSoNum = new JButton("Sort by Number");

    ItemSoNum.addActionListener(new ActionListener() {

      //Funktion zum öffnen eines neuen Fensters, um artikel zu sortieren zu können
      public void actionPerformed(ActionEvent e) {

        updateTabelle(sortNumberItemList(storage.getAllItems())); //sortiert die Tabelle für die Artikel
      }
    });
    ItemSoNum.setBounds(420, 120, 130, 20);
    ItemSoNum.setBackground(new Color(255, 204, 153));
    ItemSoNum.setForeground(new Color(102, 51, 0));

    StorageTab.add(ItemSoNum);

    // created button "sort items name"

    JButton ItemSoNam = new JButton("Sort by Name");
    ItemSoNam.addActionListener(new ActionListener() {

      //Function to open a new window to be able to sort items

      public void actionPerformed(ActionEvent e) {

        updateTabelle(sortNameItemList(storage.getAllItems())); // sorts the table for the items

      }
    });
    ItemSoNam.setBounds(273, 120, 130, 20);
    ItemSoNam.setBackground(new Color(255, 204, 153));
    ItemSoNam.setForeground(new Color(102, 51, 0));
    StorageTab.add(ItemSoNam);

    // sorts the table for the items

    JScrollPane Layout1 = new JScrollPane();
    Layout1.setBounds(30, 557, 170, 202);
    StorageTab.add(Layout1);

    // create a grid in the layout to be able to insert buttons etc.

    JPanel Grid = new JPanel();
    Grid.setBackground(new Color(255, 255, 204));
    Layout1.setViewportView(Grid);
    Grid.setLayout(null);

    // creates label for new stock

    JLabel NewStocklbl = new JLabel("New Stock :");
    NewStocklbl.setBounds(37, 92, 113, 14);
    NewStocklbl.setForeground(new Color(102, 51, 0));
    Grid.add(NewStocklbl);

    //creates a text input to write for inventory

    JSpinner spinnerStock = new JSpinner();
    spinnerStock.setBounds(37, 110, 60, 26);
    Grid.add(spinnerStock);

    // creates label for item name

    JLabel ItemNrlbl = new JLabel("ItemNr :");
    ItemNrlbl.setBounds(37, 24, 113, 14);
    ItemNrlbl.setForeground(new Color(102, 51, 0));
    Grid.add(ItemNrlbl);

    //creates a text input to write for item number

    ItemNrText = new JTextField();
    ItemNrText.setColumns(10);
    ItemNrText.setBounds(37, 41, 96, 20);
    Grid.add(ItemNrText);

    // creates a label for incorrect entry (it remains empty for the time being)

    JLabel WrongItemNrlbl = new JLabel(" ");
    WrongItemNrlbl.setForeground(Color.RED);
    WrongItemNrlbl.setBounds(14, 150, 170, 14);
    Grid.add(WrongItemNrlbl);

    System.out.println(ItemNrText.getText());

    // created button "change stock"

    JButton changeStockbtn = new JButton("Change Stock");
    changeStockbtn.setBackground(new Color(255, 204, 153));
    changeStockbtn.setForeground(new Color(102, 51, 0));
    changeStockbtn.addActionListener(new ActionListener() {

      // 	created function to change inventory

      public void actionPerformed(ActionEvent e) {

        //variables for the stock reduction and fill the text fields
        String itemNumber = "";
        String itemStock = "";
        int iSto;
        int iNum;
        System.out.println(ItemNrText.getText());
        itemNumber = ItemNrText.getText();
        System.out.println(ItemNrText.getText());
        iNum = Integer.parseInt(itemNumber);

        iSto = (Integer) spinnerStock.getValue(); // read  the spinner

        try { // try to change the item Stocks
          if (checkNumberStock(iNum, iSto)) { // if the entry was correct ..
            WrongItemNrlbl.setText(null); // delete the error message
            ItemNrText.setText(null); //clear the input fields
            spinnerStock.setValue((Integer) 0); //clear the spinner field
            updateTabelle(storage.getAllItems()); //update the Table
            WrongItemNrlbl.setForeground(Color.black); //color of the text field on black
            WrongItemNrlbl.setText("Stock changed!"); //report the successful change
            logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item: " + iNum + " hast been incrased by : " + iSto, true));
            try {
              storage.writeItems(); // save/write the new item inventory
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
        } catch (InvalidItemNumberException ex) { // if the entry was not correct ..
          WrongItemNrlbl.setForeground(Color.RED); //makes text color red
          WrongItemNrlbl.setText("Incorrect item number!"); //errormessage
          textItem.setText(null); //lempty input field for the item number
          System.out.println(ex.getMessage()); //output the error message in the console
          logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage() + "while increasing the stock", true));
        }

      }
    });
    changeStockbtn.setBounds(8, 174, 152, 23);

    Grid.add(changeStockbtn);

    /*-------------------------------------------------------------------------------------------------------------------*/

    // create tab changelog

    JPanel ChangelogTab = new JPanel();
    Maintab.addTab("Changelog", null, ChangelogTab);
    ChangelogTab.setBackground(new Color(255, 255, 230));

    ChangelogTab.setLayout(null);

    // create das layout to scroll the table

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(270, 158, 940, 600);
    scrollPane_1.setBackground(new Color(102, 102, 0));
    ChangelogTab.add(scrollPane_1);

    // create Table

    tableChangelog = new JTable();
    tableChangelog.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "Date",
      "Nr",
      "Firsname",
      "Lastname",
      "Report"
    }) {});

    tableChangelog.getColumnModel().getColumn(0).setPreferredWidth(53);
    tableChangelog.getColumnModel().getColumn(1).setPreferredWidth(33);
    tableChangelog.getColumnModel().getColumn(2).setPreferredWidth(62);
    tableChangelog.getColumnModel().getColumn(3).setPreferredWidth(67);
    tableChangelog.getColumnModel().getColumn(4).setPreferredWidth(60);
    scrollPane_1.setViewportView(tableChangelog);
    tableChangelog.setGridColor(new Color(102, 102, 0));
    tableChangelog.setBackground(Color.white);
    tableChangelog.getTableHeader().setBackground(new Color(179, 179, 0));
    tableChangelog.setRowHeight(30);

    updateChangelogTable(logmanager.getChangelog());

    // create button for searching for a name

    JButton searchaNamebtn = new JButton("Search a name");
    searchaNamebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        nameSearchFrame = new JFrame();
        nameSearchFrame.setTitle("Search a name");
        nameSearchFrame.setVisible(true);
        nameSearchFrame.setBounds(345, 38, 250, 240);
        nameSearchFrame.getContentPane().setBackground(new Color(255, 255, 230));
        nameSearchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameSearchFrame.getContentPane().setLayout(null);

        JLabel namelbl = new JLabel("Who are you looking for?");

        namelbl.setForeground(new Color(102, 51, 0));
        namelbl.setBounds(43, 20, 193, 31);
        nameSearchFrame.getContentPane().add(namelbl);

        JLabel userNamelbl = new JLabel("Enter a name please:");
        userNamelbl.setBounds(60, 60, 282, 14);
        userNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        nameSearchFrame.getContentPane().add(userNamelbl);

        textName2 = new JTextField();
        textName2.setBounds(60, 85, 104, 24);
        nameSearchFrame.getContentPane().add(textName2);
        textName2.setColumns(10);

        JLabel FalscherName = new JLabel("");
        FalscherName.setForeground(Color.RED);
        FalscherName.setBounds(75, 178, 240, 14);
        nameSearchFrame.getContentPane().add(FalscherName);

        JButton searchInsidebtn = new JButton("Search");
        searchInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            //variable and input
            String lName = "";

            lName = textName2.getText();

            try {

              updateChangelogTable(logmanager.searchChangelogName(lName)); //if true update the table with the search result
              nameSearchFrame.setVisible(false); //delete error message
            } catch (InvalidNameChangelogException ex) { // if not, catch the exception and give an error message
              System.out.println(ex.getMessage()); // output error message in console
              FalscherName.setText("Invalid name!"); // error message in gui
              textName2.setText(null); // empty the input field
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage() + "while searching in the changelog", true));
            }

          }
        });
        searchInsidebtn.setBounds(60, 143, 104, 32);
        searchInsidebtn.setBackground(new Color(255, 204, 153));
        searchInsidebtn.setForeground(new Color(102, 51, 0));
        nameSearchFrame.getContentPane().add(searchInsidebtn);

      }
    });

    searchaNamebtn.setBounds(30, 157, 180, 23);
    searchaNamebtn.setBackground(new Color(255, 204, 153));
    searchaNamebtn.setForeground(new Color(102, 51, 0));
    ChangelogTab.add(searchaNamebtn);

    //-----------------------------------------------------------	------------------------------------

    //created button customer search

    JButton nrsearchbtn = new JButton("Search a nr");
    nrsearchbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // creates a new window for  customer

        JFrame nrSearchFrame = new JFrame();
        nrSearchFrame.setTitle("Searching a Customer");
        nrSearchFrame.setVisible(true);
        nrSearchFrame.setBounds(1270, 150, 304, 246);
        nrSearchFrame.getContentPane().setBackground(new Color(255, 255, 230));
        nrSearchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nrSearchFrame.getContentPane().setLayout(null);

        // creates label customer search

        JLabel nrsearchlbl = new JLabel("Who are you looking for ?");
        nrsearchlbl.setForeground(new Color(102, 51, 0));
        nrsearchlbl.setBounds(72, 11, 193, 31);
        nrSearchFrame.getContentPane().add(nrsearchlbl);

        // creates label customer nr

        JLabel nrcustomerlbl = new JLabel("Please enter the User Nr");
        nrcustomerlbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        nrcustomerlbl.setBounds(80, 60, 282, 14);
        nrSearchFrame.getContentPane().add(nrcustomerlbl);

        // creates text entry to enter customer number

        JTextField nrcustomertxt = new JTextField();
        nrcustomertxt.setBounds(89, 93, 104, 20);
        nrSearchFrame.getContentPane().add(nrcustomertxt);
        nrcustomertxt.setColumns(10);

        // creates label for incorrect entry, initially empty

        JLabel invalidnrsearchlbl = new JLabel("");
        invalidnrsearchlbl.setForeground(Color.RED);
        invalidnrsearchlbl.setBounds(98, 186, 240, 14);
        nrSearchFrame.getContentPane().add(invalidnrsearchlbl);

        // creates button search + function search

        JButton nrsearchInsidebtn = new JButton("Search");
        nrsearchInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String cNumber = "";
            int cNum;
            cNumber = nrcustomertxt.getText();
            cNum = Integer.parseInt(cNumber);

            try {

              updateChangelogTable(logmanager.searchChangelogNr(cNum));
              nrSearchFrame.setVisible(false);

            } catch (InvalidNumberChangelogException ex) {
              invalidnrsearchlbl.setText("Invalid number!");
              nrcustomertxt.setText(null);
              System.out.println(ex.getMessage());
            }

          }
        });
        nrsearchInsidebtn.setBounds(89, 143, 104, 32);
        nrsearchInsidebtn.setBackground(new Color(255, 204, 153));
        nrsearchInsidebtn.setForeground(new Color(102, 51, 0));
        nrSearchFrame.getContentPane().add(nrsearchInsidebtn);

      }
    });
    nrsearchbtn.setBounds(30, 197, 180, 23);
    nrsearchbtn.setBackground(new Color(255, 204, 153));
    nrsearchbtn.setForeground(new Color(102, 51, 0));
    ChangelogTab.add(nrsearchbtn);

    //-----------------------------------------------------------------------------------

    // created button to sort by date

    JButton searchbyDatebtn = new JButton("Sort by Date");
    searchbyDatebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateChangelogTable(sortDateChangelogliste(logmanager.getChangelog()));
      }
    });
    searchbyDatebtn.setBounds(30, 237, 180, 23);
    searchbyDatebtn.setBackground(new Color(255, 204, 153));
    searchbyDatebtn.setForeground(new Color(102, 51, 0));
    ChangelogTab.add(searchbyDatebtn);

    // created button to update

    JButton updatebtn = new JButton("Update");
    updatebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateChangelogTable(logmanager.getChangelog());
      }
    });
    updatebtn.setBounds(30, 277, 180, 23);
    updatebtn.setBackground(new Color(255, 204, 153));
    updatebtn.setForeground(new Color(102, 51, 0));
    ChangelogTab.add(updatebtn);

    /*----------------------------------------------------------------------------------------------------------------*/

    JPanel userManagementTab = new JPanel();
    userManagementTab.setBackground(new Color(255, 255, 230));
    Maintab.addTab("Usermanagement", null, userManagementTab, null);
    userManagementTab.setLayout(null);

    //  creates ScrollPane for table

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(400, 50, 700, 300);
    scrollPane.setBackground(new Color(102, 102, 0));
    userManagementTab.add(scrollPane);

    // creates table for customer

    tableCustomer = new JTable();
    tableCustomer.setGridColor(new Color(102, 102, 0));
    tableCustomer.setBackground(Color.white);
    tableCustomer.getTableHeader().setBackground(new Color(179, 179, 0));
    tableCustomer.setRowHeight(30);

    tableCustomer.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "CustomerNr",
      "Username",
      "Firstname",
      "Lastname",
      "Adress"
    }) {});

    // sets the size of the column of the window with the names

    tableCustomer.getColumnModel().getColumn(0).setPreferredWidth(20);
    tableCustomer.getColumnModel().getColumn(1).setPreferredWidth(40);
    tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(40);
    tableCustomer.getColumnModel().getColumn(3).setPreferredWidth(40);
    tableCustomer.getColumnModel().getColumn(4).setPreferredWidth(200);

    scrollPane.setViewportView(tableCustomer);

    // fill the table with customers from customermanagement
    updateUserCustomerTable(customerManagement.getAllCustomers());

    //  creates scrollPane for the 2nd table

    JScrollPane scrollPane1 = new JScrollPane();
    scrollPane1.setBounds(400, 550, 700, 300);
    scrollPane1.setBackground(new Color(102, 102, 0));
    userManagementTab.add(scrollPane1);

    // creates table for employees

    tableEmployee = new JTable();
    tableEmployee.setGridColor(new Color(102, 102, 0));
    tableEmployee.setBackground(Color.white);
    tableEmployee.getTableHeader().setBackground(new Color(179, 179, 0));
    tableEmployee.setRowHeight(30);

    tableEmployee.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "EmployeeNr",
      "Username",
      "Firstname",
      "Lastname",
      "Email"
    }) {});

    //sets the size of the column of the window with the names
    tableEmployee.getColumnModel().getColumn(0).setPreferredWidth(20);
    tableEmployee.getColumnModel().getColumn(1).setPreferredWidth(40);
    tableEmployee.getColumnModel().getColumn(2).setPreferredWidth(40);
    tableEmployee.getColumnModel().getColumn(3).setPreferredWidth(40);
    tableEmployee.getColumnModel().getColumn(4).setPreferredWidth(200);

    scrollPane1.setViewportView(tableEmployee);

    //fill the table with employess from employeemanagement
    updateUserEmployeeTable(employeeManagement.getAllEmployees());

    // created button search for employees 
    JButton searchEmployeenrbtn = new JButton("Search Employees by Nr");

    searchEmployeenrbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // created new windows for search an employee 

        searchEmployeeNrFrame = new JFrame();
        searchEmployeeNrFrame.setTitle("Searching an Employee");
        searchEmployeeNrFrame.setVisible(true);
        searchEmployeeNrFrame.setBounds(1270, 450, 304, 246);
        searchEmployeeNrFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchEmployeeNrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchEmployeeNrFrame.getContentPane().setLayout(null);

        // creates a label which employee you are looking for

        JLabel searchEmployeenrlbl = new JLabel("Who are you looking for?");
        searchEmployeenrlbl.setForeground(new Color(102, 51, 0));
        searchEmployeenrlbl.setBounds(65, 11, 193, 31);
        searchEmployeeNrFrame.getContentPane().add(searchEmployeenrlbl);

        // creates a label that you should enter an employee number

        JLabel employeeNrlbl = new JLabel("Please enter the Employee Nr :");
        employeeNrlbl.setBounds(60, 68, 282, 14);
        employeeNrlbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        searchEmployeeNrFrame.getContentPane().add(employeeNrlbl);

        // creates a text entry where you can enter the employee number

        employeeNrText = new JTextField();
        employeeNrText.setBounds(81, 93, 104, 20);
        searchEmployeeNrFrame.getContentPane().add(employeeNrText);
        employeeNrText.setColumns(10);

        // creates label if wrong input (initially empty, in function it will be filled)
        JLabel FalscherArtikel = new JLabel("");
        FalscherArtikel.setForeground(Color.RED);
        FalscherArtikel.setBounds(105, 186, 240, 14);
        searchEmployeeNrFrame.getContentPane().add(FalscherArtikel);

        // creates buton search + function to search for an employee

        JButton searchnrbtn = new JButton("Search");
        searchnrbtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String mNummer = "";
            int mNum;
            mNummer = employeeNrText.getText();
            mNum = Integer.parseInt(mNummer);

            try {
              if (checkNumberEmployee(mNum)) {
                updateUserEmployeeTable(employeeManagement.searchByNumber(mNum));
                searchEmployeeNrFrame.setVisible(false);
              }
            } catch (InvalidEmployeeNumberException ex) {
              FalscherArtikel.setText("Invalid Nr!");
              textItem.setText(null);
              System.out.println(ex.getMessage());
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Search Error! " + ex.getMessage(), true));
            }

          }
        });
        searchnrbtn.setBounds(81, 143, 104, 32);
        searchnrbtn.setBackground(new Color(255, 204, 153));
        searchnrbtn.setForeground(new Color(102, 51, 0));
        searchEmployeeNrFrame.getContentPane().add(searchnrbtn);

      }
    });
    searchEmployeenrbtn.setBounds(80, 650, 200, 23);
    searchEmployeenrbtn.setForeground(new Color(102, 51, 0));
    searchEmployeenrbtn.setBackground(new Color(255, 204, 153));
    userManagementTab.add(searchEmployeenrbtn);

    //fill the table with employess from employeemanagement
    updateUserEmployeeTable(employeeManagement.getAllEmployees());

    JButton searchEmployeenamebtn = new JButton("Search Employees by Name");
    searchEmployeenamebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // created new windows for search an employee 

        searchEmployeeNameFrame = new JFrame();
        searchEmployeeNameFrame.setTitle("Searching an Employee");
        searchEmployeeNameFrame.setVisible(true);
        searchEmployeeNameFrame.setBounds(1270, 450, 304, 246);
        searchEmployeeNameFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchEmployeeNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchEmployeeNameFrame.getContentPane().setLayout(null);

        // creates a label which employee you are looking for

        JLabel searchEmployeenamelbl = new JLabel("Who are you looking for?");
        searchEmployeenamelbl.setForeground(new Color(102, 51, 0));
        searchEmployeenamelbl.setBounds(65, 11, 193, 31);
        searchEmployeeNameFrame.getContentPane().add(searchEmployeenamelbl);

        // creates a label that you should enter an employee number

        JLabel employeeNamelbl = new JLabel("Please enter the Employee Name :");
        employeeNamelbl.setBounds(60, 68, 282, 14);
        employeeNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        searchEmployeeNameFrame.getContentPane().add(employeeNamelbl);

        // creates a text entry where you can enter the employee number

        JTextField employeeNameText = new JTextField();
        employeeNameText.setBounds(81, 93, 104, 20);
        searchEmployeeNameFrame.getContentPane().add(employeeNameText);
        employeeNameText.setColumns(10);

        // creates label if wrong input (initially empty, in function it will be filled)
        JLabel FalscherArtikel = new JLabel("");
        FalscherArtikel.setForeground(Color.RED);
        FalscherArtikel.setBounds(105, 186, 240, 14);
        searchEmployeeNameFrame.getContentPane().add(FalscherArtikel);

        // creates buton search + function to search for an employee

        JButton searchnmbtn = new JButton("Search");
        searchnmbtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String employeeName = "";

            employeeName = employeeNameText.getText();

            try {
              if (checkNameEmp(employeeName)) {
                updateUserEmployeeTable(employeeManagement.searchByName(employeeName));
                searchEmployeeNameFrame.setVisible(false);
              }
            } catch (InvalidEmployeeNameException ex) {
              FalscherArtikel.setText("Invalid Name!");
              textItem.setText(null);
              System.out.println(ex.getMessage());
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Search Error! " + ex.getMessage(), true));
            }

          }
        });
        searchnmbtn.setBounds(81, 143, 104, 32);
        searchnmbtn.setBackground(new Color(255, 204, 153));
        searchnmbtn.setForeground(new Color(102, 51, 0));
        searchEmployeeNameFrame.getContentPane().add(searchnmbtn);

      }
    });
    searchEmployeenamebtn.setBounds(80, 600, 200, 23);
    searchEmployeenamebtn.setBackground(new Color(255, 204, 153));
    searchEmployeenamebtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(searchEmployeenamebtn);

    // creates button delete employee
    JButton deleteEmployeebtn = new JButton("Delete an Employee ");
    deleteEmployeebtn.setBackground(new Color(255, 204, 153));
    deleteEmployeebtn.setForeground(new Color(102, 51, 0));
    deleteEmployeebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // // creates window for deleting an employee

        deleteEmployeeFrame = new JFrame();
        deleteEmployeeFrame.setTitle("Deleting an Employee");
        deleteEmployeeFrame.setVisible(true);
        deleteEmployeeFrame.setBounds(1270, 550, 304, 246);
        deleteEmployeeFrame.getContentPane().setBackground(new Color(255, 255, 230));
        deleteEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteEmployeeFrame.getContentPane().setLayout(null);

        // created label delete employee

        JLabel deleteEmployeelbl = new JLabel("Who would you delete ?");
        deleteEmployeelbl.setForeground(new Color(102, 51, 0));
        deleteEmployeelbl.setBounds(68, 11, 160, 31);
        deleteEmployeeFrame.getContentPane().add(deleteEmployeelbl);

        // creates a label which is initially empty

        JLabel employeeNrErrorlbl = new JLabel("  ");
        employeeNrErrorlbl.setForeground(Color.RED);
        employeeNrErrorlbl.setBounds(24, 186, 280, 14);
        deleteEmployeeFrame.getContentPane().add(employeeNrErrorlbl);

        // creates a label to ask the user to enter an employee number

        JLabel enterEmployeeNrlbl = new JLabel("Please Enter the Employee Nr :");
        enterEmployeeNrlbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        enterEmployeeNrlbl.setBounds(60, 68, 282, 14);
        deleteEmployeeFrame.getContentPane().add(enterEmployeeNrlbl);

        // creates a text entry in order to be able to enter the employee number

        textEmployeeNr = new JTextField();
        textEmployeeNr.setBounds(107, 93, 54, 20);
        deleteEmployeeFrame.getContentPane().add(textEmployeeNr);
        textEmployeeNr.setColumns(10);

        // created button delete + function to delete an employee

        JButton deleteEmployeeInsidebtn = new JButton("Delete");
        deleteEmployeeInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String mNummer = "";
            int mNum;

            mNummer = textEmployeeNr.getText();
            System.out.println(mNummer);
            mNum = Integer.parseInt(mNummer);

            try {
              if (checkNumberEmployee(mNum)) {
                employeeManagement.deleteEmployee(mNum);
                deleteEmployeeFrame.setVisible(false);
                updateUserEmployeeTable(employeeManagement.getAllEmployees());
                logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The employee with the number: " + mNum + " has been deleted.", true));
                try {
                  employeeManagement.writeEmployees();
                } catch (IOException e1) {
                  e1.printStackTrace();
                }
              }
            } catch (InvalidEmployeeNumberException ex) {
              System.out.println(ex.getMessage());
              employeeNrErrorlbl.setText("Please enter a valid employee number!");
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Incorrect employee number while deleting!", true));
              employeeNrErrorlbl.setText(null);
            }

          }
        });
        deleteEmployeeInsidebtn.setBounds(81, 143, 104, 32);
        deleteEmployeeInsidebtn.setBackground(new Color(255, 204, 153));
        deleteEmployeeInsidebtn.setForeground(new Color(102, 51, 0));
        deleteEmployeeFrame.getContentPane().add(deleteEmployeeInsidebtn);

      }
    });
    deleteEmployeebtn.setBounds(80, 750, 200, 23);
    userManagementTab.add(deleteEmployeebtn);

    // create button create employee

    JButton createEmployeeInsidebtn = new JButton("Create a new Employee");
    createEmployeeInsidebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        shopEmployeeRegistration(true);
        updateUserEmployeeTable(employeeManagement.getAllEmployees());
      }
    });
    createEmployeeInsidebtn.setBounds(80, 700, 200, 23);
    createEmployeeInsidebtn.setBackground(new Color(255, 204, 153));
    createEmployeeInsidebtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(createEmployeeInsidebtn);

    //created button customer search

    JButton searchCustomernrbtn = new JButton("Search Customers by Nr");
    searchCustomernrbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // creates a new window for  customer

        searchCustomerNrFrame = new JFrame();
        searchCustomerNrFrame.setTitle("Searching a Customer");
        searchCustomerNrFrame.setVisible(true);
        searchCustomerNrFrame.setBounds(1270, 150, 304, 246);
        searchCustomerNrFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchCustomerNrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchCustomerNrFrame.getContentPane().setLayout(null);

        // creates label customer search

        JLabel searchCustomerNrlbl = new JLabel("Who are you looking for ?");
        searchCustomerNrlbl.setForeground(new Color(102, 51, 0));
        searchCustomerNrlbl.setBounds(62, 11, 193, 31);
        searchCustomerNrFrame.getContentPane().add(searchCustomerNrlbl);

        // creates label customer nr

        JLabel CustomerNrlbl = new JLabel("Please enter the Customer Nr");
        CustomerNrlbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        CustomerNrlbl.setBounds(62, 68, 282, 14);
        searchCustomerNrFrame.getContentPane().add(CustomerNrlbl);

        // creates text entry to enter customer number

        CustomerNrText = new JTextField();
        CustomerNrText.setBounds(81, 93, 104, 20);
        searchCustomerNrFrame.getContentPane().add(CustomerNrText);
        CustomerNrText.setColumns(10);

        // creates label for incorrect entry, initially empty

        JLabel invalidCustomerlbl = new JLabel("");
        invalidCustomerlbl.setForeground(Color.RED);
        invalidCustomerlbl.setBounds(90, 186, 240, 14);
        searchCustomerNrFrame.getContentPane().add(invalidCustomerlbl);

        // creates button search + function search

        JButton searchnrInsidebtn = new JButton("Search");
        searchnrInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String cNumber = "";
            int cNum;
            cNumber = CustomerNrText.getText();
            cNum = Integer.parseInt(cNumber);

            try {
              if (checkNumberCustomer(cNum)) {
                updateUserCustomerTable(customerManagement.searchByNumber(cNum));
                searchCustomerNrFrame.setVisible(false);
              }
            } catch (InvalidCustomerNumberException ex) {
              invalidCustomerlbl.setText("Invalid number!");
              CustomerNrText.setText(null);
              System.out.println(ex.getMessage());
            }

          }
        });
        searchnrInsidebtn.setBounds(81, 143, 104, 32);
        searchnrInsidebtn.setBackground(new Color(255, 204, 153));
        searchnrInsidebtn.setForeground(new Color(102, 51, 0));
        searchCustomerNrFrame.getContentPane().add(searchnrInsidebtn);

      }
    });
    searchCustomernrbtn.setBounds(80, 150, 200, 23);
    searchCustomernrbtn.setBackground(new Color(255, 204, 153));
    searchCustomernrbtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(searchCustomernrbtn);

    //fill the table with employess from employeemanagement
    updateUserCustomerTable(customerManagement.getAllCustomers());

    JButton searchCustomernamebtn = new JButton("Search Customers by Name");
    searchCustomernamebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // created new windows for search an employee 

        searchCustomerNameFrame = new JFrame();
        searchCustomerNameFrame.setTitle("Searching a Customer");
        searchCustomerNameFrame.setVisible(true);
        searchCustomerNameFrame.setBounds(1270, 450, 304, 246);
        searchCustomerNameFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchCustomerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchCustomerNameFrame.getContentPane().setLayout(null);

        // creates a label which employee you are looking for

        JLabel searchCustomernamelbl = new JLabel("Who are you looking for?");
        searchCustomernamelbl.setForeground(new Color(102, 51, 0));
        searchCustomernamelbl.setBounds(65, 11, 193, 31);
        searchCustomerNameFrame.getContentPane().add(searchCustomernamelbl);

        // creates a label that you should enter an employee number

        JLabel customerNamelbl = new JLabel("Please enter the Customer Name :");
        customerNamelbl.setBounds(60, 68, 282, 14);
        customerNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        searchCustomerNameFrame.getContentPane().add(customerNamelbl);

        // creates a text entry where you can enter the employee number

        JTextField customerNameText = new JTextField();
        customerNameText.setBounds(81, 93, 104, 20);
        searchCustomerNameFrame.getContentPane().add(customerNameText);
        customerNameText.setColumns(10);

        // creates label if wrong input (initially empty, in function it will be filled)
        JLabel InvalidCustomerlbl = new JLabel("");
        InvalidCustomerlbl.setForeground(Color.RED);
        InvalidCustomerlbl.setBounds(105, 186, 240, 14);
        searchCustomerNameFrame.getContentPane().add(InvalidCustomerlbl);

        // creates buton search + function to search for an employee

        JButton searchcnmbtn = new JButton("Search");
        searchcnmbtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String customerName = "";

            customerName = customerNameText.getText();

            try {
              if (checkNameCus(customerName)) {
                updateUserCustomerTable(customerManagement.searchByName(customerName));
                searchCustomerNameFrame.setVisible(false);
              }
            } catch (InvalidCustomerNameException ex) {
              InvalidCustomerlbl.setText("Invalid Name!");
              textItem.setText(null);
              System.out.println(ex.getMessage());
              logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Search Error! " + ex.getMessage(), true));
            }

          }
        });
        searchcnmbtn.setBounds(81, 143, 104, 32);
        searchcnmbtn.setBackground(new Color(255, 204, 153));
        searchcnmbtn.setForeground(new Color(102, 51, 0));
        searchCustomerNameFrame.getContentPane().add(searchcnmbtn);

      }
    });
    searchCustomernamebtn.setBounds(80, 100, 200, 23);
    searchCustomernamebtn.setBackground(new Color(255, 204, 153));
    searchCustomernamebtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(searchCustomernamebtn);

    // creates button delete customer

    JButton deleteCustomerbtn = new JButton("Delete Customers");
    deleteCustomerbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        //  created a new window to delete customer

        deleteCustomerFrame = new JFrame();
        deleteCustomerFrame.setTitle("Deleting a customer");
        deleteCustomerFrame.setVisible(true);
        deleteCustomerFrame.setBounds(1270, 300, 304, 246);
        deleteCustomerFrame.getContentPane().setBackground(new Color(255, 255, 230));
        deleteCustomerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteCustomerFrame.getContentPane().setLayout(null);

        // creates label delete 

        JLabel deleteCustomerlbl = new JLabel("Who would you delete");
        deleteCustomerlbl.setForeground(new Color(102, 51, 0));
        deleteCustomerlbl.setBounds(68, 11, 160, 31);
        deleteCustomerFrame.getContentPane().add(deleteCustomerlbl);

        // creates label for incorrect text entry, initially empty

        JLabel CustomerNrErrorlbl = new JLabel("  ");
        CustomerNrErrorlbl.setForeground(Color.RED);
        CustomerNrErrorlbl.setBounds(30, 186, 280, 14);
        deleteCustomerFrame.getContentPane().add(CustomerNrErrorlbl);

        // creates label for customer nr

        JLabel enterCustomerNr = new JLabel("Please enter the Customer Nr");
        enterCustomerNr.setFont(new Font("Dialog", Font.PLAIN, 11));
        enterCustomerNr.setBounds(60, 68, 282, 14);
        deleteCustomerFrame.getContentPane().add(enterCustomerNr);

        //creates a text entry for the customer number

        customerNumberText = new JTextField();
        customerNumberText.setBounds(107, 93, 54, 20);
        deleteCustomerFrame.getContentPane().add(customerNumberText);
        customerNumberText.setColumns(10);

        // 

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String cNumber = "";
            int cNum;

            cNumber = customerNumberText.getText();
            System.out.println(cNumber);
            cNum = Integer.parseInt(cNumber);

            try {
              if (checkNumberCustomer(cNum)) {
                customerManagement.deleteCustomer(cNum);
                deleteCustomerFrame.setVisible(false);
                logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Customer with Number: " + cNum + " has been deleted", true));
                updateUserCustomerTable(customerManagement.getAllCustomers());
                try {
                  customerManagement.writeCustomers();
                } catch (IOException e1) {
                  e1.printStackTrace();
                }
              }
            } catch (InvalidCustomerNumberException ex) {
              System.out.println(ex.getMessage());
              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Error while deleting a customer", true));
              CustomerNrErrorlbl.setText("Please enter a valid customer number!");
              customerNumberText.setText(null);
            }

          }
        });
        deleteBtn.setBounds(81, 143, 104, 32);
        deleteBtn.setBackground(new Color(255, 204, 153));
        deleteBtn.setForeground(new Color(102, 51, 0));
        deleteCustomerFrame.getContentPane().add(deleteBtn);

      }
    });
    deleteCustomerbtn.setBounds(80, 250, 200, 23);
    deleteCustomerbtn.setBackground(new Color(255, 204, 153));
    deleteCustomerbtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(deleteCustomerbtn);

    // creates button create customer

    JButton createCustomerbtn = new JButton("Create a new customer");
    createCustomerbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        shopCustomerRegistration(true);
        System.out.println("entry?");
        updateUserCustomerTable(customerManagement.getAllCustomers());

      }
    });
    createCustomerbtn.setBounds(80, 200, 200, 23);
    createCustomerbtn.setBackground(new Color(255, 204, 153));
    createCustomerbtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(createCustomerbtn);

    // created button update lists

    JButton updateblistsbtn = new JButton("Update lists");
    updateblistsbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        updateUserCustomerTable(customerManagement.getAllCustomers());
        updateUserEmployeeTable(employeeManagement.getAllEmployees());
      }
    });
    updateblistsbtn.setBounds(80, 450, 200, 26);
    updateblistsbtn.setBackground(new Color(255, 204, 153));
    updateblistsbtn.setForeground(new Color(102, 51, 0));
    userManagementTab.add(updateblistsbtn);

    // creates a label for the title of Tables (customer)

    JLabel listofCustomerslbl = new JLabel("List of customers");
    listofCustomerslbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
    listofCustomerslbl.setForeground(new Color(102, 51, 0));
    listofCustomerslbl.setBounds(650, 20, 160, 26);
    userManagementTab.add(listofCustomerslbl);

    // creates a label for title the tables (employees)

    JLabel listofEmployeeslbl = new JLabel("List of employees");
    listofEmployeeslbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
    listofEmployeeslbl.setForeground(new Color(102, 51, 0));
    listofEmployeeslbl.setBounds(650, 520, 160, 26);
    userManagementTab.add(listofEmployeeslbl);

    // creates the logout button and sends us back to the start window

    JButton logOutbtn = new JButton("Logout");
    logOutbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getMenu(); //gibt das startmenue aus
        employeeMenuFrame.setVisible(false); //ausloggen
        logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "has logged out", true));
        System.exit(0);
      }
    });
    logOutbtn.setBounds(1160, 920, 125, 40);
    logOutbtn.setBackground(new Color(255, 128, 128));
    logOutbtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
    employeeMenuFrame.getContentPane().add(logOutbtn);

  }

  /*--------------------------------------------------------------------------------*/

  /**
   *  Used by: shopAnmeldungKunde
   *  Description: generates the GUI for the customer menu. 
   */
  public void customerMenu() {

    // creates windows for the customer menu

    CustomerMenuFrame = new JFrame();
    CustomerMenuFrame.setVisible(true);
    CustomerMenuFrame.setTitle("Menü for Customer");
    CustomerMenuFrame.setBounds(125, 20, 1300, 1000);
    CustomerMenuFrame.getContentPane().setBackground(new Color(255, 255, 204));
    CustomerMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    CustomerMenuFrame.getContentPane().setLayout(null);

    // main tab 

    JTabbedPane Maintab = new JTabbedPane(JTabbedPane.TOP);
    Maintab.setBounds(40, 20, 1250, 900);
    CustomerMenuFrame.getContentPane().add(Maintab);

    /*-------------------------------------------------------------------------------------*/

    // creates tab assortment 

    JPanel assortment = new JPanel();
    assortment.setBackground(new Color(255, 255, 230));
    Maintab.addTab("Assortment", null, assortment, null);
    assortment.setLayout(null);

    // creates the layout where the table is created

    JScrollPane Layout = new JScrollPane();
    Layout.setBounds(270, 58, 390, 410);
    Layout.setBounds(270, 158, 940, 600);
    Layout.setBackground(new Color(102, 102, 0));
    assortment.add(Layout);

    // creates the table
    itemTable = new JTable();
    itemTable.setGridColor(new Color(102, 102, 0));
    itemTable.setBackground(Color.white);
    itemTable.getTableHeader().setBackground(new Color(179, 179, 0));
    itemTable.setRowHeight(30);
    itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "Name",
      "Number",
      "Price",
      "In Stock",
      "Minimum Amount"
    }) {});

    // size of the columns

    itemTable.getColumnModel().getColumn(0).setPreferredWidth(60);
    itemTable.getColumnModel().getColumn(1).setPreferredWidth(55);

    Layout.setViewportView(itemTable);

    //fill the table
    updateItemTable(storage.getAllItems());

    // created button "search item"

    JButton searchItembtn = new JButton("Search Item");
    searchItembtn.addActionListener(new ActionListener() {

      // Function to open a new window to search for items

      public void actionPerformed(ActionEvent e) {

        // created new window search for items	
        searchItemFrame = new JFrame();
        searchItemFrame.setTitle("Search Item");
        searchItemFrame.setVisible(true);
        searchItemFrame.setBounds(970, 150, 300, 250);
        searchItemFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchItemFrame.getContentPane().setLayout(null);

        // created label item search

        JLabel searchCustomerlbl = new JLabel("Which Item are you looking for?");
        searchCustomerlbl.setForeground(new Color(102, 51, 0));
        searchCustomerlbl.setBounds(50, 11, 193, 31);
        searchItemFrame.getContentPane().add(searchCustomerlbl);

        // creates a label for the item name

        JLabel itemNamelbl = new JLabel("Please enter the name of Item");
        itemNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        itemNamelbl.setBounds(65, 68, 282, 14);
        searchItemFrame.getContentPane().add(itemNamelbl);

        // creates a text entry so that you can type in the item name

        textItem = new JTextField();
        textItem.setBounds(90, 96, 104, 20);
        searchItemFrame.getContentPane().add(textItem);
        textItem.setColumns(10);

        // creates label for incorrect entry, initially empty in function is filled

        JLabel wrongItemlbl3 = new JLabel("");
        wrongItemlbl3.setForeground(Color.RED);
        wrongItemlbl3.setBounds(50, 186, 260, 14);
        searchItemFrame.getContentPane().add(wrongItemlbl3);

        // created button search + function search for an item in the range

        JButton searchinsidebtn = new JButton("Search");
        searchinsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String itemName = "";

            itemName = textItem.getText();

            try {
              if (checkNameItem(itemName)) {
                updateTabelle(storage.searchByName(itemName));
                searchItemFrame.setVisible(false);
              }
            } catch (InvalidItemNameException ex) {
              System.out.println(ex.getMessage());
              wrongItemlbl3.setText("Please enter a valid item name!");
              textItem.setText(null);
              logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "There was an error while searching for an item", false));
            }
          }
        });
        searchinsidebtn.setBounds(90, 143, 104, 32);
        searchinsidebtn.setBackground(new Color(255, 204, 153));
        searchinsidebtn.setForeground(new Color(102, 51, 0));
        searchItemFrame.getContentPane().add(searchinsidebtn);

      }

    });
    searchItembtn.setBounds(70, 190, 126, 23);
    searchItembtn.setBackground(new Color(255, 204, 153));
    searchItembtn.setForeground(new Color(102, 51, 0));
    assortment.add(searchItembtn);

    // creates button "sort item number"

    JButton sortItembyNrbtn = new JButton("Sort by Number");
    sortItembyNrbtn.addActionListener(new ActionListener() {

      // Function to open a new window to be able to sort items
      public void actionPerformed(ActionEvent e) {
        updateItemTable(sortNumberItemList(storage.getAllItems()));
      }
    });
    sortItembyNrbtn.setBounds(450, 115, 160, 23);
    sortItembyNrbtn.setBackground(new Color(255, 204, 153));
    sortItembyNrbtn.setForeground(new Color(102, 51, 0));
    assortment.add(sortItembyNrbtn);

    // created button "sort items name"

    JButton sortItembyNamebtn = new JButton("Sort by Name");
    sortItembyNamebtn.addActionListener(new ActionListener() {

      // Function to open a new window to be able to sort items

      public void actionPerformed(ActionEvent e) {
        updateItemTable(sortNameItemList(storage.getAllItems()));
      }
    });
    sortItembyNamebtn.setBounds(273, 115, 160, 23);
    sortItembyNamebtn.setBackground(new Color(255, 204, 153));
    sortItembyNamebtn.setForeground(new Color(102, 51, 0));
    assortment.add(sortItembyNamebtn);

    // creates button "show item"

    JButton showItemsbtn = new JButton("Show Items");
    showItemsbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateItemTable(storage.getAllItems());
      }
    });
    showItemsbtn.setBounds(70, 250, 126, 23);
    showItemsbtn.setBackground(new Color(255, 204, 153));
    showItemsbtn.setForeground(new Color(102, 51, 0));
    assortment.add(showItemsbtn);

    // create scrollPane (if scrollable)

    JScrollPane Layout1 = new JScrollPane();
    Layout1.setBounds(10, 468, 249, 291);
    assortment.add(Layout1);

    // create a "grid" or a JPanel (layout) to be able to insert buttons etc.

    JPanel Grid = new JPanel();
    Layout1.setViewportView(Grid);
    Grid.setLayout(null);

    // creates a label in the event of an incorrect entry, initially empty

    JLabel WrongItemlbl4 = new JLabel("  ");
    WrongItemlbl4.setBounds(60, 200, 170, 14);
    WrongItemlbl4.setFont(new Font("Dialog", Font.PLAIN, 10));
    Grid.add(WrongItemlbl4);

    // creates a counter to be able to click up the number

    JSpinner spinnerAmount = new JSpinner();
    spinnerAmount.setBounds(75, 166, 67, 23);
    Grid.add(spinnerAmount);

    // created button add item

    JButton addItembtn = new JButton("Add Item");
    addItembtn.addActionListener(new ActionListener() {

      // creates a function to add goods to the shopping cart

      public void actionPerformed(ActionEvent e) {

        String itemNumber = "";
        int iNum;
        itemNumber = itemNrtext.getText();
        iNum = Integer.parseInt(itemNumber);

        String iAmount = "";

        int iAmo = (Integer) spinnerAmount.getValue();

        try {
          if (checkCart(iNum, iAmo)) {
            cart.addItem(iNum, iAmo);
            cart.output();
            WrongItemlbl4.setForeground(Color.BLACK);
            WrongItemlbl4.setText("Item added.");

            itemNrtext.setText(null);
            spinnerAmount.setValue((Integer) 0);
            logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "The Item: " + iNum + "has been added  " + iAmo + "times to the Cart", false));

            // function to load in the table

            updateCustomerCartTable(cart.getCart());
          }
        } catch (InvalidCartException ex) {
          WrongItemlbl4.setForeground(Color.RED);
          WrongItemlbl4.setText("     Uncorrect Entry!");
          itemNrtext.setText(null);
          spinnerAmount.setValue((Integer) 0);
          logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Incorrect entry while adding to shopping cart", false));
          System.out.println(ex.getMessage());
        }

      }
    });
    addItembtn.setBounds(49, 229, 152, 23);
    addItembtn.setBackground(new Color(255, 204, 153));
    addItembtn.setForeground(new Color(102, 51, 0));
    Grid.add(addItembtn);

    // creates label for number

    JLabel Amountlbl = new JLabel("Amount :");
    Amountlbl.setBounds(76, 141, 113, 14);
    Amountlbl.setForeground(new Color(102, 51, 0));
    Grid.add(Amountlbl);

    // creates label for item number

    JLabel itemNamelbl = new JLabel("ItemNr:");
    itemNamelbl.setForeground(new Color(102, 51, 0));
    itemNamelbl.setBounds(76, 74, 113, 14);
    Grid.add(itemNamelbl);

    // creates a text entry to be able to enter an itemnumber

    itemNrtext = new JTextField();
    itemNrtext.setColumns(10);
    itemNrtext.setBounds(76, 110, 96, 20);
    Grid.add(itemNrtext);

    // creates label for the headline

    JLabel titellbl = new JLabel("Add an Item to the Shopping cart");
    titellbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
    titellbl.setBounds(20, 11, 227, 32);
    Grid.add(titellbl);

    /*--------------------------------------------------------------------------------------------------------------------------*/

    // creates shopping cart tab

    JPanel Cart = new JPanel();
    Cart.setBackground(new Color(255, 255, 230));
    Maintab.addTab("Cart", null, Cart, null);
    Cart.setLayout(null);

    // creates the window in the tab

    JScrollPane Layout3 = new JScrollPane();

    Layout3.setBounds(415, 150, 400, 550);

    Cart.add(Layout3);

    // creates a table 

    cartItemTable = new JTable();
    cartItemTable.setGridColor(new Color(102, 102, 0));
    cartItemTable.setBackground(new Color(230, 255, 230));
    cartItemTable.getTableHeader().setBackground(new Color(179, 179, 0));
    cartItemTable.setRowHeight(20);
    cartItemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
      "Name",
      "Nr",
      "Amount",
      "Unit Price",
      "Price"
    }) {});

    // size of the individual components in the table

    cartItemTable.getColumnModel().getColumn(0).setPreferredWidth(36);
    cartItemTable.getColumnModel().getColumn(1).setPreferredWidth(50);
    cartItemTable.getColumnModel().getColumn(2).setPreferredWidth(48);
    cartItemTable.getColumnModel().getColumn(3).setPreferredWidth(64);
    cartItemTable.getColumnModel().getColumn(4).setPreferredWidth(40);
    Layout3.setViewportView(cartItemTable);

    // load the shopping car

    // layout size for total price

    JScrollPane Layout2 = new JScrollPane();
    Layout2.setBounds(518, 715, 200, 37);
    Cart.add(Layout2);

    // label for total price

    totalPricelbl = new JLabel("Totalprice :");

    Layout2.setRowHeaderView(totalPricelbl);
    totalPricelbl.setFont(new Font("SansSerif", Font.PLAIN, 14));

    // label for the price that will be displayed

    totalPriceNumberlbl = new JLabel("");
    totalPriceNumberlbl.setFont(new Font("SansSerif", Font.PLAIN, 17));
    Layout2.setViewportView(totalPriceNumberlbl);

    // button "buy" created

    JButton buybtn = new JButton("Buy");
    buybtn.addActionListener(new ActionListener() {

      // function to buy the items

      public void actionPerformed(ActionEvent e) {

        // creates new window bill when buying

        BillFrame = new JFrame();
        BillFrame.setVisible(true);
        BillFrame.setTitle("Bill");
        BillFrame.setBounds(370, 75, 620, 800);
        BillFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BillFrame.getContentPane().setLayout(null);
        BillFrame.getContentPane().setBackground(Color.white);

        // creates a scrollpane

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(110, 190, 400, 350);
        BillFrame.getContentPane().add(scrollPane);

        //creates a table

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
          "Name",
          "Nr",
          "Amount",
          "Unit Price",
          "Price"
        }) {});

        // size of the individual components in the table

        table.getColumnModel().getColumn(0).setPreferredWidth(48);
        table.getColumnModel().getColumn(1).setPreferredWidth(33);
        table.getColumnModel().getColumn(2).setPreferredWidth(53);
        table.getColumnModel().getColumn(3).setPreferredWidth(65);
        table.getColumnModel().getColumn(4).setPreferredWidth(45);
        scrollPane.setViewportView(table);

        // creates label for "bill from"

        JLabel billFrom = new JLabel("Date of Bill: ");

        billFrom.setBounds(70, 116, 156, 28);
        BillFrame.getContentPane().add(billFrom);

        // creates label for the date

        Datelbl = new JLabel(new Date().toGMTString());
        Datelbl.setFont(new Font("Sans", Font.PLAIN, 10));
        Datelbl.setBounds(195, 115, 180, 28);
        BillFrame.getContentPane().add(Datelbl);

        // creates label for the editor

        Editorlbl = new JLabel("Editor:   ");
        Editorlbl.setBounds(388, 30, 73, 14);
        BillFrame.getContentPane().add(Editorlbl);

        // creates label for the name

        Namelbl = new JLabel("Helmut Eirund");
        Namelbl.setBounds(460, 30, 83, 14);
        BillFrame.getContentPane().add(Namelbl);

        // erstellt label für e mail 

        Emaillbl = new JLabel("E-Mail:");
        Emaillbl.setBounds(388, 55, 73, 14);
        BillFrame.getContentPane().add(Emaillbl);

        // creates label for e mail

        nameofEmaillbl = new JLabel("eshop@yahoo.de");
        nameofEmaillbl.setBounds(460, 55, 133, 14);
        BillFrame.getContentPane().add(nameofEmaillbl);

        // creates label for Customer nr

        CustomerNrlbl = new JLabel("CustomerNr: ");
        CustomerNrlbl.setBounds(70, 100, 141, 14);
        BillFrame.getContentPane().add(CustomerNrlbl);

        // creates a label for the customer's number

        Nrlbl = new JLabel("" + customerManagement.searchByNumber(currentCustomer).get(0).getCustomerNr());
        Nrlbl.setBounds(150, 100, 141, 14);
        BillFrame.getContentPane().add(Nrlbl);

        // creates a layout scrollPane for the total price in case the numbers get too big
        ScrollTotalPrice = new JScrollPane();
        ScrollTotalPrice.setBounds(290, 550, 220, 40);
        BillFrame.getContentPane().add(ScrollTotalPrice);

        // creates the label for the total price

        labelTotalprice = new JLabel("Totalprice: ");
        labelTotalprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ScrollTotalPrice.setRowHeaderView(labelTotalprice);

        // creates the label for the numbers (the price)

        Pricelbl = new JLabel("");
        Pricelbl.setForeground(Color.RED);
        ScrollTotalPrice.setViewportView(Pricelbl);

        // label for saying thank you

        Thankslbl = new JLabel("Thank you for your order! ");
        Thankslbl.setBounds(120, 560, 254, 14);
        BillFrame.getContentPane().add(Thankslbl);

        // label for kindregards

        Kindregardslbl = new JLabel("Kind regards, Eshop Team");
        Kindregardslbl.setBounds(119, 585, 215, 14);
        BillFrame.getContentPane().add(Kindregardslbl);

        // label for the name of customer

        customerNamelbl = new JLabel("Herr " + customerManagement.searchByNumber(currentCustomer).get(0).getLastname());
        customerNamelbl.setBounds(70, 11, 141, 14);
        BillFrame.getContentPane().add(customerNamelbl);

        // label for address from customer

        Adresslbl = new JLabel(customerManagement.searchByNumber(currentCustomer).get(0).getAdress());
        Adresslbl.setBounds(70, 36, 141, 14);
        BillFrame.getContentPane().add(Adresslbl);

        updateCustomerBillTable(cart.getCart());

        // creates button "ok"

        JButton closebtn = new JButton("Close");
        closebtn.addActionListener(new ActionListener() {

          // function to close the window

          public void actionPerformed(ActionEvent e) {
            BillFrame.setVisible(false);
            logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Items has been bought!", false));
            cart.buy();
            cart.empty();
            WrongItemlbl4.setText(null);
            try {
              storage.writeItems();
            } catch (IOException e1) {

              e1.printStackTrace();
            }
            totalPriceNumberlbl.setText(null);
            updateCustomerCartTable(cart.getCart());
            updateItemTable(storage.getAllItems());
          }
        });
        closebtn.setBounds(265, 664, 70, 23);
        closebtn.setBackground(new Color(255, 204, 153));
        closebtn.setForeground(new Color(102, 51, 0));

        BillFrame.getContentPane().add(closebtn);
      }
    });
    buybtn.setBounds(518, 757, 200, 35);
    buybtn.setBackground(new Color(230, 184, 0));
    buybtn.setFont(new Font("SansSerif", Font.PLAIN, 17));
    Cart.add(buybtn);

    //  creates button  "change amount" 

    JButton changeAmountbtn = new JButton("Change amount");

    // created function to change the amount of items in Cart

    changeAmountbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String itemNumber = "";
        int iNum;
        itemNumber = ItemNrtext1.getText();
        iNum = Integer.parseInt(itemNumber);

        String itemAmount = "";
        int iAmo;
        itemAmount = amounttext1.getText();
        iAmo = Integer.parseInt(itemAmount);

        try {
          if (checkCartChange(iNum, iAmo)) {
            cart.changeStockofItem(iNum, iAmo);
            logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "The amount of Item: " + iNum + "has been changed to " + iAmo, false));
            wrongEntrylbl.setForeground(Color.BLACK);
            wrongEntrylbl.setText("You tried to change the amount");
            ItemNrtext1.setText(null);
            amounttext1.setText(null);
            updateCustomerCartTable(cart.getCart());
          }
        } catch (InvalidCartException ex) {
          System.out.println(ex.getMessage());
          wrongEntrylbl.setForeground(Color.RED);
          logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Uncorrect entry while deleting an Item", false));
          wrongEntrylbl.setText("Uncorrect Entry!");
          ItemNrtext1.setText(null);
          amounttext1.setText(null);
        }

      }
    });

    changeAmountbtn.setBounds(210, 620, 141, 23);
    changeAmountbtn.setBackground(new Color(255, 204, 153));
    changeAmountbtn.setForeground(new Color(102, 51, 0));
    Cart.add(changeAmountbtn);

    //	JLabel titlelbl = new JLabel("Change amount ");
    //	titlelbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
    //	titlelbl.setBounds(190, 430, 59, 14);
    //	Cart.add(titlelbl);

    // creates label for item nr 

    JLabel itemNrlbl = new JLabel("ItemNr :");
    itemNrlbl.setForeground(new Color(102, 51, 0));
    itemNrlbl.setBounds(230, 490, 59, 14);
    Cart.add(itemNrlbl);

    // creates a text entry for item nr

    amounttext1 = new JTextField();
    //amounttext1.setBackground(Color.red);
    amounttext1.setBounds(225, 570, 96, 20);
    amounttext1.setColumns(10);
    Cart.add(amounttext1);

    // creates a label for nr

    JLabel amountlbl = new JLabel(" Amount :");
    amountlbl.setForeground(new Color(102, 51, 0));
    amountlbl.setBounds(225, 550, 59, 14);
    Cart.add(amountlbl);

    // creates a textfield for amount

    ItemNrtext1 = new JTextField();
    ItemNrtext1.setColumns(10);
    ItemNrtext1.setBounds(225, 520, 96, 20);
    Cart.add(ItemNrtext1);

    wrongEntrylbl = new JLabel("  ");
    wrongEntrylbl.setForeground(Color.RED);
    wrongEntrylbl.setBounds(210, 650, 141, 14);
    wrongEntrylbl.setFont(new Font("Dialog", Font.PLAIN, 9));
    Cart.add(wrongEntrylbl);

    // creates layout  for remove item

    JScrollPane Layout4 = new JScrollPane();
    Layout4.setBounds(150, 400, 250, 300);
    Cart.add(Layout4);

    // create button "empty shopping cart"

    JButton emptyCartbtn = new JButton("Empty Cart");
    emptyCartbtn.addActionListener(new ActionListener() {

      // function to empty the shopping cart

      public void actionPerformed(ActionEvent e) {
        logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), " has emptied the  cart!", false));
        cart.empty();
        totalPriceNumberlbl.setText(null);
        //FalscherArt.setText(null);
        wrongEntrylbl.setText(null);
        updateCustomerCartTable(cart.getCart());

      }
    });
    emptyCartbtn.setBounds(200, 250, 141, 23);
    emptyCartbtn.setBackground(new Color(255, 204, 153));
    emptyCartbtn.setForeground(new Color(102, 51, 0));
    Cart.add(emptyCartbtn);

    // creates button "search item"

    JButton SearchItemBtn = new JButton("Search Item");
    SearchItemBtn.addActionListener(new ActionListener() {

      // function to search for items in the shopping cart

      public void actionPerformed(ActionEvent e) {

        // creates a new search item window

        searchItemFrame = new JFrame();
        searchItemFrame.setVisible(true);
        searchItemFrame.setTitle("Search for an Item");
        searchItemFrame.setBounds(970, 150, 304, 246);
        searchItemFrame.getContentPane().setBackground(new Color(255, 255, 230));
        searchItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchItemFrame.getContentPane().setLayout(null);

        // created label item search

        JLabel SearchItemlbl = new JLabel("Which Item are you looking for ?");
        SearchItemlbl.setForeground(new Color(102, 51, 0));
        SearchItemlbl.setBounds(50, 11, 193, 31);
        searchItemFrame.getContentPane().add(SearchItemlbl);

        // created label enter item name

        JLabel itemNamelbl = new JLabel("Please enter the item name :");
        itemNamelbl.setFont(new Font("Dialog", Font.PLAIN, 11));
        itemNamelbl.setBounds(65, 68, 282, 14);

        searchItemFrame.getContentPane().add(itemNamelbl);

        // creates text input so that you can type in the name of the item

        textItem = new JTextField();
        textItem.setBounds(81, 93, 104, 20);
        searchItemFrame.getContentPane().add(textItem);
        textItem.setColumns(10);

        // Creates a label if entered incorrectly, initially empty

        JLabel WrongItemlbl33 = new JLabel(" ");
        WrongItemlbl33.setForeground(Color.RED);
        WrongItemlbl33.setBounds(70, 186, 240, 14);
        searchItemFrame.getContentPane().add(WrongItemlbl33);

        // creates button search + function to search for an Item

        JButton searchInsidebtn = new JButton("Search");
        searchInsidebtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String itemName = "";

            itemName = textItem.getText();

            try {
              if (checkCartSearch(itemName)) {
                updateCustomerCartTable(cart.searchByName(itemName));
                searchItemFrame.setVisible(false);
              }
            } catch (InvalidCartNameException ex) {
              System.out.println(ex.getMessage());
              WrongItemlbl33.setText("Invalid Name!");
              textItem.setText(null);
              logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Incorrect entry while searching in the shopping cart!", false));
            }

          }
        });
        searchInsidebtn.setBounds(81, 143, 104, 32);
        searchInsidebtn.setBackground(new Color(255, 204, 153));
        searchInsidebtn.setForeground(new Color(102, 51, 0));
        searchItemFrame.getContentPane().add(searchInsidebtn);

      }
    });

    SearchItemBtn.setBounds(200, 150, 141, 23);
    SearchItemBtn.setBackground(new Color(255, 204, 153));
    SearchItemBtn.setForeground(new Color(102, 51, 0));
    Cart.add(SearchItemBtn);

    // creates button "show item"

    JButton showItemsBtn = new JButton("Show items");
    showItemsBtn.addActionListener(new ActionListener() {

      // funktion zum anzeigen der Artikeln, nachdem man ein artikel gesucht hat ( sinnig)

      public void actionPerformed(ActionEvent e) {
        updateCustomerCartTable(cart.getCart());
      }
    });
    showItemsBtn.setBounds(200, 200, 141, 23);
    showItemsBtn.setBackground(new Color(255, 204, 153));
    showItemsBtn.setForeground(new Color(102, 51, 0));
    Cart.add(showItemsBtn);

    // created button "log out" + function log out

    JButton logoutbtn = new JButton("Log out");
    logoutbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getMenu();
        CustomerMenuFrame.setVisible(false);
        logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "has logged out", false));
      }
    });
    logoutbtn.setBounds(1160, 920, 125, 40);
    logoutbtn.setBackground(new Color(255, 128, 128));
    logoutbtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
    CustomerMenuFrame.getContentPane().add(logoutbtn);

    // Creater

    JLabel createrlbl2 = new JLabel("E-Shop creater : Diyar Aydin, Marc Dristram, Milan Klaussener");
    createrlbl2.setBounds(40, 510, 442, 14);
    CustomerMenuFrame.getContentPane().add(createrlbl2);

  }

  /**
   * used by : getMenu
   * Description: GUI window in which you can choose whether you are an employee or a customer.
   */
  public void login() {

    // Create the e shop window

    loginFrame = new JFrame();
    loginFrame.setTitle("E-Shop");
    loginFrame.setBounds(800, 200, 500, 500);
    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loginFrame.getContentPane().setBackground(new Color(255, 255, 204));
    loginFrame.getContentPane().setLayout(null);
    loginFrame.setVisible(true);

    // Label customer or employee

    JLabel selectTyplbl = new JLabel("Please select your account typ");
    selectTyplbl.setBounds(121, 50, 241, 250);
    selectTyplbl.setFont(new Font("Serif", Font.PLAIN, 20));
    selectTyplbl.setForeground(new Color(102, 51, 0));
    loginFrame.getContentPane().add(selectTyplbl);

    // button employee + function to the employee window

    JButton typEmployeebtn = new JButton("Employee");
    typEmployeebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        loginFrame.setVisible(false);
        shopLoginEmployee();

      }
    });

    typEmployeebtn.setBounds(280, 360, 105, 50);
    typEmployeebtn.setFont(new Font("Sans", Font.PLAIN, 17));

    typEmployeebtn.setForeground(new Color(102, 51, 0));
    typEmployeebtn.setBackground(new Color(255, 204, 153));
    loginFrame.getContentPane().add(typEmployeebtn);

    // button customer + function to the customer window

    JButton typCustomerbtn = new JButton("Customer");
    typCustomerbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        loginFrame.setVisible(false);
        shopLoginCustomer();

      }
    });

    typCustomerbtn.setBounds(98, 360, 105, 50);
    typCustomerbtn.setForeground(new Color(102, 51, 0));
    typCustomerbtn.setBackground(new Color(255, 204, 153));
    typCustomerbtn.setFont(new Font("Sans", Font.PLAIN, 17));
    loginFrame.getContentPane().add(typCustomerbtn);
  }

  /**
   * used by: shopLogin
   * Description: Generates the GUI in which the user name and password are entered by the employee.
   */
  public void shopLoginEmployee() {

    // creates new windows for employees

    shopLoginEmployeeFrame = new JFrame();
    shopLoginEmployeeFrame.setTitle("Employee Window");
    shopLoginEmployeeFrame.setBounds(880, 280, 420, 320);
    shopLoginEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    shopLoginEmployeeFrame.getContentPane().setBackground(new Color(255, 255, 204));
    shopLoginEmployeeFrame.getContentPane().setLayout(null);
    shopLoginEmployeeFrame.setVisible(true);

    // creates username text + input text

    JLabel Usernamelbl = new JLabel("Username");
    Usernamelbl.setBounds(170, 35, 103, 14);
    Usernamelbl.setForeground(new Color(102, 51, 0));
    shopLoginEmployeeFrame.getContentPane().add(Usernamelbl);

    textID = new JTextField();
    textID.setBounds(153, 60, 96, 25);
    shopLoginEmployeeFrame.getContentPane().add(textID);
    textID.setColumns(10);

    // creates password text + input text

    JLabel Passwordlbl = new JLabel("Password");
    Passwordlbl.setBounds(170, 120, 103, 14);
    Passwordlbl.setForeground(new Color(102, 51, 0));
    shopLoginEmployeeFrame.getContentPane().add(Passwordlbl);

    Passwordtext = new JPasswordField();
    Passwordtext.setColumns(10);
    Passwordtext.setBounds(153, 140, 96, 25);
    shopLoginEmployeeFrame.getContentPane().add(Passwordtext);

    wrongIdorPw = new JLabel("    ");
    wrongIdorPw.setForeground(Color.RED);
    wrongIdorPw.setBounds(75, 185, 240, 14);
    shopLoginEmployeeFrame.getContentPane().add(wrongIdorPw);

    // creates button + function to check the login data

    JButton loginbtn = new JButton("Login");
    loginbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String username = "";
        String password = "";
        boolean login = false;
        List < Employee > list;
        list = employeeManagement.getAllEmployees();

        username = textID.getText();
        System.out.print(username);

        password = Passwordtext.getText();
        System.out.print(password);

        LoginEmployee a = new LoginEmployee();
        try {
          a.login(employeeManagement.getAllEmployees(), username, password);
          currentEmployee = a.getNumber();

          System.out.print(currentEmployee);
          employeeMenu();
          shopLoginEmployeeFrame.setVisible(false);
          logmanager.add(new Changelog(employeeManagement.searchByNumber(a.getNumber()).get(0), "has logged in ", true));
          //logmanager.outputLog();

        } catch (IncorrectLoginDataException ex) {
          wrongIdorPw.setText("Username or Password are uncorrect!");
          logmanager.add(new Changelog(system, "Login failed!", true));
        }
      }
    });

    loginbtn.setBounds(150, 210, 103, 40);
    loginbtn.setForeground(new Color(102, 51, 0));
    loginbtn.setBackground(new Color(255, 204, 153));
    shopLoginEmployeeFrame.getContentPane().add(loginbtn);
  }

  /**
   * used by: shop registration
   * Description: generates the GUI in which the user name and password for a customer can be entered.
   */
  public void shopLoginCustomer() {

    // creates a new window for the customer

    shopLoginCustomerFrame = new JFrame();
    shopLoginCustomerFrame.setTitle("Customer Window");
    shopLoginCustomerFrame.setBounds(880, 280, 420, 320);
    shopLoginCustomerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    shopLoginCustomerFrame.getContentPane().setBackground(new Color(255, 255, 204));
    shopLoginCustomerFrame.getContentPane().setLayout(null);
    shopLoginCustomerFrame.setVisible(true);

    // creates username text + input text

    JLabel Usernameclbl = new JLabel("Username");
    Usernameclbl.setBounds(170, 35, 103, 14);
    Usernameclbl.setForeground(new Color(102, 51, 0));
    shopLoginCustomerFrame.getContentPane().add(Usernameclbl);

    textID = new JTextField();
    textID.setBounds(153, 60, 96, 25);
    shopLoginCustomerFrame.getContentPane().add(textID);
    textID.setColumns(10);

    // creates password text + input text

    JLabel Passwordlbl = new JLabel("Password");
    Passwordlbl.setBounds(170, 120, 103, 14);
    Passwordlbl.setForeground(new Color(102, 51, 0));
    shopLoginCustomerFrame.getContentPane().add(Passwordlbl);

    Passwordtext = new JPasswordField();
    Passwordtext.setColumns(10);
    Passwordtext.setBounds(153, 140, 96, 25);
    shopLoginCustomerFrame.getContentPane().add(Passwordtext);

    // creates a text if you entered wrong id and pw

    wrongIdorPw = new JLabel("    ");
    wrongIdorPw.setForeground(Color.RED);
    wrongIdorPw.setBounds(75, 185, 240, 14);
    shopLoginCustomerFrame.getContentPane().add(wrongIdorPw);

    // creates button + function to check the login data

    JButton loginbtn = new JButton("Login");
    loginbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String username = "";
        String password = "";
        boolean login = false;
        List < Customer > list;
        list = customerManagement.getAllCustomers();

        username = textID.getText();
        System.out.print(username);

        password = Passwordtext.getText();
        System.out.print(password);

        LoginCustomer a = new LoginCustomer();
        try {
          a.login(customerManagement.getAllCustomers(), username, password);
          currentCustomer = a.getNumber();
          System.out.println("|" + currentCustomer + "|");
          customerMenu();
          shopLoginCustomerFrame.setVisible(false);
          logmanager.add(new Changelog(customerManagement.searchByNumber(a.getNumber()).get(0), "has logged in.", false));

        } catch (IncorrectLoginDataException ex) {
          wrongIdorPw.setText("Username or Password are uncorrect!");
          logmanager.add(new Changelog(system, "Login failed!", true));
        }

      }
    });

    loginbtn.setBounds(150, 210, 103, 40);
    loginbtn.setForeground(new Color(102, 51, 0));
    loginbtn.setBackground(new Color(255, 204, 153));
    shopLoginCustomerFrame.getContentPane().add(loginbtn);

  }

  /**
   * Used by: EmployeeMenu & getMenu
   * Beschreibung: Description: The GUI for customer registration is generated.
   * @param b indicates whether you are already logged in or whether the program is still before login
   */
  public void shopCustomerRegistration(boolean b) {

    // creates the registration window

    shopCustomerRegistrationFrame = new JFrame();
    shopCustomerRegistrationFrame.setTitle("Register a Customer");
    shopCustomerRegistrationFrame.setBounds(500, 300, 400, 450);
    shopCustomerRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    shopCustomerRegistrationFrame.getContentPane().setLayout(null);
    shopCustomerRegistrationFrame.getContentPane().setBackground(new Color(255, 255, 230));
    shopCustomerRegistrationFrame.setVisible(true);

    // creates first name text + input text

    JLabel firstnamelbl = new JLabel("Firstname:");
    firstnamelbl.setForeground(new Color(102, 51, 0));
    firstnamelbl.setBounds(24, 38, 115, 14);
    shopCustomerRegistrationFrame.getContentPane().add(firstnamelbl);

    firstnametext = new JTextField();
    firstnametext.setColumns(10);
    firstnametext.setBounds(24, 63, 115, 20);
    shopCustomerRegistrationFrame.getContentPane().add(firstnametext);

    // creates last name text + input text

    JLabel lastnamelbl = new JLabel("Lastname:");
    lastnamelbl.setForeground(new Color(102, 51, 0));
    lastnamelbl.setBounds(24, 94, 115, 14);
    shopCustomerRegistrationFrame.getContentPane().add(lastnamelbl);

    lastnametext = new JTextField();
    lastnametext.setColumns(10);
    lastnametext.setBounds(24, 119, 115, 20);
    shopCustomerRegistrationFrame.getContentPane().add(lastnametext);

    // creates adress text + input text

    JLabel Adresslbl = new JLabel("Adress:");
    Adresslbl.setForeground(new Color(102, 51, 0));
    Adresslbl.setBounds(24, 194, 221, 14);
    shopCustomerRegistrationFrame.getContentPane().add(Adresslbl);

    Adresstext = new JTextField();
    Adresstext.setColumns(10);
    Adresstext.setBounds(24, 219, 270, 20);
    shopCustomerRegistrationFrame.getContentPane().add(Adresstext);

    // creates username text + input text

    JLabel usernamelbl = new JLabel("Username:");
    usernamelbl.setForeground(new Color(102, 51, 0));
    usernamelbl.setBounds(171, 38, 115, 14);
    shopCustomerRegistrationFrame.getContentPane().add(usernamelbl);

    usernametext = new JTextField();
    usernametext.setColumns(10);
    usernametext.setBounds(171, 63, 115, 20);
    shopCustomerRegistrationFrame.getContentPane().add(usernametext);

    // creates password text + input text

    JLabel passwordlbl = new JLabel("Password: ");
    passwordlbl.setForeground(new Color(102, 51, 0));
    passwordlbl.setBounds(171, 94, 221, 14);
    shopCustomerRegistrationFrame.getContentPane().add(passwordlbl);

    Passwordtext = new JPasswordField();
    Passwordtext.setColumns(10);
    Passwordtext.setBounds(171, 119, 115, 20);
    shopCustomerRegistrationFrame.getContentPane().add(Passwordtext);

    // creates customer number text + input text

    JLabel CustomerNrlbl = new JLabel("CustomerNr:");
    CustomerNrlbl.setForeground(new Color(102, 51, 0));
    CustomerNrlbl.setBounds(24, 267, 121, 14);
    shopCustomerRegistrationFrame.getContentPane().add(CustomerNrlbl);

    CustomerNrText = new JTextField("" + newNumberCustomer(customerManagement.getAllCustomers()));
    CustomerNrText.setEditable(false);
    CustomerNrText.setColumns(10);
    CustomerNrText.setBounds(24, 290, 43, 20);
    shopCustomerRegistrationFrame.getContentPane().add(CustomerNrText);

    // if the customer number already exists!

    InvalidCustomerNr = new JLabel("      ");
    InvalidCustomerNr.setForeground(Color.RED);
    InvalidCustomerNr.setBounds(215, 279, 180, 14);
    shopCustomerRegistrationFrame.getContentPane().add(InvalidCustomerNr);

    // Creates button

    JButton Registerbtn = new JButton("Register!");
    Registerbtn.setForeground(new Color(102, 51, 0));
    Registerbtn.setBackground(new Color(255, 204, 153));
    Registerbtn.setBounds(125, 360, 115, 23);
    shopCustomerRegistrationFrame.getContentPane().add(Registerbtn);

    //  Function to save the data when clicking the button
    // or registration of a new customer 

    Registerbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String username = "";
        String password = "";
        String firstname = "";
        String lastname = "";
        String adress = "";
        String customerNumber = "";
        int customerNr;
        List < Customer > list;
        list = customerManagement.getAllCustomers();

        // to read the text input

        firstname = firstnametext.getText();
        System.out.print(firstname);

        lastname = lastnametext.getText();
        System.out.print(lastname);

        adress = Adresstext.getText();
        System.out.print(adress);

        username = usernametext.getText();
        System.out.print(username);

        password = Passwordtext.getText();
        System.out.print(password);

        customerNumber = CustomerNrText.getText();
        System.out.print(customerNumber);

        customerNr = Integer.parseInt(customerNumber);

        if (!usernametext.getText().isEmpty()) {
          customerManagement.addACustomer(username, password, firstname, lastname, adress, customerNr);
          try {
            customerManagement.writeCustomers();
          } catch (IOException e1) {

            e1.printStackTrace();
          }
          shopCustomerRegistrationFrame.setVisible(false);
          System.out.println("Customer has been created.");
          if (!b) {
            shopLoginCustomer();
            logmanager.add(new Changelog(system, "the customer: " + username + " | " + customerNr + " has been created.", true));
          } else {
            logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "the customer: " + username + " | " + customerNr + " has been created.", true));
          }

        } else {
          InvalidCustomerNr.setText("Please fill all fields!");
        }
      }

    });

  }

  /**
   * used by: EmployeeMenu
   * description: creates the registration window for a new employee
   * @param b indicates whether the function is called from the employee menu or not
   */
  public void shopEmployeeRegistration(boolean b) {

    // creates the registration window

    shopEmployeeRegistrationFrame = new JFrame();
    shopEmployeeRegistrationFrame.setTitle("Register new Employee");

    shopEmployeeRegistrationFrame.setBounds(500, 300, 400, 450);
    shopEmployeeRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    shopEmployeeRegistrationFrame.getContentPane().setLayout(null);
    shopEmployeeRegistrationFrame.getContentPane().setBackground(new Color(255, 255, 230));
    shopEmployeeRegistrationFrame.setVisible(true);

    // creates first name text + input text

    JLabel firstnamelbl = new JLabel("Firstname:");
    firstnamelbl.setBounds(24, 38, 115, 14);
    firstnamelbl.setForeground(new Color(102, 51, 0));
    shopEmployeeRegistrationFrame.getContentPane().add(firstnamelbl);

    firstnametext = new JTextField();
    firstnametext.setColumns(10);
    firstnametext.setBounds(24, 63, 115, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(firstnametext);

    // creates last name text + input text

    JLabel lastnamelbl = new JLabel("Lastname:");
    lastnamelbl.setBounds(24, 94, 115, 14);
    lastnamelbl.setForeground(new Color(102, 51, 0));
    shopEmployeeRegistrationFrame.getContentPane().add(lastnamelbl);

    lastnametext = new JTextField();
    lastnametext.setColumns(10);
    lastnametext.setBounds(24, 119, 115, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(lastnametext);

    // creates Adress text + input text

    JLabel Adresslbl = new JLabel("E-mail:");
    Adresslbl.setBounds(24, 194, 115, 14);
    Adresslbl.setForeground(new Color(102, 51, 0));
    shopEmployeeRegistrationFrame.getContentPane().add(Adresslbl);

    Adresstext = new JTextField();
    Adresstext.setColumns(10);
    Adresstext.setBounds(24, 219, 160, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(Adresstext);

    // creates username text + input text

    JLabel usernamelbl = new JLabel("Username:");
    usernamelbl.setBounds(171, 38, 115, 20);
    usernamelbl.setForeground(new Color(102, 51, 0));
    shopEmployeeRegistrationFrame.getContentPane().add(usernamelbl);

    usernametext = new JTextField();
    usernametext.setColumns(10);
    usernametext.setBounds(171, 63, 115, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(usernametext);

    // creates password text + input text

    JLabel passwordlbl = new JLabel("Password: ");
    passwordlbl.setBounds(171, 94, 221, 14);
    passwordlbl.setForeground(new Color(102, 51, 0));
    shopEmployeeRegistrationFrame.getContentPane().add(passwordlbl);

    Passwordtext = new JPasswordField();
    Passwordtext.setColumns(10);
    Passwordtext.setBounds(171, 119, 115, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(Passwordtext);

    // creates employee number text + input text

    JLabel employeeNrlbl = new JLabel("Employee Nr:");
    employeeNrlbl.setForeground(new Color(102, 51, 0));
    employeeNrlbl.setBounds(24, 267, 121, 14);
    shopEmployeeRegistrationFrame.getContentPane().add(employeeNrlbl);

    employeeNrText = new JTextField("" + newNumberMitarbeiter(employeeManagement.getAllEmployees()));
    employeeNrText.setEditable(false);
    employeeNrText.setColumns(10);
    employeeNrText.setBounds(24, 290, 43, 20);
    shopEmployeeRegistrationFrame.getContentPane().add(employeeNrText);

    //  errormessage if Employe number already exists 

    InvalidEmployeeNr = new JLabel("      ");
    InvalidEmployeeNr.setForeground(Color.RED);
    InvalidEmployeeNr.setBounds(215, 279, 180, 14);
    shopEmployeeRegistrationFrame.getContentPane().add(InvalidEmployeeNr);

    // Creates Register button

    JButton Registrieren = new JButton("Register");
    Registrieren.setForeground(new Color(102, 51, 0));
    Registrieren.setBackground(new Color(255, 204, 153));
    Registrieren.setBounds(125, 360, 115, 23);
    shopEmployeeRegistrationFrame.getContentPane().add(Registrieren);

    /* Function to save the data when clicking the button
     * or registration of a new customer */

    Registrieren.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String username = "";
        String password = "";
        String firstname = "";
        String lastname = "";
        String email = "";

        String employeeNumber = "";
        int employeeNr;

        firstname = firstnametext.getText();
        System.out.print(firstname);

        lastname = lastnametext.getText();
        System.out.print(lastname);

        email = Adresstext.getText();
        System.out.print(email);

        username = usernametext.getText();
        System.out.print(username);

        password = Passwordtext.getText();
        System.out.print(password);

        employeeNumber = employeeNrText.getText();
        System.out.print(employeeNumber);

        employeeNr = Integer.parseInt(employeeNumber);

        if (!usernametext.getText().isEmpty()) {
          employeeManagement.addAnEmployee(username, password, firstname, lastname, email, employeeNr);
          try {
            employeeManagement.writeEmployees();
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Employee : " + username + " | " + employeeNr + " has been registered.", true));
          shopEmployeeRegistrationFrame.setVisible(false);
          System.out.println("A new employee has been created.");
          if (!b) {
            shopLoginCustomer();

          }

        } else {
          InvalidEmployeeNr.setText("Please fill all fields!");
        }
      }

    });

  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  //------------------------------------------------------------------ Shop Methods-------------------------------------------------------------

  // automatic number assignment

  public int newNumberCustomer(List < Customer > list) {
    // outputs the highest customer number, it compares all customers in the list with getCustomerNr
    Customer maxByNumber = list.stream().max(Comparator.comparing(Customer::getCustomerNr)).orElseThrow(NoSuchElementException::new);
    // the highest customer number 
    int newNumber = maxByNumber.getCustomerNr() + 1;
    // returns the new number
    return newNumber;
  }

  public int newNumberMitarbeiter(List < Employee > liste) {
    Employee maxByNumber = liste.stream().max(Comparator.comparing(Employee::getEmployeeNr)).orElseThrow(NoSuchElementException::new);
    int newNumber = maxByNumber.getEmployeeNr() + 1;
    return newNumber;
  }

  //------------------------------------Sorting methods------------------------------------------------

  /**
   * used by: employee menu & customer menu
   * Description: Sorts the item list by name with the help of a comparator
   * 
   * @param list is the list you want to be sorted
   * @return is the sorting list
   */
  private List < Item > sortNameItemList(List < Item > list) {
    if (list.isEmpty()) {
      System.out.println("List is empty.");
    } else {

      Collections.sort(list, new Comparator < Item > () {
        @Override
        public int compare(Item u1, Item u2) {
          return u1.getName().compareTo(u2.getName());
        }
      });

    }
    return list;
  }

  /**
   * used by: employee menu & customer menu
   * Description: Sorts the item list by number with the help of a comparator
   * @param list is the item list
   * @return returns the sort list
   */
  private List < Item > sortNumberItemList(List < Item > list) {
    if (list.isEmpty()) {
      System.out.println("List is empty .");
    } else {

      Collections.sort(list, new Comparator < Item > () {
        @Override
        public int compare(Item u1, Item u2) {

          int x = Integer.compare(u1.getNumber(), u2.getNumber());

          return x;
        }
      });

    }
    return list;
  }

  private List < Changelog > sortDateChangelogliste(List < Changelog > liste) {
    if (liste.isEmpty()) {
      System.out.println("List is empty .");
    } else {

      Collections.sort(liste, new Comparator < Changelog > () {
        @Override
        public int compare(Changelog u1, Changelog u2) {
          return u1.getTime().compareTo(u2.getTime());
        }
      });

    }
    return liste;
  }

  //---------------------------------------------Table Methods---------------------------------------------

  /**
   * Used by: Employee Menu
   * Description: fills the table in the employee window for the articles.
   */
  // Fill and update tables

  public void updateTabelle(List < Item > l) {

    DefaultTableModel filltheTable = (DefaultTableModel) itemTable.getModel(); // specifies which table should be filled.
    filltheTable.setRowCount(0); // clears the table
    Object rowData[] = new Object[6]; //indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) { // goes through the list and saves the data of the columns

      rowData[0] = l.get(i).getName();
      rowData[1] = l.get(i).getNumber();
      rowData[2] = l.get(i).getPrice();
      rowData[3] = l.get(i).getStock();
      rowData[4] = l.get(i).getminimumStock();
      rowData[5] = l.get(i).getBulk();

      if (l.get(i).getStock() <= l.get(i).getminimumStock()) {
        //fill the table
      }
      filltheTable.addRow(rowData); //fills lines with  all colums
    }
  }

  /**
   * Used by: Employee Menu
   * Description: fills the table for the employees in user management. Works in the same way as update table
   */

  // fill and update tables

  public void updateUserEmployeeTable(List < Employee > l) {
    DefaultTableModel filltheTable = (DefaultTableModel) tableEmployee.getModel(); // specifies which table should be filled.
    filltheTable.setRowCount(0); // clears the table
    Object rowData[] = new Object[7]; // indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) { // goes through the list and saves the data of the columns

      rowData[0] = l.get(i).getEmployeeNr();
      rowData[1] = l.get(i).getUsername();
      rowData[2] = l.get(i).getFirstname();
      rowData[3] = l.get(i).getLastname();
      rowData[4] = l.get(i).getEmail();
      filltheTable.addRow(rowData); // fills  lines with columns
    }
  }

  /**
   * Used by: Employee Menu
   * Description: Fills the table for the customers in the user management. Works just like the update table.
   * 
   */

  // fill and update tables
  public void updateUserCustomerTable(List < Customer > l) {
    DefaultTableModel filltheTable = (DefaultTableModel) tableCustomer.getModel();
    filltheTable.setRowCount(0); // clears the table
    Object rowData[] = new Object[7]; // indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) { // goes through the list and saves the data of the columns

      rowData[0] = l.get(i).getCustomerNr();
      rowData[1] = l.get(i).getUsername();
      rowData[2] = l.get(i).getFirstname();
      rowData[3] = l.get(i).getLastname();
      rowData[4] = l.get(i).getAdress();

      filltheTable.addRow(rowData); //fills  lines with columns
    }
  }

  /**
   * Used by: CustomerMenu
   * Description: fills the table for the item list in the customer menu, works in the same way as the update table
   * 
   */

  // fill and update tables

  public void updateItemTable(List < Item > l) {
    DefaultTableModel filltheTable = (DefaultTableModel) itemTable.getModel();
    filltheTable.setRowCount(0); // clears the table
    Object rowData[] = new Object[5]; // indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) { // goes through the list and saves the data of the columns

      rowData[0] = l.get(i).getName();
      rowData[1] = l.get(i).getNumber();
      rowData[2] = l.get(i).getPrice();
      rowData[3] = l.get(i).getStock();
      rowData[4] = l.get(i).getBulk();
      if (l.get(i).getStock() <= l.get(i).getminimumStock()) {
        //TabelleBefüllen
      }
      filltheTable.addRow(rowData); //fills  lines with columns
    }
  }

  /**
   * Used by: CustomerMenu
   * Description: Fills the table in the customer's shopping cart.
   * 
   */

  public void updateCustomerCartTable(List < cartItem > l) {
    double totalprice;
    double test = 0;
    String testString = "";

    DefaultTableModel filltheTable = (DefaultTableModel) cartItemTable.getModel();
    filltheTable.setRowCount(0); // clears the table
    Object rowData[] = new Object[5]; // indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) { // goes through the list and saves the data of the columns

      rowData[0] = l.get(i).getItem().getName();
      rowData[1] = l.get(i).getItem().getNumber();
      rowData[2] = l.get(i).getAmount();
      rowData[3] = l.get(i).getItem().getPrice();
      rowData[4] = l.get(i).getItem().getPrice() * l.get(i).getAmount();

      totalprice = l.get(i).getItem().getPrice() * l.get(i).getAmount();
      test = totalprice + test; // to calculate the total price in the shopping cart
      testString = String.valueOf(test);
      filltheTable.addRow(rowData); // fills a line with all columns
      totalPriceNumberlbl.setText("  " + testString + " "); // set the total price in the GUI
    }
  }

  /**
   * Used by: Customer MenuBill
   * Beschreibung:fill the List in Bill
   * 
   */

  public void updateCustomerBillTable(List < cartItem > l) {
    double totalprice;
    double test = 0;
    String testString = "";

    DefaultTableModel filltheTable = (DefaultTableModel) table.getModel();
    filltheTable.setRowCount(0);
    Object rowData[] = new Object[5];
    for (int i = 0; i < l.size(); i++) {
      rowData[0] = l.get(i).getItem().getName();
      rowData[1] = l.get(i).getItem().getNumber();
      rowData[2] = l.get(i).getAmount();
      rowData[3] = l.get(i).getItem().getPrice();
      rowData[4] = l.get(i).getItem().getPrice() * l.get(i).getAmount();

      totalprice = l.get(i).getItem().getPrice() * l.get(i).getAmount();
      test = totalprice + test;
      testString = String.valueOf(test);
      filltheTable.addRow(rowData);
      Pricelbl.setText(" " + testString + " ");
      System.out.println(testString);
    }
  }

  public void updateChangelogTable(List < Changelog > l) {
    DefaultTableModel filltheTable = (DefaultTableModel) tableChangelog.getModel(); //indicates which table is to be filled.
    filltheTable.setRowCount(0); //empties the current table
    Object rowData[] = new Object[5]; //indicates how many columns the table has
    for (int i = 0; i < l.size(); i++) //goes through the list and saves the data of the columns
    {
      rowData[0] = l.get(i).getTime();
      if (l.get(i).getTyp()) { //Employee
        rowData[1] = l.get(i).getEmployee().getEmployeeNr();
        rowData[2] = l.get(i).getEmployee().getFirstname();
        rowData[3] = l.get(i).getEmployee().getLastname();
      } else {
        rowData[1] = l.get(i).getCustomer().getCustomerNr();
        rowData[2] = l.get(i).getCustomer().getFirstname();
        rowData[3] = l.get(i).getCustomer().getLastname();
      }
      rowData[4] = l.get(i).getMessage();

      filltheTable.addRow(rowData); // fills a line with all columns
      System.out.println(l.get(i).getMessage());
    }
  }

  //--------------------------------------------------Check Methods---------------------------------------------------------

  /**
   * @return true: Item for the number has been found. false: no item has been found and Exception is thrown
   * @throws InvalidArtikelNummerException
   */
  private boolean checkNumber(int iNum) throws InvalidItemNumberException {
    boolean x = false;
    for (Item i: storage.getAllItems()) { // goes through the item list
      System.out.println(i.getNumber() + " | " + iNum);
      if (i.getNumber() == iNum) { // Checks whether the item number and Inr match
        x = true; // if yes, true
        break;
      } else {
        x = false; //if no false
      }
    }
    if (!x) {
      throw new InvalidItemNumberException(); //throws the exception
    }
    return x;
  }

  /**
   * 
   * @return true: A name has been found. false: no item with the name has been found.
   * @throws InvalidArtikelNameException
   */
  private boolean checkName(String iName) throws InvalidItemNameException {
    boolean x = false;
    for (Item i: storage.getAllItems()) {
      if (i.getName().equals(iName)) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidItemNameException();
    }
    return x;
  }

  /**
   * 
   * @return true: A name has been found. false: no item with the name has been found.
   * @throws InvalidCustomerNameException 
   *
   */
  private boolean checkNameCus(String cName) throws InvalidCustomerNameException {
    boolean x = false;
    for (Customer c: customerManagement.getAllCustomers()) {
      if (c.getFirstname().equals(cName)) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidCustomerNameException();
    }
    return x;
  }

  /**
   * 
   * @return true: A name has been found. false: no item with the name has been found.
   * 
   * @throws InvalidEmployeeNameException 
   *
   */
  private boolean checkNameEmp(String eName) throws InvalidEmployeeNameException {
    boolean x = false;
    for (Employee e: employeeManagement.getAllEmployees()) {
      System.out.println(e.getFirstname() + " | " + eName);
      if (e.getFirstname().equals(eName)) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidEmployeeNameException();
    }
    return x;
  }

  /**
   * 
   * @return true: Item number and item stock match, inventory is increased. false: exception is thrown
   * @throws InvalidArtikelNummerException
   */
  private boolean checkNumberStock(int iNum, int iSto) throws InvalidItemNumberException {
    boolean x = false;
    for (Item i: storage.getAllItems()) {
      if (i.getNumber() == iNum) {
        i.setStock(iSto);
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidItemNumberException();
    }
    return x;
  }

  /**
   * 
   * @return true: Employee number and number match. False: exception is thrown
   * @throws InvalidMitarbeiterNummerException
   */
  private boolean checkNumberEmployee(int eNum) throws InvalidEmployeeNumberException {
    boolean x = false;
    for (Employee e: employeeManagement.getAllEmployees()) {
      if (e.getEmployeeNr() == eNum) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidEmployeeNumberException();
    }
    return x;
  }

  /**
   * 
   * @return true: Customer number and number match. False: exception is thrown
   * @throws InvalidKundenNummerException
   */
  private boolean checkNumberCustomer(int cNum) throws InvalidCustomerNumberException {
    boolean x = false;
    for (Customer c: customerManagement.getAllCustomers()) {
      if (c.getCustomerNr() == cNum) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidCustomerNumberException();
    }
    return x;
  }

  /**
   * 
   * @return true: Item name and name match. False: exception is thrown
   * @throws InvalidArtikelNameException
   */
  private boolean checkNameItem(String iName) throws InvalidItemNameException {
    boolean x = false;
    for (Item i: storage.getAllItems()) {
      System.out.println(i.getName() + " | " + iName);
      if (i.getName().equals(iName)) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidItemNameException();
    }
    return x;
  }

  /**
   * 
   * @return true: Item number and number & number and stock match. false: exception is thrown
   * @throws InvalidWarenkorbException
   */
  private boolean checkCart(int iNum, int iAmo) throws InvalidCartException {
    boolean x = false;
    for (Item i: storage.getAllItems()) {
      System.out.println(i.getNumber() == iAmo && iAmo <= i.getStock());
      System.out.println(!(i.getNumber() == iNum));
      System.out.println(i.getStock() <= iAmo);
      if (i.getNumber() == iNum && iAmo <= i.getStock() && iAmo >= i.getBulk()) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidCartException();
    }
    return x;
  }

  /**
   * 
   * @return true: Item number and amount match the values. False: exception is thrown.
   * @throws InvalidWarenkorbException
   */
  private boolean checkCartChange(int tiNum, int tiAmo) throws InvalidCartException {
    boolean x = false;
    for (cartItem ti: cart.getCart()) {
      if (ti.getItem().getNumber() == tiNum) {
        x = true;
        break;
      } else {
        x = false;
      }

    }
    if (!x) {
      throw new InvalidCartException();
    }
    return x;
  }

  /**
   * 
   * @return true: Item number and amount match the values. False: exception is thrown.
   * @throws InvalidWarenkorbException
   */
  private boolean checkCartDelete(int tiNum, int tiAmo) throws InvalidCartException {
    boolean x = false;
    for (cartItem ti: cart.getCart()) {
      if (ti.getItem().getNumber() == tiNum && ti.getAmount() >= tiAmo) {
        x = true;
        break;
      } else {
        x = false;
      }

    }
    if (!x) {
      throw new InvalidCartException();
    }
    return x;
  }

  /**
   *  
   * @return true: an Item has been  found in the search. False: exception is thrown.
   * @throws InvalidWarenkorbNameException
   */
  private boolean checkCartSearch(String aName) throws InvalidCartNameException {
    boolean x = false;
    for (cartItem ti: cart.getCart()) {
      if (ti.getItem().getName().equals(aName)) {
        x = true;
        break;
      } else {
        x = false;
      }
    }
    if (!x) {
      throw new InvalidCartNameException();
    }
    return x;
  }

}