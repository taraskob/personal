import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class PersonDB implements PersonStorage {
    Person person;
    ArrayList<Person> personList = new ArrayList<>();

    @Override
    public ArrayList<Person> read() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Person.*,street,city,zip,homephone,cellphone,workphone" +
                    " FROM Person,Address,Phones where Person.id=Address.id and Person.id=Phones.id;");
            while (rs.next()) {
                person = new Person(new Date());
                person.setId(rs.getInt("id"));
                person.setFirstName(rs.getString("firstname"));
                person.setSurname(rs.getString("surname"));
                String str_birthday = rs.getString("birthday");
                person.setBirthday(new SimpleDateFormat("dd.MM.yyyy").parse(str_birthday));
                person.setStreet(rs.getString("street"));
                person.setCity(rs.getString("city"));
                person.setZIP(rs.getString("zip"));
                person.setHomePhone(rs.getString("homephone"));
                person.setCellPhone(rs.getString("cellphone"));
                person.setWorkPhone(rs.getString("workphone"));
                personList.add(person);
                person = null;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return personList;
    }

    @Override
    public void update(Person person, ArrayList<Person> personList) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql;
            sql = "UPDATE Phones set homephone='" + person.getHomePhone() + "',cellphone='" + person.getCellPhone() +
                    "',workphone='" + person.getWorkPhone() + "' where id=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            sql = "UPDATE Address set street='" + person.getStreet() + "',city='" + person.getCity() +
                    "',zip='" + person.getZIP() + "' where id=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            String strBirthDay = new SimpleDateFormat("dd.MM.yyyy").format(person.getBirthday());
            sql = "UPDATE Person set firstname='" + person.getFirstName() + "',surname='" + person.getSurname() +
                    "',birthday='" + strBirthDay + "' where id=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void add(Person person) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql;
            sql = "INSERT INTO Phones (homephone,cellphone,workphone) " +
                    "VALUES (" + person.getHomePhone() + "," + person.getCellPhone() +
                    "," + person.getWorkPhone() + ");";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (street,city,zip) " +
                    "VALUES (" + person.getStreet() + "," + person.getCity() +
                    "," + person.getZIP() + ");";
            stmt.executeUpdate(sql);
            String strBirthDay = new SimpleDateFormat("dd.MM.yyyy").format(person.getBirthday());
            sql = "INSERT INTO Person (firstname,surname,birthday) " +
                    "VALUES (" + person.getFirstName() + "," + person.getSurname() +
                    ",'" + strBirthDay + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence where name='Person';");
            int id = rs.getInt("seq");
            person.setId(id);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void delete(Person person, ArrayList<Person> personList) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql;
            sql = "DELETE from Person where ID=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE from Address where ID=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE from Phones where ID=" + person.getId() + ";";
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void save(ArrayList<Person> personList) {

    }

}
