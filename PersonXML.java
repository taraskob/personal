import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class PersonXML implements PersonStorage {
    static int ID_SEQUENCE = 0;
    Person person;
    ArrayList<Person> personList = new ArrayList<>();
    String fileName;

    public PersonXML(String s) {
        fileName = s;
    }

    @Override
    public void save(ArrayList<Person> personList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("<?xml version = \"1.0\"?>\r\n");
            writer.write("<personal_data>\r\n");
            for (Person person : personList) {
                writer.write("   <person id=\"" + person.getId() + "\">\r\n");
                writer.write("      <firstname>" + person.getFirstName() + "</firstname>\r\n");
                writer.write("      <surname>" + person.getSurname() + "</surname>\r\n");
                String strBirthDay = new SimpleDateFormat("dd.MM.yyyy").format(person.getBirthday());
                writer.write("      <birthday>" + strBirthDay + "</birthday>\r\n");
                writer.write("      <street>" + person.getStreet() + "</street>\r\n");
                writer.write("      <city>" + person.getCity() + "</city>\r\n");
                writer.write("      <zip>" + person.getZIP() + "</zip>\r\n");
                writer.write("      <homephone>" + person.getHomePhone() + "</homephone>\r\n");
                writer.write("      <cellphone>" + person.getCellPhone() + "</cellphone>\r\n");
                writer.write("      <workphone>" + person.getWorkPhone() + "</workphone>\r\n");
                writer.write("   </person>\r\n");
            }
            writer.write("</personal_data>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Person> read() {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("person");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    person = new Person(new Date());
                    person.setId(Integer.parseInt(eElement.getAttribute("id")));
                    ID_SEQUENCE = person.getId();
                    person.setFirstName(eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    person.setSurname(eElement.getElementsByTagName("surname").item(0).getTextContent());
                    String str_birthday = eElement.getElementsByTagName("birthday").item(0).getTextContent();
                    try {
                        person.setBirthday(new SimpleDateFormat("dd.MM.yyyy").parse(str_birthday));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    person.setHomePhone(eElement.getElementsByTagName("homephone").item(0).getTextContent());
                    person.setCellPhone(eElement.getElementsByTagName("cellphone").item(0).getTextContent());
                    person.setWorkPhone(eElement.getElementsByTagName("workphone").item(0).getTextContent());
                    person.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
                    person.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
                    person.setZIP(eElement.getElementsByTagName("zip").item(0).getTextContent());
                    personList.add(person);
                    person = null;
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public void update(Person person, ArrayList<Person> personList) {
        save(personList);
    }


    @Override
    public void add(Person person) {
        person.setId(++ID_SEQUENCE);
    }

    @Override
    public void delete(Person person, ArrayList<Person> personList) {
        save(personList);

        if (ID_SEQUENCE > 0)
            ID_SEQUENCE--;
    }


}
