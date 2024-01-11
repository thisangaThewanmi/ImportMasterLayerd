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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.MachineBo;
import lk.ijse.bo.SupplierBO;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.PlaceMrnDto;
import lk.ijse.dto.supDto;
import lk.ijse.dto.tm.MrnTM;
import lk.ijse.bo.PlaceMRNBoImpl;
import lk.ijse.entity.Machine;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;

public class MachineGRNFormController {
    public TableView tblMachineGRN;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn cilUnitPrice;
    public TableColumn colSellingPrice;
    public TableColumn colTotal;
    public TableColumn colOption;
    public JFXTextField txtSellingPrice;
    public Label txtDate;
    public JFXComboBox cmbSupplier;
    public Label txtId;
    public JFXComboBox cmbMachine;
    public Label txtTotal;
    public Label txtName;
    public Label lblNetTotal;
    public JFXTextField txtQty;
    public JFXButton btnAddToTable;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtSupplier;


    PlaceMRNBoImpl placeMRNBo = (PlaceMRNBoImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_MRN);


    private ObservableList<MrnTM> oblist = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        generateNextMRId();
        loadAllSuppliers();
        loadAllMachines();
        setCellValues();
        setDate();

    }

    private void generateNextMRId() {
        String mrnId = null;
        try {
            mrnId = placeMRNBo.nextMachineId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtId.setText(mrnId);
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValues() {
        colId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cilUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));

    }

    private void loadAllSuppliers() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<supDto> idList = null;
        idList = placeMRNBo.getAllSuppliers();

        for (supDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbSupplier.setItems(obList);

    }

    private void loadAllMachines() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<MachineDto> idList = null;
        idList = placeMRNBo.getAllMachine();

        for (MachineDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbMachine.setItems(obList);

    }

   /* public void cmbSupplierOnAction(ActionEvent actionEvent) throws SQLException {
        String id = (String) cmbSupplier.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            supDto supplierDto = supplierBO(id);
            txtSupplier.setText(supplierDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/

    public void cmbMachineOnAction(ActionEvent actionEvent) {
        String id = (String) cmbMachine.getValue();
//        CustomerModel customerModel = new CustomerModel();
        Machine machine = null;
        try {
            machine = placeMRNBo.searchMachine(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtName.setText(machine.getName());

    }

    public void txtSellingPriceOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE, txtSellingPrice);
    }





    public void txtQtyOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER, txtQty);
    }



    public void txtUnitPriceOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE, txtUnitPrice);
    }

    public void addToMachineGRNCart(ActionEvent actionEvent) {
        String code = (String) cmbMachine.getSelectionModel().getSelectedItem();
        String description = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = parseDouble(txtUnitPrice.getText());
        double sellingPrice = parseDouble(txtSellingPrice.getText());
        double tot = qty * unitPrice;


        System.out.println(code);
        System.out.println(description);
        System.out.println(qty);
        System.out.println(unitPrice);
        System.out.println(sellingPrice);
        System.out.println(tot);


        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);




        if (!oblist.isEmpty()) {
            for (int i = 0; i < oblist.size(); i++) {
                if (oblist.get(i).getMachineId().equals(code)) {
                    int col_qty = oblist.get(i).getQty();
                    double col_selling = oblist.get(i).getSellingPrice();
                    double col_unit = oblist.get(i).getUnitPrice();
                    double col_tot = oblist.get(i).getTotal();

                    qty += col_qty;
                    tot = unitPrice * qty;

                    oblist.get(i).setQty(qty);
                    oblist.get(i).setSellingPrice(col_selling);
                    oblist.get(i).setUnitPrice(col_unit);
                    oblist.get(i).setTotal(tot);
                    calculateTotal();

                    tblMachineGRN.refresh();
                    return;
                }
            }
        }


        var MachineGrnTM = new MrnTM(code, description, qty, unitPrice, sellingPrice, tot, btn);
        System.out.println("MachinegrnTm = " + MachineGrnTM);
        oblist.add(MachineGrnTM);

        System.out.println("oblist = "+ oblist);

        tblMachineGRN.setItems(oblist);
        calculateTotal();
        txtQty.clear();



    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        String mrnId = txtId.getText();
        String supplierId = (String) cmbSupplier.getSelectionModel().getSelectedItem();
        String supplierName = txtSupplier.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());
        double total = Double.parseDouble(txtTotal.getText());

        System.out.println("mrnId = " + mrnId);
        System.out.println("supplierId = " + supplierId);
        System.out.println("supplierName = " + supplierName);
        System.out.println("date = " + date);
        System.out.println("total = " + total);

        List<MrnTM> machineTmList = new ArrayList<>();
        for (int i = 0; i < tblMachineGRN.getItems().size(); i++) {
            MrnTM mrnTM = oblist.get(i);

            machineTmList.add(mrnTM);
        }

        System.out.println("Place order form controller: " + machineTmList);
        var placeMrnDto = new PlaceMrnDto(mrnId, date, supplierId, supplierName, total, machineTmList);
        boolean isSuccess = placeMRNBo.placeMRNOrder(placeMrnDto);
        if (isSuccess) {
            new Alert(Alert.AlertType.CONFIRMATION, "Machine Recieve Success!").show();
        }


    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblMachineGRN.getSelectionModel().getSelectedIndex();

                tblMachineGRN.getItems().remove(focusedIndex);
                tblMachineGRN.refresh();
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double tot = 0;
        for (int i = 0; i < tblMachineGRN.getItems().size(); i++) {
            tot += (double) colTotal.getCellData(i);
            System.out.println("tot = " + tot);
        }
        txtTotal.setText(String.valueOf(tot));
    }


}
