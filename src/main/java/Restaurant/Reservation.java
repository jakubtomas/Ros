package Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int reservation_id;
    private int restaurant_id;
    private int customer_id;
    private int table;
    private LocalDateTime CreatedTime;   // time when you create the orders
    private LocalDateTime OrderTime;     // time when you  will eat the food

    private List<Integer> FoodList = new ArrayList<>();
    private List<Integer> DrinkList = new ArrayList<>();

    private double price;
    private double priceWithDph;
    private double DPH;

    public Reservation(int reservation_id, int restaurant_id, int customer_id, int table, LocalDateTime createdTime, LocalDateTime orderTime, List<Integer> foodList, List<Integer> drinkList, double price, double priceWithDph, double DPH) {
        this.reservation_id = reservation_id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.table = table;
        CreatedTime = createdTime;
        OrderTime = orderTime;
        FoodList = foodList;
        DrinkList = drinkList;
        this.price = price;
        this.priceWithDph = priceWithDph;
        this.DPH = DPH;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public LocalDateTime getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        CreatedTime = createdTime;
    }

    public LocalDateTime getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        OrderTime = orderTime;
    }

    public List<Integer> getFoodList() {
        return FoodList;
    }

    public void setFoodList(List<Integer> foodList) {
        FoodList = foodList;
    }

    public List<Integer> getDrinkList() {
        return DrinkList;
    }

    public void setDrinkList(List<Integer> drinkList) {
        DrinkList = drinkList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDph() {
        return priceWithDph;
    }

    public void setPriceWithDph(double priceWithDph) {
        this.priceWithDph = priceWithDph;
    }

    public double getDPH() {
        return DPH;
    }

    public void setDPH(double DPH) {
        this.DPH = DPH;
    }
}
