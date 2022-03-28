package inputchecker;

import alerts.AlertBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckField {
    /**
     * Funkcja, która sprawdza poprawność wprowadzonego imienia lub nazwiska
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkFullnameField(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Puste pole!", "Puste pole, wprowadź dane");
            return false;
        } else {
            Pattern p = Pattern.compile("[a-zA-Z]+");
            Matcher m = p.matcher(tmpField.getText());

            if(m.find() && m.group().equals(tmpField.getText())) {
                return true;
            } else {
                AlertBox.errorAlert("Twoje pole z imieniem lub nazwiskiem jest nieprawidłowe", "Proszę korzystać tylko ze znaków. Sprobuj jeszcze raz!");
                return false;
            }
        }

    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego emaila
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkEmail(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Brak tekstu w pole mail", "Wpisz swoje hasło");
            return false;
        }
        Pattern p = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+
                                    "[a-zA-Z0-9_+&*-]+)*@" +
                                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                    "A-Z]{2,7}$");
        Matcher m = p.matcher(tmpField.getText());

        if(m.find()) {
            return true;
        } else {
            AlertBox.errorAlert("Twoje pole z mailem jest nieprawidłowe", "Przykładowy email: hicks@example.com. Sprobuj jeszcze raz!");
            return false;
        }
    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego numeru
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkNumberField(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Brak tekstu w pole number", "Wpisz swoje telefon");
            return false;
        }

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tmpField.getText());

        if(m.find() && m.group().equals(tmpField.getText()) && tmpField.getText().length() == 9) {
            return true;
        } else {
            AlertBox.errorAlert("Twoje pole z numerem jest nieprawidłowe", "Proszę wpisać tylko cyfry lub sprawdzić długość (wymagana długość - 9). Sprobuj jeszcze raz!");
            return false;
        }
    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonej ulicy
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkAddressField(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Puste pole!", "Puste pole, wprowadź dane");
            return false;
        } else {
            Pattern p = Pattern.compile("[A-Za-z0-9'\\.\\-\\s\\,]");
            Matcher m = p.matcher(tmpField.getText());

            if(m.find()) {
                return true;
            } else {
                AlertBox.errorAlert("Twoje pole z adresem jest nieprawidłowe", "Sprobuj jeszcze raz!");
                return false;
            }
        }

    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego miasta
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkCityField(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Puste pole!", "Puste pole, wprowadź dane");
            return false;
        } else {
            Pattern p = Pattern.compile("[^\\d]+");
            Matcher m = p.matcher(tmpField.getText());

            if(m.find()) {
                return true;
            } else {
                AlertBox.errorAlert("Twoje pole z miastem jest nieprawidłowe", "Sprobuj jeszcze raz!");
                return false;
            }
        }

    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego kodu pocztowego
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkZipCodeField(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Puste pole!", "Puste pole, wprowadź dane");
            return false;
        } else {
            Pattern p = Pattern.compile("\\d{2}-\\d{3}");
            Matcher m = p.matcher(tmpField.getText());

            if(m.find()) {
                return true;
            } else {
                AlertBox.errorAlert("Twoje pole z kodem pocztowym jest nieprawidłowe", "Sprobuj jeszcze raz!");
                return false;
            }
        }

    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego loginu
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkLogin(TextField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Brak tekstu w pole login", "Wpisz swój login");
            return false;
        }
        return true;
    }

    /**
     * Funkcja, która sprawdza poprawność wprowadzonego hasła
     *      @param tmpField [TextField]
     *      @return  [boolean]
     */

    public static boolean checkPass(PasswordField tmpField) {
        if (tmpField.getText().equals("")) {
            AlertBox.errorAlert("Brak tekstu w pole hasło", "Wpisz swoje hasło");
            return false;
        }
        return true;
    }
}
