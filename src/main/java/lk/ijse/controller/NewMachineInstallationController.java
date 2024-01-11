package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import lk.ijse.bo.*;
import lk.ijse.dto.*;
import lk.ijse.dao.*;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Double.valueOf;

public class NewMachineInstallationController {

    public Label txtOrderId;
    public JFXComboBox cmbMachine;
    public JFXComboBox cmbCustomer;
    public Label lblCustomerName;
    public Label lblEngName;
    public Label lblMachineName;

    public Label lblDate;
    public JFXComboBox cmbEngineer;
    public JFXTextField txtPrice;


    PlaceMachineInstallationBO placeMachineInstallationBO = (PlaceMachineInstallationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_MACHINE_INSTALLATION);


    public void initialize() throws SQLException {
        setDate();
        loadMachieneIds();
        loadCustomerIds();
        loadEngineerIds();
        generateNextMachineInstallationId();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        String MIid = txtOrderId.getText();
        String machineId = (String) cmbMachine.getValue();
        String machineName = lblMachineName.getText();
        String customerId = (String) cmbCustomer.getValue();
        String customerName = lblCustomerName.getText();
        String engineerId = (String) cmbEngineer.getValue();
        String engineerName = lblEngName.getText();
        Double price = Double.valueOf(txtPrice.getText());
        LocalDate date = LocalDate.parse(lblDate.getText());




        var machineInstallDto = new MachineInstallDto(MIid,date, machineId, machineName, customerId, customerName, engineerId, engineerName, price);
        boolean isSuccess = placeMachineInstallationBO.placeInstallation(machineInstallDto);


    }

    public void cmbMahineOnAction(ActionEvent actionEvent) {
        String id = (String) cmbMachine.getValue();

        Machine machine = null;
        try {
            machine = placeMachineInstallationBO.searchMachine(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        lblMachineName.setText(machine.getName());

    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomer.getValue();
//        CustomerModel customerModel = new CustomerModel();
        Customer customer = null;
        try {
            customer = placeMachineInstallationBO.searchCustomer(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lblCustomerName.setText(customer.getName());

    }


  public void cmbEngOnAction(ActionEvent actionEvent) {
        String id = (String) cmbEngineer.getValue();
//        CustomerModel customerModel = new CustomerModel();
      Engineer engineer = null;
      try {
          engineer = placeMachineInstallationBO.searchEngineer(id);
      } catch (SQLException e) {
          new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
      } catch (ClassNotFoundException e) {
          new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
      }

      lblEngName.setText(engineer.getName());

  }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<CustomerDto> idList = null;
        try {
            idList = placeMachineInstallationBO.getAllCustomers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        for (CustomerDto dto : idList) {
            obList.add(dto.getId());
        }

        cmbCustomer.setItems(obList);
    }


    private void loadMachieneIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<MachineDto> idList = null;
        try {
            idList = placeMachineInstallationBO.getAllMachine();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        for (MachineDto dto : idList) {
            obList.add(dto.getId());
        }

        cmbMachine.setItems(obList);
    }

    private void loadEngineerIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<EngineerDTO> idList = null;
        idList = placeMachineInstallationBO.getAllEngineers();

        for (EngineerDTO dto : idList) {
            obList.add(dto.getEId());
        }

        cmbEngineer.setItems(obList);
    }

    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextMachineInstallationId() throws SQLException {
        String MachInstId = null;
        try {
            MachInstId = placeMachineInstallationBO.generateNextMachineInstallationId();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtOrderId.setText(MachInstId);
    }



}
