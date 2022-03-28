package clientdashboard;

import inputchecker.CheckField;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Callback;
import logistics.Logistics;
import screenchanger.ChangeScreen;
import sqlmanagement.DBManagment;
import alerts.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import login.LoginController;
import products.Products;
import users.Client;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientDashboardController implements Initializable{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ChangeScreen screen = new ChangeScreen();

    @FXML
    private ComboBox<String> categoriesList;
    @FXML
    private TableView<Products> listOfProductsTable;
    @FXML
    private TableColumn<Products, String> products;
    @FXML
    private TableColumn<Products, Integer> quantity;
    @FXML
    private TableColumn<Products, Double> price;
    @FXML
    private TableView<Products> boughtProductsTable;
    @FXML
    private TableColumn<Products, String> boughtProduct;
    @FXML
    private TableColumn<Products, Double> pricePerUnit;
    @FXML
    private TextField zip;
    @FXML
    private TextField city;
    @FXML
    private TextField street;

    private final ObservableList<Products> picked = FXCollections.observableArrayList();
    private Client c;

    /**
     * Metoda, inicjalizuje zmienną c danymi o zalogowanym kliencie
     * @param c [Client]
     */

    public void setClient(Client c) {
        this.c = c;
    }

    /**
     * Metoda, która wylogowuje użytkownika
     * @param event [MouseEvent]
     * @see IOException
     */

    public void logout(MouseEvent event) throws IOException {
        screen.logout(event);
    }

    /**
     * Metoda, ustawiająca początkowe dane na ekranie
     * @see Exception
     */

    private void setData() {
        try {
            con = DBManagment.connect();
            String sql = "SELECT * FROM project.kategorie";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                String tmp = res.getString("nazwaKategorii");
                categoriesList.getItems().add(tmp);
            }
            System.out.println("setdata");
            res.close();
            pst.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda, która generuje listę produktów w zalezności od wybranej kategorii
     *      @param event [MouseEvent]    -   zdarzenie
     *      @see Exception
     */

    @FXML
    private void searchCategory(MouseEvent event) {
        products.setCellValueFactory(product -> product.getValue().getProduct());
        price.setCellValueFactory(product -> product.getValue().getPrice().asObject());


        ObservableList<Products> list = FXCollections.observableArrayList();
        try {
            if (categoriesList.getSelectionModel().getSelectedItem() == null) {
                throw new Exception();
            }
            String tmp = categoriesList.getSelectionModel().getSelectedItem();
            con = DBManagment.connect();
            String sql = "select * from project.informacjaProdukt where \"nazwaKategorii\"=? and przerwane=?; ";
            pst = con.prepareStatement(sql);
            pst.setString(1, tmp);
            pst.setBoolean(2, false);

            res = pst.executeQuery();
            while (res.next()) {
                System.out.println(res.getString("nazwaProduktu"));

                list.add(new Products(res.getInt("id_produktu"), res.getString("nazwaProduktu"),
                        res.getInt("id_kategorii"), res.getString("nazwaKategorii"), res.getDouble("cena")));

            }

            res.close();
            pst.close();
            con.close();

        }catch (Exception e) {
            AlertBox.errorAlert("Bląd", "Wybierz kategorię z listy");
            e.printStackTrace();
        }
        listOfProductsTable.setItems(list);

    }

    /**
     * Metoda, która dodaje na listę wybrane produkty
     *      @param mouseEvent [MouseEvent]
     *      @see Exception
     */

    public void addToCart(MouseEvent mouseEvent) {
        boughtProduct.setCellValueFactory(product -> product.getValue().getProduct());
        pricePerUnit.setCellValueFactory(product -> product.getValue().getPrice().asObject());
        quantity.setCellValueFactory(product -> product.getValue().quantityProperty().asObject());

        try {
            if (listOfProductsTable.getSelectionModel().getSelectedItems() == null) {
                AlertBox.errorAlert("Bląd", "Wybierz produkt z listy");
                return;
            }


            for(int i = 0; i < listOfProductsTable.getSelectionModel().getSelectedItems().size(); i++) {
                boolean notFound = true;
                for (Products val : picked) {
                    if (listOfProductsTable.getSelectionModel().getSelectedItems().get(i) == val) {
                        val.setQuantity(val.getQuantity() + 1);
                        notFound = false;
                    }
                }
                if(notFound){
                    Products product = listOfProductsTable.getSelectionModel().getSelectedItems().get(i);
                    product.setQuantity(1);
                    picked.add(product);
                }
            }

            boughtProductsTable.setItems(picked);

        } catch (Exception e) {
            AlertBox.errorAlert("Bląd", "Wybierz produkt z listy");
            e.printStackTrace();
        }

    }

    /**
     * Metoda, która pozwala kupić wybrane produkty
     *      @see NullPointerException
     *      @see SQLException
     */

    @FXML
    private void buyProducts(MouseEvent event){

        try{
            if(picked.isEmpty()){
                AlertBox.errorAlert("Bląd", "Lista jest pusta!");
                return;
            }
            double sum = 0;
            LocalDate date = LocalDate.now();
            String products = "";


            for(Products val : picked){
                products += val.getProduct().get() + ", " + val.getCategory() + "\n";
                sum += val.getPrice().getValue();
            }

            c.setFinalPrice(sum);
            c.setOrderDate(String.valueOf(date));
            c.setProductList(products);

            con = DBManagment.connect();
            String sql = "insert into project.zamowienia (id_klienta, \"dataZamowienia\", \"adresWysylki\", \"miastoWysylki\", \"kodPocztowyWysylki\", opis, cena) values (?, ?, ?, ?, ?, ?, ?) returning id_zamowienia";
            pst = con.prepareStatement(sql);

            pst.setInt(1, c.getClientId());
            pst.setDate(2, Date.valueOf(date));
            pst.setString(3, c.addressProperty().get());
            pst.setString(4, c.cityProperty().get());
            pst.setString(5, c.zipProperty().get());
            pst.setString(6, c.getProductList());
            pst.setDouble(7, c.getFinalPrice());


            res = pst.executeQuery();

            if(!res.next()){
                throw new RuntimeException("no return from order insert");
            }

            int orderID = res.getInt("id_zamowienia");

            System.out.println("----" + orderID);

            for (Products val : picked) {
                sql = "insert into project.\"szczegolyZamowienia\" (id_zamowienia, id_produktu, cena, ilosc) values (?,?,?,?) returning id_zamowienia";
                pst = con.prepareStatement(sql);
                pst.setInt(1, orderID);
                pst.setInt(2, val.getId_product());
                pst.setDouble(3, val.getPrice().get());
                pst.setInt(4, val.getQuantity());
                res = pst.executeQuery();
            }

            picked.clear();
            boughtProductsTable.refresh();

            res.close();
            pst.close();
            con.close();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Metoda, która pozwala usunąć wszystkie wybrane produkty
     *      @see NullPointerException
     *      @see SQLException
     */

    public void deleteAll(MouseEvent mouseEvent) {
        picked.clear();
        boughtProductsTable.refresh();
    }

    /**
     * Funkcja, która sprawdza mozliwość zmiany adresu
     *      @param clientZip
     *      @param clientCity
     *      @param clientStreet
     *      @see SQLException
     */

    public boolean newAddress(String clientZip, String clientCity, String clientStreet){
        try {
            con = DBManagment.connect();
            String sql = "update project.klienci set adres=?, miasto=?, \"kodPocztowy\"=? where id_klienta=?;";
            pst = con.prepareStatement(sql);

            pst.setString(1, clientStreet);
            pst.setString(2, clientCity);
            pst.setString(3, clientZip);
            pst.setInt(4, c.getClientId());

            pst.execute();

            pst.close();
            con.close();

            return true;

        } catch (SQLException e) {
            AlertBox.errorAlert("Nadawca nie dodany", "Nadawca nie dodany");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManagment.closeAll(con, res, pst);
        }
        return false;
    }

    /**
     * Metoda, zmieniająca adres klienta
     *      @param mouseEvent [MouseEvent]    -   zdarzenie
     *      @see IOException
     */

    public void changeAddress(MouseEvent mouseEvent) {
        String clientZip = zip.getText();
        String clientCity = city.getText();
        String clientStreet = street.getText();

        if(CheckField.checkCityField(city) && CheckField.checkZipCodeField(zip)) {
            if(newAddress(clientZip, clientCity, clientStreet)){
                AlertBox.infoAlert("Adres został zmieniony", "Adres został zmieniony.", "Adres został zmieniony.");
            }
        }
    }

    /**
     * Jest to główna metoda wykorzystująca metodę setData()
     * @param url [URL]
     * @param resourceBundle [ResourceBundle]
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }


}
