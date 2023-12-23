package lk.ijse.controller;

import com.ctc.wstx.shaded.msv_core.grammar.BinaryExp;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.StockDto;
import lk.ijse.model.CusModel;
import lk.ijse.model.MachineModel;
import lk.ijse.model.StockModel;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

public class StockFormController {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXComboBox <String>cmbType;
    public javafx.scene.layout.Pane Pane;


    public void initialize() {
        String [] types = {"Stock", "Machine"};
        cmbType.setItems(FXCollections.observableArrayList(types));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String id= txtId.getText();
        String name= txtName.getText();
        int qty= Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtUnitPrice.getText());


         int value = cmbType.getSelectionModel().getSelectedIndex();

        if(value==-1){
            new Alert(Alert.AlertType.ERROR,"Please select type").showAndWait();
            return;
        }

        if(value==0){
            var dto = new StockDto(id,name,qty,price);

            try {
                boolean isSaved =  StockModel.saveStock(dto);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"stock sucessfully added").showAndWait();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Error occued").showAndWait();
            }
        }

        if(value==1) {
            var dto = new MachineDto(id,name,qty,price);

            try {
                boolean isSaved = MachineModel.saveMachine(dto);

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Machine sucessfully added").showAndWait();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }

        }

    public void btnClearPOnAction(ActionEvent actionEvent) {
        clearFeilds();
    }

    private void clearFeilds() {
        txtId.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id= txtId.getText();
        String name= txtId.getText();
        int qty= Integer.parseInt(txtQty.getText());
        double  price = Double.parseDouble(txtUnitPrice.getText());
        String supplier = txtName.getText();


        var dto = new StockDto(id,name,qty,price);

        try {
            boolean isSaved =  StockModel.updateStock(dto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"stock sucessfully updated").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = StockModel.deleteStock(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Removed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "stock not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    public void btnVeiwMachineOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/MachineVeiwForm.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }

    public void btnVeiwStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/StockViewForm.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }



    public void txtIdOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtQty.requestFocus();

    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        txtUnitPrice.requestFocus();

    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ID,txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER,txtQty);
    }

    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtUnitPrice);
    }

    public boolean validate(){
        return Regex.setTextColor(TextFields.ID,txtId)&&
                Regex.setTextColor(TextFields.NAME,txtName)&&
                Regex.setTextColor(TextFields.INTEGER,txtQty)&&
                Regex.setTextColor(TextFields.DOUBLE,txtUnitPrice);
    }

    public void btnGRNStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/GRNForm.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }


    public void btnGRNOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/MachineGRNForm.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(root);
    }
}

