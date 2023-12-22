package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.OrderStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStockModel {
  /*  public static OrderStockDto searchOrderStock(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  WHERE e_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        EngineerDTO dto = null;
        if (resultSet.next()) {
            String engId = resultSet.getString(1);
            String engName = resultSet.getString(2);
            String engAddress = resultSet.getString(3);
            String engTel = resultSet.getString(4);

            dto = new EngineerDTO(engId, engName, engAddress, engTel);
        }
        return dto;
    }
    }*/
}
