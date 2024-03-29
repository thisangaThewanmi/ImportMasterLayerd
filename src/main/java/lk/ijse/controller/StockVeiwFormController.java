package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.StockBO;
import lk.ijse.dto.StockDto;
import lk.ijse.dto.tm.StockTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockVeiwFormController {
    public TableView<StockTM> tblStock;

    public TableColumn<StockTM, String> colId;

    public TableColumn <StockTM, String> colName;
    public TableColumn <StockTM, Integer>colQty;
    public TableColumn <StockTM, Double>colPrice;

    public TableColumn<StockTM, Button> colOption;
    public Label lblStock;

    StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);


    public void initialize() throws SQLException {
        setCellValues();
        setDataStock();
        try {
            setTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDataStock() throws SQLException {
        ResultSet resultSet = stockBO.countStock();
        lblStock.setText(String.valueOf(resultSet));
    }

    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
    }

    public void setTableData() throws SQLException, ClassNotFoundException {

        List<StockTM> list = new ArrayList<>();
        List<StockDto>allStocks = stockBO.getAllStock();

        for (StockDto allStock : allStocks) {
            StockTM stockTM = new StockTM();
            stockTM.setId(allStock.getId());
            stockTM.setName(allStock.getName());
            stockTM.setQty(Integer.parseInt(String.valueOf(allStock.getQty())));
            stockTM.setPrice(Double.parseDouble(String.valueOf(allStock.getPrice())));
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                    boolean isDeleted = stockBO.deleteStock(stockTM.getId());

                    if(isDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION, "Stock deleted!").show();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            });
            button.getStyleClass().add("delete-button");
            stockTM.setOption(button);
            list.add(stockTM);
        }
        ObservableList<StockTM> stockTMS = FXCollections.observableArrayList(list);
        tblStock.setItems(stockTMS);
    }
}

