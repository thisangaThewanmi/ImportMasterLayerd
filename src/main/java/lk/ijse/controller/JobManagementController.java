package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JobManagementController {
    public JFXButton test;

    public javafx.scene.layout.Pane Pane;

    public void btnEngineerStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/ESINForm.fxml"));


        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }

    public void btnJobOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/PlaceOrderForm.fxml"));



        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }

    public void btnEnginnerVisitOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/NewMachineInstallation.fxml"));

        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }
}
