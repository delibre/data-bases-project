package orders;

import javafx.beans.property.*;

public class Orders {
    private int orderID;
    private final IntegerProperty clientID = new SimpleIntegerProperty();
    private final StringProperty orderDate = new SimpleStringProperty();
    private final StringProperty shipDate = new SimpleStringProperty();
    private final IntegerProperty shippedBy = new SimpleIntegerProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty zip = new SimpleStringProperty();
    private final IntegerProperty processedBy = new SimpleIntegerProperty();
    private final DoubleProperty finalPrice = new SimpleDoubleProperty();
    private final StringProperty productList = new SimpleStringProperty();

    /**
     * Konstuuje obiekt Orders
     * @param orderID [int]
     * @param clientID [int]
     * @param orderDate [String]
     * @param shipDate [String]
     * @param address [String]
     * @param shippedBy [int]
     * @param city [String]
     * @param zip [String]
     * @param processedBy [int]
     * @param finalPrice [double]
     * @param productList [String]
     */

    public Orders(int orderID, int clientID, String orderDate, String shipDate, String address, int shippedBy, String city, String zip, int processedBy, double finalPrice, String productList){
        this.orderID = orderID;
        this.clientID.set(clientID);
        this.orderDate.set(orderDate);
        this.shipDate.set(shipDate);
        this.address.set(address);
        this.shippedBy.set(shippedBy);
        this.city.set(city);
        this.zip.set(zip);
        this.processedBy.set(processedBy);
        this.finalPrice.set(finalPrice);
        this.productList.set(productList);
    }

    public void setClientID(int clientID) {
        this.clientID.set(clientID);
    }

    public int getClientID() {
        return clientID.get();
    }

    public IntegerProperty clientIDProperty() {
        return clientID;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public void setShipDate(String shipDate) {
        this.shipDate.set(shipDate);
    }

    public void setShippedBy(int shippedBy) {
        this.shippedBy.set(shippedBy);
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

    public void setProcessedBy(int processedBy) {
        this.processedBy.set(processedBy);
    }

    public int getProcessedBy() {
        return processedBy.get();
    }

    public IntegerProperty processedByProperty() {
        return processedBy;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice.set(finalPrice);
    }

    public void setProductList(String productList) {
        this.productList.set(productList);
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public StringProperty orderDateProperty() {
        return orderDate;
    }

    public String getShipDate() {
        return shipDate.get();
    }

    public StringProperty shipDateProperty() {
        return shipDate;
    }

    public int getShippedBy() {
        return shippedBy.get();
    }

    public IntegerProperty shippedByProperty() {
        return shippedBy;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getZip() {
        return zip.get();
    }

    public StringProperty zipProperty() {
        return zip;
    }

    public double getFinalPrice() {
        return finalPrice.get();
    }

    public DoubleProperty finalPriceProperty() {
        return finalPrice;
    }

    public String getProductList() {
        return productList.get();
    }

    public StringProperty productListProperty() {
        return productList;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

}

