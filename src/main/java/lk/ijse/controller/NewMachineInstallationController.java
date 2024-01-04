package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.CustomerBO;
import lk.ijse.bo.EngineerBO;
import lk.ijse.bo.EnginnerBOImpl;
import lk.ijse.dto.*;
import lk.ijse.dao.*;

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


    private MachineModel machineModel = new MachineModel();

    EngineerBO engineerBO = (EngineerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENGINNER);
  CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);



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
        boolean isSuccess = PlaceMachineInstallionModel.placeInstallation(machineInstallDto);


    }

    public void cmbMahineOnAction(ActionEvent actionEvent) {
        String id = (String) cmbMachine.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            MachineDto machineDto = MachineModel.searchMachine(id);
            lblMachineName.setText(machineDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   /* public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomer.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            CustomerDto customerDto = CustomerDaoImpl.searchCustomer(id);
            lblCustomerName.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


   /* public void cmbEngOnAction(ActionEvent actionEvent) {
        String id = (String) cmbEngineer.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            EngineerDTO engineerDto = engineerBO.searchEngineer(id);

            lblEngName.setText(engineerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> idList = customerBO.getAllCustomers();

            for (CustomerDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbCustomer.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }


    private void loadMachieneIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<MachineDto> idList = machineModel.getAllMachines();

            for (MachineDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbMachine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEngineerIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<EngineerDTO> idList = null;
        try {
            idList = engineerBO.getAllEngineers();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

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
        String MachInstId = MachineInstallationModel.generateNextMachineInstallationId();
        txtOrderId.setText(MachInstId);
    }



}
