import java.util.ArrayList;

interface PersonStorage {
ArrayList<Person> read();
void update(Person person, ArrayList<Person> personList);
void add(Person person);
void delete(Person person, ArrayList<Person> personList);
void save(ArrayList<Person> personList);
}
