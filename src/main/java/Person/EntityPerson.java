package Person;

public abstract class EntityPerson {

    private String fname;
    private String lname;
    private String login;
    private String password;
    private String token;
    private Address address; // class


    public EntityPerson(String fname, String lname, String login, String password, String token, Address address) {
        this.fname = fname;
        this.lname = lname;
        this.login = login;
        this.password = password;
        this.token = token;
        this.address = address;
    }


    // getters and setters okey

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Address getContact() {
        return address;
    }

    public void setContact(Address address) {
        this.address = address;
    }




}
