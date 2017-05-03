import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;

class PersonForm {
    JList jlst;
    JLabel jlab;
    JScrollPane jscrlp;
    JButton jbtnAdd;
    JButton jbtnDel;
    PersonForm personform = this;
    PersonAttributesForm attributesForm;
    DefaultListModel lm;
    ArrayList<Person> personList;

    PersonForm(ArrayList<Person> personList, Controller ctrl) {
        setPersonList(personList);
        JFrame jfrm = new JFrame("JList Person");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(200, 317);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CreateListModel(getPersonList());
        jlst = new JList(lm);
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jscrlp = new JScrollPane(jlst);
        jscrlp.setPreferredSize(new Dimension(120, 200));
        jlab = new JLabel("Please choose a person.");
        jlst.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = jlst.getAnchorSelectionIndex();
                if (idx != -1) {

                    if (attributesForm == null)
                        attributesForm = new PersonAttributesForm(personList.get(idx), personform, ctrl);
                    else
                        attributesForm.setFields(personList.get(idx));
                    attributesForm.jfrm.setVisible(true);

                } else
                    jlab.setText("Please choose a person.");
            }
        });
        jbtnAdd = new JButton("Add");
        jbtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personList.add(new Person(new Date()));
                lm.addElement(personList.get(personList.size() - 1).getFirstName() + ' ' +
                        personList.get(personList.size() - 1).getSurname());
                if (attributesForm == null)
                    attributesForm = new PersonAttributesForm(personList.get(personList.size() - 1), personform, ctrl);
                else
                    attributesForm.setFields(personList.get(personList.size() - 1));
                ctrl.add(personList.get(personList.size() - 1));
                attributesForm.setFields(personList.get(personList.size() - 1));
                attributesForm.jfrm.setVisible(true);
            }
        });
        jbtnDel = new JButton("Del");
        jbtnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = jlst.getSelectedIndex();
                if (idx != -1) {
                    lm.remove(idx);
                    Person deletedPerson = personList.get(idx);
                    personList.remove(idx);
                    ctrl.delete(deletedPerson, personList);

                    attributesForm.jfrm.setVisible(false);
                } else
                    jlab.setText("No person has been selected.");
            }
        });
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnAdd);
        jfrm.getContentPane().add(jbtnDel);
        // jfrm.getContentPane().add(jbtnSave);
        jfrm.setVisible(true);
    }

    void CreateListModel(ArrayList<Person> personList) {
        lm = new DefaultListModel();
        for (Person person : personList)
            lm.addElement(person.getFirstName() + ' ' + person.getSurname());
    }

    ArrayList<Person> getPersonList() {
        return personList;
    }

    void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    void ChangeListModel(Person person) {
        lm = (DefaultListModel) jlst.getModel();
        lm.remove(personList.indexOf(person));
        lm.add(personList.indexOf(person), person.getFirstName() + ' ' + person.getSurname());
    }
}
