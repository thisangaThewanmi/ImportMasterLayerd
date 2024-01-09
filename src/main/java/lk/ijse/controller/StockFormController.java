package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.MachineBo;
import lk.ijse.bo.StockBO;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.StockDto;


import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

public class StockFormController {

    StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXComboBox <String>cmbType;
    public javafx.scene.layout.Pane Pane;

    MachineBo machineBo = (MachineBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MACHINE);


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
                boolean isSaved = stockBO.saveStock(dto);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"stock sucessfully added").showAndWait();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Error occued").showAndWait();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();

            }
        }

        if(value==1) {
            var dto = new MachineDto(id,name,qty,price);

            try {
                boolean isSaved = machineBo.saveMachine(dto);

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Machine sucessfully added").showAndWait();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            } catch (ClassNotFoundException e) {
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
        int type = cmbType.getSelectionModel().getSelectedIndex();


        /*var dto = new StockDto(id,name,qty,price);

        try {
            boolean isSaved =  .updateStock(dto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"stock sucessfully updated").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }*/

        if(type==-1){
            new Alert(Alert.AlertType.ERROR,"Please Select Type").show();
            return;
        }

        if(type==0){
            var dto = new StockDto(id,name,qty,price);
            try {
                boolean isUpdated = stockBO.updateStock(dto);

                if(isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Stock Updated Successfully").showAndWait();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }

        if(type==1) {
            var dto = new MachineDto(id, name, qty, price);
            try {
                boolean isUpdated =machineBo.updateMachine(dto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Machine Updated Successfully").showAndWait();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();

            }

        }
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = stockBO.deleteStock(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Removed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "stock not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
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

