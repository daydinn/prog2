package Domain;

import java.io.IOException;
import java.util.*;

import Exceptions.InvalidNameChangelogException;
import Persistance.*;
import valueObjects.*;

public class ChangelogManager {

private List<Changelog> changelog = new ArrayList<Changelog>();
private PersistenceManager pm = new FilePersistenceManager();


public void readData(String data) throws IOException{
pm.openForReading(data+"L.txt"); // ?
Changelog c;

do {
c = pm.loadChangelogNew();
if(c != null) {
addChangelog(c);
}
}while(c != null);


}

public void writeDate(String data) throws IOException {
pm.openForWriting(data+"L.txt");
for(Changelog c : changelog) {
pm.saveChangelog(c);
}
pm.close();
}

public void addChangelog(Changelog c) {
changelog.add(c);
System.out.println(c);}

//gibt den aktuellen changelog aus
public List<Changelog> getChangelog(){
return new ArrayList<Changelog>(changelog);
}

//gibt den Changelog in der console aus
public void outputChangelog() {
System.out.println(changelog);
}

//durchsucht den Changelog nach einem Namen undgibt alle Einträge mit den Namen zurück.
// Die Fehlerhafte Eingaben werden als Exception geworfen

public List<Changelog> searchChangelogName(String name) throws InvalidNameChangelogException{
List<Changelog> searchResult = new ArrayList<Changelog>();
Iterator<Changelog> iter = changelog.iterator();

while(iter.hasNext()) {
Changelog c = iter.next();
if(c.getTyp()) {
if(c.getEmployee().getFirstname().equals(name)) {
searchResult.add(c);
}


}else {
if(c.getCustomer().getFirstname().equals(name)) {
searchResult.add(c);
}
}
}
if(searchResult.isEmpty()) {
throw new InvalidNameChangelogException();
}
return searchResult;





}
}
