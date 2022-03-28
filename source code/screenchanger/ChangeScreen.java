package screenchanger;

import admindashboard.AdminDashboardController;
import clientdashboard.ClientDashboardController;
import createaccount.CreateAccountController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.Client;
import users.Manager;

import java.io.IOException;
import java.util.Objects;

public class ChangeScreen {

    /**
     * Metoda, przekazująca dane o zalogowanym kliencie
     * @param resource [String]
     * @param event [MouseEvent]
     * @param myStage [Stage]
     * @param acc [Client]
     * @see IOException
     */

    public void loadClient(String resource, MouseEvent event, Stage myStage, Client acc) throws IOException {
        loadScreen(resource, event).<ClientDashboardController>getController().setClient(acc);

    }

    /**
     * Metoda, przekazująca dane o zalogowanym menedżerze
     * @param resource [String]
     * @param event [MouseEvent]
     * @param myStage [Stage]
     * @param acc [Client]
     * @see IOException
     */


    public void loadManager(String resource, MouseEvent event, Stage myStage, Manager acc) throws IOException {
        loadScreen(resource, event).<AdminDashboardController>getController().setManager(acc);
    }

    /**
     * Metoda, przekierowująca użytkownika do okna, ktore jest określone w zmiennej resource
     * @param resource [String]
     * @param event [MouseEvent]
     * @see IOException
     */

    public FXMLLoader loadScreen(String resource, MouseEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sklep internetowy");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        return loader;
    }


    /**
     * Metoda, przekierowująca użytkownika do okna logowania
     * @param event [MouseEvent]
     * @see IOException
     */
    public void logout(MouseEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login/LoginScreen.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Sklep internetowy");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
