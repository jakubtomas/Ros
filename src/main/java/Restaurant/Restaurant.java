package Restaurant;

import Person.Address;
import Person.Employee;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int restaurant_id;
    private int owner_id;

    private String name;
    private Address address;


    private List<Employee> EmployeeList = new ArrayList<>();
    private List<OrderReservation> orderReservationList = new ArrayList<>();
    private List<Integer> TablesList = new ArrayList<>();

    private MenulList menulList;

    private int visitors;
    private double raiting;



}

