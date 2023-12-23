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
import javafx.util.StringConverter;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.CartTM;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.model.*;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;
import static lk.ijse.model.GRNModel.generateNextGRId;

public class GRNFormController {
    public JFXComboBox cmbProduct;
    public JFXTextField txtQty;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn cilUnitPrice;
    public TableColumn colSellingPrice;
    public TableColumn colOption;
    public JFXComboBox cmbSupplier;
    public Label lblNetTotal;
    public Label txtId;
    public Label txtName;
    public JFXTextField txtSellingPrice;
    public JFXTextField txtUnitPrice;
    public Label txtTotal;
    public JFXTextField txtSupplier;
    public JFXButton btnAddToTable;
    public TableColumn colQty;
    public TableView tblGRN;
    public Label txtDate;
    public TableColumn colTotal;

    private ObservableList<GrnTM> oblist = FXCollections.observableArrayList();



    private GRNModel GRNModel = new GRNModel();

    private StockModel stockModel = new StockModel();

    private supModel supModel = new supModel();


    public void initialize() throws SQLException, ClassNotFoundException {
        generateNextGRId();
        loadAllSuppliers();
        loadAllProducts();
        setCellValues();
        setDate();

    }

    private void loadAllProducts() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<StockDto> idList = stockModel.getAllStocks();

        for (StockDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbProduct.setItems(obList);

    }

    private void loadAllSuppliers() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<supDto> idList = supModel.getAllSuppliers();

        for (supDto dto : idList) {
            obList.add(dto.getId());

        }

        cmbSupplier.setItems(obList);

    }

    private void generateNextGRId() {
        try {
            String grnId = GRNModel.generateNextGRId();
            txtId.setText(grnId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cilUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));

    }

    public void cmbSupplierOnAction(ActionEvent actionEvent) {
        String id = (String) cmbSupplier.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            supDto supplierDto = supModel.searchSupplier(id);
            txtSupplier.setText(supplierDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        public void cmdProductOnAction(ActionEvent actionEvent) {
            String id = (String) cmbProduct.getValue();
//        CustomerModel customerModel = new CustomerModel();
            try {
                StockDto stockDto = stockModel.searchStock(id);
                txtName.setText(stockDto.getName());



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }

    public void txtQtyOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER, txtQty);


    }

    public void txtSellingPriceOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE, txtSellingPrice);
    }

    public void txtUnitPriceOnKeyReleaseAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE, txtUnitPrice);

    }


    //Sirge code eka
    public void addToGRNCart(ActionEvent actionEvent) {
        String code = (String) cmbProduct.getSelectionModel().getSelectedItem();
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
                if (oblist.get(i).getStockId().equals(code)) {
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

                    tblGRN.refresh();
                    return;
                }
            }
        }

        var grnTm = new GrnTM(code, description, qty, unitPrice, sellingPrice, tot, btn);
        System.out.println("grnTm = " + grnTm);
        oblist.add(grnTm);

        System.out.println("oblist = "+ oblist);

        tblGRN.setItems(oblist);
        calculateTotal();
        txtQty.clear();


    }









    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblGRN.getSelectionModel().getSelectedIndex();

                tblGRN.getItems().remove(focusedIndex);
                tblGRN.refresh();
                calculateTotal();
            }
        });
    }


   private void setDate() {
      LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(LocalDate.now()));
}

    private void calculateTotal() {
        double tot = 0;
        for (int i = 0; i < tblGRN.getItems().size(); i++) {
            tot += (double) colTotal.getCellData(i);
            System.out.println("tot = " + tot);
        }
        txtTotal.setText(String.valueOf(tot));
    }





    public void btnSaveOnAction(ActionEvent actionEvent) {

        String grnId = txtId.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());

        String supplierId = (String) cmbSupplier.getSelectionModel().getSelectedItem();
        System.out.println(supplierId + "supplierId");
        String supplierName = txtSupplier.getText();
        System.out.println("txtTotal = " + txtTotal.getText());
        Double total = Double.valueOf(txtTotal.getText());

        List<GrnTM> grnTMList = new ArrayList<>();
        for (int i = 0; i < tblGRN.getItems().size(); i++) {
            GrnTM grnTM = oblist.get(i);

            grnTMList.add(grnTM);
        }


        System.out.println("GRN form controller: " + grnTMList);
        var placeGRNdto= new PlaceGRNdto(grnId,date,supplierId,supplierName,total,grnTMList);
        try {
            boolean isSuccess = PlaceGRNModel.placeGrnOrder(placeGRNdto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Good Recieve Note Success!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }



}
