import java.time.LocalDate;
import java.util.*;

abstract class Room {

    protected int roomNumber;
    protected boolean available;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.available = true;
    }

    public abstract double calculatePrice(int days);

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class StandardRoom extends Room {

    public StandardRoom(int roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 180;
    }
}

class Suite extends Room {

    public Suite(int roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 300;
    }
}

class Customer {

    private int customerId;
    private String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {

    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Reservation(
        Customer customer,
        Room room,
        LocalDate startDate,
        LocalDate endDate
    ) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    public boolean overlaps(
        LocalDate newStart,
        LocalDate newEnd
    ) {

        return newStart.isBefore(endDate)
            && newEnd.isAfter(startDate);
    }

    public void cancel() {
        active = false;
        room.setAvailable(true);
    }

    public boolean isActive() {
        return active;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {

        long days =
            java.time.temporal.ChronoUnit.DAYS
            .between(startDate, endDate);

        return room.calculatePrice((int) days);
    }
}

class Hotel {

    private ArrayList<Reservation> reservations;

    public Hotel() {
        reservations = new ArrayList<>();
    }

    public boolean isAvailable(
        Room room,
        LocalDate startDate,
        LocalDate endDate
    ) {

        for (Reservation reservation : reservations) {

            if (reservation.isActive()
                && reservation.getRoom() == room
                && reservation.overlaps(
                    startDate,
                    endDate
                )) {

                return false;
            }
        }

        return true;
    }

    public Reservation bookRoom(
        Customer customer,
        Room room,
        LocalDate startDate,
        LocalDate endDate
    ) {

        if (!isAvailable(
                room,
                startDate,
                endDate)) {

            System.out.println(
                "Room " +
                room.getRoomNumber() +
                " is not available."
            );

            return null;
        }

        Reservation reservation =
            new Reservation(
                customer,
                room,
                startDate,
                endDate
            );

        reservations.add(reservation);

        room.setAvailable(false);

        System.out.println(
            "Reservation confirmed for " +
            customer.getName() +
            ", Room " +
            room.getRoomNumber() +
            " (" +
            startDate +
            " - " +
            endDate +
            ")."
        );

        System.out.println(
            "Price: $" +
            reservation.getPrice()
        );

        return reservation;
    }

    public void cancelReservation(
        Reservation reservation
    ) {

        if (reservation == null) {
            return;
        }

        LocalDate today =
            LocalDate.now();

        if (today.isBefore(
                reservation.getStartDate())) {

            reservation.cancel();

            System.out.println(
                "Reservation cancelled successfully."
            );

        } else {

            System.out.println(
                "Cancellation deadline has passed."
            );
        }
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Customer customerA =
            new Customer(1, "Customer A");

        Customer customerB =
            new Customer(2, "Customer B");

        Customer customerC =
            new Customer(3, "Customer C");

        Room room101 =
            new StandardRoom(101);

        Room room201 =
            new DeluxeRoom(201);

        LocalDate jan1 =
            LocalDate.of(2027, 1, 1);

        LocalDate jan5 =
            LocalDate.of(2027, 1, 5);

        LocalDate jan3 =
            LocalDate.of(2027, 1, 3);

        LocalDate jan7 =
            LocalDate.of(2027, 1, 7);

        if (hotel.isAvailable(
                room101,
                jan1,
                jan5)) {

            System.out.println(
                "Standard Room 101 is available from " +
                jan1 +
                " to " +
                jan5
            );
        }

        Reservation reservation =
            hotel.bookRoom(
                customerA,
                room101,
                jan1,
                jan5
            );

        hotel.bookRoom(
            customerB,
            room101,
            jan3,
            jan7
        );
        hotel.cancelReservation(reservation);

        
        hotel.bookRoom(
            customerC,
            room201,
            LocalDate.of(2027, 2, 10),
            LocalDate.of(2027, 2, 12)
        );
    }
}