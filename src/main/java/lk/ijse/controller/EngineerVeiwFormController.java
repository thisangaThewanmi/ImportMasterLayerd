package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.EngineerBO;
import lk.ijse.bo.EnginnerBOImpl;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.tm.EnginnerTM;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerVeiwFormController {
    public TableView<EnginnerTM> tblEngineer;
    public TableColumn <EngineerDTO,String>colId;
    public TableColumn <EngineerDTO,String>colName;
    public TableColumn <EngineerDTO,String>colAddress;
    public TableColumn <EngineerDTO,String>colTel;
    public TableColumn <EngineerDTO,Button>colOption;

   EngineerBO engineerBO = (EngineerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENGINNER);

    public void initialize() {
        setCellValues();
        try {
            setTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValues() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
    }

    public void setTableData() throws SQLException, ClassNotFoundException {

        List<EnginnerTM> list = new ArrayList<>();
        List<EngineerDTO>allEngineers = engineerBO.getAllEngineers();

        for (EngineerDTO allEngineer : allEngineers) {
            EnginnerTM enginnerTM = new EnginnerTM();
            enginnerTM.setId(allEngineer.getEId());
            enginnerTM.setName(allEngineer.getName());
            enginnerTM.setAddress(allEngineer.getAddress());
            enginnerTM.setTel(allEngineer.getTel());
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                    boolean isDeleted = engineerBO.deleteEnginner(enginnerTM.getId());

                    if(isDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION, "Engineer deleted!").show();
                    }

                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            });
            button.getStyleClass().add("delete-button");
            enginnerTM.setOption(button);
            list.add(enginnerTM);
        }
        ObservableList<EnginnerTM> enginnerTMS = FXCollections.observableArrayList(list);
        tblEngineer.setItems(enginnerTMS);
    }

}
