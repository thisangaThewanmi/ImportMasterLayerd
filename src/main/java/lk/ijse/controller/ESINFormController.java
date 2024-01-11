package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.EngineerBO;
import lk.ijse.bo.PlaceEngineerStockBO;
import lk.ijse.bo.StockBO;
import lk.ijse.dao.custom.Impl.EngineerStockDaoImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.CartTM;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ESINFormController {
    public Label txtId;
    public JFXComboBox<EngineerDTO> cmbEngineer;
    public JFXComboBox<StockDto> cmbProduct;
    public JFXTextField txtEmpName;
    public Label txtDesc;
    public Label txtPrice;
    public Label txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXButton btnAddToTable;
    public TableView<CartTM> tblCart;
    public TableColumn<CartTM,String> colCode;
    public TableColumn<CartTM,String> colDescription;
    public TableColumn<CartTM,Integer> colQty;
    public TableColumn<CartTM,Double> colUnitPrice;
    public TableColumn<CartTM,Double> colTotal;
    public TableColumn<CartTM, Button> colAction;

    PlaceEngineerStockBO placeEngineerStockBO = (PlaceEngineerStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ENGINEER_STOCK);


    public void initialize(){
        setNewId();
        setComboBox();
        setCellValues();
        btnAddToTable.setDisable(true);
        cmbEngineer.setConverter(new StringConverter<EngineerDTO>() {
            @Override
            public String toString(EngineerDTO employeeDto) {
                return employeeDto==null ? "" : employeeDto.getEId()+" : "+employeeDto.getName();
            }

            @Override
            public EngineerDTO fromString(String s) {

                return null;
            }
        });

        cmbProduct.setConverter(new StringConverter<StockDto>() {
            @Override
            public String toString(StockDto dto) {
                return dto==null ? "" : dto.getId()+" : "+dto.getName();
            }

            @Override
            public StockDto fromString(String s) {
                return null;
            }
        });

    }
    public void setNewId(){
        String s = null;
        try {
            s = placeEngineerStockBO.generateNewId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
        txtId.setText(s);
    }

    public void setComboBox(){
        List<EngineerDTO> list = null;
        try {
            list = placeEngineerStockBO.getAllEngineers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
        ObservableList<EngineerDTO> n_list = FXCollections.observableArrayList(list);

        cmbEngineer.setItems(n_list);


        List<StockDto> allStocks = null;
        try {
            allStocks = placeEngineerStockBO.getAllStock();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
        ObservableList<StockDto> n_list1 = FXCollections.observableArrayList(allStocks);
        cmbProduct.setItems(n_list1);

    }


    public void cmbEMployeeOnAction(ActionEvent actionEvent) {
        EngineerDTO selectedItem = cmbEngineer.getSelectionModel().getSelectedItem();
        if (selectedItem==null)return;
        txtEmpName.setText(selectedItem.getName());
    }

    public void cmdProductOnAction(ActionEvent actionEvent) {
        StockDto selectedItem = cmbProduct.getSelectionModel().getSelectedItem();
        if (selectedItem==null)return;
        txtDesc.setText(selectedItem.getName());
        txtQtyOnHand.setText(String.valueOf(selectedItem.getQty()));
        txtPrice.setText(String.valueOf(selectedItem.getPrice()));

    }

    public void txtQtyOnKeyReleaseAction(KeyEvent keyEvent) {
        boolean b = Regex.setTextColor(TextFields.INTEGER, txtQty);

        try {
            if (b) {
                int qt = Integer.parseInt(txtQty.getText());
                int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
                if (qt<=qtyOnHand){
                    btnAddToTable.setDisable(false);
                }else {
                    btnAddToTable.setDisable(true);
                }
            } else {

            }
        }catch (NumberFormatException e){
            btnAddToTable.setDisable(true);
        }

    }

    public void setCellValues(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {
        String code = cmbProduct.getSelectionModel().getSelectedItem().getId();
        int qty = Integer.parseInt(txtQty.getText());
        StockDto selectedItem = cmbProduct.getSelectionModel().getSelectedItem();
        selectedItem.setQty(Integer.parseInt(String.valueOf(Integer.parseInt(String.valueOf(selectedItem.getQty()))-qty)));
        for (CartTM item : tblCart.getItems()) {
            if (item.getProductId().equals(code)){
                item.setQty(item.getQty()+qty);
                tblCart.refresh();
                return;
            }
        }
        String description = txtDesc.getText();
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = qty*unitPrice;
        Button btn = new Button("Delete");
        CartTM tm = new CartTM(code,description,qty,unitPrice,total,btn);
        tblCart.getItems().add(tm);
        btn.setOnAction(e->{
            tblCart.getItems().remove(tm);
            selectedItem.setQty(Integer.parseInt(String.valueOf(Integer.parseInt(String.valueOf(selectedItem.getQty()))+qty)));
        });

        txtDesc.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        btnAddToTable.setDisable(true);
        txtQtyOnHand.setText("");
        cmbProduct.getSelectionModel().clearSelection();

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            List<CartTM> items = tblCart.getItems();
            List<EngineerStockDetailsDto> list = new ArrayList<>();
            for (CartTM item : items) {
                String code = item.getProductId();
                int qty = item.getQty();
                double total = item.getTotal();

                EngineerStockDetailsDto engineerStockDetailsDto = new EngineerStockDetailsDto();
                engineerStockDetailsDto.setEsinId(txtId.getText());
                engineerStockDetailsDto.setStockId(code);
                engineerStockDetailsDto.setQty(qty);
                engineerStockDetailsDto.setTotal(total);

                list.add(engineerStockDetailsDto);
                //ESINModel.saveOrder(item);
            }
            EngineerStockDto engineerStockDto = new EngineerStockDto();
            engineerStockDto.setEsinId(txtId.getText());
            engineerStockDto.setDate(Date.valueOf(LocalDate.now()));
            engineerStockDto.setEmpId(cmbEngineer.getSelectionModel().getSelectedItem().getEId());
            engineerStockDto.setDetailsList(list);

            boolean isIssued = placeEngineerStockBO.issueStock(engineerStockDto);
            if (isIssued){
                new Alert(Alert.AlertType.INFORMATION, "Issue Success").show();;
                clearALl();

            }else {
                new Alert(Alert.AlertType.ERROR, "Issue Failed").show();
            }


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void clearALl(){
        txtId.setText("");
        txtEmpName.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtQtyOnHand.setText("");
        btnAddToTable.setDisable(true);
        tblCart.getItems().clear();
        cmbEngineer.getSelectionModel().clearSelection();
        cmbProduct.getSelectionModel().clearSelection();
        setNewId();
    }

}
