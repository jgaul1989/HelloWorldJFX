package jgaul.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.model.Country;
import jgaul.model.Customer;
import jgaul.model.Division;
import jgaul.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public abstract class Helper {

    public static final ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static final ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static final ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> ukDivision = FXCollections.observableArrayList();
    private static String currentUser;
    private static Customer customerToModify;

    public static void initializeConstantFields() {
        ClientScheduleQuery.selectAllCountries(allCountries);
        ClientScheduleQuery.selectAllDivisions(allDivisions);

        for (Division division : allDivisions) {
            int divisionNum = division.getCountryID();
            switch (divisionNum) {
                case 1 -> americaDivision.add(division);
                case 2 -> ukDivision.add(division);
                case 3 -> canadaDivision.add(division);
            }
        }
    }

    public static boolean checkForBlankString(String errorMessage, String toCheck) {
        if (toCheck.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Text Field is Blank");
            alert.setHeaderText(errorMessage);
            alert.setContentText("Fill in all fields to proceed.");
            alert.show();
            return true;
        }
        return false;
    }

    public static boolean checkForNullValue(String errorMessage, Object object) {
        if (object == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Text Field is Blank");
            alert.setHeaderText(errorMessage);
            alert.setContentText("Fill in all fields to proceed.");
            alert.show();
            return true;
        }
        return false;
    }

    public static LocalDateTime convertToUserTime(LocalDateTime databaseTime) {
        ZonedDateTime step1 = databaseTime.atZone(ZoneId.of("Etc/UTC"));
        ZonedDateTime step2 = step1.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime step3 = step2.toLocalDateTime();
        return step3;
    }

    public static void setCurrentUser(User user) {
        Helper.currentUser = user.getUsername();
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static Customer getCustomerToModify() {
        return customerToModify;
    }

    public static void setCustomerToModify(Customer customerToModify) {
        Helper.customerToModify = customerToModify;
    }
}
