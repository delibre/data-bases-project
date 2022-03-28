package createaccount;

import alerts.AlertBox;
import login.Main;
import inputchecker.CheckField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import screenchanger.ChangeScreen;
import sqlmanagement.DBManagment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ChangeScreen screen = new ChangeScreen();

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passField;

    /**
     * Metoda, przekierowująca użytkownika do okna logowania
     * @param event [MouseEvent]    -   zdarzenie
     * @see IOException
     */

    public void backToLogin(MouseEvent event) throws IOException {
        screen.loadScreen("/login/LoginScreen.fxml", event);
    }

    /**
     * Funkcja, która sprawdza mozliwość dodania klienta do bazy
     *      @param name [String]
     *      @param surname [String]
     *      @param mail [String]
     *      @param number [String]
     *      @param address [String]
     *      @param city [String]
     *      @param zip [String]
     *      @param login [String]
     *      @param pass [String]
     *      @return  [boolean]
     *      @see SQLException
     */

    public boolean createClient(String name, String surname, String mail, String number, String address, String city, String zip, String login, String pass) {
        try {
            con = DBManagment.connect();
            String sql = "Select project.dodajKlienta(?,?,?,?,?,?,?,?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2,surname);
            pst.setString(3, mail);
            pst.setString(4,number);
            pst.setString(5,address);
            pst.setString(6,city);
            pst.setString(7,zip);
            pst.setString(8,login);
            pst.setString(9,pass);


            pst.execute();

            pst.close();
            con.close();
            return true;

        } catch (SQLException e) {
            AlertBox.errorAlert("Konto nie utworzone", "Twoje konto nie zostało utworzone! Sprawdź dane i sprobuj jeszcze raz");
            e.printStackTrace();
        } finally {
            DBManagment.closeAll(con, res, pst);
        }
        return false;
    }

    /**
     * Metoda, dodająca klienta do bazy
     *      @param event [MouseEvent]
     *      @see IOException
     */

    public void newAccount(MouseEvent event) throws IOException{
        String name = nameField.getText();
        String surname = surnameField.getText();
        String number = phoneField.getText();
        String mail = mailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String zip = zipField.getText();
        String login = loginField.getText();
        String pass = passField.getText();

        if (CheckField.checkFullnameField(nameField) && CheckField.checkFullnameField(surnameField) && CheckField.checkEmail(mailField)
                && CheckField.checkNumberField(phoneField) && CheckField.checkAddressField(addressField)
                && CheckField.checkCityField(cityField) && CheckField.checkZipCodeField(zipField)
                && CheckField.checkLogin(loginField) && CheckField.checkPass(passField)) {
            if (createClient(name, surname, mail, number, address, city, zip, login, pass)) {
                AlertBox.infoAlert("Konto utworzone", "Konto utworzone.", "Twoje konto zostało pomyślnie utworzone. Możesz zalogować się za pomocą logina i hasła");
                Main.myStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login/LoginScreen.fxml"))));
            }
        }
    }

    /**
     * Jest to główna metoda ustawiająca ograniczenia dla wybranych pól
     * @param url [URL]
     * @param resourceBundle [ResourceBundle]
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                phoneField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
