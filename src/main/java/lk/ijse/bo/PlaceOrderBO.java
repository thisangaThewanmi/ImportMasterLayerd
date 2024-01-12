package lk.ijse.bo;

import lk.ijse.dto.*;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO  extends SuperBO{

     boolean placeOrder(PlaceOrderDto placeOrderDto);


     String generateNewOrderId() throws SQLException, ClassNotFoundException;

     List<EngineerDTO> getAllEngineers() throws SQLException;

     ArrayList<MachineDto> getAllMachine() throws SQLException;

     List<StockDto> getAllStock() throws SQLException;

     List<CustomerDto> getAllCustomers() throws SQLException;

     Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException;

     Stock searchStock(String id) throws SQLException, ClassNotFoundException;

     Machine searchMachine(String id) throws SQLException, ClassNotFoundException;

     Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;

     ResultSet countOrders() throws SQLException;
}
