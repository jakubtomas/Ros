package Person;

public class Employee extends EntityPerson {
    public Employee(String fname, String lname, String login, String password, String token, Address address) {
        super(fname, lname, login, password, token, address);
    }
}
