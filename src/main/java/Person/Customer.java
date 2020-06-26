package Person;

public class Customer extends EntityPerson {
    private int customer_id;

    public Customer(String fname, String lname, String login, String password, String token, Address address) {
        super(fname, lname, login, password, token, address);
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
