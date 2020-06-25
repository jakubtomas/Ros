package Restaurant;

import Person.Address;
import Person.OwnerRestaurant;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private Address address;
    private OwnerRestaurant ownerRestaurant;
    private List<OrdersFood> Orders = new ArrayList<>();
    private List<Integer> positions = new ArrayList<>();

}
