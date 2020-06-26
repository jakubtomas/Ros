package Restaurant;

public class Drink {
    private int drink_id;
    private String name;
    private int price;
    private String type;
    private String description;
    private int timeToCreate;

    public Drink(int drink_id, String name, int price, String type, String description, int timeToCreate) {
        this.drink_id = drink_id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.timeToCreate = timeToCreate;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeToCreate() {
        return timeToCreate;
    }

    public void setTimeToCreate(int timeToCreate) {
        this.timeToCreate = timeToCreate;
    }



}
