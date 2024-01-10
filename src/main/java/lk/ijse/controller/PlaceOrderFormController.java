package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.*;
import lk.ijse.dao.custom.Impl.OrderDAOImpl;

import lk.ijse.dto.*;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Stock;

import java.time.LocalDate;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class PlaceOrderFormController {
    public JFXComboBox cmbEngineer;
    public JFXTextField txtEngName;
    public JFXComboBox cmbProduct;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;

    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public JFXButton btnAddToTable;
    public Label txtOrderId;
    public Label txtDesc;
    public Label txtPrice;
    public Label txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXTextField txtMachineName;
    public JFXComboBox cmbMachine;
    public JFXTextField txtCustomerName;
    public JFXComboBox cmbCustomer;


    public Label txtTotal;
    public Label lblDate;


    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);


    private ObservableList<StockTM> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        generateNextOrderId();
        setDate();
        //loadCustomerIds();
        loadMachineIds();
        loadEngineerIds();
        loadItemCodes();
    }

    private void loadEngineerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<EngineerDTO> idList = null;
        try {
            idList = placeOrderBO.getAllEngineers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        for (EngineerDTO dto : idList) {
            obList.add(dto.getEId());

        }

        cmbEngineer.setItems(obList);
        System.out.println("loadEngineerIds: " + obList);


    }


    private void loadMachineIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<MachineDto> idList = null;
        placeOrderBO.getAllMachine();

        for (MachineDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbMachine.setItems(obList);
    }


    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("option"));
    }

    private void generateNextOrderId() {
        try {
            String orderId = null;
            try {
                orderId = placeOrderBO.generateNewOrderId();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            txtOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<StockDto> idList = placeOrderBO.getAllStock();

        for (StockDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbProduct.setItems(obList);


    }


    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

     void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> idList = null;
            idList = placeOrderBO.getAllCustomers();

            for (CustomerDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbEngOnAction(ActionEvent actionEvent) {
        String id = (String) cmbEngineer.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            Engineer engineer = placeOrderBO.searchEngineer(id);
            txtEngName.setText(engineer.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void cmbProductOnAction(ActionEvent actionEvent) {
        String id = (String) cmbProduct.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            Stock stock = placeOrderBO.searchStock(id);
            txtDesc.setText(stock.getName());
            txtQtyOnHand.setText(String.valueOf(stock.getQty()));
            txtPrice.setText(String.valueOf(stock.getPrice()));


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {
        String code = (String) cmbProduct.getValue();
        String description = txtDesc.getText();
        int qty = parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblCart.getItems().size(); i++) {
                if (colCode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(tot);

                    calculateTotal();
                    tblCart.refresh();
                    return;
                }
            }
        }
        var stockTM = new StockTM(code, description, qty, unitPrice, tot, btn);

        obList.add(stockTM);

        tblCart.setItems(obList);
        calculateTotal();
        txtQty.clear();

    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblCart.refresh();
                calculateTotal();
            }
        });
    }


    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        txtTotal.setText(String.valueOf(total));
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        String orderId = txtOrderId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());


        String customerId = (String) cmbCustomer.getSelectionModel().getSelectedItem();
        String engineerId = (String) cmbEngineer.getSelectionModel().getSelectedItem();
        String machineId = (String) cmbMachine.getSelectionModel().getSelectedItem();
        String engineerName = txtEngName.getText();
        String machineName = txtMachineName.getText();
        String customerName = txtCustomerName.getText();


        List<StockTM> stockTmList = new ArrayList<>();
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            StockTM stockTM = obList.get(i);

            stockTmList.add(stockTM);
        }

        System.out.println("Place order form controller: " + stockTmList);
        var placeOrderDto = new PlaceOrderDto(orderId, date, customerId, customerName, engineerId, engineerName, machineId, machineName, stockTmList);
        boolean isSuccess = placeOrderBO.placeOrder(placeOrderDto);
        if (isSuccess) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
        }

    }


    public void txtQtyOnKeyReleaseAction(KeyEvent keyEvent) {
    }

    public void cmbMahineOnAction(ActionEvent actionEvent) {
        String id = (String) cmbMachine.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            Machine machine = placeOrderBO.searchMachine(id);
            txtMachineName.setText(machine.getName());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomer.getValue();
//        CustomerModel customerModel = new CustomerModel();
        Customer customer = null;
        try {
            customer = placeOrderBO.searchCustomer(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtCustomerName.setText(customer.getName());

    }
}
