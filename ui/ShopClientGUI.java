package ui;

//package CUI;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

import java.awt.BorderLayout;
import java.awt.Color;
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

import valueObjects.*;
import Exceptions.*;
import Funktionen.LoginCustomer;
import Funktionen.LoginEmployee;
import Domain.*;


/**
 * 
 *
 *
 */
public class ShopClientGUI extends JFrame{
	
	private static Employee system;
	private int currentCustomer;
	private int currentEmployee;
	private static Storage storage;
	private static EmployeeManagement employeeManagement;
	private static CustomerManagement customerManagement;
	private static ChangelogManager logmanager;
	private static Cart cart;
	static List<String> log = new ArrayList<String>();
	private List<Item> itemList;
	private List<Customer> customerList;
	private List<Employee> employeeList;
	private JFrame gibMenueAus;
	private JFrame shopAnmeldung;
	private JFrame shopKundeRegistrierung;
	private JFrame shopMitarbeiterRegistrierung;
	private JFrame shopAnmeldungMitarbeiter;
	private JFrame shopAnmeldungKunde;
	private JFrame mitarbeiterMenue;
	private JFrame ArtikelHinzufuegenMenue;
	private JFrame kundenMenue;
	private JFrame ArtikelLoeschenMenue;
	private JFrame artikelScreach;
	private JFrame nameScreach;
	private JTextField textVorname;
	private JTextField textName2;
	private JTextField textNachname;
	private JTextField textWohnort;
	private JTextField textStraße;
	private JTextField textplz;
	private JTextField textNr;
	private JTextField textBenutzername;
	private JTextField textID;
	private JTextField textArtikel;
	private JTextField textArtikel2;
	private JTextField textNummer;
	private JTextField textPreis;
	private JTextField textBestand;
	private JTextField textMindestbestand;
	private JTextField textArtikelNummer;
	private JTextField textArtikelname;
	private JTextField textAnzahl;
	private JTextField textArtikelNr;
	private JTextField textArtikelNr1;
	private JTextField textAnzahl1;
	private JTextField textMassengut;
	private JTextField textArtikel5;
	private JPasswordField textPasswort;
	private JTextField textKundeNr;
	private JTextField textMitarbeiterNr;
	private JTextField textKundenNummer;
	private JLabel FalscheKundenNr;
	private JLabel FalscheMitarbeiterNr;
	private JLabel FalscheIDundPw;
	private JLabel falscherArtikel;
	private JLabel falscheEingabe;
	private JTable tabelle;
	private JTable tabelle1;
	private JTable tabelle2;
	private JTable tabelle3;
	private JTable tabelle4;
	private JLabel gesamtPreisZahl;
	private JFrame kundLoeschen;
	private JFrame artikelScreach1;
	private JFrame mitaLoeschen;
	private JTextField textMitarbeiterNummer;
	private JFrame mitarbeiterScreach;
	private Object tabelleFeld;
	private JSpinner spinnerAnzahl;
	private JFrame Rechnung;
    private JTable table;
    private JLabel labelDatum;
    private JLabel labelBearbeiter;
    private JLabel labelName;
    private JLabel labelEmail;
    private JLabel labelEmailName;
    private JLabel labelKundeNr;
    private JLabel labelNr;
    private JScrollPane scollGesamtpreis;
    private JLabel labelGesamtpreis;
    private JLabel labelPreis;
    private JLabel labelDanke;
    private JLabel labelMfg;
    private JLabel labelKundenname;
    private JLabel labelAdresse;
    private JLabel labelAdresse1;

	
	/**
	 * 
	 * Verwendet von: Der Main Methode
	 * Methodenbeschriebung: Der Konstrukter der GUI Erzeugt die Manager und ihre schnittstellen. Außerdem wird der Changelog und der Warenkorb erzeugt.
	 * 
	 * @param dArtikel ist die Datei in der die Artikel stehen
	 * @param dMitarbeiter ist die Datei in der die Mitarbeiter stehen
	 * @param dKunden ist die Datei in der die Kunden stehen
	 * @param dLog ist die Datei in der der Log steht
	 */
	public ShopClientGUI(String dItem, String dEmployee, String dCustomer, String dLog) {
		
		try {
			storage = new Storage(dItem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//buero = new Buero(dMitarbeiter);
			employeeManagement = new EmployeeManagement(dEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//customerManagement = new customerManagement(dCustomer);
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
		
		
		employeeList = employeeManagement.getAllEmployee();
		
		customerList = customerManagement.getAllCustomer();
		
		itemList= storage.getAllItems();
		
		
		cart = new Cart(itemList);
	
		
		
	}
	



	/**
	 *  Beschreibung: Gibt das GUI aus, in dem man sich entweder anmelden oder Registrieren kann
	 *  Verwendung: Es ist das erste Menue, welches beim starten erscheint.
	 * 
	 */
	public void gibMenueAus() {
		
			// Fenster erstellen
		
			gibMenueAus = new JFrame();
			gibMenueAus.setTitle("E-Shop");
			gibMenueAus.setBounds(500, 300, 418, 269);
			gibMenueAus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gibMenueAus.getContentPane().setLayout(null);
			gibMenueAus.setVisible(true);
			
			//Label Wilkommen
			
			JLabel Willkommen = new JLabel("Herzlich Wilkommen auf unseren Online Shop!");
			Willkommen.setBounds(73, 37, 312, 14);
			gibMenueAus.getContentPane().add(Willkommen);
			
			//Label Anmeldung oder Registrieren
			
			JLabel AoderR = new JLabel("Anmelden oder Registrieren?");
			AoderR.setBounds(115, 100, 241, 14);
			gibMenueAus.getContentPane().add(AoderR);
			
			//button Mitarbeiter
			
			JButton Anmelden = new JButton("Anmelden");
			Anmelden.addActionListener(new ActionListener() {
				
			// funktion zum laden nächsten fenster "anmeldung" und schließt das alte fenster 
				
				public void actionPerformed(ActionEvent e) {
			
					gibMenueAus.setVisible(false);
					shopAnmeldung();
						
				}
			});
			Anmelden.setBounds(225, 165, 105, 23);
			gibMenueAus.getContentPane().add(Anmelden);
			
			
			//button Registrierung
			
			JButton Registrieren = new JButton("Registrieren");
			Registrieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
			// funktion zum schließen dess alten fensters und öffnet das registrierungsfenster 	
					gibMenueAus.setVisible(false);
					shopKundeRegistrierung(false);
					
						}
			});
			
			Registrieren.setBounds(73, 165, 105, 23);
			gibMenueAus.getContentPane().add(Registrieren);

	}
	 
	/**
	 * Verwendet von: MitarbeiterMenue
	 * Beschreibung: füllt die Tabelle in Mitarbeiter Fenster fuer die Artikel.
	 * @param l ist die Liste die welche in die Tabelle gefüllt werden soll.
	 */
	// tabellen befüllen und aktualisieren
	
	public void updateTabelle(List<Item> l) {
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle.getModel(); //gibt an welche Tabelle befüllt werden soll.
		TabelleBefüllen.setRowCount(0); 	//leert die aktuelle Tabelle
        Object rowData[] = new Object[6]; 	//gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++){ 	//geht die Liste durch und speichert die Daten der Spalten
        
        	rowData[0] = l.get(i).getName();
            rowData[1] = l.get(i).getNumber();
            rowData[2] = l.get(i).getPrice();
            rowData[3] = l.get(i).getStock();
            rowData[4] = l.get(i).getMinimumstock();
            rowData[5] = l.get(i).getBulk();
        	
        	
        	
        	
            
            if(l.get(i).getStock()<= l.get(i).getMinimumstock()) { //nur ein test
            	//TabelleBefüllen
            }
            TabelleBefüllen.addRow(rowData); //befüllt eine Zeile mit allen Spalten
        }
	} 
	
	/**
	 * Verwendet von: MitarbeiterMenue
	 * Beschreibung: füllt die Tabelle für die Mitarbeiter im Benutzermanagement. Funktioniert genauso wie Update Tabelle
	 * @param l gibt die Liste an welche in die Tabelle soll
	 */
	
	// tabellen befüllen und aktualisieren
	
	public void updateBenutzerMitarbeiterTabelle(List<Employee> l) {
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle3.getModel();
		TabelleBefüllen.setRowCount(0); 	//leert die tabelle
        Object rowData[] = new Object[7]; 	//gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++){	 //geht die Liste durch und speichert die Daten der Spalten
        
            rowData[0] = l.get(i).getEmployeeNr();
            rowData[1] = l.get(i).getFirstname();
            rowData[2] = l.get(i).getLastname();
            rowData[3] = l.get(i).getPassword();
            TabelleBefüllen.addRow(rowData); //befüllt eine Zeile mit allen spalten
        }
	}
	
	/**
	 * Verwendet von: MitarbeiterMenue
	 * Beschreibung: füllt die Tabelle für die Kunden im BenutzerManagement. Funktioniert genauso wie die UpdateTabelle.
	 * @param l gibt die Lsite an, welche in die Tabelle soll.
	 */
	
	// tabellen befüllen und aktualisieren
	
	public void updateBenutzerKundenTabelle(List<Customer> l) {
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle2.getModel();
		TabelleBefüllen.setRowCount(0); 	//leert die tabelle
        Object rowData[] = new Object[7]; 	//gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++){ 	//geht die Liste durch und speichert die Daten der Spalten
  
            rowData[0] = l.get(i).getCustomerNr();
            rowData[1] = l.get(i).getFirstname();
            rowData[2] = l.get(i).getLastname();
            rowData[3] = l.get(i).getAdress();
            TabelleBefüllen.addRow(rowData); //befüllt eine Zeile mit allen spalten
        }
	}
	
	
	/**
	 * Verwendet von: KundenMenue
	 * Beschreibung: fuellt die Tabelle für die Artikelliste im KundenMenue, funktioniert genauso wie die Update Tabelle
	 * @param l gibt die Liste an, welche in die Tabelle soll.
	 */
	
	// tabellen befüllen und aktualisieren
	
	public void updateKundenTabelle(List<Item> l) {
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle.getModel();
		TabelleBefüllen.setRowCount(0);		//leert die tabelle
        Object rowData[] = new Object[5];	//gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++){	//geht die Liste durch und speichert die Daten der Spalten
        	  
        	
            rowData[0] = l.get(i).getName();
            rowData[1] = l.get(i).getNumber();
            rowData[2] = l.get(i).getPrice();
            rowData[3] = l.get(i).getStock();
            rowData[4] = l.get(i).getBulk();
            if(l.get(i).getStock()<= l.get(i).getMinimumstock()) {
            	//TabelleBefüllen
            }
            TabelleBefüllen.addRow(rowData); // befüllt eine Zeile mit allen Spalten
        }
	}
	
	
	/**
	 * Verwendet von: KundenMenue
	 * Beschriebung: fuellt die Tabelle im Warenkorb vom Kunden.
	 * @param l ist die Liste welche die Tabelle vom Warenkorb befüllen soll.
	 */
	
	// tabellen befüllen und aktualisieren
	
	public void updateKundenWarenkorbTabelle(List<tempItem> l) {
		double totalPrice;
		double test = 0;
		String testString = "";
		
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle1.getModel();
		TabelleBefüllen.setRowCount(0);			//leert die tabelle
        Object rowData[] = new Object[5];		//gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++){		//geht die Liste durch und speichert die Daten der Spalten
        	
            rowData[0] = l.get(i).getItem().getName();
            rowData[1] = l.get(i).getItem().getNumber();
            rowData[2] = l.get(i).getAmount();
            rowData[3] = l.get(i).getItem().getPrice();
            rowData[4] = l.get(i).getItem().getPrice() * l.get(i).getAmount();
            
            totalPrice = l.get(i).getItem().getPrice()* l.get(i).getAmount();
            test = totalPrice + test; //um den Gesamtpreis im Warenkorb zu berechenn
            testString = String.valueOf(test);
            TabelleBefüllen.addRow(rowData); //befüllt eine Zeile mit allen Spalten
            gesamtPreisZahl.setText("  " + testString + " "); //setzt in der GUI den Gesamtpreis ein
        }   
	}
	
	/**
	 * Verwendet von: KundenMenue Rechnung
	 * Beschreibung: füllt die Liste in der Rechnung
	 * @param l sit die Liste welche die Tabelle in der Rechnung befüllt
	 */
	
	
	public void updateKundenRechnungTabelle(List<tempItem> l) {
		double totalPrice;
		double test = 0;
		String testString = "";
		
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) table.getModel();
		TabelleBefüllen.setRowCount(0);
        Object rowData[] = new Object[5];
        for(int i = 0; i < l.size(); i++)
        {
            rowData[0] = l.get(i).getItem().getName();
            rowData[1] = l.get(i).getItem().getNumber();
            rowData[2] = l.get(i).getAmount();
            rowData[3] = l.get(i).getItem().getPrice();
            rowData[4] = l.get(i).getItem().getPrice() * l.get(i).getAmount();
            
            totalPrice = l.get(i).getItem().getPrice()* l.get(i).getAmount();
            test = totalPrice + test;
            testString = String.valueOf(test);
            TabelleBefüllen.addRow(rowData);
            labelPreis.setText(" " + testString + " ");
            System.out.println(testString);
        }   
	}
	
	public void updateChangelogTabelle(List<Changelog> l) {
		DefaultTableModel TabelleBefüllen = (DefaultTableModel) tabelle4.getModel(); //gibt an welche Tabelle befüllt werden soll.
		TabelleBefüllen.setRowCount(0); //leert die aktuelle Tabelle
        Object rowData[] = new Object[5]; //gibt an wie viele Spalten die Tabelle hat
        for(int i = 0; i < l.size(); i++) //geht die Liste durch und speichert die Daten der Spalten
        {
            rowData[0] = l.get(i).getTime();
            if(l.get(i).getTyp()) { //mitarbeiter
            	rowData[1] = l.get(i).getEmployee().getEmployeeNr();
                rowData[2] = l.get(i).getEmployee().getFirstname();
                rowData[3] = l.get(i).getEmployee().getLastname();
            } else {
            	rowData[1] = l.get(i).getCustomer().getCustomerNr();
                rowData[2] = l.get(i).getCustomer().getFirstname();
                rowData[3] = l.get(i).getCustomer().getLastname();
            }
            rowData[4] = l.get(i).getMessage();
            
           
            TabelleBefüllen.addRow(rowData); //befüllt eine Zeile mit allen Spalten
            System.out.println(l.get(i).getMessage());
        }
	}
	
	/**
	 * 
	 * @param iNum ist die Itemnummer
	 * @return true: Item zu dem Nummer wurde gefunden. false: kein Item wurde gefunden Exception wird geworfen
	 * @throws InvalidArtikelNummerException
	 */
	private boolean checkNumber(int iNum) throws InvalidItemNumberException {
		boolean x = false;
		for(Item i : storage.getAllItems()) { //geht die Artikelliste durch
			System.out.println(i.getNumber() +  " | " + iNum);
			if(i.getNumber() == iNum) {	 //prüft ob artikelnummer und aNum passen
				x = true; //falls ja true
				break;
			} else {
				x = false; //false nein false
			}
		}
		if(!x) {
			throw new InvalidItemNumberException(); //wirft die Exception
		}
		return x;
	}
	
	/**
	 * 
	 * @param aName ist der Itemname
	 * @return true: Ein Name wurde gefunden.  false: kein Artikel mit dem Namen wurde gefunden.
	 * @throws InvalidArtikelNameException
	 */
	private boolean checkName(String iName) throws InvalidItemNameException {
		boolean x = false;
		for(Item i : storage.getAllItems()) {
			if(i.getName().equals(iName)) {	
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidItemNameException();
		}
		return x;
	}
	
	
	/**
	 * 
	 * @param iNum die Itemnummer
	 * @param iBe der Itembestand
	 * @return true: Itemnummer und Nummer stimmen überein, bestand wird erhöht. false: Exception wird geworfen
	 * @throws InvalidArtikelNummerException
	 */
	private boolean checkNummerBestand(int iNum, int iSt) throws InvalidItemNumberException {
		boolean x = false;
		for(Item i : storage.getAllItems()) {
			if(i.getNumber() == iNum) {	
				i.setStock(iSt);
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidItemNumberException();
		}
		return x;
	}
	
	/**
	 * 
	 * @param eNum ist die MitarbeiterNummer
	 * @return true: Mitarbeiternummer und Nummer stimmen überein. False: Exception wird geworfen
	 * @throws InvalidMitarbeiterNummerException
	 */
	private boolean checkNummerMitarbeiter(int eNum) throws InvalidEmployeeNumberException {
		boolean x = false;
		for(Employee e : employeeManagement.getAllEmployee()) {
			if(e.getEmployeeNr() == eNum) {	
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidEmployeeNumberException();
		}
		return x;
	}
	
	/**
	 * 
	 * @param cNum die Customernummer
	 * @return true: Customernummer und Nummer stimmen überein. False: Exception wird geworfen
	 * @throws InvalidCustomerNummerException
	 */
	private boolean checkNummerKunde(int cNum) throws InvalidCustomerNumberException {
		boolean x = false;
		for(Customer c : customerManagement.getAllCustomer()) {
			if(c.getCustomerNr() == cNum) {	
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidCustomerNumberException(); 
		}
		return x;
	}
	
	/**
	 * 
	 * @param aName Name des Itemss
	 * @return true: Itemname und Name stimmen überein. False: Exception wird geworfen
	 * @throws InvalidItemNameException
	 */
	private boolean checkNameKunde(String iName) throws InvalidItemNameException {
		boolean x = false;
		for(Item i : storage.getAllItems()) {
			System.out.println(i.getName() + " | " + iName);
			if(i.getName().equals(iName)) {	
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidItemNameException();
		}
		return x;
	}
	
	/**
	 * 
	 * @param iNum die Itemnummer
	 * @param iAmo die Itemanzahl
	 * @return ture: Itemnummer und Nummer & Anzahl und Bestand stimmen überein. false: Exception wird geworfen
	 * @throws InvalidWarenkorbException
	 */
	private boolean checkWarenkorb(int iNum, int iAmo) throws InvalidCartException {
		boolean x = false;
		for(Item i : storage.getAllItems()) {
			System.out.println(i.getNumber()==iNum && iAmo <= i.getStock());
			System.out.println(!( i.getNumber() == iNum));
			System.out.println( i.getStock() <= iAmo);
			if(i.getNumber()==iNum && iAmo <= i.getStock() && iAmo >= i.getBulk()) {	
				x = true;
				break;
			} else {
				x = false;				
			} 
		}
		if(!x) {
			throw new InvalidCartException();
		}
		return x;
	}
	
	/**
	 * 
	 * @param iNum die Artikelnummer
	 * @param aAnz die Artikelanzahl
	 * @return true: Artikelnummer und Anzahl stimmen mit den werten überein. False: Exception wird geworfen.
	 * @throws InvalidWarenkorbException
	 */
	private boolean checkWarenkorbDelete(int iNum, int iAmo) throws InvalidCartException {
		boolean x = false;
		for(tempItem ti : cart.getCart()) {
			if(ti.getItem().getNumber() == iNum && ti.getAmount()>= iAmo) {
				x = true;
				break;
			} else {
				x = false;
			}
			
		}
		if(!x) {
			throw new InvalidCartException();
		}
		return x;
	}
	
	/**
	 * 
	 * @param aName der Artikelname
	 * @return true: ein Artikel wurde in der Suche gefunden. False: Exception wird geworfen.
	 * @throws InvalidWarenkorbNameException
	 */
	private boolean checkWarenkorbSearch(String aName) throws InvalidCartNameException{
		boolean x = false;
		for(tempItem ti : cart.getCart()) {
			if(ti.getItem().getName().equals(aName)) {	
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if(!x) {
			throw new InvalidCartNameException();
		}
		return x;
	}
	
	/**
	 *  Verwendet von: shopAnmelungMitarbeiter
	 *  Beschriebung: Erzeugt die GUI für das MitarbeiterFenster. Hier werden alle Tabs erstellt und auch die Funktionen aufgerufen für das Mitarbeiter Menue.
	 */
	public void mitarbeiterMenue() {
		
		// mitarbeiter menue erstellt ( mit titel, fenstergröße, schließen des fenster, sowie layout festgesetzt ) 
		
		mitarbeiterMenue = new JFrame();
		mitarbeiterMenue.setVisible(true);
		mitarbeiterMenue.setTitle("Menü für Mitarbeiter");
		mitarbeiterMenue.setBounds(300, 150, 680, 564);
		mitarbeiterMenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mitarbeiterMenue.getContentPane().setLayout(null);
		
		// main tab erstellt
		
				JTabbedPane Maintab = new JTabbedPane(JTabbedPane.TOP);
				Maintab.setBounds(0, 0, 668, 507);
				mitarbeiterMenue.getContentPane().add(Maintab);
				
	/*---------------------------------------------------------------------------------------------------------------------------------------------------------*/
				
				 // lager tab erstellt
				
				JPanel LagerTab = new JPanel();
				Maintab.addTab("Lager", null, LagerTab, null);
				LagerTab.setLayout(null);
				
				// erstellt das Layout, wo die Tabelle entsteht
				
				JScrollPane Layout = new JScrollPane();
				Layout.setBounds(194, 58, 445, 410);
				LagerTab.add(Layout);
				
				// erstellt die Tabelle
				tabelle = new JTable();
				tabelle.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Name", "Nummer", "Preis", "Bestand", "Mindestbestand", "Massengut"}) {});
				
				// setzt die größe der spalte des fensters mit den namen fest 
				
				tabelle.getColumnModel().getColumn(0).setPreferredWidth(45);
				tabelle.getColumnModel().getColumn(1).setPreferredWidth(55);
				tabelle.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabelle.getColumnModel().getColumn(3).setPreferredWidth(57);
				tabelle.getColumnModel().getColumn(4).setPreferredWidth(90);
				tabelle.getColumnModel().getColumn(5).setPreferredWidth(57);
				Layout.setViewportView(tabelle);
				
					
				//Tabelle erstes mal befüllen , ruft methode oben auf
				updateTabelle(storage.getAllItems());
     
			    
				
				//erstellt ein button "artikel hinzufügen"
				
				JButton ArtikelHinzufuegen = new JButton("Add an Item");
				ArtikelHinzufuegen.addActionListener(new ActionListener() {
					
			    // Funktion zum öffnen eines neuen Fensters, um artikel hinzufügen zu können
					
					public void actionPerformed(ActionEvent e) {
						
						ArtikelHinzufuegenMenue = new JFrame();
						ArtikelHinzufuegenMenue.setTitle("Add an Item");
						ArtikelHinzufuegenMenue.setVisible(true);
						ArtikelHinzufuegenMenue.setBounds(970, 150, 328, 370);
						ArtikelHinzufuegenMenue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ArtikelHinzufuegenMenue.getContentPane().setLayout(null);
						
						// erstellt label für artikel anlegen (größe , font etc..) 
						
						JLabel Artikelanlegen = new JLabel("Legen Sie ein neuen Artikel an!");
						Artikelanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
						Artikelanlegen.setBounds(56, 11, 197, 31);
						ArtikelHinzufuegenMenue.getContentPane().add(Artikelanlegen);
						
						// erstellt label Artikelname
						
						JLabel Artikelname = new JLabel("Artikelname :");
						 Artikelname.setBounds(37, 64, 96, 14);
						 ArtikelHinzufuegenMenue.getContentPane().add(Artikelname);
						
						 // erstell eine texteingabe für den artikelname
						 
						textArtikel= new JTextField(null);
						textArtikel.setBounds(37, 89, 96, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textArtikel);
						textArtikel.setColumns(10);
						
						// erstellt label für die artikelnummer 
						
						JLabel lblArtikelnummer = new JLabel("Artikelnummer :");
						lblArtikelnummer.setBounds(37, 120, 96, 14);
						ArtikelHinzufuegenMenue.getContentPane().add(lblArtikelnummer);
						
						// erstellt die texteingabe für die artikelnummer 
						
						textNummer = new JTextField(null);
						textNummer.setColumns(10);
						textNummer.setBounds(37, 145, 37, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textNummer);
						
						
				        // erstellt label für den Preis
						
						JLabel Preis = new JLabel("Preis :");
						Preis.setBounds(187, 64, 96, 14);
						ArtikelHinzufuegenMenue.getContentPane().add(Preis);
						
						// erstellt die texteingabe für den Preis 
						
						textPreis = new JTextField(null);
						textPreis.setColumns(10);
						textPreis.setBounds(187, 89, 96, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textPreis);
						
						 // erstellt den Label bestand 
						
						JLabel Bestand = new JLabel("Bestand");
						Bestand.setBounds(187, 120, 96, 14);
						ArtikelHinzufuegenMenue.getContentPane().add(Bestand);
						
						 // erstellt eine texteingabe für den bestand 
						
						textBestand = new JTextField(null);
						textBestand.setColumns(10);
						textBestand.setBounds(187, 145, 96, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textBestand);
						
						
						 // erstellt label für mindestbestand 
						
						JLabel Mindestbestand = new JLabel("Mindestbestand :");
						Mindestbestand.setBounds(37, 176, 110, 14);
						ArtikelHinzufuegenMenue.getContentPane().add(Mindestbestand);
						
						// erstellt texteingabe für mindestbestand 
						
						textMindestbestand = new JTextField(null);
						textMindestbestand.setColumns(10);
						textMindestbestand.setBounds(37, 201, 96, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textMindestbestand);
						
						// erstellt label massengut
						
						JLabel Massengut = new JLabel("Massengut :");
						Massengut.setBounds(37, 225, 96, 14);
						ArtikelHinzufuegenMenue.getContentPane().add(Massengut);
						
						// erstellt texteingabe für massengut 
						
						textMassengut = new JTextField(null);
						textMassengut.setColumns(10);
						textMassengut.setBounds(37, 250, 96, 20);
						ArtikelHinzufuegenMenue.getContentPane().add(textMassengut);
						
						
						// falls man nicht alle felder ausgefüllt hat, kommt dieser label 
						
						falscherArtikel = new JLabel("Bitte füllen Sie alle Felder.");
						falscherArtikel.setBounds(81, 282, 170, 14);
						falscherArtikel.setForeground(Color.BLACK);
						falscherArtikel.setFont(new Font("Tahoma", Font.PLAIN, 13));
						ArtikelHinzufuegenMenue.getContentPane().add(falscherArtikel);
					
						// erstellt button hinzufügen + funktion zum hinzufügen der artikeln
						
						JButton Hinzufügen = new JButton("Hinzuf\u00FCgen");
						Hinzufügen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//die Variablen für die Eingabefelder und das Auslesen der Eingabefelder in der GUI
								String itemName = "";
								String itemNumber = "";
								String itemPrice = "";
								String itemStock = "";
								String itemMinimumStock = "";
								String itemBulk = "";
								
								int iNumber;
								double iPrice; 
								int iStock; 
								int iMinimumStock;
								int iBulk;
								
								itemName = textArtikel.getText();
								System.out.println(itemName);
								
								itemNumber = textNummer.getText();
								System.out.println(itemNumber);
								iNumber = Integer.parseInt(itemNumber);
								
								itemPrice = textPreis.getText();
								System.out.println(itemPrice);
								iPrice = Double.parseDouble(itemPrice);
								
								itemStock = textBestand.getText();
								System.out.println(itemStock);
								iStock = Integer.parseInt(itemStock);
								
								itemMinimumStock = textMindestbestand.getText();
								System.out.println(itemMinimumStock);
								iMinimumStock = Integer.parseInt(itemMinimumStock);
								
								itemBulk = textMassengut.getText();
								iBulk = Integer.parseInt(itemBulk);
								
								
								try {
									for(Item i : storage.getAllItems()) { //geht alle Artikel durch
										if(i.getNumber() == iNumber) {	 //wenn Artikelnummer schon vorhanden ist 
											falscherArtikel.setForeground(Color.RED); //Rote farbe für die Fehlermeldung
											falscherArtikel.setText("Die ArtikelNr existiert bereits!"); //die Fehlermeldung
											logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"You tryed add an existing Item",true));
											textNummer.setText(null); //leert das feld mit der Artikelnummer
											throw new InvalidItemNumberException(); //dann wirf die Exception
									    
										} 	
									}
								}
								catch (InvalidItemNumberException ex) { 
									System.out.println(ex.getMessage()); //fängt die Exception und gibt sie in der Console aus 
								}
								
								if(!textNummer.getText().isEmpty()) { //fals alle felder richtig befüllt sind 
									
									storage.addanItem(itemName, iNumber, iPrice, iStock, iMinimumStock, iBulk);//füge neuen artikel dem lager hinzu
								    logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"The Item "+ itemName+" has been added.",true));
									
									try {
										storage.writeItem(); //speichert den neuer Lagerbestand in der Txt datei
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
						
									ArtikelHinzufuegenMenue.setVisible(false); //schließt das Menu
									updateTabelle(storage.getAllItems()); //updated die Tabelle für die Artikel
								}
							
							}
						});
						Hinzufügen.setBounds(177, 189, 104, 32);
						ArtikelHinzufuegenMenue.getContentPane().add(Hinzufügen);
							
						
					}
				});
				ArtikelHinzufuegen.setBounds(486, 11, 153, 23);
				LagerTab.add(ArtikelHinzufuegen);
				
				//erstellt button "artikel löschen" 
				
				JButton ArtikelLoeschen = new JButton("Artikel L\u00F6schen");
				ArtikelLoeschen.addActionListener(new ActionListener() {
					
					// Funktion zum öffnen eines neuen Fensters, um artikel löschen zu können
					
					public void actionPerformed(ActionEvent e) {
						
						// erstellt neues fenster für  artikel löschen 
						
						ArtikelLoeschenMenue = new JFrame();
						ArtikelLoeschenMenue.setTitle("Loeschen eines Artikels");
						ArtikelLoeschenMenue.setVisible(true);
						ArtikelLoeschenMenue.setBounds(970, 150, 304, 246);
						ArtikelLoeschenMenue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ArtikelLoeschenMenue.getContentPane().setLayout(null);
						
						// erstellt label für löschen eines Artikel 
						
						JLabel ArtikelLöschen = new JLabel("L\u00F6schen Sie ein Artikel!");
						ArtikelLöschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
						ArtikelLöschen.setBounds(68, 11, 160, 31);
						ArtikelLoeschenMenue.getContentPane().add(ArtikelLöschen);
						
						// ersstellt label für falsshe eingabe ( bleibt vorerst leer) 
						
						JLabel ANumNichtvergeben = new JLabel("  ");
						ANumNichtvergeben.setForeground(Color.RED);
						ANumNichtvergeben.setBounds(10, 186, 280, 14);
						ArtikelLoeschenMenue.getContentPane().add(ANumNichtvergeben);
						
						// erstellt label für geben ssie eine artikle nummer ein 
						
						JLabel eingeben = new JLabel("Geben Sie die Artikelnummer des Artikels ein :");
						eingeben.setBounds(10, 68, 282, 14);
						ArtikelLoeschenMenue.getContentPane().add(eingeben);
						
						// text eingabe für die artikelnummer die man dort eingibt 
						
						textArtikelNummer = new JTextField();
						textArtikelNummer.setBounds(107, 93, 54, 20);
						ArtikelLoeschenMenue.getContentPane().add(textArtikelNummer);
						textArtikelNummer.setColumns(10);
						
						// erstellt den button löschen, um artikel zu löschen 
						
						JButton Loeschen = new JButton("L\u00F6schen");
						Loeschen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String itemNummer = "";
								int iNummer;
								
								itemNummer = textArtikelNummer.getText();
								System.out.println(itemNummer);
								iNummer = Integer.parseInt(itemNummer);
								
								try { // try und catch ob die Artikelnummer richtig ist 
									if(checkNumber(iNummer)) { //fals ja lösche Artikel
										storage.deleteanItem(iNummer); //löscht den Artikel aus dem Lager
										logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentCustomer).get(0),"The Item "+ iNummer+" has been deleted.",true));
										
										ArtikelLoeschenMenue.setVisible(false); //schließt das Lösch Fenster 
										updateTabelle(storage.getAllItems()); //updated die Tabelel´der Artikel
										try {
											storage.writeItem(); //speichert die Artikel in die TXT datei
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								} catch(InvalidItemNumberException ex) { //falls nein fange die Exception und gib fehlermeldung
									System.out.println(ex.getMessage()); //gibt die Fehlermeldung in der Console aus 
									ANumNichtvergeben.setText("Bitte geben Sie eine gültige Artikelnummer ein!"); //fehlermeldung falls die Artikelnummer falsch ist in der GUI
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),ex.getMessage(),true));//schriebt den Fehler im Changelog
									
									textArtikelNummer.setText(null); //leert das Eingabefeld für die Artikelnummer
							
								}
								
						
							}
						});
						Loeschen.setBounds(81, 143, 104, 32);
						ArtikelLoeschenMenue.getContentPane().add(Loeschen);
					}
				});
				ArtikelLoeschen.setBounds(330, 11, 126, 23);
				LagerTab.add(ArtikelLoeschen);
				
				
				
				
				// erstellt button "artikel suchen"
				
				JButton ArtikelSuchen = new JButton("Artikel suchen");
				ArtikelSuchen.addActionListener(new ActionListener() {
					
					//Funktion zum öffnen eines neuen Fensters, um artikel suchen zu können

					public void actionPerformed(ActionEvent e) {
						
						// erstellt ein fensster artikel suchen 
						
						artikelScreach = new JFrame();
						artikelScreach.setTitle("Artikel suchen");
						artikelScreach.setVisible(true);
						artikelScreach.setBounds(970, 150, 304, 246);
						artikelScreach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						artikelScreach.getContentPane().setLayout(null);
						
						// erstellt ein label für welchen artikel man sucht 
						
						JLabel Artikelanlegen = new JLabel("Welchen Artikel Suchen Sie?");
						Artikelanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
						Artikelanlegen.setBounds(50, 11, 193, 31);
						artikelScreach.getContentPane().add(Artikelanlegen);
						
						// erstellt ein label für den namen des artikels
						
						JLabel Artikelname = new JLabel("Geben Sie den Artikelname des Artikels ein :");
						Artikelname.setBounds(10, 68, 282, 14);
						artikelScreach.getContentPane().add(Artikelname);
						
						// erstellt eine texteingabe, wo man den namen rein schreiben kann 
						
						textArtikel2 = new JTextField();
						textArtikel2.setBounds(81, 93, 104, 20);
						artikelScreach.getContentPane().add(textArtikel2);
						textArtikel2.setColumns(10);
						
						// falls man ein falschen namen eingetragen hat( bleibt vorerst leer, in der funktion selber wirds gefüllt ) 
						
						JLabel FalscherArtikel = new JLabel("");
						FalscherArtikel.setForeground(Color.RED);
						FalscherArtikel.setBounds(80, 186, 240, 14);
						artikelScreach.getContentPane().add(FalscherArtikel);
						
						// erstellt button suchen und eine funktion zum suchen eines artikels
						
						JButton Suchen = new JButton("Suchen");
						Suchen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String itemName = "";
								
								itemName = textArtikel2.getText();
								
								try { //try und catch ob der Name richtig ist 
									if(checkName(itemName)) { //falls ja suche danach
										updateTabelle(storage.searchbyName(itemName)); //updated die Tabelle mit dem Suchergebnis
										artikelScreach.setVisible(false); //schließt das Fenster
									}
								} catch (InvalidItemNameException ex) { //falls nein fange die Exception und gib fehlermeldung
									System.out.println(ex.getMessage()); //gibt die Fehlermeldung in der Console aus 
									FalscherArtikel.setText("Ungültiger Name!"); //fehlermeldung in der Gui
									textArtikel2.setText(null); //leert  das Eingabefeld für den Artikelnamen
									//logmanager.einfuegen(new Changelog(buero.sucheNachNummer(aktuellerMitarbeiter).get(0), ex.getMessage() + "bei der Suche", true));
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),ex.getMessage()+"bei der Suche",true));
								}
								
							}
						});
						Suchen.setBounds(81, 143, 104, 32);
						artikelScreach.getContentPane().add(Suchen);
						
						
						
						
					}
				});
				ArtikelSuchen.setBounds(194, 11, 126, 23);
				LagerTab.add(ArtikelSuchen);
				
				
				
				// erstellen button" Artikel anzeigen und funktion damit die tablle aktualisiert wird"
				
				JButton Anzeigen = new JButton("Artikel anzeigen");
				Anzeigen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						updateTabelle(storage.getAllItems()); //updated die Tabelle in der Alle Artikel stehen (zeigt wieder alles an)
						
					}
				});
				Anzeigen.setBounds(10, 11, 153, 23);
				LagerTab.add(Anzeigen);
				
				
				
				
				
				//erstellt button "Artikel sortieren Nummer"
				
				JButton ArtikelSoNum = new JButton("Artikel Sortieren Nummer");
				ArtikelSoNum.addActionListener(new ActionListener() {
					
					//Funktion zum öffnen eines neuen Fensters, um artikel zu sortieren zu können
					public void actionPerformed(ActionEvent e) {
						
						updateTabelle(sortNummerArtikelliste(storage.getAllItems())); //sortiert die Tabelle für die Artikel
					}
				});
				ArtikelSoNum.setBounds(0, 419, 195, 23);
				LagerTab.add(ArtikelSoNum);
				
				
				
				
				// erstellt button " artikeln sortieren Namen"
				
				JButton ArtikelSoNam = new JButton("Artikel Sortieren Namen");
				ArtikelSoNam.addActionListener(new ActionListener() {
					
					//Funktion zum öffnen eines neuen Fensters, um artikel zu sortieren zu können
					
					public void actionPerformed(ActionEvent e) {
						
						
						updateTabelle(sortNameArtikelliste(storage.getAllItems())); //sortiert die Tabelle für die Artikel
					
					}
				});
				ArtikelSoNam.setBounds(0, 373, 196, 23);
				LagerTab.add(ArtikelSoNam);
				
				
				// erstell layout für die bestandserhöhung 
				
				JScrollPane Layout1 = new JScrollPane();
				Layout1.setBounds(10, 147, 170, 202);
				LagerTab.add(Layout1);
				
				// erstell "Raster im layout, um buttons etc einfügen zu können
				
				JPanel Raster = new JPanel();
				Layout1.setViewportView(Raster);
				Raster.setLayout(null);  
				
                // erstellt Label für neuer bestand
				
				JLabel NeuerBestand = new JLabel("Neuer Bestand :");
				NeuerBestand.setBounds(37, 72, 113, 14);
				Raster.add(NeuerBestand);
				
				//erstellt eine Texteingabe zum schreiben für bestand
				
				JSpinner spinnerBestand = new JSpinner();
				spinnerBestand.setBounds(52, 97, 60, 26);
				Raster.add(spinnerBestand);
				
				
				// erstellt label für Artikelname
				
				JLabel lblNewLabel_1_1 = new JLabel("ArtikelNr :");
				lblNewLabel_1_1.setBounds(37, 16, 113, 14);
				Raster.add(lblNewLabel_1_1);
				
				//erstellt eine Texteingabe zum schreiben für ArtikelNummer
				
				textArtikel5 = new JTextField();
				textArtikel5.setColumns(10);
				textArtikel5.setBounds(37, 41, 96, 20);
				Raster.add(textArtikel5);
				
				// erstellt label für falscher eingabe ( vorerst bleibt es leer) 
				
				JLabel FalscheArtNr = new JLabel(" ");
				FalscheArtNr.setForeground(Color.RED);
				FalscheArtNr.setBounds(7, 168, 170, 14);
				Raster.add(FalscheArtNr);
				
				System.out.println(textArtikel5.getText());
				
				// erstellt button " bestand aendern"
				
				JButton Bestandaendern = new JButton("Bestand \u00E4ndern");
				Bestandaendern.addActionListener(new ActionListener() {
					
				// erstellt funktion zum bestand ändern 	
					
					public void actionPerformed(ActionEvent e) {
						
						//variablenn für die Bestandänderung und ausleben der Textfelder
						String itemNumber = "";
						String itemStock ="";
						int iStock;
						int iNumber;
						System.out.println(textArtikel5.getText());
						itemNumber = textArtikel5.getText();
						System.out.println(textArtikel5.getText());
						iNumber = Integer.parseInt(itemNumber);
						
						iStock = (Integer)spinnerBestand.getValue(); //auslesen des Spinners
						
						
						try { //versuche den Artikebestand zu verändern
							if(checkNummerBestand(iNumber, iStock)) { //falls die Eingbae richig war..
								FalscheArtNr.setText(null); //Lösche die Fehlermeldung 
								textArtikel.setText(null);  //leere die eingabefelder
								spinnerBestand.setValue((Integer)0); //leere die Eingabefelder
								updateTabelle(storage.getAllItems()); //update die Tabelle
								FalscheArtNr.setForeground(Color.black); //farbe des Textfeldes auf schwarz
								FalscheArtNr.setText("Bestand geändert!"); //melde die erfolgreiche änderung 
								logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Der Artikel: "+ iNumber +"wurde um die Anzahl: " + iStock +" erhöht",true));
				
								try {
									
									storage.writeItem(); //speichere den neuen Artikelbestand
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						} catch (InvalidItemNumberException ex) { //falls die Eingabe fehlerhaft war
							FalscheArtNr.setForeground(Color.RED); //Text für die Meldung auf Rot
							FalscheArtNr.setText("Fehlerhafte Artikelnummer!"); //fehlermeldung
							textArtikel.setText(null); //leere eingabefeld für die Artikelnummer
							System.out.println(ex.getMessage()); //gib die Fehlermeldung in der console aus
	
							logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),ex.getMessage() + "beim Bestand erhöhen",true));
						}
						
					}
				});
				Bestandaendern.setBounds(10, 134, 152, 23);
				Raster.add(Bestandaendern);
				
				
				
				
				/*-------------------------------------------------------------------------------------------------------------------*/
				
				// erstellt tab changelog
				
				JPanel panel_2 = new JPanel();
				Maintab.addTab("Changelog", null, panel_2, null);
				panel_2.setLayout(null);
				
				// erstellt das layout zum scrollen der tabelle
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(29, 30, 604, 392);
				panel_2.add(scrollPane_1);
				
				// erstellt die tabelle
				
				tabelle4 = new JTable();
				tabelle4.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Datum", "Nr", "Vorname", "Nachname", "Meldung"}) {});
				
				tabelle4.getColumnModel().getColumn(0).setPreferredWidth(53);
				tabelle4.getColumnModel().getColumn(1).setPreferredWidth(33);
				tabelle4.getColumnModel().getColumn(2).setPreferredWidth(62);
				tabelle4.getColumnModel().getColumn(3).setPreferredWidth(67);
				tabelle4.getColumnModel().getColumn(4).setPreferredWidth(60);
				scrollPane_1.setViewportView(tabelle4);
				
				
				updateChangelogTabelle(logmanager.getChangelog());
				// erstellt button name suchen
				
				
				JButton btnNewButton_2 = new JButton("Nach Name suchen");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						nameScreach = new JFrame();
						nameScreach.setTitle("Namen suchen");
						nameScreach.setVisible(true);
						nameScreach.setBounds(970, 150, 304, 246);
						nameScreach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						nameScreach.getContentPane().setLayout(null);
						
						JLabel Nameanlegen = new JLabel("Welchen Namen suchen Sie?");
						Nameanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
						Nameanlegen.setBounds(50, 11, 193, 31);
						nameScreach.getContentPane().add(Nameanlegen);
						
						JLabel Artikelname = new JLabel("Geben Sie eine Namen ein:");
						Artikelname.setBounds(60, 68, 282, 14);
						nameScreach.getContentPane().add(Artikelname);
						
						textName2 = new JTextField();
						textName2.setBounds(81, 93, 104, 20);
						nameScreach.getContentPane().add(textName2);
						textName2.setColumns(10);
						
						JLabel FalscherName = new JLabel("");
						FalscherName.setForeground(Color.RED);
						FalscherName.setBounds(80, 186, 240, 14);
						nameScreach.getContentPane().add(FalscherName);
						
						JButton Suchen = new JButton("Suchen");
						Suchen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								//variable und eingabe auslesen
								String lName = "";
								
								lName = textName2.getText();
								
								try { //try und catch ob der Name richtig ist 
										
										updateChangelogTabelle(logmanager.searchChangelogName(lName)); //falls richtig update die Tabelle mit dem Suchergebnis
										nameScreach.setVisible(false); //fehlermeldung löschen
								} catch (InvalidNameChangelogException ex) { //falls nein fange die Exception und gib fehlermeldung
									System.out.println(ex.getMessage()); //fehlermeldung in console ausgeben
									FalscherName.setText("Ungültiger Name!"); //fehlermeldung in gui
									textName2.setText(null); //eingabefeld leeren
									
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),ex.getMessage() + "bei der Suche im Changelog",true));
								}
								
							}
						}); 
						Suchen.setBounds(81, 143, 104, 32);
						nameScreach.getContentPane().add(Suchen);	
						
					}
				});
				
				btnNewButton_2.setBounds(66, 445, 180, 23);
				panel_2.add(btnNewButton_2);
				
				// erstellt button nach datum sortieren
				
				JButton btnNewButton_3 = new JButton("Nach Datum sortieren");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateChangelogTabelle(sortDateChangelogliste(logmanager.getChangelog()));
					}
				});
				btnNewButton_3.setBounds(287, 445, 180, 23);
				panel_2.add(btnNewButton_3);
				
				// erstellt button aktualisieren
				
				JButton btnNewButton_4 = new JButton("Aktualisieren");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateChangelogTabelle(logmanager.getChangelog());
					}
				});
				btnNewButton_4.setBounds(500, 445, 133, 23);
				panel_2.add(btnNewButton_4);
				
				/*----------------------------------------------------------------------------------------------------------------*/
				
				
				JPanel 	Benutzermanagement = new JPanel();
				Maintab.addTab("Benutzermanagement", null, Benutzermanagement, null);
				Benutzermanagement.setLayout(null);
				
				// ScrollPane für Tabelle erstellt
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(38, 50, 414, 165);
				Benutzermanagement.add(scrollPane);
				
				// erstellt tabelle für Kunde
				
				tabelle2 = new JTable();
				tabelle2.setModel(new DefaultTableModel(new Object[][] {},new String[] {"KundenNr", "Username", "Vorname", "Nachname", "Wohnort", "PLZ", "Strasse"}) {});
				
				// setzt die größe der spalte des fensters mit den namen fest 
				
				tabelle2.getColumnModel().getColumn(0).setPreferredWidth(62);
				tabelle2.getColumnModel().getColumn(1).setPreferredWidth(63);
				tabelle2.getColumnModel().getColumn(2).setPreferredWidth(58);
				tabelle2.getColumnModel().getColumn(3).setPreferredWidth(63);
				tabelle2.getColumnModel().getColumn(4).setPreferredWidth(57);
				tabelle2.getColumnModel().getColumn(5).setPreferredWidth(35);
				tabelle2.getColumnModel().getColumn(6).setPreferredWidth(52);
				scrollPane.setViewportView(tabelle2);
				
				
				//befüllen der Tabelle
				updateBenutzerKundenTabelle(customerManagement.getAllCustomer());
				
				/*-----------------------------------------------------*/
				
				// scrollPane für die 2. tabelle erstellt
				
				JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(38, 274, 414, 175);
				Benutzermanagement.add(scrollPane1);
				
				// erstellt tabelle für Mitarbeiter
				
				tabelle3 = new JTable();
				tabelle3.setModel(new DefaultTableModel(new Object[][] {},new String[] {"MitarbeiterNr", "Username", "Vorname", "Nachname", "Wohnort", "PLZ", "Strasse"}) {});
				
				// setzt die größe der spalte des fensters mit den namen fest 
				
				tabelle3.getColumnModel().getColumn(1).setPreferredWidth(61);
				tabelle3.getColumnModel().getColumn(2).setPreferredWidth(58);
				tabelle3.getColumnModel().getColumn(3).setPreferredWidth(64);
				tabelle3.getColumnModel().getColumn(4).setPreferredWidth(57);
				tabelle3.getColumnModel().getColumn(5).setPreferredWidth(34);
				tabelle3.getColumnModel().getColumn(6).setPreferredWidth(51);
				scrollPane1.setViewportView(tabelle3);
				
				//befüllen der Tabelle
				updateBenutzerMitarbeiterTabelle(employeeManagement.getAllEmployee());
				
				
				// erstellt button mitarbeiter suchen 
				
				JButton mitarbeiterSuchen = new JButton("Mitarbeiter suchen");
				mitarbeiterSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						// erstellt neue fenster für mitarbeiter suchen 
						
						mitarbeiterScreach = new JFrame();
						mitarbeiterScreach.setTitle("Mitarbeiter suchen");
						mitarbeiterScreach.setVisible(true);
						mitarbeiterScreach.setBounds(970, 150, 304, 246);
						mitarbeiterScreach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						mitarbeiterScreach.getContentPane().setLayout(null);
						
						// erstellt label welchen mitarbeiter man sucht 
						
						JLabel mitarbeitersuche = new JLabel("Welchen Mitarbeiter suchen Sie?");
						mitarbeitersuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
						mitarbeitersuche.setBounds(50, 11, 193, 31);
						mitarbeiterScreach.getContentPane().add(mitarbeitersuche);
						
						// erstellt label, dass man eine mitarbeiterNr eingeben soll 
						
						JLabel mitarbeiterNr = new JLabel("Geben Sie die MitarbeiterNr ein :");
						mitarbeiterNr.setBounds(50, 68, 282, 14);
						mitarbeiterScreach.getContentPane().add(mitarbeiterNr);
						
						// erstellt eine texteingabe, wo man die mitarbeiterNr eintragen kann
						
						textMitarbeiterNr = new JTextField();
						textMitarbeiterNr.setBounds(81, 93, 104, 20);
						mitarbeiterScreach.getContentPane().add(textMitarbeiterNr);
						textMitarbeiterNr.setColumns(10);
						
						// erstellt label bei falscher eingabge ( vorerst leer, in funktion wird sie gefüllt) 
						JLabel FalscherArtikel = new JLabel("");
						FalscherArtikel.setForeground(Color.RED);
						FalscherArtikel.setBounds(80, 186, 240, 14);
						mitarbeiterScreach.getContentPane().add(FalscherArtikel);
						
						// erstellt buton suchen + funktion zum suchen eines mitarbeiters 
						
						JButton suchen = new JButton("Suchen");
						suchen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String mNummer = "";
								int mNum;
								mNummer = textMitarbeiterNr.getText();
								mNum = Integer.parseInt(mNummer);
								
								try {
									if(checkNummerMitarbeiter(mNum)) {
										updateBenutzerMitarbeiterTabelle(employeeManagement.searchByNumber(mNum)); 
										mitarbeiterScreach.setVisible(false);
									}
								} catch (InvalidEmployeeNumberException ex) {
									FalscherArtikel.setText("Ungültige Nr!");
									textArtikel.setText(null);
									System.out.println(ex.getMessage());
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Fehler bei der Suche! " + ex.getMessage(),true));
									
								}
								
								
								
								
							}
						});
						suchen.setBounds(81, 143, 104, 32);
						mitarbeiterScreach.getContentPane().add(suchen);
						
					}
				});
				mitarbeiterSuchen.setBounds(484, 329, 155, 23);
				Benutzermanagement.add(mitarbeiterSuchen);
				
				
				
				// erstellt button mitarbeiter löschen 
				
				JButton mitarbeiterLoeschen = new JButton("Mitarbeiter l\u00F6schen ");
				mitarbeiterLoeschen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// erstellt fenster für löschen einess mitarbeiters 
						
						mitaLoeschen = new JFrame();
						mitaLoeschen.setTitle("Loeschen eines Mitarbeiter");
						mitaLoeschen.setVisible(true);
						mitaLoeschen.setBounds(970, 150, 304, 246);
						mitaLoeschen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						mitaLoeschen.getContentPane().setLayout(null);
						
						// erstellt label mitarbeiter löschen 
						
						JLabel mitaLoesch = new JLabel("L\u00F6schen Sie ein Mitarbeiter!");
						mitaLoesch.setFont(new Font("Tahoma", Font.PLAIN, 14));
						mitaLoesch.setBounds(68, 11, 160, 31);
						mitaLoeschen.getContentPane().add(mitaLoesch);
						
						// erstellt label was vorerst leer ist , bei falscher eingabe wird dies gefüllt 
						
						JLabel mitaNrNichtvergeben = new JLabel("  ");
						mitaNrNichtvergeben.setForeground(Color.RED);
						mitaNrNichtvergeben.setBounds(10, 186, 280, 14);
						mitaLoeschen.getContentPane().add(mitaNrNichtvergeben);
						
						// erstellt label um den user aufzufordern eine MitarbeiterNr einzugeben 
						
						JLabel eingeben = new JLabel("Geben Sie die MitarbeiterNr des Mitarbeiter ein :");
						eingeben.setBounds(10, 68, 282, 14);
						mitaLoeschen.getContentPane().add(eingeben);
						
						// erstellt eine texteingabe, um die mitarbeiterNr eintragen zu können 
						
						textMitarbeiterNummer = new JTextField();
						textMitarbeiterNummer.setBounds(107, 93, 54, 20);
						mitaLoeschen.getContentPane().add(textMitarbeiterNummer);
						textMitarbeiterNummer.setColumns(10);
						
						// erstellt button löschen + funktion zum löschen einess mitarbeiters 
						
						JButton Loeschen = new JButton("L\u00F6schen");
						Loeschen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String employeeNumber = "";
								int eNumber;
								
								employeeNumber = textMitarbeiterNummer.getText();
								System.out.println(employeeNumber);
								eNumber = Integer.parseInt(employeeNumber);
								
								try {
									if(checkNummerMitarbeiter(eNumber)) {
										employeeManagement.deleteEmployee(eNumber);
										
										mitaLoeschen.setVisible(false);
										updateBenutzerMitarbeiterTabelle(employeeManagement.getAllEmployee());
										logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Der Mitarbeiter mit der Number"+eNumber+"wurde gelöscht", true));
										
										try {
											employeeManagement.writeEmployee();
											
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								} catch (InvalidEmployeeNumberException ex) {
									System.out.println(ex.getMessage()); 
									mitaNrNichtvergeben.setText("Bitte geben Sie eine gültige Mitarbeiternummer ein!"); 
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Fehlerhafte Mitarbeiternummer beim löschen!",true));
							
									textMitarbeiterNummer.setText(null); 
								}
								
								
								
						
							}
						});
						Loeschen.setBounds(81, 143, 104, 32);
						mitaLoeschen.getContentPane().add(Loeschen);
						
					}
				});
				mitarbeiterLoeschen.setBounds(484, 380, 155, 23);
				Benutzermanagement.add(mitarbeiterLoeschen);
				
				
				
				// erstellt button mitarbeiter anlegen
				
				JButton mitarbeiterAnlegen = new JButton("Mitarbeiter anlegen");
				mitarbeiterAnlegen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						shopMitarbeiterRegistrierung(true);
						updateBenutzerMitarbeiterTabelle(employeeManagement.getAllEmployee());
					}
				});
				mitarbeiterAnlegen.setBounds(484, 426, 155, 23);
				Benutzermanagement.add(mitarbeiterAnlegen);
				
				
				
				
				//erstellt button kunde suchen
				
				JButton kundeSuchen = new JButton("Kunde suchen");
				kundeSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					   
						// erstellt ein neues fenster kunde suchen 
						
						artikelScreach1 = new JFrame();
						artikelScreach1.setTitle("Kunden suchen");
						artikelScreach1.setVisible(true);
						artikelScreach1.setBounds(970, 150, 304, 246);
						artikelScreach1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						artikelScreach1.getContentPane().setLayout(null);
						
						// erstellt label kunde suchen
						
						JLabel Artikelanlegen = new JLabel("Welchen Kunden suchen Sie?");
						Artikelanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
						Artikelanlegen.setBounds(50, 11, 193, 31);
						artikelScreach1.getContentPane().add(Artikelanlegen);
						
						// erstellt label kundenNr
						
						JLabel KundenNr = new JLabel("Geben Sie die KundenNr ein :");
						KundenNr.setBounds(50, 68, 282, 14);
						artikelScreach1.getContentPane().add(KundenNr);
						
						// erstellt texteingabe um kundenNr eintragen zu können 
						
						textKundeNr = new JTextField();
						textKundeNr.setBounds(81, 93, 104, 20);
						artikelScreach1.getContentPane().add(textKundeNr);
						textKundeNr.setColumns(10);
						
						// erstellt label für falsche eingabe , vorerst leer 
						
						JLabel FalscherArtikel = new JLabel("");
						FalscherArtikel.setForeground(Color.RED);
						FalscherArtikel.setBounds(80, 186, 240, 14);
						artikelScreach1.getContentPane().add(FalscherArtikel);
						
						// erstellt button suchen + funktion suchen 
						
						JButton suchen = new JButton("Suchen");
						suchen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String customerNummer = "";
								int cNummer;
								customerNummer = textKundeNr.getText();
								cNummer = Integer.parseInt(customerNummer);
								
								try {
									if(checkNummerKunde(cNummer)) {
										updateBenutzerKundenTabelle(customerManagement.searchByNumber(cNummer));
										artikelScreach1.setVisible(false); 
									}
								} catch(InvalidCustomerNumberException ex) {
									FalscherArtikel.setText("Ungültige Nr!"); 
									textKundeNr.setText(null); 
									System.out.println(ex.getMessage()); 
								}
								
							}
						});
						suchen.setBounds(81, 143, 104, 32);
						artikelScreach1.getContentPane().add(suchen);	
					}
				});
				kundeSuchen.setBounds(484, 53, 155, 23);
				Benutzermanagement.add(kundeSuchen);
				
				
				
				
				// erstellt button kunde löschen
				
				JButton kundeLoeschen = new JButton("Kunde l\u00F6schen");
				kundeLoeschen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// erstellt eine neues fenster kunde löschen 
						
						kundLoeschen = new JFrame();
						kundLoeschen.setTitle("Loeschen eines Kunden");
						kundLoeschen.setVisible(true);
						kundLoeschen.setBounds(970, 150, 304, 246);
						kundLoeschen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						kundLoeschen.getContentPane().setLayout(null);
						
						// erstellt label löschen 
						
						JLabel kundeLoeschArt = new JLabel("L\u00F6schen Sie ein Kunde!");
						kundeLoeschArt.setFont(new Font("Tahoma", Font.PLAIN, 14));
						kundeLoeschArt.setBounds(68, 11, 160, 31);
						kundLoeschen.getContentPane().add(kundeLoeschArt);
						
						// erstellt label für falsche texteingabe, vorerst leer 
						
						JLabel kundNrNichtvergeben = new JLabel("  ");
						kundNrNichtvergeben.setForeground(Color.RED);
						kundNrNichtvergeben.setBounds(10, 186, 280, 14);
						kundLoeschen.getContentPane().add(kundNrNichtvergeben);
						
						// erstellt label für kunden nr 
						
						JLabel eingeben = new JLabel("Geben Sie die KundenNr des Kunden ein :");
						eingeben.setBounds(10, 68, 282, 14);
						kundLoeschen.getContentPane().add(eingeben);
						
						// erssstellt eine texteingabe für die kundennummer 
						
						textKundenNummer = new JTextField();
						textKundenNummer.setBounds(107, 93, 54, 20);
						kundLoeschen.getContentPane().add(textKundenNummer);
						textKundenNummer.setColumns(10);
						
						// erstellt button löschen + funktion zum löschen eines kunden
						
						JButton Loeschen = new JButton("L\u00F6schen");
						Loeschen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String customerNummer = "";
								int cNummer;
								
								customerNummer = textKundenNummer.getText();
								System.out.println(customerNummer);
								cNummer = Integer.parseInt(customerNummer);
								
								try {
									if(checkNummerKunde(cNummer)) {
										customerManagement.deleteCustomer(cNummer);
									
										kundLoeschen.setVisible(false);
										logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Der Kunde mit der Nummer:" + cNummer+ "wurde gelöscht",true));
										updateBenutzerKundenTabelle(customerManagement.getAllCustomer());
										try {
											customerManagement.writeCustomer();;
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								} catch(InvalidCustomerNumberException ex) {
									System.out.println(ex.getMessage());
									logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Fehler beim löschen eines Kunden", true));
							
									kundNrNichtvergeben.setText("Bitte geben Sie eine gültige Kundennummer ein!"); 
									textKundenNummer.setText(null);
								}
								
							}
						});
						Loeschen.setBounds(81, 143, 104, 32);
						kundLoeschen.getContentPane().add(Loeschen);
					
					}
				});
				kundeLoeschen.setBounds(484, 101, 155, 23);
				Benutzermanagement.add(kundeLoeschen);
				
				
				
				
				// erstellt button kunden anlegen 
				
				JButton kundeAnlegen = new JButton("Kunde anlegen");
				kundeAnlegen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						shopKundeRegistrierung(true);
						System.out.println("kommt das?");
						updateBenutzerKundenTabelle(customerManagement.getAllCustomer());
						
					}
				});
				 kundeAnlegen.setBounds(484, 147, 155, 23);
				Benutzermanagement.add( kundeAnlegen);
				
				
				
				
				// erstellt button listen aktualisieren 
				
				JButton listenAktualisieren = new JButton("Listen aktualisieren");
				listenAktualisieren.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						updateBenutzerKundenTabelle(customerManagement.getAllCustomer());
						updateBenutzerMitarbeiterTabelle(employeeManagement.getAllEmployee());
					}
				});
				listenAktualisieren.setBounds(484, 227, 155, 26);
				Benutzermanagement.add(listenAktualisieren);
				
				
				
				
				// erstellt ein label bzw übeschrift für die tabellen (kunde)
				
				JLabel labelKunden = new JLabel("Liste von Kunden");
				labelKunden.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelKunden.setBounds(38, 13, 160, 26);
				Benutzermanagement.add(labelKunden);
				
				// erstellt ein label bzw übeschrift für die tabellen (mitarbeiter)
				
				JLabel labelMitarbeiter = new JLabel("Liste von Mitarbeiter");
				labelMitarbeiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelMitarbeiter.setBounds(38, 237, 160, 26);
				Benutzermanagement.add(labelMitarbeiter);
				
				
				
				
				// erstellt Ausloggen button und schickt uns zurück zum Startfenster
				
				JButton Ausloggen = new JButton("Ausloggen");
				Ausloggen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gibMenueAus(); //gibt das startmenue aus
						mitarbeiterMenue.setVisible(false); //ausloggen
						
						logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Hat sich abgemeldet",true));
					}
				});
				Ausloggen.setBounds(549, 506, 119, 23);
				mitarbeiterMenue.getContentPane().add(Ausloggen);
				
				
				// erstellt ein heftiges label für die krassen creater xD 
				
				JLabel Creater = new JLabel("E-Shop creater : Mario Schulz, Bernd Henke, Diyar Aydin");
				Creater.setBounds(40, 510, 442, 14);
				mitarbeiterMenue.getContentPane().add(Creater);
			}		
	
	
	/*--------------------------------------------------------------------------------*/
	
	
	/**
	 *  Verwendet von: shopAnmeldungKunde
	 *  Beschriebung: erzeugt die GUI für das KundenMenue. Hier sind auch alle funktionisaufrufe die in der GUI stattfinden.
	 */
	public void kundenMenue() {
		
		// erstellt fenster für das kundenMenü
		
		kundenMenue = new JFrame();
		kundenMenue.setVisible(true);
		kundenMenue.setTitle("Menü für Kunden");
		kundenMenue.setBounds(300, 150, 680, 564);
		kundenMenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kundenMenue.getContentPane().setLayout(null);
		
		// main tab 
		
		JTabbedPane Maintab = new JTabbedPane(JTabbedPane.TOP);
		Maintab.setBounds(0, 0, 668, 507);
		kundenMenue.getContentPane().add(Maintab);
		
		/*-------------------------------------------------------------------------------------*/
		
		 // tab sortiment erstellt
		
		JPanel sortiment = new JPanel();
		Maintab.addTab("Sortiment", null, sortiment, null);
		sortiment.setLayout(null);
		
		// erstellt das Layout, wo die Tabelle entsteht
		
		JScrollPane Layout = new JScrollPane();
		Layout.setBounds(270, 58, 390, 410);
		sortiment.add(Layout);
		
		// erstellt die Tabelle
		tabelle = new JTable();
		tabelle.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Name", "Nummer", "Preis","Auf Lager", "Mindestanzahl"}) {});
		
		// größe der spalten
		
		tabelle.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabelle.getColumnModel().getColumn(1).setPreferredWidth(55);
		
		Layout.setViewportView(tabelle);
		
		//tabelle befüllen
		updateKundenTabelle(storage.getAllItems());
		
		// erstellt button "artikel suchen"
		
		JButton ArtikelSuchen = new JButton("Artikel suchen");
		ArtikelSuchen.addActionListener(new ActionListener() {
			
			//Funktion zum öffnen eines neuen Fensters, um artikel zu suchen
			
			public void actionPerformed(ActionEvent e) {
				
				// erstellt neues fenster artike suchen 
				artikelScreach = new JFrame();
				artikelScreach.setTitle("Artikel suchen");
				artikelScreach.setVisible(true);
				artikelScreach.setBounds(970, 150, 304, 246);
				artikelScreach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				artikelScreach.getContentPane().setLayout(null);
				
				// erstellt label artikel suchen 
				
				JLabel Artikelanlegen = new JLabel("Welchen Artikel Suchen Sie?");
				Artikelanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
				Artikelanlegen.setBounds(50, 11, 193, 31);
				artikelScreach.getContentPane().add(Artikelanlegen);
				
				// erstellt label für den artikelname 
				
				JLabel Artikelname = new JLabel("Geben Sie den Artikelname des Artikels ein :");
				Artikelname.setBounds(10, 68, 282, 14);
				artikelScreach.getContentPane().add(Artikelname);
				
				// erstellt eine texteingabe, um den artikel namen eintippen zu können 
				
				textArtikel = new JTextField();
				textArtikel.setBounds(81, 93, 104, 20);
				artikelScreach.getContentPane().add(textArtikel);
				textArtikel.setColumns(10);
				
				// erstellt label für falsche eingabe, vorerst leer in funktion wird gefüllt 
				
				JLabel FalscherArtikel = new JLabel("");
				FalscherArtikel.setForeground(Color.RED);
				FalscherArtikel.setBounds(20, 186, 260, 14);
				artikelScreach.getContentPane().add(FalscherArtikel);
				
				// erstellt button suchen + funktion suchen eines artikels im sortiment 
				
				JButton Suchen = new JButton("Suchen");
				Suchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String itemName = "";
						
						itemName = textArtikel.getText();
						
						try {
							if(checkNameKunde(itemName)){
								updateTabelle(storage.searchbyName(itemName));
								artikelScreach.setVisible(false);
							}
						} catch(InvalidItemNameException ex) {
							System.out.println(ex.getMessage());
							FalscherArtikel.setText("Bitte geben Sie ein gültigen Artikelnamen an!");
							textArtikel.setText(null);
							
							logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),"Es gab einen Fehler bei der Artikelsuche",false));
						}
					}
				});
				Suchen.setBounds(81, 143, 104, 32);
				artikelScreach.getContentPane().add(Suchen);
				
				
				
				
			}
		
			
		});
		ArtikelSuchen.setBounds(513, 11, 126, 23);
		sortiment.add(ArtikelSuchen);
		
		
		
		//erstellt button "Artikel sortieren Nummer"
		
		JButton ArtikelSoNum = new JButton("Artikel Sortieren Nummer");
		ArtikelSoNum.addActionListener(new ActionListener() {
			
			//Funktion zum öffnen eines neuen Fensters, um artikel zu sortieren zu können
			public void actionPerformed(ActionEvent e) {
				updateKundenTabelle(sortNummerArtikelliste(storage.getAllItems()));
			}
		});
		ArtikelSoNum.setBounds(52, 417, 195, 23);
		sortiment.add(ArtikelSoNum);
		
		
		
		// erstellt button " artikeln sortieren Namen"
		
		JButton ArtikelSoNam = new JButton("Artikel Sortieren Namen");
		ArtikelSoNam.addActionListener(new ActionListener() {
			
			//Funktion zum öffnen eines neuen Fensters, um artikel zu sortieren zu können
			
			public void actionPerformed(ActionEvent e) {
				updateKundenTabelle(sortNameArtikelliste(storage.getAllItems()));
			}
		});
		ArtikelSoNam.setBounds(51, 372, 196, 23);
		sortiment.add(ArtikelSoNam);
		
		// erstellt button " Artikel anzeigen" 
		
		JButton btnArtikelAnzeigen = new JButton("Artikel anzeigen");
		btnArtikelAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateKundenTabelle(storage.getAllItems());
			}
		});
		btnArtikelAnzeigen.setBounds(319, 11, 153, 23);
		sortiment.add(btnArtikelAnzeigen);
		
		// erstell scrollPane (falls scrollbar) 
		
		JScrollPane Layout1 = new JScrollPane();
		Layout1.setBounds(10, 58, 249, 291);
		sortiment.add(Layout1);
		
		// erstell "Raster" bzw ein JPanel (layout) , um buttons etc einfügen zu können
		
		JPanel Raster = new JPanel();
		Layout1.setViewportView(Raster);
		Raster.setLayout(null);  
		
		// erstellt ein label bei falscher eingabe , vorerst leer 
		
		JLabel FalscherArt = new JLabel("  ");
		FalscherArt.setBounds(65, 263, 170, 14);
		Raster.add(FalscherArt);
		
		// erstellt ein Zähler, um die Anzahl x hoch klicken zu können 
		
		JSpinner spinnerAnzahl = new JSpinner();
        spinnerAnzahl.setBounds(86, 166, 67, 23);
        Raster.add(spinnerAnzahl);

		
		// erstellt button " artikel hinzufügen"
		
		JButton ArtikelHinzufügen = new JButton("Artikel hinzuf\u00FCgen");
		ArtikelHinzufügen.addActionListener(new ActionListener() {
			
		// erstellt eine funktion um ware im warenkorb hinzuzufügen 
			
			public void actionPerformed(ActionEvent e) {
				
				String itemNummer = "";
				int iNummer;
				itemNummer = textArtikelNr.getText();
				iNummer = Integer.parseInt(itemNummer);
				
				String itemAmount ="";
				
				
				int iAmount = (Integer)spinnerAnzahl.getValue();
				
				try {
					if(checkWarenkorb(iNummer, iAmount)) {
						cart.addItem(iNummer, iAmount);
						cart.showCart();;
						FalscherArt.setForeground(Color.BLACK);
						FalscherArt.setText("Artikel hinzugefügt.");
						textArtikelNr.setText(null);
						spinnerAnzahl.setValue((Integer)0);
				
						logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),"Der Artikel: "+iNummer+ "wurde "+ iAmount+"x den Warenkorb hinzugefügt",false));
						// funktion zum laden in der Tabelle
						
						updateKundenWarenkorbTabelle(cart.getCart());
					}
				} catch (InvalidCartException ex) {
					FalscherArt.setForeground(Color.RED);
					FalscherArt.setText("     Falsche Eingabe!");
					textArtikelNr.setText(null);
					spinnerAnzahl.setValue((Integer)0);
					logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),"Fehlerhafte eingabe Beim hinzufügen zum Warenkorb", false));
			
					System.out.println(ex.getMessage());
				}
					   
			}
		});
		ArtikelHinzufügen.setBounds(49, 229, 152, 23);
		Raster.add(ArtikelHinzufügen);
		
		// erstellt label für anzahl 
		
		JLabel Anzahl = new JLabel("Anzahl :");
		Anzahl.setBounds(76, 141, 113, 14);
		Raster.add(Anzahl);
		
		// erstellt label für artikelnummer 
		
		JLabel ArtikelName = new JLabel("ArtikelNr:");
		ArtikelName.setBounds(76, 74, 113, 14);
		Raster.add(ArtikelName);
		
		// erstellt eine texteingabe um eine artikelNr eingeben zu können 
		
		textArtikelNr = new JTextField();
		textArtikelNr.setColumns(10);
		textArtikelNr.setBounds(76, 110, 96, 20);
		Raster.add(textArtikelNr);
		
		// erstellt label für die überschrift 
		
		JLabel ueberschrift = new JLabel("Artikel zum Warenkorb hinzuf\u00FCgen");
		ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ueberschrift.setBounds(20, 11, 227, 32);
		Raster.add(ueberschrift);
		

		
		
		/*--------------------------------------------------------------------------------------------------------------------------*/
		
		// erstellt warenkorb tab
		
		JPanel Warenkorb = new JPanel();
		Maintab.addTab("Warenkorb", null, Warenkorb, null);
		Warenkorb.setLayout(null);
		
		// erstellt das fenster in den Tab
		
		JScrollPane Layout3 = new JScrollPane();
		Layout3.setBounds(54, 21, 326, 374);
		Warenkorb.add(Layout3);
		
		// erstellt eine tabelle mit den jeweiligen eigenschaften
		
		tabelle1 = new JTable();
		tabelle1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Name", "Nr", "Anzahl", "St\u00FCckpreis", "Preis"}) {});
		
		// größe der einzelnen componenten in der tabelle
		
		tabelle1.getColumnModel().getColumn(0).setPreferredWidth(36);
		tabelle1.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelle1.getColumnModel().getColumn(2).setPreferredWidth(48);
		tabelle1.getColumnModel().getColumn(3).setPreferredWidth(64);
		tabelle1.getColumnModel().getColumn(4).setPreferredWidth(40);
		Layout3.setViewportView(tabelle1);
		
		//laden des Warenkorbs
		
		//updateKundenWarenkorbTabelle(warenkorb.getWarenkorb());
		
		// layout größe für Gesamtpreis
		
		JScrollPane Layout2 = new JScrollPane();
		Layout2.setBounds(52, 404, 219, 37);
		Warenkorb.add(Layout2);
		
		// Label für gesamtpreis
		
		JLabel gesamtPreis = new JLabel("Gesamtpreis :");
		Layout2.setRowHeaderView(gesamtPreis);
		gesamtPreis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// label für den Preis, der angezeigt wird
		
		gesamtPreisZahl = new JLabel("");
		gesamtPreisZahl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Layout2.setViewportView(gesamtPreisZahl);
		
		
		
		// button "kaufen" erstellt
		
		JButton kaufen = new JButton("Kaufen");
		kaufen.addActionListener(new ActionListener() {
			
		// funktion zum kaufen der Artikeln
			
			public void actionPerformed(ActionEvent e) {
				
				// erstellt neues fenster rechnung beim kaufen 
				
				Rechnung = new JFrame();
				Rechnung.setVisible(true);
				Rechnung.setTitle("Rechnung");
				Rechnung.setBounds(300, 150, 680, 564);
				Rechnung.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Rechnung.getContentPane().setLayout(null);
				
				// erstellt ein scrollpane , falls es scrollbar sein sollte ( rechnung zb sehr lang ) 
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(70, 141, 526, 283);
				Rechnung.getContentPane().add(scrollPane);
				
				// erstellt die tabelle 
				
				table = new JTable();
				table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Name", "Nr", "Anzahl", "St\u00FCckpreis", "Preis"}) {});
				
				// größe der einzelnen componenten in der tabell
				
				table.getColumnModel().getColumn(0).setPreferredWidth(48);
				table.getColumnModel().getColumn(1).setPreferredWidth(33);
				table.getColumnModel().getColumn(2).setPreferredWidth(53);
				table.getColumnModel().getColumn(3).setPreferredWidth(65);
				table.getColumnModel().getColumn(4).setPreferredWidth(45);
				scrollPane.setViewportView(table);
				
				// erstellt label für "Rechnung vom" 
				
				JLabel rechnungVom = new JLabel("Rechnung vom: ");
				rechnungVom.setFont(new Font("Tahoma", Font.PLAIN, 15));
				rechnungVom.setBounds(70, 102, 156, 28);
				Rechnung.getContentPane().add(rechnungVom);
				
				// erstellt label für das Datum 
				
				labelDatum = new JLabel(new Date().toGMTString());
				labelDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
				labelDatum.setBounds(181, 102, 180, 28);
				Rechnung.getContentPane().add(labelDatum);
				
				// erstellt label für den Bearbeiter
				
				labelBearbeiter = new JLabel("Bearbeiter:   ");
				labelBearbeiter.setBounds(388, 36, 73, 14);
				Rechnung.getContentPane().add(labelBearbeiter);
				
				// erstellt label für den Namen 
				
				labelName = new JLabel("Jendrik Bulke");
				labelName.setBounds(513, 36, 83, 14);
				Rechnung.getContentPane().add(labelName);
				
				// erstellt label für e mail 
				
				labelEmail = new JLabel("E-Mail:");
				labelEmail.setBounds(388, 61, 73, 14);
				Rechnung.getContentPane().add(labelEmail);
				
				// erstellt label für die mail 
				
				labelEmailName = new JLabel("Prog2@hs-bremen.de");
				labelEmailName.setBounds(471, 61, 133, 14);
				Rechnung.getContentPane().add(labelEmailName);
				
				// erstellt label für KundenNr
				
				labelKundeNr = new JLabel("KundenNr: ");
				labelKundeNr.setBounds(388, 83, 73, 14);
				Rechnung.getContentPane().add(labelKundeNr);
				
				// erstellt label für die Nr des Kundens
			
				labelNr = new JLabel("" + customerManagement.searchByNumber(currentCustomer).get(0).getCustomerNr());
				labelNr.setBounds(564, 83, 40, 14);
				Rechnung.getContentPane().add(labelNr);
				
				// erstell layout scrollPane für den Gesamtpreis , falls die zahlen zu groß werden 
				
				scollGesamtpreis = new JScrollPane();
				scollGesamtpreis.setBounds(374, 435, 221, 41);
				Rechnung.getContentPane().add(scollGesamtpreis);
				
				// erstellt das label für den Gesamtpreis
				
				labelGesamtpreis = new JLabel("Gesamtpreis: ");
				labelGesamtpreis.setFont(new Font("Tahoma", Font.PLAIN, 14));
				scollGesamtpreis.setRowHeaderView(labelGesamtpreis);
				
				// erstellt das label für die Zahlen ( der Preis) 
				
				labelPreis = new JLabel("");
				labelPreis.setForeground(Color.RED);
				scollGesamtpreis.setViewportView(labelPreis);
				
				// label für danke sagen
				
				labelDanke = new JLabel("Herzlichen Dank f\u00FCr Ihre Bestellung! ");
				labelDanke.setBounds(70, 445, 254, 14);
				Rechnung.getContentPane().add(labelDanke);
				
				// label für die mfg
				
				labelMfg = new JLabel("MfG Diyar, Milan und Marc");
				labelMfg.setBounds(80, 470, 215, 14);
				Rechnung.getContentPane().add(labelMfg);
				
				// label für namen vom kunden
				
				labelKundenname = new JLabel("Herr " + customerManagement.searchByNumber(currentCustomer).get(0).getLastname());
				labelKundenname.setBounds(70, 11, 141, 14);
				Rechnung.getContentPane().add(labelKundenname);
				
				// label für adresse vom kunden
				
				labelAdresse = new JLabel(customerManagement.searchByNumber(currentCustomer).get(0).getAdress());
				labelAdresse.setBounds(70, 36, 141, 14);
				Rechnung.getContentPane().add(labelAdresse);
				
				// label für addresse vom kunden !!!!!
				
				//labelAdresse1 = new JLabel(verkaufsstand.sucheNachNummer(aktuellerKunde).get(0).getStrasse()); !!!!
				//labelAdresse1.setBounds(70, 61, 141, 14);            !!!!
				//Rechnung.getContentPane().add(labelAdresse1);        !!!
				
				updateKundenRechnungTabelle(cart.getCart());
				// erstellt button "ok" 
				
				JButton btnNewButton = new JButton("OK");
				btnNewButton.addActionListener(new ActionListener() {
					
				// funktion zum schließen des Fensters 
					
					public void actionPerformed(ActionEvent e) {
					Rechnung.setVisible(false);
					logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Der Warenkorb wurde gekauft!", false));
					cart.buy();
					cart.remove();
					try {
						storage.writeItem();;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gesamtPreisZahl.setText(null);
					updateKundenWarenkorbTabelle(cart.getCart());
					updateKundenTabelle(storage.getAllItems());
					}
				});
				btnNewButton.setBounds(287, 484, 60, 23);
				Rechnung.getContentPane().add(btnNewButton);
			}
		});
		kaufen.setBounds(291, 406, 89, 35);
		Warenkorb.add(kaufen);
		

		
		
		// button " artikel entfernen" erstellt 
		
		JButton artikelEntfernen = new JButton("Artikel entfernen");
		
		// erstellt funktion um artikel entfernen zu können
		
		artikelEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String itemNumber = "";
				int iNumber;
				itemNumber = textAnzahl1.getText();
				iNumber = Integer.parseInt(itemNumber);
				
				String itemAmount ="";
				int iAmount;
				itemAmount = textArtikelNr1.getText();
				iAmount = Integer.parseInt(itemAmount);
				
				try {
					if(checkWarenkorbDelete(iNumber, iAmount)) {
						cart.delItem(iNumber, iAmount);
						logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Der Artikel: " + iNumber + "wurde " + iAmount + "x aus den Warenkorb entfernt", false));
						falscheEingabe.setForeground(Color.BLACK);
						falscheEingabe.setText("Artikel entfernt.");
						textArtikelNr1.setText(null);
						textAnzahl1.setText(null);
						updateKundenWarenkorbTabelle(cart.getCart());
					}
				} catch(InvalidCartException ex) {
					System.out.println(ex.getMessage());
					falscheEingabe.setForeground(Color.RED);
					logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Fehlerhafte eingabe beim Entfernen aus dem Warenkorb!", false));
					falscheEingabe.setText("Fehlerhafte Eingabe!");
					textArtikelNr1.setText(null);
					textAnzahl1.setText(null);
				}
				
			}
		});
		
		artikelEntfernen.setBounds(446, 372, 141, 23);
		Warenkorb.add(artikelEntfernen);
		
		// erstellt label für artikelnr
		
		JLabel artikelNr = new JLabel("ArtikelNr :");
		artikelNr.setBounds(489, 247, 59, 14);
		Warenkorb.add(artikelNr);
		
		// erstellt eine texteingabe für artikelnr
		
		textArtikelNr1 = new JTextField();
		textArtikelNr1.setBounds(467, 329, 96, 20);
		Warenkorb.add(textArtikelNr1);
		textArtikelNr1.setColumns(10);
		
		// erstellt ein label für anzahl
		
		JLabel anzahl = new JLabel(" Anzahl :");
		 anzahl.setBounds(489, 304, 59, 14);
		Warenkorb.add( anzahl);
		
		// erstellt eine texteingabe für anzahl 
		
		textAnzahl1 = new JTextField();
		textAnzahl1.setColumns(10);
		textAnzahl1.setBounds(467, 272, 96, 20);
		Warenkorb.add(textAnzahl1);
		
		falscheEingabe = new JLabel("  ");
		falscheEingabe.setForeground(Color.RED);
		falscheEingabe.setBounds(460, 404, 141, 14);
		Warenkorb.add(falscheEingabe);
		
		// erstellt layout größe für artikel entfernen
		
		JScrollPane Layout4 = new JScrollPane();
		Layout4.setBounds(422, 211, 184, 226);
		Warenkorb.add(Layout4);
		
		
		
		
		
		
		
		// erstell button "warenkorb leeren" 
		
		JButton warenkorbLeeren = new JButton("Warenkorb leeren");
		warenkorbLeeren.addActionListener(new ActionListener() {
			
			// funktion zum leeren des warenkorbs
			
			public void actionPerformed(ActionEvent e) {
				logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Hat den Warenkorb geleert!", false));
				cart.remove();
				gesamtPreisZahl.setText(null);
				updateKundenWarenkorbTabelle(cart.getCart());
			
			}
		});
		warenkorbLeeren.setBounds(446, 133, 141, 23);
		Warenkorb.add(warenkorbLeeren);
		
		
		
		
		// erstellt button "artikel suchen"
		
		JButton artikelSuchen = new JButton("Artikel suchen");
		artikelSuchen.addActionListener(new ActionListener() {
			
			// funktion zum suchen der Artikeln im warenkorb 
			
			public void actionPerformed(ActionEvent e) {
				
				// erstellt ein neues fenster artikel suchen 
				
				artikelScreach = new JFrame();
				artikelScreach.setVisible(true);
				artikelScreach.setTitle("Artikeln suchen");
				artikelScreach.setBounds(970, 150, 304, 246);
				artikelScreach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				artikelScreach.getContentPane().setLayout(null);
				
				// erstellt label artikel suchen 
				
				JLabel Artikelanlegen = new JLabel("Welchen Artikel Suchen Sie?");
				Artikelanlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
				Artikelanlegen.setBounds(50, 11, 193, 31);
				artikelScreach.getContentPane().add(Artikelanlegen);
				
				// erstellt label artikelnamen eingeben 
				
				JLabel Artikelname = new JLabel("Geben Sie den Artikelname des Artikels ein :");
				Artikelname.setBounds(10, 68, 282, 14);
				artikelScreach.getContentPane().add(Artikelname);
				
				// erstellt texteingabe , um den namen des Artikel eintippen zu können 
				
				textArtikel = new JTextField();
				textArtikel.setBounds(81, 93, 104, 20);
				artikelScreach.getContentPane().add(textArtikel);
				textArtikel.setColumns(10);
				
				// erstellt label bei falscher eingabe , vorerst leer 
				
				JLabel FalscherArtikel = new JLabel(" ");
				FalscherArtikel.setForeground(Color.RED);
				FalscherArtikel.setBounds(70, 186, 240, 14);
				artikelScreach.getContentPane().add(FalscherArtikel);
				
				// erstellt button suchen + funktion zum suchen eines artikels 
				
				JButton Suchen = new JButton("Suchen");
				Suchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String itemName = "";
						
						itemName = textArtikel.getText();
						
						try {
							if(checkWarenkorbSearch(itemName)) {
								updateKundenWarenkorbTabelle(cart.searchByName(itemName));
								artikelScreach.setVisible(false);
							}
						} catch(InvalidCartNameException ex) {
							System.out.println(ex.getMessage());
							FalscherArtikel.setText("Ungültiger Name!");
							textArtikel.setText(null);
							logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Fehlerhafte eingabe in der Suche im Warenkorb!", false));
						}
						
					}
				});
				Suchen.setBounds(81, 143, 104, 32);
				artikelScreach.getContentPane().add(Suchen);
				
				
				
				
			}
		});
		
		
		artikelSuchen.setBounds(446, 24, 141, 23);
		Warenkorb.add(artikelSuchen);
		
		
		
		
		// erstellt button "artikel anzeigen" 
		
		JButton artikelAnzeigen = new JButton("Artikel anzeigen");
		artikelAnzeigen.addActionListener(new ActionListener() {
			
			// funktion zum anzeigen der Artikeln, nachdem man ein artikel gesucht hat ( sinnig)
			
			public void actionPerformed(ActionEvent e) {
				updateKundenWarenkorbTabelle(cart.getCart());
			}
		});
		artikelAnzeigen.setBounds(446, 72, 141, 23);
		Warenkorb.add(artikelAnzeigen);
	
		
		// erstellt button "ausloggen" + funktion ausloggen 
		
		JButton btnNewButton = new JButton("Ausloggen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gibMenueAus();
				kundenMenue.setVisible(false);
				logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Hat sich abgemeldet", false));
			}
		});
		btnNewButton.setBounds(549, 506, 119, 23);
		kundenMenue.getContentPane().add(btnNewButton);
		
		// erstellt ein heftige label für die krassen creator des Projekts xDD 
		
		JLabel lblNewLabel = new JLabel("E-Shop creater : Mario Schulz, Bernd Henke, Diyar Aydin");
		lblNewLabel.setBounds(40, 510, 442, 14);
		kundenMenue.getContentPane().add(lblNewLabel);
		
	}                 
	
	
	/**
	 * verwendet von: gibMenueAus
	 * Beschriebung: GUI fenster in dem Gewählt werden kann, ob man ein Mitarbeiter oder Kunde ist.
	 */
	public void shopAnmeldung() {
		
				//Fenster e shop erstellen
		
				shopAnmeldung = new JFrame();
				shopAnmeldung.setTitle("E-Shop");
				shopAnmeldung.setBounds(500, 300, 418, 269);
				shopAnmeldung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				shopAnmeldung.getContentPane().setLayout(null);
				shopAnmeldung.setVisible(true);
				
				//Label Kunde oder Mitarbeiter
				
				JLabel lblNewLabel_1 = new JLabel("Sind sie ein Kunde oder Mitarbeiter?");
				lblNewLabel_1.setBounds(93, 88, 241, 14);
				shopAnmeldung.getContentPane().add(lblNewLabel_1);
				
				//button Mitarbeiter + funktion zum mitarbeiterfenster 
				
				JButton btnNewButton = new JButton("Mitarbeiter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						shopAnmeldung.setVisible(false);
						shopAnmeldungMitarbeiter();
						
					}
				});
				
				btnNewButton.setBounds(225, 165, 105, 23);
				shopAnmeldung.getContentPane().add(btnNewButton);
				
				
				//button Kunde + funktion zum kundenfenster
				
				JButton btnNewButton_1 = new JButton("Kunde");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						shopAnmeldung.setVisible(false);
						shopAnmeldungKunde();
						
							}
				});
				
				btnNewButton_1.setBounds(73, 165, 105, 23);
				shopAnmeldung.getContentPane().add(btnNewButton_1);
	}
	
	
	
	
	/**
	 * verwendet von: shopAnmeldung
	 * Beschriebung: Erzeugt die GUI in der Benutzername und Passwort vom Mitarbeiter eingetragen werden.
	 */
	public void shopAnmeldungMitarbeiter() {
		
		// erstellt neue fenster für mitarbeiter 
		
		shopAnmeldungMitarbeiter = new JFrame();
		shopAnmeldungMitarbeiter.setTitle("Mitarbeiterfenster");
		shopAnmeldungMitarbeiter.setBounds(500, 300, 320, 204);
		shopAnmeldungMitarbeiter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopAnmeldungMitarbeiter.getContentPane().setLayout(null);
		shopAnmeldungMitarbeiter.setVisible(true);
		
		// erstellt Benutzernametext + eingabetext
		
		JLabel Benutzername = new JLabel("Benutzername :");
		Benutzername.setBounds(36, 35, 103, 14);
		shopAnmeldungMitarbeiter.getContentPane().add(Benutzername);
		
		textID = new JTextField();
		textID.setBounds(36, 60, 96, 20);
		shopAnmeldungMitarbeiter.getContentPane().add(textID);
		textID.setColumns(10);
		
		// erstellt Passworttext + eingabetext
		
		JLabel Passwort = new JLabel("Passwort :");
		Passwort.setBounds(167, 35, 103, 14);
		shopAnmeldungMitarbeiter.getContentPane().add(Passwort);
		
		textPasswort = new JPasswordField();
		textPasswort.setColumns(10);
		textPasswort.setBounds(167, 60, 96, 20);
		shopAnmeldungMitarbeiter.getContentPane().add(textPasswort);
		
		FalscheIDundPw = new JLabel("    ");
		FalscheIDundPw.setForeground(Color.RED);
		FalscheIDundPw.setBounds(32, 91, 240, 14);
		shopAnmeldungMitarbeiter.getContentPane().add(FalscheIDundPw);
		
		// erstellt Button + Funktion zum überprüfen der Anmeldedaten
		
		JButton Anmelden = new JButton("Login");
		Anmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstname = "";
				String password = "";
				boolean einloggen = false;
				List<Employee> liste;
				liste = employeeManagement.getAllEmployee();
				
				firstname = textID.getText();
				System.out.print(firstname);
				
				password = textPasswort.getText();
				System.out.print(password);
				
				LoginEmployee a = new LoginEmployee();
				try {
					a.login(employeeManagement.getAllEmployee(), firstname, password);
					currentEmployee = a.getNumber();
					System.out.println(currentEmployee);
					mitarbeiterMenue();
					shopAnmeldungMitarbeiter.setVisible(false);
					logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(a.getNumber()).get(0), "hat sich angemeldet.", true));
					logmanager.outputChangelog();
					
					
				} catch(WrongLoginInformationsException ex) {
					FalscheIDundPw.setText("Benutzername oder Passwort sind falsch!");
					logmanager.addChangelog(new Changelog(system, "Fehler bei der Anmeldung!", true));
				}               
			}
		});
		
		Anmelden.setBounds(104, 115, 103, 23);
		shopAnmeldungMitarbeiter.getContentPane().add(Anmelden); 
	}
	
	
	/**
	 * verwendet von: shopAnmeldung
	 * Beschriebung: erzeugt die GUI in der Benutzername und Passwort für einen Kunden eingetragen werden können.
	 */
	public void shopAnmeldungKunde() {
		
		// erstellt neues fennster für die kunden 
		
		shopAnmeldungKunde = new JFrame();
		shopAnmeldungKunde.setTitle("Kundenfenster");
		shopAnmeldungKunde.setBounds(500, 300, 320, 204);
		shopAnmeldungKunde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopAnmeldungKunde.getContentPane().setLayout(null);
		shopAnmeldungKunde.setVisible(true);
		
		// erstellt Benutzernametext + eingabetext
		
		JLabel Benutzername = new JLabel("Benutzername :");
		Benutzername.setBounds(36, 35, 103, 14);
		shopAnmeldungKunde.getContentPane().add(Benutzername);
		
		textID = new JTextField();
		textID.setBounds(36, 60, 96, 20);
		shopAnmeldungKunde.getContentPane().add(textID);
		textID.setColumns(10);
		
		// erstellt Passworttext + eingabetext
		
		JLabel Passwort = new JLabel("Passwort :");
		Passwort.setBounds(167, 35, 103, 14);
		shopAnmeldungKunde.getContentPane().add(Passwort);
		
		textPasswort = new JPasswordField();
		textPasswort.setColumns(10);
		textPasswort.setBounds(167, 60, 96, 20);
		shopAnmeldungKunde.getContentPane().add(textPasswort);
		
		// erstellt ein Text, falls man falsche id und pw eingegeben hat 
		
		FalscheIDundPw = new JLabel("    ");
		FalscheIDundPw.setForeground(Color.RED);
		FalscheIDundPw.setBounds(32, 91, 240, 14);
		shopAnmeldungKunde.getContentPane().add(FalscheIDundPw); 
		
		// erstellt Button + Funktion zum überprüfen der Anmeldedaten
		
		JButton Anmelden = new JButton("Anmelden");
		Anmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstname = "";
				String password = "";
				boolean einloggen = false;
				List<Customer> customerList;
				customerList = customerManagement.getAllCustomer();
				
				firstname = textID.getText();
				System.out.print(firstname);
				
				password = textPasswort.getText();
				System.out.print(password);
				
				LoginCustomer a = new LoginCustomer();
				try {
					a.login(customerManagement.getAllCustomer(), firstname, password);
					currentCustomer = a.getNumber();
					System.out.println("|"+currentCustomer+"|");
					kundenMenue();
					shopAnmeldungKunde.setVisible(false);                                   //a Anmendungkunde ??
					logmanager.addChangelog(new Changelog(customerManagement.searchByNumber(a.getNumber()).get(0), "hat sich angemeldet.", false));
					
				} catch(WrongLoginInformationsException ex) {
					FalscheIDundPw.setText("Benutzername oder Passwort sind falsch!");
					logmanager.addChangelog(new Changelog(system, "Fehler bei der Anmeldung!", true));
				}               
				
			}
		});
		
		Anmelden.setBounds(104, 115, 103, 23);
		shopAnmeldungKunde.getContentPane().add(Anmelden);
		
	}
	
	
	/**
	 * Verwendet von: MitarbeiterMenue & gibMenueAus
	 * Beschreibung: Die GUI für die Kundenregistrierung wird erzeugt.
	 * @param b gibt an ob man schon angemeldet ist oder ob das Programm noch vor der Anmeldung ist
	 */
	public void shopKundeRegistrierung(boolean b) {
		
		 // erstellt das Fenster für die Registrierung
		
		 shopKundeRegistrierung = new JFrame();
		 shopKundeRegistrierung.setTitle("Registrierungsfenster");
		 shopKundeRegistrierung.setBounds(500, 300, 397, 394);
		 shopKundeRegistrierung.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 shopKundeRegistrierung.getContentPane().setLayout(null);
		 shopKundeRegistrierung.setVisible(true);
		 
		// erstellt Vornametext + eingabetext
		 
		JLabel Vorname = new JLabel("Vorname:");
		Vorname.setBounds(24, 38, 115, 14);
		shopKundeRegistrierung.getContentPane().add(Vorname);
		
		textVorname = new JTextField();
		textVorname.setColumns(10);
		textVorname.setBounds(24, 63, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textVorname);
		
		// erstellt Nachnametext + eingabetext
		
		JLabel Nachname = new JLabel("Nachname:");
		Nachname.setBounds(24, 94, 115, 14);
		shopKundeRegistrierung.getContentPane().add(Nachname);
		
		textNachname = new JTextField();
		textNachname.setColumns(10);
		textNachname.setBounds(24, 119, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textNachname);
		
		// erstellt Wohnorttext + eingabetext
		
		JLabel Wohnort = new JLabel("Wohnort:");
		Wohnort.setBounds(171, 38, 115, 14);
		shopKundeRegistrierung.getContentPane().add(Wohnort);
		
		textWohnort = new JTextField();
		textWohnort.setColumns(10);
		textWohnort.setBounds(171, 63, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textWohnort);
		
		// erstellt Straßetext + eingabetext
		
		JLabel Straße = new JLabel("Stra\u00DFe:");
		Straße.setBounds(171, 94, 115, 14);
		shopKundeRegistrierung.getContentPane().add(Straße);
		
		textStraße = new JTextField();
		textStraße.setColumns(10);
		textStraße.setBounds(171, 119, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textStraße);
		
		
		// erstellt PLZtext + eingabetext
		
		JLabel PLZ = new JLabel("PLZ:");
		PLZ.setBounds(296, 38, 95, 14);
		shopKundeRegistrierung.getContentPane().add(PLZ);
		
		textplz = new JTextField();
		textplz.setColumns(10);
		textplz.setBounds(296, 63, 57, 20);
		shopKundeRegistrierung.getContentPane().add(textplz);
		
		
		// erstellt Benutzernametext + eingabetext
		
		JLabel Benutzername = new JLabel("Geben Sie einen Benutzernamen ein :");
		Benutzername.setBounds(24, 194, 221, 14);
		shopKundeRegistrierung.getContentPane().add(Benutzername);
		
		textBenutzername = new JTextField();
		textBenutzername.setColumns(10);
		textBenutzername.setBounds(24, 219, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textBenutzername);
		
		// erstellt Passworttext + eingabetext
		
		JLabel Passwort = new JLabel("Legen Sie einen Passwort fest : ");
		Passwort.setBounds(24, 251, 221, 14);
		shopKundeRegistrierung.getContentPane().add(Passwort);
		
		textPasswort = new JPasswordField();
		textPasswort.setColumns(10);
		textPasswort.setBounds(24, 272, 115, 20);
		shopKundeRegistrierung.getContentPane().add(textPasswort);
		
		// erstellt KundenNrtext + eingabetext
		
		JLabel KundeNr = new JLabel("KundenNr :");
		KundeNr.setBounds(250, 219, 121, 14);
		shopKundeRegistrierung.getContentPane().add(KundeNr);
		
		textKundeNr = new JTextField(""+newNumberKunde(customerManagement.getAllCustomer()));
		textKundeNr.setEditable(false);
		textKundeNr.setColumns(10);
		textKundeNr.setBounds(264, 248, 43, 20);
		shopKundeRegistrierung.getContentPane().add(textKundeNr);
		
		
		//  bei bereits existernder KundenNr!
		
		FalscheKundenNr = new JLabel("      ");
		FalscheKundenNr.setForeground(Color.RED);
		FalscheKundenNr.setBounds(215, 279, 180, 14);
		shopKundeRegistrierung.getContentPane().add(FalscheKundenNr);  
		
		
		//Erstellt Button
		
		JButton Registrieren = new JButton("Registrieren!");
		Registrieren.setBounds(141, 325, 115, 23);
		shopKundeRegistrierung.getContentPane().add(Registrieren);
		
		
		/*Funktion zum Speichern der Daten beim klick auf button
		 * bzw Registrierung eines neuen Kunden*/
		
		
		Registrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String firstname = "";
				String lastname = "";
				String password = "";
				String adress= "";
				String customerNumber = "";
				int cNumber;
				List<Customer> customerList;
				customerList = customerManagement.getAllCustomer();
				
				// zumm lesen der texteingabe 
				
				firstname = textVorname.getText();
				System.out.print(firstname);
				
				lastname = textNachname.getText();
				System.out.print(lastname);
				
				adress = textWohnort.getText();
				System.out.print(adress);
				
				
				password = textPasswort.getText();
				System.out.print(password);
				
				customerNumber = textKundeNr.getText();
				System.out.print(customerNumber);
				
				cNumber = Integer.parseInt(customerNumber);
			
				
				if(!textBenutzername.getText().isEmpty()) {
					customerManagement.addCustomer(firstname, lastname, password, adress, cNumber);
					try {
						customerManagement.writeCustomer();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					shopKundeRegistrierung.setVisible(false);
					System.out.println("Kunde wurde angelegt.");
					if(!b) {
						shopAnmeldungKunde();
						logmanager.addChangelog(new Changelog(system, "Der Kunde: "+ firstname + " | " + cNumber + " wurde angelegt.", true));
					} else {
						logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Der Kunde: "+ firstname + " | " + cNumber+ " wurde angelegt.", true));
					}
					
				} else {
					FalscheKundenNr.setText("Bitte füllen Sie alle Felder!");
				}
			}

			
				
			
		});
		
	}
	
	
	
	/**
	 * verwendet von: MitarbeiterMenue
	 * beschriebung: erzeugt das Registrierungsfenster für einen neuen Mitarbeiter
	 * @param b gibt an ob die Funktion aus dem MitarbeiterMenue aufgerufen wird oder nicht
	 */
	public void shopMitarbeiterRegistrierung(boolean b) {
		
		 // erstellt das Fenster für die Registrierung
		
		 shopMitarbeiterRegistrierung = new JFrame();
		 shopMitarbeiterRegistrierung.setTitle("Mitarbeiter anlegen");
		 shopMitarbeiterRegistrierung.setBounds(500, 300, 397, 394);
		 shopMitarbeiterRegistrierung.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 shopMitarbeiterRegistrierung.getContentPane().setLayout(null);
		 shopMitarbeiterRegistrierung.setVisible(true);
		 
		// erstellt Vornametext + eingabetext
		 
		JLabel Firstname = new JLabel("Firstname:");
		Firstname.setBounds(24, 38, 115, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Firstname);
		
		textVorname = new JTextField();
		textVorname.setColumns(10);
		textVorname.setBounds(24, 63, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textVorname);
		
		// erstellt Nachnametext + eingabetext
		
		JLabel Lastname = new JLabel("Lastname:");
		Lastname.setBounds(24, 94, 115, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Lastname);
		
		textNachname = new JTextField();
		textNachname.setColumns(10);
		textNachname.setBounds(24, 119, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textNachname);
		
		// erstellt Wohnorttext + eingabetext
		
		JLabel Wohnort = new JLabel("Wohnort:");
		Wohnort.setBounds(171, 38, 115, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Wohnort);
		
		textWohnort = new JTextField();
		textWohnort.setColumns(10);
		textWohnort.setBounds(171, 63, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textWohnort);
		
		// erstellt Straßetext + eingabetext
		
		JLabel Straße = new JLabel("Stra\u00DFe:");
		Straße.setBounds(171, 94, 115, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Straße);
		
		textStraße = new JTextField();
		textStraße.setColumns(10);
		textStraße.setBounds(171, 119, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textStraße);
		
		
		// erstellt PLZtext + eingabetext
		
		JLabel PLZ = new JLabel("PLZ:");
		PLZ.setBounds(296, 38, 95, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(PLZ);
		
		textplz = new JTextField();
		textplz.setColumns(10);
		textplz.setBounds(296, 63, 57, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textplz);
		
	
		
		// erstellt Benutzernametext + eingabetext
		
		JLabel Username = new JLabel("Geben Sie einen Benutzernamen ein :");
		Username.setBounds(24, 194, 221, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Username);
		
		textBenutzername = new JTextField();
		textBenutzername.setColumns(10);
		textBenutzername.setBounds(24, 219, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textBenutzername);
		
		// erstellt Passworttext + eingabetext
		
		JLabel Password = new JLabel("Legen Sie einen Passwort fest : ");
		Password.setBounds(24, 251, 221, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(Password);
		
		textPasswort = new JPasswordField();
		textPasswort.setColumns(10);
		textPasswort.setBounds(24, 272, 115, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textPasswort);
		
		// erstellt MitarbeiterNrtext + eingabetext
		
		JLabel KundeNr = new JLabel("MitarbeiterNr :");
		KundeNr.setBounds(232, 219, 121, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(KundeNr);
		
		textMitarbeiterNr = new JTextField(""+newNumberMitarbeiter(employeeManagement.getAllEmployee()));
		textMitarbeiterNr.setEditable(false);
		textMitarbeiterNr.setColumns(10);
		textMitarbeiterNr.setBounds(264, 248, 43, 20);
		shopMitarbeiterRegistrierung.getContentPane().add(textMitarbeiterNr);
		
		//  bei bereits existernder MitarbeiterNr!
		
		FalscheMitarbeiterNr = new JLabel("      ");
		FalscheMitarbeiterNr.setForeground(Color.RED);
		FalscheMitarbeiterNr.setBounds(215, 279, 180, 14);
		shopMitarbeiterRegistrierung.getContentPane().add(FalscheMitarbeiterNr);  
		
		
		//Erstellt Button
		
		JButton Registrieren = new JButton("Mitarbeiter anlegen");
		Registrieren.setBounds(125, 325, 150, 23);
		shopMitarbeiterRegistrierung.getContentPane().add(Registrieren);
		
		
		/*Funktion zum Speichern der Daten beim klick auf button
		 * bzw Registrierung eines neuen Kunden*/
		
		
		Registrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String firstname = "";
				String lastname = "";
				String password= "";
				String employeeNumber = "";
				int eNumber;
				/*List<Kunde> liste;
				liste = verkaufsstand.gibAlleKunden();*/
				
				
				firstname = textVorname.getText();
				System.out.print(firstname);
				
				lastname = textNachname.getText();
				System.out.print(lastname);
				
				
				
				
				
				
				password = textPasswort.getText();
				System.out.print(password);
				
				employeeNumber = textMitarbeiterNr.getText();
				System.out.print(employeeNumber);
				
				eNumber = Integer.parseInt(employeeNumber);
				
				
				if(!textBenutzername.getText().isEmpty()) {
					employeeManagement.addEmployee(firstname, lastname, password, eNumber);
					try {
						employeeManagement.writeEmployee();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					logmanager.addChangelog(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Der Mitarbeiter: "+ firstname+ " | " + eNumber + " wurde angelegt.", true));
					shopMitarbeiterRegistrierung.setVisible(false);
					System.out.println("Mitarbeiter wurde angelegt.");
					if(!b) {
						shopAnmeldungMitarbeiter();
						
					}
					
				} else {
					FalscheMitarbeiterNr.setText("Bitte füllen sie alle Felder aus!");
				}
			}

			
				
			
		});
		
	}
	
	/**
	 * Beschreibung: wird nicht mehr benötigt, hat in der CUI eine Liste ausgegeben
	 * @param liste ist die Liste die ausgegegeben werden soll
	 */
	private void gibArtikellisteAus(List<Item> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Item a : itemList) {
				System.out.println(a);
			}
		}
	}
	
	
	/**
	 * verwendet von: MitarbeiterMenue & KundenMenue
	 * Beschriebung: Sortiert mit hilfe von einen Comperator die Artikelliste nach Namen
	 * 
	 * @param liste ist die Liste die Sortier werden woll
	 * @return ist die Sortiere liste
	 */
	private List<Item> sortNameArtikelliste(List<Item> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			
			Collections.sort(liste, new Comparator<Item>() {
				  @Override
				  public int compare(Item u1, Item u2) {
				    return u1.getName().compareTo(u2.getName());
				  }
				});
				
			}
		return liste;
		}
	
	/**
	 * verwendet von: MitarbeiterMenue & KundenMenue
	 * Beschriebung: Sortiert mit hilfe eines Comperators die Artikelliste nach Nummer
	 * @param liste ist die Artikelliste
	 * @return gibt die Sortiere Liste zurück
	 */
	private List<Item> sortNummerArtikelliste(List<Item> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			
			Collections.sort(liste, new Comparator<Item>() {
				  @Override
				  public int compare(Item u1, Item u2) {
				  
					  int x = Integer.compare(u1.getNumber(),u2.getNumber());
					  
				    return x;
				  }
				});
				
			}
		return liste;
	}
	
	private List<Changelog> sortDateChangelogliste(List<Changelog> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			
			Collections.sort(liste, new Comparator<Changelog>() {
				  @Override
				  public int compare(Changelog u1, Changelog u2) {
					  return u1.getTime().compareTo(u2.getTime());
				  }
			});
				
		}
		return liste;
	}
	
	
	
	
	/**
	 * Beschriebung: wird nicht mehr benötigt, kommt aus der CUI
	 * @param liste
	 */
	private void gibMitarbeiterlisteAus(List<Employee> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty");
		} else {
			for (Employee e : employeeList) {
				System.out.println(e);
			}
		}
	}
	/**
	 * Beschreibgung: wird nicht mehr benötigt, kommt aus der CUI
	 * @param liste
	 */
	private void gibKundenlisteAus(List<Customer>list) {
		if (list.isEmpty()) {
			System.out.println("List is empty");
		} else {
			for (Customer c : list) {
				System.out.println(c);
			}
		}
	}

	
	public int newNumberKunde(List<Customer> liste) {
		//gibt die höchste Kundenummer aus, er vergleicht alle Kunden in der liste mit GetKundenNr
		Customer maxByNumber = liste.stream().max(Comparator.comparing(Customer::getCustomerNr)).orElseThrow(NoSuchElementException::new);
		//die Höchste KundenNr +1 
		int newNumber = maxByNumber.getCustomerNr() + 1;
		//gibt die neue NUmmer zurück
		return newNumber;
	}
	
	public int newNumberMitarbeiter(List<Employee> liste) {
		Employee maxByNumber = liste.stream().max(Comparator.comparing(Employee::getEmployeeNr)).orElseThrow(NoSuchElementException::new);
		int newNumber = maxByNumber.getEmployeeNr() + 1;
		return newNumber;
	}
	
	/**
	 * Beschreibung: startet das Programm. Erstellt die GUI. Speichert alle Listen bevor das Programm beendet wird.
	 * @param args
	 */
	public static void main(String[] args) {
		
		ShopClientGUI gui;
		
		gui = new ShopClientGUI("Art","Mit","Kund","Log");
		
		
		gui.gibMenueAus();
		
		// erstellt das datum mit der heutigen zeit
		
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
        String strDate = dateFormat.format(date);  
        System.out.println("Converted String: " + strDate);  
		
        //systembenutzer für den Changelog
        employeeManagement.deleteEmployee(1);
        system = employeeManagement.addEmployee("system", "Admin", "123456789",1);
        
        
        //speichert alle Daten vor dem Beenden des Programmes
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	System.out.println("test");
	            try {
					logmanager.writeData("Log");
					System.out.println("hat geklappt");
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("hat nicht geklappt");
				}
	            try {
					storage.writeItem();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            try {
					customerManagement.writeCustomer();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            try {
					employeeManagement.writeEmployee();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }, "Shutdown-thread"));
	
	}

	
	
}
