package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.tm.StockTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {

    public static boolean saveOrderDetails(String orderId, List<StockTM> stockTMList) throws SQLException {
        for(StockTM tm : stockTMList) {
            if(!saveOrderDetails(orderId, tm)) {    //run karala balannoo
                return false;// hariyoooooo run wenooo
            }
        }
        return true;
    }

    private static boolean saveOrderDetails(String orderId, StockTM tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setString(2, tm.getId());
        pstm.setInt(3, tm.getQty());
        pstm.setDouble(4, tm.getPrice());

        return pstm.executeUpdate() > 0;
    }
}
