package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDao;
import lk.ijse.dao.custom.CrudDao;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDao extends CrudDao<Order> {
    boolean save(String orderId, String MachineId, String engineerId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
}
