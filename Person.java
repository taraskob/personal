import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Person {
    private int id;
    private String firstName, surname;
    private Date birthday;

  /*  {
        try {
            birthday = (new SimpleDateFormat("dd.MM.yyyy")).parse((new SimpleDateFormat("dd.MM.yyyy"))
                    .format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    private String street, city, zip;
    private String homePhone, cellPhone, workPhone;

    Person() {    }
    Person(Date bdate) {
        setBirthday(bdate);
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    Date getBirthday() {
        return birthday;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getZIP() {
        return zip;
    }

    void setZIP(String zip) {
        this.zip = zip;
    }

    String getHomePhone() {
        return homePhone;
    }

    void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    String getCellPhone() {
        return cellPhone;
    }

    void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    String getWorkPhone() {
        return workPhone;
    }

    void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
}
