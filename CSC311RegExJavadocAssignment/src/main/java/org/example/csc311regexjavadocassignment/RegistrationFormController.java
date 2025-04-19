package org.example.csc311regexjavadocassignment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.util.regex.Pattern;

public class RegistrationFormController {

    @FXML private TextField firstNameField, lastNameField, emailField, dobField, zipField;
    @FXML private Label firstNameError, lastNameError, emailError, dobError, zipError;
    @FXML private Button addButton;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,25}$");
    private static final Pattern DOB_PATTERN = Pattern.compile("^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@farmingdale\\.edu$");
    private static final Pattern ZIP_PATTERN = Pattern.compile("^\\d{5}$");

    @FXML
    public void initialize() {
        setErrorLabelColor();
        addListeners();
        addButton.setDisable(true);
    }

    private void addListeners() {
        firstNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validate(firstNameField, NAME_PATTERN, firstNameError, "Invalid first name");
            toggleButton();
        });
        lastNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validate(lastNameField, NAME_PATTERN, lastNameError, "Invalid last name");
            toggleButton();
        });
        emailField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validate(emailField, EMAIL_PATTERN, emailError, "Use @farmingdale.edu email only");
            toggleButton();
        });
        dobField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validate(dobField, DOB_PATTERN, dobError, "Date must be MM/DD/YYYY");
            toggleButton();
        });
        zipField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validate(zipField, ZIP_PATTERN, zipError, "Zip must be 5 digits");
            toggleButton();
        });
    }

    private void validate(TextField field, Pattern pattern, Label errorLabel, String errorMessage) {
        if (!pattern.matcher(field.getText()).matches()) {
            errorLabel.setText(errorMessage);
        } else {
            errorLabel.setText("");
        }
    }

    private void setErrorLabelColor() {
        firstNameError.setTextFill(Color.RED);
        lastNameError.setTextFill(Color.RED);
        emailError.setTextFill(Color.RED);
        dobError.setTextFill(Color.RED);
        zipError.setTextFill(Color.RED);
    }

    private void toggleButton() {
        addButton.setDisable(
                !NAME_PATTERN.matcher(firstNameField.getText()).matches() ||
                        !NAME_PATTERN.matcher(lastNameField.getText()).matches() ||
                        !EMAIL_PATTERN.matcher(emailField.getText()).matches() ||
                        !DOB_PATTERN.matcher(dobField.getText()).matches() ||
                        !ZIP_PATTERN.matcher(zipField.getText()).matches()
        );
    }

    @FXML
    public void handleAdd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Registration Successful!");
        alert.showAndWait();
    }
}

