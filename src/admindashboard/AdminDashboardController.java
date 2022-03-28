package admindashboard;

import alerts.AlertBox;
import inputchecker.CheckField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import login.Main;
import logistics.Logistics;
import orders.Orders;
import products.Products;
import screenchanger.ChangeScreen;
import sqlmanagement.DBManagment;
import users.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ChangeScreen screen = new ChangeScreen();

    @FXML
    private TableView<Orders> ordersList;
    @FXML
    public TableColumn<Orders, Integer> clientId;
    @FXML
    public TableColumn<Orders, String> orderedProducts;
    @FXML
    public TableColumn<Orders, Double> price;
    @FXML
    public TableColumn<Orders, String> date;
    @FXML
    public TableColumn<Orders, String> shipDate;
    @FXML
    public ComboBox<String> shipperList;
    @FXML
    public DatePicker datePicker;


    @FXML
    public TextField productNameField;
    @FXML
    public TextField productPriceField;
    @FXML
    public TextField productCategoryField;
    @FXML
    public TextField productSupplierField;
    @FXML
    public TextField productWarehouseField;
    @FXML
    private TableView<Products> productsTable;
    @FXML
    public TableColumn<Products, String> productColumn;
    @FXML
    public TableColumn<Products, String> categoryColumn;


    @FXML
    public TextField warehouseEmailField;
    @FXML
    public TextField warehouseOwnerField;
    @FXML
    public TextField warehouseZipField;
    @FXML
    public TextField warehouseStreetField;
    @FXML
    public TextField warehouseCityField;
    @FXML
    public TextField warehousePhoneField;
    @FXML
    private TableView<Logistics> warehouseTable;
    @FXML
    public TableColumn<Logistics, String> warehouseOwner;
    @FXML
    public TableColumn<Logistics, String> warehouseCity;
    @FXML
    public TableColumn<Logistics, String> warehousePhone;
    @FXML
    public TableColumn<Logistics, String> warehouseEmail;
    @FXML
    public TableColumn<Logistics, String> warhouseProducts;


    @FXML
    public TextField supplierNameField;
    @FXML
    public TextField supplierEmailField;
    @FXML
    public TextField supplierAddressField;
    @FXML
    public TextField supplierZipField;
    @FXML
    public TextField supplierCityField;
    @FXML
    public TextField supplierPhoneField;
    @FXML
    private TableView<Logistics> supplierTable;
    @FXML
    public TableColumn<Logistics, String> supplierName;
    @FXML
    public TableColumn<Logistics, String> supplierStreet;
    @FXML
    public TableColumn<Logistics, String> supplierCity;
    @FXML
    public TableColumn<Logistics, String> supplierPhone;
    @FXML
    public TableColumn<Logistics, String> supplierEmail;


    @FXML
    public TextField shipperNameField;
    @FXML
    public TextField shipperPhoneField;
    @FXML
    private TableView<Logistics> shipperTable;
    @FXML
    public TableColumn<Logistics, String> shipperName;
    @FXML
    public TableColumn<Logistics, String> shipperPhone;

    private ObservableList<Orders> orders = FXCollections.observableArrayList();
    private ObservableList<Products> products = FXCollections.observableArrayList();
    private ObservableList<Logistics> warehouses = FXCollections.observableArrayList();
    private ObservableList<Logistics> supplier = FXCollections.observableArrayList();
    private ObservableList<Logistics> shipper = FXCollections.observableArrayList();

    private Manager m;

    /**
     * Metoda, inicjalizuje zmienną m danymi o zalogowanym menedżerze
     * @param m [Manager]
     */

    public void setManager(Manager m){
        this.m = m;
    }

    /**
     * Metoda, która wylogowuje użytkownika
     * @param event [MouseEvent]    -   zdarzenie
     * @see IOException
     */

    public void logout(MouseEvent event) throws IOException {
        screen.logout(event);
    }

    /**
     * Metoda, inicjalizuje zmienną orders danymi z tabeli zamowienia
     * @see Exception
     */

    void initializeOrders(){
        clientId.setCellValueFactory(order -> order.getValue().clientIDProperty().asObject());
        orderedProducts.setCellValueFactory(order -> order.getValue().productListProperty());
        price.setCellValueFactory(order -> order.getValue().finalPriceProperty().asObject());
        date.setCellValueFactory(order -> order.getValue().orderDateProperty());
        shipDate.setCellValueFactory(order -> order.getValue().shipDateProperty());

        try{
            con = DBManagment.connect();
            String sql = "select * from project.zamowienia;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();

            while (res.next()) {
                orders.add(new Orders(res.getInt("id_zamowienia"), res.getInt("id_klienta"), res.getString("dataZamowienia"),
                        res.getString("dataWysylki"), res.getString("adresWysylki"), res.getInt("wyslanePrzez"),
                        res.getString("miastoWysylki"), res.getString("kodPocztowyWysylki"), res.getInt("przetworzonePrzez"),
                        res.getDouble("cena"), res.getString("opis")));
            }

            ordersList.setItems(orders);

            res.close();
            pst.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Metoda, inicjalizuje zmienną warehouses danymi z tabeli produkty
     * @see Exception
     */

    void initializeProducts(){
        productColumn.setCellValueFactory(product -> product.getValue().getProduct());
        categoryColumn.setCellValueFactory(product -> product.getValue().categoryProperty());
        try{
            con = DBManagment.connect();
            String sql = "select * from project.informacjaProdukt where przerwane=false;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while(res.next()){
                products.add(new Products(res.getInt("id_produktu"), res.getString("nazwaProduktu"),
                        res.getInt("id_kategorii"), res.getString("nazwaKategorii"), res.getDouble("cena")));
            }

            productsTable.setItems(products);

            res.close();
            pst.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda, inicjalizuje zmienną supplier danymi z tabeli nadawcy
     * @see Exception
     */

    void initializeSupplier(){
        supplierName.setCellValueFactory(supplier -> supplier.getValue().supplierNameProperty());
        supplierStreet.setCellValueFactory(supplier -> supplier.getValue().supplierStreetProperty());
        supplierCity.setCellValueFactory(supplier -> supplier.getValue().supplierCityProperty());
        supplierPhone.setCellValueFactory(warehouse -> warehouse.getValue().supplierPhoneProperty());
        supplierEmail.setCellValueFactory(warehouse -> warehouse.getValue().supplierEmailProperty());

        try {
            con = DBManagment.connect();
            String sql = "select * from project.nadawcy where przerwane=false;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();

            while(res.next()){
                Logistics l = new Logistics();

                l.initSupplier(res.getInt("id_nadawcy"), res.getString("nazwaFirmy"),
                        res.getString("adres"), res.getString("miasto"),
                        res.getString("telefon"), res.getString("email"));

                supplier.add(l);
            }

            supplierTable.setItems(supplier);

            res.close();
            pst.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda, inicjalizuje zmienną shipper danymi z tabeli spedytory
     * @see Exception
     */

    void initializeShipper(){
        shipperName.setCellValueFactory(shipper -> shipper.getValue().shipperNameProperty());
        shipperPhone.setCellValueFactory(shipper -> shipper.getValue().shipperPhoneProperty());

        try {
            con = DBManagment.connect();
            String sql = "select * from project.spedytor where przerwane=false;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();

            while(res.next()){
                Logistics l = new Logistics();

                l.initShipper(res.getInt("id_spedytora"), res.getString("nazwaFirmy"), res.getString("telefon") );

                shipper.add(l);
            }

            shipperTable.setItems(shipper);

            res.close();
            pst.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda, która liczy ile produktów jest w magazynie
     * @see IOException
     */

    void getCount(){
        warhouseProducts.setCellValueFactory(warehouse -> warehouse.getValue().warehouseProductsProperty());
        try{
            con = DBManagment.connect();

            String sql = "select\n" +
                    "    id_magazynu,\n" +
                    "    count(id_produktu)\n" +
                    "from\n" +
                    "    project.produkty where przerwane=false\n" +
                    "group by\n" +
                    "    id_magazynu;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();

            while(res.next()){
                for(Logistics val:warehouses){
                    if(val.getWarehouseID() == res.getInt("id_magazynu")){
                        val.setWarehouseProducts(res.getString("count"));
                    }
                }
            }

            res.close();
            pst.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Metoda, inicjalizuje zmienną warehouses danymi z tabeli magazyny
     * @see Exception
     */

    void initializeWarehouses(){
        warehouseOwner.setCellValueFactory(warehouse -> warehouse.getValue().warehouseOwnerProperty());
        warehouseCity.setCellValueFactory(warehouse -> warehouse.getValue().warehouseCityProperty());
        warehousePhone.setCellValueFactory(warehouse -> warehouse.getValue().warehousePhoneProperty());
        warehouseEmail.setCellValueFactory(warehouse -> warehouse.getValue().warehouseEmailProperty());

        try {
            con = DBManagment.connect();
            String sql = "select * from project.magazyny where przerwane=false;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();

            while(res.next()){
                Logistics l = new Logistics();

                l.initWarehouse(res.getInt("id_magazynu"), res.getString("wlasciciel"),
                        res.getString("adres"), res.getString("miasto"),
                        res.getString("telefon"), res.getString("email"));

                warehouses.add(l);
            }

            warehouseTable.setItems(warehouses);

            res.close();
            pst.close();
            con.close();

            getCount();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda, ustawiająca początkowe dane na ekranie
     * @see Exception
     */

    private void setData() {
        try {
            con = DBManagment.connect();
            String sql = "SELECT * FROM project.spedytor;";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                String tmp = res.getString("nazwaFirmy");
                shipperList.getItems().add(tmp);
            }

            res.close();
            pst.close();

            res.close();
            pst.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda, która inicjalizuje zmienną shipper
     * @see IOException
     */

    public Logistics getShipper(){
        Logistics shipper = new Logistics();

        try {
            con = DBManagment.connect();
            String sql = "select * from project.spedytor where \"nazwaFirmy\"=?; ";
            pst = con.prepareStatement(sql);
            pst.setString(1, shipperList.getSelectionModel().getSelectedItem());

            res = pst.executeQuery();
            if (!res.next()) {
                throw new RuntimeException("no return from shipper select");
            }

            shipper.initShipper(res.getInt("id_spedytora"), res.getString("nazwaFirmy"), res.getString("telefon"));

            res.close();
            pst.close();
            con.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return shipper;
    }

    /**
     * Metoda, która uzupełnia tablicę zamówienia danymi o wysłaniu
     * @see IOException
     */

    public boolean send() {
        try {
            if (shipperList.getSelectionModel().getSelectedItem() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz spedytora z listy");
                return false;
            }

            if (ordersList.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz zamowienie z listy");
                return false;
            }

            if (datePicker == null) {
                AlertBox.errorAlert("Bląd", "Wybierz datę wysylki");
                return false;
            }

            Logistics shipper = getShipper();
            LocalDate date = LocalDate.of(datePicker.getValue().getYear(), datePicker.getValue().getMonth(), datePicker.getValue().getDayOfMonth());

            int orderID = 0;

            for(Orders val:orders){
                for(int i = 0; i < ordersList.getSelectionModel().getSelectedItems().size(); i++) {
                    if (val == ordersList.getSelectionModel().getSelectedItems().get(i)) {
                        orderID = val.getOrderID();
                        val.setShipDate(String.valueOf(date));
                        val.setShippedBy(shipper.getShipperID());
                        val.setProcessedBy(m.getManagerID());
                    }
                }
            }

            con = DBManagment.connect();
            String sql = "update project.zamowienia\n" +
                    "set \"dataWysylki\" = ?, \"wyslanePrzez\" = ?, \"przetworzonePrzez\" = ?\n" +
                    "where id_zamowienia = ? returning id_zamowienia;";
            pst = con.prepareStatement(sql);

            pst.setDate(1, Date.valueOf(date));
            pst.setInt(2, shipper.getShipperID());
            pst.setInt(3, m.getManagerID());
            pst.setInt(4, orderID);
            pst.executeQuery();

            res.close();
            pst.close();
            con.close();

            ordersList.setItems(orders);
            ordersList.refresh();

            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metoda, która wysyłą zamówienie
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void confirm(MouseEvent mouseEvent){
        if(send()){
            AlertBox.infoAlert("Produkt został wysłany", "Produkt został wysłany.", "Wysłałeś produkt");
        }
    }

    /**
     * Metoda, która dodaje nowy produkt do bazy
     * @param name [String]
     * @param price [String]
     * @param category [String]
     * @param suppliers [String]
     * @param warehouse [String]
     * @see IOException
     */

    public void newProduct(String name, String price, String category, String suppliers, String warehouse){
        try {
            int categoryID = 0;
            for(Products val:products){
                if(Objects.equals(val.getCategory(), category)){
                    System.out.println("products");
                    categoryID = val.getId_category();
                }
            }

            System.out.println(categoryID);

            int supplierID = 0;
            for(Logistics val:supplier){
                if(Objects.equals(val.getSupplierName(), suppliers)){
                    supplierID = val.getSupplierID();
                }
            }

            int warehouseID = 0;
            for(Logistics val:warehouses){
                if(Objects.equals(val.getWarehouseOwner(), warehouse)){
                    System.out.println("warehouse");
                    warehouseID = val.getWarehouseID();
                }
            }

            System.out.println(warehouseID);


            con = DBManagment.connect();
            String sql = "select project.dodajProdukt(?,?,?,?,?);";

            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setDouble(2, Double.parseDouble(price));
            pst.setInt(3, categoryID);
            pst.setInt(4, supplierID);
            pst.setInt(5, warehouseID);

            System.out.println("addition");

            pst.execute();

            sql = "select * from project.informacjaProdukt where \"nazwaProduktu\"=?;";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);

            res = pst.executeQuery();
            while(res.next()){
                products.add(new Products(res.getInt("id_produktu"), res.getString("nazwaProduktu"),
                        res.getInt("id_kategorii"), res.getString("nazwaKategorii"), res.getDouble("cena")));
            }

            productsTable.setItems(products);

            res.close();
            pst.close();
            con.close();

        } catch (SQLException e) {
            AlertBox.errorAlert("Produkt nie dodany", "Produkt nie dodany");
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            DBManagment.closeAll(con, res, pst);
        }
    }

    /**
     * Metoda, która dadje produkt na listę
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void addProduct(MouseEvent mouseEvent) {
        String name = productNameField.getText();
        String price = productPriceField.getText();
        String category = productCategoryField.getText();
        String supplier = productSupplierField.getText();
        String warehouse = productWarehouseField.getText();

        newProduct(name, price, category, supplier, warehouse);
        warehouses = FXCollections.observableArrayList();
        initializeWarehouses();

    }

    /**
     * Metoda, która odświeża ekran
     * @see IOException
     */

    public void refresh(){
        try{
            con = DBManagment.connect();
            String sql = "select * from project.informacjaProdukt where przerwane=false;";
            pst = con.prepareStatement(sql);

            res = pst.executeQuery();
            products = FXCollections.observableArrayList();
            while(res.next()){
                products.add(new Products(res.getInt("id_produktu"), res.getString("nazwaProduktu"),
                        res.getInt("id_kategorii"), res.getString("nazwaKategorii"), res.getDouble("cena")));
            }

            productsTable.setItems(products);
            productsTable.refresh();

            res.close();
            pst.close();
            con.close();

            warehouses = FXCollections.observableArrayList();
            initializeWarehouses();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda, usuwająca produkt z listy
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void deleteProduct(MouseEvent mouseEvent) {
        try{
            if (productsTable.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz produkt z listy");
                return;
            }

            int productID = 0;
            for(Products val:products) {
                for (int i = 0; i < productsTable.getSelectionModel().getSelectedItems().size(); i++) {
                    if (val == productsTable.getSelectionModel().getSelectedItems().get(i)){
                        productID = val.getId_product();
                    }
                }
            }

            con = DBManagment.connect();
            String sql = "update project.produkty set przerwane=? where id_produktu=? returning 1;";
            pst = con.prepareStatement(sql);
            pst.setBoolean(1, true);
            pst.setInt(2, productID);

            pst.executeQuery();

            pst.close();
            con.close();

            refresh();
            warehouses = FXCollections.observableArrayList();
            initializeWarehouses();


        }catch (Exception e){
            AlertBox.errorAlert("Nie udało się wysłać produkt", "Nie udało się wysłać produkt");

            e.printStackTrace();
        }
    }

    /**
     * Metoda, usuwająca magazyn z listy
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void deleteWarehouse(MouseEvent mouseEvent) {
        try{
            if (warehouseTable.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz magazyn z listy");
                return;
            }

            int warehouseID = 0;
            for(Logistics val:warehouses){
                for(int i = 0; i<warehouseTable.getSelectionModel().getSelectedItems().size(); i++) {
                    if (val == warehouseTable.getSelectionModel().getSelectedItems().get(i)){
                        warehouseID = val.getWarehouseID();
                        System.out.println("----" + warehouseID);
                    }
                }
            }

            con = DBManagment.connect();
            String sql = "update project.magazyny set przerwane=true where id_magazynu=? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, warehouseID);
            pst.execute();

            pst.close();
            con.close();

            warehouses = FXCollections.observableArrayList();
            initializeWarehouses();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda, dodająca magazyn na listę
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void addWarehouse(MouseEvent mouseEvent) {
        String owner = warehouseOwnerField.getText();
        String city = warehouseCityField.getText();
        String email = warehouseEmailField.getText();
        String phone = warehousePhoneField.getText();
        String street = warehouseStreetField.getText();
        String zip = warehouseZipField.getText();

        if(CheckField.checkCityField(warehouseCityField) && CheckField.checkEmail(warehouseEmailField) &&
                CheckField.checkZipCodeField(warehouseZipField) && CheckField.checkNumberField(warehousePhoneField)) {

            try {
                con = DBManagment.connect();
                String sql = "select project.dodajMagazyn(?,?,?,?,?,?);";
                pst = con.prepareStatement(sql);

                pst.setString(1, owner);
                pst.setString(2, phone);
                pst.setString(3, email);
                pst.setString(4, street);
                pst.setString(5, zip);
                pst.setString(6, city);

                pst.execute();

                pst.close();
                con.close();

                warehouses = FXCollections.observableArrayList();
                initializeWarehouses();

            } catch (SQLException e) {
                AlertBox.errorAlert("Magazyn nie dodany", "Magazyn nie dodany");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManagment.closeAll(con, res, pst);
            }
        }
    }

    /**
     * Metoda, dodająca nadawcę na listę
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void addSupplier(MouseEvent mouseEvent) {
        String name = supplierNameField.getText();
        String city = supplierCityField.getText();
        String email = supplierEmailField.getText();
        String phone = supplierPhoneField.getText();
        String street = supplierAddressField.getText();
        String zip = supplierZipField.getText();

        if(CheckField.checkCityField(supplierCityField) && CheckField.checkEmail(supplierEmailField) &&
                CheckField.checkZipCodeField(supplierZipField) && CheckField.checkNumberField(supplierPhoneField)) {
            try {
                con = DBManagment.connect();
                String sql = "select project.dodajNadawce(?,?,?,?,?,?);";
                pst = con.prepareStatement(sql);

                pst.setString(1, name);
                pst.setString(2, phone);
                pst.setString(3, email);
                pst.setString(4, street);
                pst.setString(5, zip);
                pst.setString(6, city);

                pst.execute();

                pst.close();
                con.close();

                supplier = FXCollections.observableArrayList();
                initializeSupplier();

            } catch (SQLException e) {
                AlertBox.errorAlert("Nadawca nie dodany", "Nadawca nie dodany");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManagment.closeAll(con, res, pst);
            }
        }

    }

    /**
     * Metoda, usuwająca nadawce z listy
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void deleteSupplier(MouseEvent mouseEvent) {
        try{
            if (supplierTable.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz nadawcę z listy");
                return;
            }

            int supplierID = 0;
            for(Logistics val:supplier){
                for(int i = 0; i<supplierTable.getSelectionModel().getSelectedItems().size(); i++) {
                    if (val == supplierTable.getSelectionModel().getSelectedItems().get(i)){
                        supplierID = val.getSupplierID();
                        System.out.println("----" + supplierID);
                    }
                }
            }

            con = DBManagment.connect();
            String sql = "update project.nadawcy set przerwane=true where id_nadawcy=? returning 1 ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, supplierID);
            pst.executeQuery();

            pst.close();
            con.close();

            supplier = FXCollections.observableArrayList();
            initializeSupplier();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda, dodająca spedytora na listę
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void addShipper(MouseEvent mouseEvent) {
        String name = shipperNameField.getText();
        String phone = shipperPhoneField.getText();

        if(CheckField.checkNumberField(shipperPhoneField)) {
            try {
                con = DBManagment.connect();
                String sql = "select project.dodajSpedytora(?,?);";
                pst = con.prepareStatement(sql);

                pst.setString(1, name);
                pst.setString(2, phone);

                pst.execute();

                pst.close();
                con.close();

                shipper = FXCollections.observableArrayList();
                initializeShipper();

            } catch (SQLException e) {
                AlertBox.errorAlert("Spedytor nie dodany", "Spedytor nie dodany");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManagment.closeAll(con, res, pst);
            }
        }
    }

    /**
     * Metoda, usuwająca spedytora z listy
     * @param mouseEvent [MouseEvent]
     * @see IOException
     */

    public void deleteShipper(MouseEvent mouseEvent) {
        try{
            if (shipperTable.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz nadawcę z listy");
                return;
            }

            int shipperID = 0;
            for(Logistics val:shipper){
                for(int i = 0; i<shipperTable.getSelectionModel().getSelectedItems().size(); i++) {
                    if (val == shipperTable.getSelectionModel().getSelectedItems().get(i)){
                        shipperID = val.getShipperID();
                        System.out.println("----" + shipperID);
                    }
                }
            }

            con = DBManagment.connect();
            String sql = "update project.spedytor set przerwane=true where id_spedytora=? returning 1 ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, shipperID);
            pst.executeQuery();

            pst.close();
            con.close();

            shipper = FXCollections.observableArrayList();
            initializeShipper();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Jest to główna metoda wykorzystująca metodę setData() oraz metody inicjalizujące tablice
     * z magazynami, spedytorami, nadawcami, zamówieniami i produktami
     * @param url [URL]
     * @param resourceBundle [ResourceBundle]
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        initializeWarehouses();
        initializeShipper();
        initializeSupplier();
        initializeOrders();
        initializeProducts();
    }
}
