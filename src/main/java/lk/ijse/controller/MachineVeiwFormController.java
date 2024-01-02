package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.tm.MachineTM;
import lk.ijse.dao.MachineModel;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineVeiwFormController {
    public TableView<MachineTM> tblMachine;

    public TableColumn <MachineTM,String>colId;

    public TableColumn <MachineTM,String>colName;
    public TableColumn <MachineTM,Integer>colQty;
    public TableColumn<MachineTM,Double> colPrice;



    public TableColumn <MachineTM, Button>colAction;
    public Label lblMachine;

    private  MachineModel machineModel = new MachineModel();

    public void initialize() throws SQLException {
        setCellValues();
        setMachineCount();
        try {
            setTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setMachineCount() throws SQLException {
        lblMachine.setText(String.valueOf(machineModel.countMachines()));
    }


    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    public void setTableData() throws SQLException, ClassNotFoundException {

        List<MachineTM> list = new ArrayList<>();
        List<MachineDto>allMachines = MachineModel.getAllMachines();

        for (MachineDto allMachine : allMachines) {
            MachineTM machineTM = new MachineTM();
            machineTM.setId(allMachine.getId());
            machineTM.setName(allMachine.getName());
            machineTM.setQty(allMachine.getQty());
            machineTM.setPrice(allMachine.getPrice());
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                    boolean isDeleted = MachineModel.deleteMachine(machineTM.getId());

                    if(isDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION, "Machine deleted!").show();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            });
            button.getStyleClass().add("delete-button");
            machineTM.setOption(button);
            list.add(machineTM);
        }
        ObservableList<MachineTM> machineTMS = FXCollections.observableArrayList(list);
        tblMachine.setItems(machineTMS);
    }


}
