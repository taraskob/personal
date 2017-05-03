import javax.swing.*;

class PersonMain {

    public static void main(String[] args) {
        if (args.length == 0)
            return;
        if (!args[0].equals("XML") && !args[0].equals("DB"))
            return;
        Controller ctrl = Controller.getController(getStorage(args[0]));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new PersonForm(getStorage(args[0]).read(), ctrl);
            }
        });
    }

    static PersonStorage getStorage(String dataSource) {
        PersonStorage PersonObj = null;
        if (dataSource.equals("XML"))
            PersonObj = new PersonXML("person.xml");
        if (dataSource.equals("DB"))
            PersonObj = new PersonDB();
        return PersonObj;

    }
}
