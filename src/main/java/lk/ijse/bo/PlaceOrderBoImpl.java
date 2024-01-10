package lk.ijse.bo;

import javafx.scene.control.Alert;
import lk.ijse.dao.*;
import lk.ijse.dao.custom.Impl.OrderDetailDaoImpl;
import lk.ijse.dao.custom.OrderDao;
import lk.ijse.dao.custom.OrderDetailDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBO {

    OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    StockDao stockDao = (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    EngineerDao engineerDao = (EngineerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNER);

    MachineDao machineDao = (MachineDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean placeOrder(PlaceOrderDto dto) {
        Connection connection = null;
        try {
            try {
                connection = DbConnection.getInstance().getConnection();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            connection.setAutoCommit(false);

            boolean isOrderSaved = false;
            try {
                isOrderSaved = orderDao .save(dto.getOrderId(), dto.getMachineId(), dto.getEngineerId(), dto.getOrderDate(), dto.getCustomerId());
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            if (isOrderSaved) {
                boolean isUpdated = stockDao.updateQty2(dto.getStockTMlist());
                if (isUpdated) {
                    boolean isOrderDetailSaved = orderDetailDao.saveOrderDetails(dto.getOrderId(), dto.getStockTMlist());
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }

            connection.rollback();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
        return true;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDao.nextId();
    }

    @Override
    public List<EngineerDTO> getAllEngineers() throws SQLException {
        ArrayList<Engineer> allEngineers = engineerDao.getAll();
        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();

        for (Engineer engineer : allEngineers) {
            engineerDTOS.add(new EngineerDTO(engineer.getEId(), engineer.getName(), engineer.getAddress(),engineer.getTel()));
        }

        return engineerDTOS;
    }

    @Override
    public ArrayList<MachineDto> getAllMachine() throws SQLException {
        ArrayList<Machine> allMachines = machineDao.getAll();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();

        for (Machine machine: allMachines) {
            machineDTOS.add(new MachineDto(machine.getId(), machine.getName(), machine.getQty(),machine.getPrice()));
        }

        return machineDTOS;


    }

    @Override
    public List<StockDto> getAllStock() throws SQLException {
        ArrayList<Stock> allStock = stockDao.getAll();
        ArrayList<StockDto>stockDTOS= new ArrayList<>();

        for (Stock stock: allStock) {
            stockDTOS.add(new StockDto(stock.getId(), stock.getName(), stock.getQty(),stock.getPrice()));
        }

        return stockDTOS;
    }


    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        ArrayList<Customer> allCustomers = customerDao.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(),customer.getTel()));
        }

        return customerDTOS;
    }

    @Override
    public Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException {
        return engineerDao.search(id);
    }

    @Override
    public Stock searchStock(String id) throws SQLException, ClassNotFoundException {
        return stockDao.search(id);
    }

    @Override
    public Machine searchMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDao.search(id);
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDao.search(id);
    }

    @Override
    public char[] countOrders() throws SQLException {
        return orderDao.count();
    }


}




