import java.util.ArrayList;
public class PersonXMLTest {
    boolean testResult = true;

    @org.junit.Test
    public void save() throws Exception {
        Person person = new Person();
        PersonStorage PersonObj = new PersonXML("person.xml");
        ArrayList<Person> personList = PersonObj.read();
        ;
        personList.add(person);
        PersonObj.save(personList);
        personList = PersonObj.read();
        if (personList.size() != 11)
            testResult = false;
        if (testResult)
            System.out.println("Test is failed");

        else
            System.out.println("Test is OK");
    }

    @org.junit.Test
    public void read() throws Exception {

        ArrayList<Person> personList = new ArrayList<>();
        PersonStorage PersonObj = new PersonXML("person.xml");
        personList = PersonObj.read();
        if (personList.size() != 10)
            testResult = false;
    }
}