import java.util.*;

abstract class Vehicle {
    protected String vehicleId;
    protected String vehicleName;
    protected boolean available;

    public Vehicle(String vehicleId, String vehicleName) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.available = true;
    }

    public abstract double calculateCharge(int days);

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Sedan extends Vehicle {

    public Sedan(String vehicleId, String vehicleName) {
        super(vehicleId, vehicleName);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {

    public SUV(String vehicleId, String vehicleName) {
        super(vehicleId, vehicleName);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {

    public Truck(String vehicleId, String vehicleName) {
        super(vehicleId, vehicleName);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double amount;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.amount = vehicle.calculateCharge(days);
    }

    public void displayRental() {
        System.out.println("Vehicle: " + vehicle.getVehicleName());
        System.out.println("Customer: " + customer.getName());
        System.out.println("Days: " + days);
        System.out.println("Rental charge: $" + amount);
    }
}

class RentalSystem {

    public void rentVehicle(Vehicle vehicle, Customer customer, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                vehicle.getVehicleName() + " is currently unavailable."
            );
            return;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(vehicle, customer, days);

        System.out.println(
            vehicle.getVehicleName() +
            " rented successfully by " +
            customer.getName() + "."
        );

        System.out.println(
            "Rental charge: $" +
            vehicle.calculateCharge(days)
        );
    }

    public void returnVehicle(Vehicle vehicle, Customer customer) {

        if (vehicle.isAvailable()) {
            System.out.println(
                vehicle.getVehicleName() + " is already available."
            );
            return;
        }

        vehicle.setAvailable(true);

        System.out.println(
            vehicle.getVehicleName() +
            " returned by " +
            customer.getName() + "."
        );
    }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Vehicle sedanA = new Sedan("S101", "Sedan A");
        Vehicle suvB = new SUV("SUV201", "SUV B");

        Customer customer1 =
            new Customer("C1", "Customer 1");

        Customer customer2 =
            new Customer("C2", "Customer 2");

        Customer customer3 =
            new Customer("C3", "Customer 3");

        RentalSystem system = new RentalSystem();

        
        system.rentVehicle(sedanA, customer1, 3);

        
        system.rentVehicle(sedanA, customer2, 2);

        system.returnVehicle(sedanA, customer1);

        system.rentVehicle(suvB, customer3, 5);
    }
}