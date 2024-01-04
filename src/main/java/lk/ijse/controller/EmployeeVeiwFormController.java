package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.EmployeeBO;
import lk.ijse.bo.EmployeeBOImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.EmployeeTM;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeVeiwFormController {

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn <EmployeeTM,String>colId;
    public TableColumn <EmployeeTM,String>colName;
    public TableColumn<EmployeeTM,String> colAddress;
    public TableColumn <EmployeeTM,String>colTel;
    public TableColumn <EmployeeTM, Button> colOption;

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
    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
    }

    public void setTableData() throws SQLException, ClassNotFoundException {

        List<EmployeeTM>list = new ArrayList<>();
        List<EmployeeDto>allEmployees = employeeBO.getAllEmployee();

        for (EmployeeDto allEmployee : allEmployees) {
            EmployeeTM employeeTM = new EmployeeTM();
            employeeTM.setId(allEmployee.getId());
            employeeTM.setName(allEmployee.getName());
            employeeTM.setAddress(allEmployee.getAddress());
            employeeTM.setTel(allEmployee.getTel());
            Button button = new Button("Delete");
            button.setOnAction(e -> {
                try {
                  boolean isDeleted = employeeBO.deleteEmployee(employeeTM.getId());

                  if(isDeleted){
                      new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                  }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            });
            button.getStyleClass().add("delete-button");
            employeeTM.setOption(button);
            list.add(employeeTM);
        }
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList(list);
        tblEmployee.setItems(employeeTMS);
    }
    }



