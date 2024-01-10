package lk.ijse.dao.custom;

import lk.ijse.dto.tm.StockTM;
import lk.ijse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetail> {

    boolean saveOrderDetails(String orderId, List<StockTM> stockTMList) throws SQLException;

   boolean saveOrderDetails(String orderId, StockTM tm) throws SQLException;
}
