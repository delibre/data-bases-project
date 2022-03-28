package users;

import javafx.beans.property.*;
import logistics.Logistics;
import orders.Orders;

import java.util.ArrayList;

public class Client extends Logistics {
    private final IntegerProperty clientId = new SimpleIntegerProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty zip = new SimpleStringProperty();
    private final StringProperty orderDate = new SimpleStringProperty();
    private final DoubleProperty finalPrice = new SimpleDoubleProperty();
    private final StringProperty productList = new SimpleStringProperty();
    private ArrayList<Orders> orderId = new ArrayList<>();

    /**
     * Defaultowy konstruktor
     */

    public Client() {

    }

    public void setProductList(String productList) {
        this.productList.set(productList);
    }

    public String getProductList() {
        return productList.get();
    }

    public StringProperty productListProperty() {
        return productList;
    }

    public void setOrderId(ArrayList<Orders> orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Orders> getOrderId() {
        return orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice.set(finalPrice);
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public StringProperty orderDateProperty() {
        return orderDate;
    }

    public double getFinalPrice() {
        return finalPrice.get();
    }

    public DoubleProperty finalPriceProperty() {
        return finalPrice;
    }

    public int getClientId() {
        return clientId.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty zipProperty() {
        return zip;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public IntegerProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId.set(clientId);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }
}
