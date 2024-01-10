package lk.ijse.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDao extends CrudDao <Order>{

    boolean save(String orderId,String MachineId, String engineerId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
}
