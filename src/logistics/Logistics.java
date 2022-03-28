package logistics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Logistics {
    private int warehouseID;
    private final StringProperty warehouseOwner = new SimpleStringProperty();
    private final StringProperty warehouseStreet = new SimpleStringProperty();
    private final StringProperty warehouseCity = new SimpleStringProperty();
    private final StringProperty warehousePhone = new SimpleStringProperty();
    private final StringProperty warehouseEmail = new SimpleStringProperty();
    private final StringProperty warehouseProducts = new SimpleStringProperty();

    private int supplierID;
    private final StringProperty supplierName = new SimpleStringProperty();
    private final StringProperty supplierStreet = new SimpleStringProperty();
    private final StringProperty supplierCity = new SimpleStringProperty();
    private final StringProperty supplierPhone = new SimpleStringProperty();
    private final StringProperty supplierEmail = new SimpleStringProperty();

    private int shipperID;
    private final StringProperty shipperName = new SimpleStringProperty();
    private final StringProperty shipperPhone = new SimpleStringProperty();

    /**
     * Defaultowy konstruktor
     */

    public Logistics(){
    }

    /**
     *  Metoda tworząca magazyn
     * @param warehouseID [int]
     * @param warehouseOwner [String]
     * @param warehouseStreet [String]
     * @param warehouseCity [String]
     * @param warehousePhone [int]
     * @param warehouseEmail [int]
     */

    public void initWarehouse(int warehouseID, String warehouseOwner, String warehouseStreet, String warehouseCity, String warehousePhone, String warehouseEmail){
        this.warehouseID = warehouseID;
        this.warehouseOwner.set(warehouseOwner);
        this.warehouseStreet.set(warehouseStreet);
        this.warehouseCity.set(warehouseCity);
        this.warehousePhone.set(warehousePhone);
        this.warehouseEmail.set(warehouseEmail);
    }

    /**
     * Metoda tworząca nadawcę
     * @param supplierID [int]
     * @param supplierName [String]
     * @param supplierStreet [String]
     * @param supplierCity [String]
     * @param supplierPhone [int]
     * @param supplierEmail [int]
     */

    public void initSupplier(int supplierID, String supplierName, String supplierStreet, String supplierCity, String supplierPhone, String supplierEmail){
        this.supplierID = supplierID;
        this.supplierName.set(supplierName);
        this.supplierStreet.set(supplierStreet);
        this.supplierCity.set(supplierCity);
        this.supplierPhone.set(supplierPhone);
        this.supplierEmail.set(supplierEmail);
    }

    /**
     * Metoda tworząca spedytora
     * @param shipperID [int]
     * @param shipperName [String]
     * @param shipperPhone [String]
     */

    public void initShipper(int shipperID, String shipperName, String shipperPhone){
        this.shipperID = shipperID;
        this.shipperName.set(shipperName);
        this.shipperPhone.set(shipperPhone);
    }

    public void setWarehouseProducts(String warehouseProducts) {
        this.warehouseProducts.set(warehouseProducts);
    }

    public String getWarehouseProducts() {
        return warehouseProducts.get();
    }

    public StringProperty warehouseProductsProperty() {
        return warehouseProducts;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone.set(warehousePhone);
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail.set(warehouseEmail);
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone.set(supplierPhone);
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail.set(supplierEmail);
    }

    public String getWarehousePhone() {
        return warehousePhone.get();
    }

    public StringProperty warehousePhoneProperty() {
        return warehousePhone;
    }

    public String getWarehouseEmail() {
        return warehouseEmail.get();
    }

    public StringProperty warehouseEmailProperty() {
        return warehouseEmail;
    }

    public String getSupplierPhone() {
        return supplierPhone.get();
    }

    public StringProperty supplierPhoneProperty() {
        return supplierPhone;
    }

    public String getSupplierEmail() {
        return supplierEmail.get();
    }

    public StringProperty supplierEmailProperty() {
        return supplierEmail;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public void setWarehouseOwner(String warehouseOwner) {
        this.warehouseOwner.set(warehouseOwner);
    }

    public void setWarehouseStreet(String warehouseStreet) {
        this.warehouseStreet.set(warehouseStreet);
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity.set(warehouseCity);
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName.set(supplierName);
    }

    public void setSupplierStreet(String supplierStreet) {
        this.supplierStreet.set(supplierStreet);
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity.set(supplierCity);
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public void setShipperName(String shipperName) {
        this.shipperName.set(shipperName);
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone.set(shipperPhone);
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public String getWarehouseOwner() {
        return warehouseOwner.get();
    }

    public StringProperty warehouseOwnerProperty() {
        return warehouseOwner;
    }

    public String getWarehouseStreet() {
        return warehouseStreet.get();
    }

    public StringProperty warehouseStreetProperty() {
        return warehouseStreet;
    }

    public String getWarehouseCity() {
        return warehouseCity.get();
    }

    public StringProperty warehouseCityProperty() {
        return warehouseCity;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName.get();
    }

    public StringProperty supplierNameProperty() {
        return supplierName;
    }

    public String getSupplierStreet() {
        return supplierStreet.get();
    }

    public StringProperty supplierStreetProperty() {
        return supplierStreet;
    }

    public String getSupplierCity() {
        return supplierCity.get();
    }

    public StringProperty supplierCityProperty() {
        return supplierCity;
    }

    public int getShipperID() {
        return shipperID;
    }

    public String getShipperName() {
        return shipperName.get();
    }

    public StringProperty shipperNameProperty() {
        return shipperName;
    }

    public String getShipperPhone() {
        return shipperPhone.get();
    }

    public StringProperty shipperPhoneProperty() {
        return shipperPhone;
    }
}
