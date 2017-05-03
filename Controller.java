import java.util.ArrayList;

class Controller {
    static PersonStorage storage;

    private Controller() {

    }


    void add(Person person) {
        storage.add(person);
    }

    void update(Person person, ArrayList<Person> personList) {
        storage.update(person, personList);
    }

    ;

    void delete(Person person, ArrayList<Person> personList) {
        storage.delete(person, personList);
    }


    private static class ControllerHandler {
        private static Controller CTRL;

        static {

            CTRL = new Controller();
        }
    }

    public static Controller getController(PersonStorage PersonObj) {
        storage = PersonObj;
        return ControllerHandler.CTRL;
    }
}
