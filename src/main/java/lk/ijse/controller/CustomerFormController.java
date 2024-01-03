package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.CustomerBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerTM;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtTel;
    public JFXTextField txtAddress;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM,String > colId;
    public TableColumn<CustomerTM,String> colName;
    public TableColumn<CustomerTM,String> colAddress;
    public TableColumn<CustomerTM,String> colTel;
    public TableColumn<CustomerTM, Button> colOption;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    public void initialize() {
        setCellValues();
        try {
            setTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void btnDeleteOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = CusModel.deleteCustomer(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "customer not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        var dto = new CustomerDto(id, name, address, tel);

//        var model = new CustomerModel();
        /*try {
            boolean isUpdated = customerBO.updateCustomer(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/

        try {
            boolean isUpdated = customerBO.updateCustomer(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tblCustomer.refresh();

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

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!validate()){
            new Alert(Alert.AlertType.ERROR,"Please Provide All Information").showAndWait();
            return;
        }

        String id = txtId.getText();
        String name = txtName.getText();
        String address= txtAddress.getText();
        String tel = txtTel.getText();

        var dto = new CustomerDto(id,name,address,tel);

        /*try {
            boolean isSaved = CustomerDaoImpl.saveCustomer(dto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").showAndWait();
                tblCustomer.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"An Error Occured").showAndWait();
        }*/

        try {
            boolean isSaved = customerBO.saveCustomer(dto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").showAndWait();
                tblCustomer.refresh();
            }
        } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTableData() throws SQLException {
        //tblCustomer.setItems(FXCollections.observableArrayList(CusModel.getAllCustomers().stream().map(e -> new CustomerTM(e.getId(), e.getName(), e.getAddress(), e.getTel(), new Button())).collect(Collectors.toList()))); ;
        List<CustomerTM> list = new ArrayList<>();
        List<CustomerDto> allCustomers = null;
        try {
            allCustomers = customerBO.getAllCustomers();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        System.out.println(allCustomers.size());
        for (CustomerDto allCustomer : allCustomers) {
            CustomerTM customerTM = new CustomerTM();
            customerTM.setId(allCustomer.getId());
            customerTM.setName(allCustomer.getName());
            customerTM.setAddress(allCustomer.getAddress());
            customerTM.setTel(allCustomer.getTel());
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                    boolean isDeleted = customerBO.deleteCustomer(allCustomer.getId());

                    if(isDeleted){
                        tblCustomer.refresh();
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                        tblCustomer.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();

                }
            });
            button.getStyleClass().add("delete-button");
            customerTM.setOption(button);
            list.add(customerTM);



        }
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList(list);
        tblCustomer.setItems(customerTMS);
    }

    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
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

  /*  public void customerSearchOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String telNo = txtTel.getText();

        try {
            CustomerDto dto = CustomerDaoImpl.searchCustomer(id);

            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtTel.setText(dto.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Customer not found!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
   }
}
*/

}
