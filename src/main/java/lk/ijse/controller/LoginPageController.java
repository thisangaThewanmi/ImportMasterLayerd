package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {


    public TextField txtUserName;
    public TextField txtPassword;
    public JFXButton btnLogin;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException {



        String username = txtUserName.getText();
        String password = txtPassword.getText();



        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fill All Fields").show();
            txtUserName.requestFocus();
            return;
        }

        // Check if the user exists in the database
        LoginModel LoginModel = new LoginModel();
        if (LoginModel.validateUser(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");

            // Event handler for the default "OK" button
            alert.setOnHidden(e -> {
                try {
                    // Load the dashboard content
                    Parent dashboard = FXMLLoader.load(getClass().getResource("/veiw/DashboardForm.fxml"));
                    Scene dashboardScene = new Scene(dashboard);

                    // Get the current stage (login window)
                    Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                    currentStage.setTitle("Dash Board Form");
                    currentStage.setScene(dashboardScene);
                    currentStage.centerOnScreen();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            alert.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            txtUserName.clear();
            txtPassword.clear();
            txtUserName.requestFocus();
        }
    }


    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

