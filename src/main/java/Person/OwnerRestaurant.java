package Person;

public class OwnerRestaurant extends  EntityPerson {
    private int owner_id;
    private int restaurant_id;
    private String companyName;
    private String ico;
    private String dic;
    private String icDph;

    private String invoiceStreet;
    private String invoiceZipcode;
    private String invoiceCity;



    public OwnerRestaurant(String fname, String lname, String login, String password, String token, Address address) {
        super(fname, lname, login, password, token, address);
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getIcDph() {
        return icDph;
    }

    public void setIcDph(String icDph) {
        this.icDph = icDph;
    }

    public String getInvoiceStreet() {
        return invoiceStreet;
    }

    public void setInvoiceStreet(String invoiceStreet) {
        this.invoiceStreet = invoiceStreet;
    }

    public String getInvoiceZipcode() {
        return invoiceZipcode;
    }

    public void setInvoiceZipcode(String invoiceZipcode) {
        this.invoiceZipcode = invoiceZipcode;
    }

    public String getInvoiceCity() {
        return invoiceCity;
    }

    public void setInvoiceCity(String invoiceCity) {
        this.invoiceCity = invoiceCity;
    }
}
