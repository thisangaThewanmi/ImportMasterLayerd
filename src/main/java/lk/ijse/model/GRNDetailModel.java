package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GRNDetailModel {
    public static boolean saveGrnOrderDetails(String orderId, List<GrnTM> stockTMList) throws SQLException {
        for(GrnTM tm : stockTMList) {
            if(!saveOrderDetails(orderId, tm)) {    //run karala balannoo
                return false;// hariyoooooo run wenooo
            }
        }
        return true;
    }

    private static boolean saveOrderDetails(String grnId, GrnTM tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO grn_details VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, grnId);
        pstm.setString(2, tm.getStockId());
        pstm.setString(3, tm.getStockName());
        pstm.setInt(4, tm.getQty());
        pstm.setDouble(5, tm.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }
}


