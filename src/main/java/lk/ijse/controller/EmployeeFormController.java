package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dao.EmpModel;
import lk.ijse.dao.EngineerModel;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeFormController {


    public JFXTextField txtId;
    public  JFXTextField txtName;
    public  JFXTextField txtAddress;
    public  JFXTextField txtTel;
    public JFXComboBox<String> cmbType;


    public void initialize() {
        String [] types = {"Engineer", "Employee"};
        cmbType.setItems(FXCollections.observableArrayList(types));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFeilds();
    }

    private void clearFeilds() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = EmpModel.deleteCustomer(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "employee not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        int type = cmbType.getSelectionModel().getSelectedIndex();

        var dto = new EmployeeDto(id, name, address, tel);

//        var model = new CustomerModel();
        try {
            boolean isUpdated = EmpModel.updateEmployee(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address= txtAddress.getText();
        String tel = txtTel.getText();
        int value = cmbType.getSelectionModel().getSelectedIndex();

        if(value==-1){
            new Alert(Alert.AlertType.ERROR,"Please Select Type").show();
            return;
        }

        if(value==0){
            var dto = new EngineerDTO(id,name,address,tel);
            try {
               boolean isSaved = EngineerModel.saveEngineer(dto);

               if(isSaved){
                   new Alert(Alert.AlertType.CONFIRMATION,"Engineer Saved Successfully").showAndWait();
               }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }

        if(value==1) {
            var dto = new EmployeeDto(id, name, address, tel);
            try {
                boolean isSaved = EmpModel.saveEmployee(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved Successfully").showAndWait();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }

        }
    }

    public void veiwEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/EmployeeVeiwForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("EmployeeVeiwForm");
        stage.setScene(new javafx.scene.Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(txtId.getScene().getWindow());
        stage.show();
    }

    public void veiwEnginneerOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/veiw/EnginnerVeiwForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("EnginnerVeiwForm");
        stage.setScene(new javafx.scene.Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(txtId.getScene().getWindow());
        stage.show();
    }


    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ID,txtId);
    }

    public void txtNameOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtAddressOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtContactOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtTel);
    }



    public void txtIdOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtTel.requestFocus();
    }

    public boolean validate(){
        return Regex.setTextColor(TextFields.ID,txtId)&&
                Regex.setTextColor(TextFields.NAME,txtName)&&
                Regex.setTextColor(TextFields.ADDRESS,txtAddress)&&
                Regex.setTextColor(TextFields.PHONE,txtTel);
    }
}
