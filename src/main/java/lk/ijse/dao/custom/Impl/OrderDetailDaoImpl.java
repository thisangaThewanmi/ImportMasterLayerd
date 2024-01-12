package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDao;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

     public boolean saveOrderDetails(String orderId, List<StockTM> stockTMList) throws SQLException {
        for(StockTM tm : stockTMList) {
            if(!saveOrderDetails(orderId, tm)) {    //run karala balannoo
                return false;// hariyoooooo run wenooo
            }
        }
        return true;
    }

     public boolean saveOrderDetails(String orderId, StockTM tm) throws SQLException {
   /*     Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setString(2, tm.getId());
        pstm.setInt(3, tm.getQty());
        pstm.setDouble(4, tm.getPrice());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO order_detail VALUES(?, ?, ?, ?)", orderId, tm.getId(), tm.getQty(), tm.getPrice());
    }

    @Override
    public boolean save(OrderDetail dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(id) FROM order_detail;");
    }
}
