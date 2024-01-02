package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.dao.*;

import java.io.IOException;
import java.sql.SQLException;

public class DashBoardController {

    public Pane timePane;
    public Label lblSuppliers;
    public Label lblOrders;
    public Label lblMachine;
    public Label lblCustomer;

    public Label lblMachines;
    public Label lblStock;

    @FXML
    private Pane root;

    private supModel supplierModel = new supModel();

    private CustomerDaoImpl customerModel = new CustomerDaoImpl();

    private MachineModel machineModel = new MachineModel();

    private StockModel stockModel = new StockModel();

    private OrderModel orderModel = new OrderModel();


    public void initialize() throws SQLException {
        setDataSuppliers();
        setDataCustomers();
       // setDataMachiens();
       // setDataStock();
        setDataOrders();



    }

    private void setDataOrders() throws SQLException {
        lblOrders.setText(String.valueOf(orderModel.countOrders()));
    }

    private void setDataStock() throws SQLException {
        lblStock.setText(String.valueOf(stockModel.countStock()));
    }

    private void setDataMachiens() throws SQLException {
        lblMachines.setText(String.valueOf(machineModel.countMachines()));
    }

    private void setDataCustomers() throws SQLException {

        lblCustomer.setText(String.valueOf(customerModel.countCustomers()));
    }

    private void setDataSuppliers() throws SQLException {
        lblSuppliers.setText(String.valueOf(supModel.countSuppliers()));
    }

   /* private void setTimeDashboard(){

            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            timePane.setText(format.format(date));

            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                timePane.setText(LocalTime.now().format(format2));
            }), new KeyFrame(Duration.seconds(1))
            );

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }*/



    private void initializeDashboard() throws IOException {

        //this.root.getChildren().clear();
       // this.root.getChildren().add(node);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/DashboardForm.fxml"));
        Stage window = (Stage) root.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        window.close();
        stage.show();
    }

    @FXML
    void CustomerBtnOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/customerForm.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {

        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/SupplierForm.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);

    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/StockForm.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/EmployeeForm.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);
    }

    public void btnJobOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/JobManagement.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);

    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }


   /* public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/ReportForm.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);
    }*/
}
