import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonDBTest {
    public static void main(String[] args) {

        Conn();
    }

    static void Conn() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    static void CreateTables() {
        /* CREATE TABLE [Address] (
[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[street] CHAR(20)  NULL,
[city] CHAR(15)  NULL,
[zip] CHAR(10)  NULL
);

CREATE TABLE [Person] (
[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,
[firstname] CHAR(20)  NULL,
[surname] CHAR(20)  NULL,
[birthday] DATE  NULL
);

CREATE TABLE [Phones] (
[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,
[homephone] CHAR(10)  NULL,
[cellphone] CHAR(15)  NULL,
[workphone] CHAR(10)  NULL
);*/
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Person " +
                    "(id CHAR(4) PRIMARY KEY     NOT NULL," +
                    " firstname   CHAR(20)    NOT NULL, " +
                    " surname CHAR(20)  NOT NULL, " +
                    " birthday DATE NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            stmt = c.createStatement();
            sql = "CREATE TABLE Address " +
                    "(id CHAR(4) PRIMARY KEY     NOT NULL," +
                    " street   CHAR(20)    NOT NULL, " +
                    " city CHAR(15)  NOT NULL, " +
                    " zip CHAR(10) NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            stmt = c.createStatement();
            sql = "CREATE TABLE Phones " +
                    "(id CHAR(4) PRIMARY KEY     NOT NULL," +
                    " homephone  CHAR(10)    NOT NULL, " +
                    " cellphone CHAR(15)  NOT NULL, " +
                    " workphone CHAR(10) NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    static void insertPerson() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql;
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1000', 'Jack', 'Nickolson','25.03.1987' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1001', 'Jane', 'Jones','05.09.1997' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1002', 'Alfred', 'Marx','05.09.1990' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1003', 'Sofia', 'Ramires','30.08.1996' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1004', 'Juliette', 'Binoche','09.03.1998' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1005', 'Michael', 'McDonald','29.10.1988' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1006', 'Michelle', 'Rodriguez','12.07.1978' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1007', 'John', 'Woods','04.07.1976' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1008', 'Richard', 'Grossmann','14.12.2002' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Person (id,firstname,surname,birthday) " +
                    "VALUES ('1009', 'Michelle', 'Wie','11.12.1989' );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    static void insertAddress() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql;
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1000', 'Gardens, 23', 'Manchester','123450' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1001', 'Ford, 235', 'Toronto','123011' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1002', 'Kohl, 2', 'Dresden','123012' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1003', 'Sombrero, 6', 'Malaga','123013' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1004', 'Limbaud, 46', 'Bordeaux','123014' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1005', 'Glenmorangie, 17', 'Glasgow','123015' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1006', '>Pinto, 7', 'San Antonio','123016' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1007', '5thAvenue, 10', 'New York','123017' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1008', 'Lemberg, 3', 'Hannover','123018' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Address (id,street,city,zip) " +
                    "VALUES ('1009', 'Oahu, 13', 'Honolulu','123019' );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    static void insertPhones() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql;
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1000', '2654320', '80542654020','2654320' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1001', '2654321', '0542654021','3456791' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1002', '2654322', '0542654022','3456792' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1003', '2654323', '0542654023','3456793' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1004', '2654324', '0542654024','3456794' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1005', '2654325', '0542654025','3456795' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1006', '2654326', '0542654026','3456796' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1007', '2654327', '0542654027','3456797' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1008', '2654328', '0542654028','3456798' );";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Phones (id,homephone,cellphone,workphone) " +
                    "VALUES ('1009', '2654329', '0542654029','3456799' );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    static void selectPersonalData() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PersonalData.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Person.*,street,city,zip,homephone,cellphone,workphone" +
                    " FROM Person,Address,Phones where Person.id=Address.id and Person.id=Phones.id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String birthday = rs.getString("birthday");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String zip = rs.getString("zip");
                String homephone = rs.getString("homephone");
                String cellphone = rs.getString("cellphone");
                String workphone = rs.getString("workphone");
                System.out.println("ID = " + id);
                System.out.println("FIRSTNAME = " + firstname);
                System.out.println("SURNAME = " + surname);
                System.out.println("BIRTHDAY = " + birthday);
                System.out.println("STREET = " + street);
                System.out.println("CITY = " + city);
                System.out.println("ZIP = " + zip);
                System.out.println("HOMEPHONE= " + homephone);
                System.out.println("CELLPHONE= " + cellphone);
                System.out.println("WORKPHONE= " + workphone);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
