package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.supDto;

import lk.ijse.dto.tm.CustomerTM;
import lk.ijse.dto.tm.SupplierTm;
import lk.ijse.model.CusModel;
import lk.ijse.model.supModel;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtTel;
    public JFXTextField txtAddress;
    public TableView <SupplierTm>tblSupplier;
    public TableColumn <SupplierTm,String>colId;
    public TableColumn <SupplierTm,String>colName;
    public TableColumn<SupplierTm,String> colAddress;
    public TableColumn <SupplierTm,String>colTel;
    public TableColumn <SupplierTm,Button>colOption;
    private supModel supModel = new supModel();



    public void initialize() {
        setCellValues();
        try {
            setTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTableData() throws SQLException {
        //tblCustomer.setItems(FXCollections.observableArrayList(CusModel.getAllCustomers().stream().map(e -> new CustomerTM(e.getId(), e.getName(), e.getAddress(), e.getTel(), new Button())).collect(Collectors.toList()))); ;
        List<SupplierTm> list = new ArrayList<>();
        List<supDto> allSuppliers = supModel.getAllSuppliers();
        System.out.println(allSuppliers.size());
        for (supDto allSupplier: allSuppliers) {
            SupplierTm supplierTm = new SupplierTm();
            supplierTm.setId(allSupplier.getId());
            supplierTm.setName(allSupplier.getName());
            supplierTm.setAddress(allSupplier.getAddress());
            supplierTm.setTel(allSupplier.getTel());
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                    boolean isDeleted = supModel.deleteSupplier(supplierTm.getId());
                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            });
            button.getStyleClass().add("delete-button");
            supplierTm.setOption(button);
            list.add(supplierTm);
        }
        ObservableList<SupplierTm> supplierTms = FXCollections.observableArrayList(list);
        tblSupplier.setItems(supplierTms);
    }

    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
    }





    public void btnSaveOnAction(ActionEvent actionEvent)  {

        if (!validate()){
            new Alert(Alert.AlertType.ERROR,"Please Provide All Information").showAndWait();
            return;
        }

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        var dto = new supDto(id, name, address, tel);


        try {
          boolean  isSaved = supModel.SaveSupplier(dto);

          if(isSaved){
              new Alert(Alert.AlertType.CONFIRMATION,"supplier Saved").show();
          }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }





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


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        var dto = new supDto(id, name, address, tel);

//        var model = new CustomerModel();
        try {
            boolean isUpdated = supModel.updateCustomer(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

  /*  public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = supModel.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/


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

    public boolean validate(){
        return Regex.setTextColor(TextFields.ID,txtId)&&
                Regex.setTextColor(TextFields.NAME,txtName)&&
                Regex.setTextColor(TextFields.ADDRESS,txtAddress)&&
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


}

