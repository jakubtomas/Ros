package Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MenulList {
    private int menuList_id;
    private int restaraunt_id;

    private String name;
    private String type;
    private LocalDateTime CreatedTime;


    private List<Integer> FoodsList = new ArrayList<>();
    private List<Integer> DrinkList = new ArrayList<>();


    public MenulList(int menuList_id, int restaraunt_id, String name, String type, LocalDateTime createdTime, List<Integer> foodsList, List<Integer> drinkList) {
        this.menuList_id = menuList_id;
        this.restaraunt_id = restaraunt_id;
        this.name = name;
        this.type = type;
        CreatedTime = createdTime;
        FoodsList = foodsList;
        DrinkList = drinkList;
    }

    public int getMenuList_id() {
        return menuList_id;
    }

    public void setMenuList_id(int menuList_id) {
        this.menuList_id = menuList_id;
    }

    public int getRestaraunt_id() {
        return restaraunt_id;
    }

    public void setRestaraunt_id(int restaraunt_id) {
        this.restaraunt_id = restaraunt_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        CreatedTime = createdTime;
    }

    public List<Integer> getFoodsList() {
        return FoodsList;
    }

    public void setFoodsList(List<Integer> foodsList) {
        FoodsList = foodsList;
    }

    public List<Integer> getDrinkList() {
        return DrinkList;
    }

    public void setDrinkList(List<Integer> drinkList) {
        DrinkList = drinkList;
    }
}
