package Persistence;


import java.io.IOException;

import Valueobjects.Changelog;


public interface PersistenceManager {


public void openForReading(String datasource) throws IOException;

public void openForWriting(String datasource) throws IOException; 

public boolean close();


public Valueobjects.Item loadItems() throws IOException;

public boolean saveItems(Valueobjects.Item i) throws IOException ;

public Valueobjects.Employee loadEmployee() throws IOException;

public boolean saveEmployee(Valueobjects.Employee e) throws IOException ;

public Valueobjects.Customer loadCustomer() throws IOException;

public boolean saveCustomer(Valueobjects.Customer c) throws IOException ;

public String readLog() throws IOException;

public boolean saveLog(String log) throws IOException ;

public Changelog loadChangelogNew() throws IOException;

public boolean saveChangelog(Changelog c) throws IOException;

}
