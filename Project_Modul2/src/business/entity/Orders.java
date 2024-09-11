package business.entity;

import business.utils.Formatter;
import presentation.run.Main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Orders implements Serializable {
    public  static  Address address = new Address();
    public  static  Products products= new Products();
    public  static  ShoppingCart shoppingCart= new ShoppingCart();
    private int orderId;
    private String serialNumber= UUID.randomUUID().toString();
    private int userId;
    private Double totalPrice;
    private Boolean status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private LocalDate created;
    private LocalDate received;

    public Orders() {
    }

    public Orders(String receiveName, int orderId, String serialNumber, int userId, Double totalPrice, Boolean status, String note, String receiveAddress, String receivePhone, LocalDate created, LocalDate received) {
        this.receiveName = receiveName;
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.note = note;
        this.receiveAddress = receiveAddress;
        this.receivePhone = receivePhone;
        this.created = created;
        this.received = received;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getReceived() {
        return received;
    }

    public void setReceived(LocalDate received) {
        this.received = received;
    }

    public void inputData(Scanner scanner, List<Orders> ordersList) {

        this.orderId = inputOrderId(scanner,ordersList);
        this.userId = Main.userLogin.getUserId();
        this.totalPrice = products.getUnitPrice()*shoppingCart.getOrderQuantity();
        this.note = inputNote(scanner);
        this.receiveName = address.getReceive();
        this.receiveAddress = scanner.nextLine();
        this.receivePhone = address.getPhone();
        this.created = LocalDate.now();
        this.received = LocalDate.now().plusDays(4);;
    }

    private String inputNote(Scanner scanner) {
        System.out.println("Mời bạn nhập ghi chú chi tiết: ");
        do {
            String note = scanner.nextLine();
            if (note.isBlank()){
                System.err.println("Không để trống ghi chú");
            }
            else {
                return note;
            }

        }while(true);
    }

    private int inputOrderId(Scanner sc, List<Orders> ordersList) {
        return ordersList.stream().mapToInt(Orders::getOrderId).
                max().
                orElse(0)+1;
    }


    public void displayData() {
        System.out.printf(
                "[OrderID: %s | Serial: %s | UserId: %s | TotalPrice: %s | Note: %s | ReceiveName: %s | ReceiveAddress: %s | ReceivePhone: %s | Created: %s | Received: %s]\n",
                this.orderId,
                this.serialNumber,
                this.userId,
                Formatter.getNumberFormatterVND(this.totalPrice),
                this.note,
                this.receiveName,
                this.receiveAddress,
                this.receivePhone,
                this.created,
                this.received
        );
    }
}

