package Person;

public class Employee extends EntityPerson {
    private int employee_id;
    private int owner_id;
    private int restaurant_id;

    public Employee(String fname, String lname, String login, String password, String token, Address address) {
        super(fname, lname, login, password, token, address);
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
