import java.util.*;

class Product {

    private int productId;
    private String name;
    private double price;

    public Product(
        int productId,
        String name,
        double price
    ) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(
        Product product,
        int quantity
    ) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

class Customer {

    private int customerId;
    private String name;

    public Customer(
        int customerId,
        String name
    ) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCardPayment
    implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
            "Processing Credit Card payment..."
        );

        return true;
    }
}

class PayPalPayment
    implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
            "Processing PayPal payment..."
        );
        return false;
    }
}

class BankTransferPayment
    implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
            "Processing Bank Transfer..."
        );

        return true;
    }
}

enum OrderStatus {
    PENDING,
    PAID
}

class Order {

    private String orderId;
    private Customer customer;

    private ArrayList<OrderItem> items;

    private OrderStatus status;

    public Order(
        String orderId,
        Customer customer
    ) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public void addProduct(
        Product product,
        int quantity
    ) {

        if (quantity <= 0) {
            System.out.println(
                "Invalid quantity."
            );
            return;
        }

        items.add(
            new OrderItem(
                product,
                quantity
            )
        );
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(
        PaymentMethod paymentMethod,
        String paymentName
    ) {

        if (items.isEmpty()) {

            System.out.println(
                "Cannot process payment for an empty order."
            );

            return;
        }

        System.out.println(
            "Payment initiated via " +
            paymentName +
            " for Order " +
            orderId + "."
        );

        double amount = calculateTotal();

        boolean success =
            paymentMethod.processPayment(amount);

        if (success) {

            status = OrderStatus.PAID;

            System.out.println(
                "Payment for Order " +
                orderId +
                " successful."
            );

        } else {

            System.out.println(
                "Payment for Order " +
                orderId +
                " failed."
            );
        }

        System.out.println(
            "Order status: " +
            status
        );
    }
}

public class PaymentProcessingSystem {

    public static void main(String[] args) {
        Product productA =
            new Product(
                101,
                "Product A",
                100
            );

        Product productB =
            new Product(
                102,
                "Product B",
                200
            );

        Product productC =
            new Product(
                103,
                "Product C",
                300
            );

        Customer customerX =
            new Customer(
                1,
                "Customer X"
            );

        Customer customerY =
            new Customer(
                2,
                "Customer Y"
            );

        Customer customerZ =
            new Customer(
                3,
                "Customer Z"
            );

       
        Order orderX =
            new Order(
                "X",
                customerX
            );

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
            "Order created for Customer X."
        );

        PaymentMethod creditCard =
            new CreditCardPayment();

        orderX.pay(
            creditCard,
            "Credit Card"
        );

        System.out.println();

        
        Order orderY =
            new Order(
                "Y",
                customerY
            );

        System.out.println(
            "Order created for Customer Y."
        );

        PaymentMethod bank =
            new BankTransferPayment();

        orderY.pay(
            bank,
            "Bank Transfer"
        );

        System.out.println();

       
        Order orderZ =
            new Order(
                "Z",
                customerZ
            );

        orderZ.addProduct(
            productC,
            1
        );

        System.out.println(
            "Order created for Customer Z."
        );

        PaymentMethod paypal =
            new PayPalPayment();

        orderZ.pay(
            paypal,
            "PayPal"
        );
    }
}