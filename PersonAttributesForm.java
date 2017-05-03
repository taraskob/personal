import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class PersonAttributesForm {
    JFrame jfrm;
    DateFormat df;
    JLabel jlab;
    JTextField id;
    JTextField firstname;
    JTextField surname;
    JFormattedTextField birthday;
    JTextField city;
    JTextField street;
    JTextField zip;
    JTextField homephone;
    JTextField cellphone;
    JTextField workphone;
    JButton jbtnSave;

    PersonAttributesForm(Person person, PersonForm personform, Controller ctrl) {
        jfrm = new JFrame("Person");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(200, 520);
        jfrm.setBounds(50, 10, 200, 575);
        jfrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jlab = new JLabel();
        JLabel jlabSeparator = new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200, 20));
        id = new JTextField();
        id.setEditable(false);
        firstname = new JTextField();
        surname = new JTextField();
        id.setColumns(15);
        firstname.setColumns(15);
        surname.setColumns(15);
        df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormatter dateFormatter = new DateFormatter(df);
        dateFormatter.setAllowsInvalid(true);
        dateFormatter.setOverwriteMode(true);
        birthday = new JFormattedTextField(dateFormatter);
        birthday.setColumns(15);
        birthday.setValue(new Date());
        street = new JTextField();
        street.setColumns(15);
        city = new JTextField();
        city.setColumns(15);
        zip = new JTextField();
        zip.setColumns(15);
        homephone = new JTextField();
        homephone.setColumns(15);
        cellphone = new JTextField();
        cellphone.setColumns(15);
        workphone = new JTextField();
        workphone.setColumns(15);
        jbtnSave = new JButton("Save Person Attributes");
        {
            setFields(person);
        }
        jbtnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                savePersonAttributes(person, personform, ctrl);
            }
        });
        jfrm.getContentPane().add(new JLabel("ID"));
        jfrm.getContentPane().add(id);
        jfrm.getContentPane().add(new JLabel("First Name"));
        jfrm.getContentPane().add(firstname);
        jfrm.getContentPane().add(new JLabel("Surname"));
        jfrm.getContentPane().add(surname);
        jfrm.getContentPane().add(new JLabel("Birthday"));
        jfrm.getContentPane().add(birthday);
        jfrm.getContentPane().add(new JLabel("Street"));
        jfrm.getContentPane().add(street);
        jfrm.getContentPane().add(new JLabel("City"));
        jfrm.getContentPane().add(city);
        jfrm.getContentPane().add(new JLabel("ZIP"));
        jfrm.getContentPane().add(zip);
        jfrm.getContentPane().add(new JLabel("Home Phone"));
        jfrm.getContentPane().add(homephone);
        jfrm.getContentPane().add(new JLabel("Cellphone"));
        jfrm.getContentPane().add(cellphone);
        jfrm.getContentPane().add(new JLabel("Work Phone"));
        jfrm.getContentPane().add(workphone);
        jfrm.getContentPane().add(jlabSeparator);
        jfrm.getContentPane().add(jbtnSave);
        jfrm.setVisible(false);
    }

    void savePersonAttributes(Person person, PersonForm personform, Controller ctrl) {
        person.setId(Integer.parseInt(id.getText()));
        person.setFirstName(firstname.getText());
        person.setSurname(surname.getText());
        try {
            person.setBirthday(new SimpleDateFormat("dd.MM.yyyy").parse(birthday.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        person.setStreet(street.getText());
        person.setCity(city.getText());
        person.setZIP(zip.getText());
        person.setHomePhone(homephone.getText());
        person.setCellPhone(cellphone.getText());
        person.setWorkPhone(workphone.getText());
        personform.ChangeListModel(person);
        setFields(person);
        ctrl.update(person, personform.getPersonList());
    }

    void setFields(Person person) {
        id.setText(String.valueOf(person.getId()));
        jfrm.getContentPane().add(jlab);
        firstname.setText(person.getFirstName());
        surname.setText(person.getSurname());
        birthday.setValue(person.getBirthday());
        street.setText(person.getStreet());
        city.setText(person.getCity());
        zip.setText(person.getZIP());
        homephone.setText(person.getHomePhone());
        cellphone.setText(person.getCellPhone());
        workphone.setText(person.getWorkPhone());
    }
}

