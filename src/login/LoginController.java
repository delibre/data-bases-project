package login;

import alerts.AlertBox;
import createaccount.CreateAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import screenchanger.ChangeScreen;
import sqlmanagement.DBManagment;
import users.Client;
import users.Manager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static Stage myStage = null;
    public static Client acc = new Client();
    public static Manager acc_m = new Manager();

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ChangeScreen screen = new ChangeScreen();

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passField;
    @FXML
    private CheckBox managerButton;
    @FXML
    private CheckBox klientButton;

    /**
     * Funkcja, która sprawdza mozliwość logowania klienta do aplikacji
     *      @param login [String]
     *      @param pass [String]
     *      @return  [boolean]
     *      @see SQLException
     */

    public boolean checkKlientLoginData(String login, String pass) {
        try {
            con = DBManagment.connect();
            String sql = "Select project.zalogujKlienta(?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1,login);
            pst.setString(2,pass);
            pst.execute();

            pst.close();
            con.close();

            return true;

        } catch (SQLException e) {
            AlertBox.errorAlert("Nie udało sie zalogować!", "Nie udało sie zalogować " + e.getMessage());
        }  finally {
            DBManagment.closeAll(con, res, pst);
        }
        return false;
    }

    /**
     * Funkcja, która dostaje adres danego klienta
     *      @see Exception
     */

    public void setClientAddress(){
        try {
            con = DBManagment.connect();
            String sql = "SELECT adres, miasto, \"kodPocztowy\" FROM project.klienci where id_klienta=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, acc.getClientId());
            res = pst.executeQuery();

            while(res.next()){
                acc.setAddress(res.getString("adres"));;
                acc.setCity(res.getString("miasto"));
                acc.setZip(res.getString("kodPocztowy"));
            }

            res.close();
            pst.close();
            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Funkcja, która sprawdza mozliwość logowania menedżera do aplikacji
     *      @param login [String]
     *      @param pass [String]
     *      @return  [boolean]
     *      @see SQLException
     */

    public boolean checkManagerLoginData(String login, String pass) {
        try {
            con = DBManagment.connect();
            String sql = "Select project.zalogujMenadzera(?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1,login);
            pst.setString(2,pass);
            pst.execute();

            pst.close();
            con.close();
            System.out.println("checkManager");

            return true;

        } catch (SQLException e) {
            AlertBox.errorAlert("Nie udało sie zalogować!", "Nie udało sie zalogować " + e.getMessage());
        } finally {
            DBManagment.closeAll(con, res, pst);
        }
        return false;
    }

    /**
     * Funkcja która loguje uzytkownika w zalezności od wybranej roli
     *      @param event [MouseEvent]    -   zdarzenie
     *      @see Exception
     */

    public void loginAccount(MouseEvent event) throws IOException{
        String login = loginField.getText();
        String pass = passField.getText();
        if(klientButton.isSelected()) {
            if (checkKlientLoginData(login, pass)) {
                try {
                    con = DBManagment.connect();
                    String sql = "select * from project.logowanie_klienta where login=? and haslo=?;";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, loginField.getText());
                    pst.setString(2, passField.getText());
                    res = pst.executeQuery();

                    if (res.next()) {
                        try {
                            acc.setClientId(res.getInt("id_klienta"));
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        System.out.println(acc.getClientId());
                    }
                    res.close();
                    pst.close();
                    con.close();

                    setClientAddress();
                    screen.loadClient("/clientdashboard/ClientDashboard.fxml", event, myStage, acc);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (managerButton.isSelected()) {
            if (checkManagerLoginData(login, pass)) {
                try {
                    con = DBManagment.connect();
                    String sql = "select * from project.logowanie where login=? and haslo=?;";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, loginField.getText());
                    pst.setString(2, passField.getText());
                    res = pst.executeQuery();

                    if (res.next()) {
                        acc_m.setManagerID(res.getInt("id_pracownika"));
                        System.out.println(acc_m);
                    }
                    res.close();
                    pst.close();
                    con.close();
                    try {
                        screen.loadManager("/admindashboard/AdminDashboard.fxml", event, myStage, acc_m);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    /**
     * Metoda, przekierowująca użytkownika do okna Rejestracji konta
     * @param event [MouseEvent]
     * @see IOException
     */

    @FXML
    private void createAccount(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(CreateAccountController.class.getResource("/createaccount/CreateAccount.fxml")));
        Scene scene = new Scene(fxml);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
        });

    }
}