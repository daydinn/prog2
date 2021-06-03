package Persistance;

import java.io.IOException;

import valueObjects.Changelog;

public interface PersistenceManager {


	

public void openForReading(String file) throws IOException;

public void openForWriting(String file) throws IOException; 

public boolean close();
		

public valueObjects.Item loadItem() throws IOException;

public boolean saveItem(valueObjects.Item i) throws IOException ;

public valueObjects.Employee loadEmployee() throws IOException;

public boolean saveEmployee(valueObjects.Employee e) throws IOException ;

public valueObjects.Customer loadCustomer() throws IOException;

public boolean saveCustomer(valueObjects.Customer c) throws IOException ;

public String readLog() throws IOException;
			
public boolean saveLog(String log) throws IOException ;

public Changelog loadChangelogNew() throws IOException;

public boolean saveChangelog(Changelog c) throws IOException;

		}





















