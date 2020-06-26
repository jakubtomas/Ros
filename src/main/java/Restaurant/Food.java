package Restaurant;

public class Food {
    private int food_id;
    private String name;
    private int price;
    private String type;
    private String description;
    private int timeToCreate;



    public Food(int food_id, String name, int price, String type, String description, int timeToCreate) {
        this.food_id = food_id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.timeToCreate = timeToCreate;
    }



    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
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
// mozno pridat alergeny list array

}
