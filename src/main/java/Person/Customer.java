package Person;

public class Customer extends EntityPerson {
    public Customer(String fname, String lname, String login, String password, String token, Address address) {
        super(fname, lname, login, password, token, address);
    }
}
