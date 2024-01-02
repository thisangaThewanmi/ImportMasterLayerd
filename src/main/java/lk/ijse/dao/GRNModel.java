package lk.ijse.dao;

import lk.ijse.db.DbConnection;

import java.sql.*;
import java.time.LocalDate;

public class GRNModel {
   /* public static String generateNextOrderId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT grn_id FROM grn ORDER BY grn_id DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

      private static String splitOrderId(String currentOrderId) {
            if(currentOrderId != null) {
                String[] split = currentOrderId.split("GRN0");

                int id = Integer.parseInt(split[2]); //01
                id++;
                return "GRN00" + id;
            } else {
                return "O001";
            }
        }*/

    public static boolean saveOrder(String grnId, LocalDate date, String supplierId, String supplierName, Double total) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO grn VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, grnId);
        pstm.setDate(2, Date.valueOf(date.toString()));
        pstm.setString(3, supplierId);
        pstm.setString(4, supplierName);
        pstm.setDouble(5, Double.parseDouble(String.valueOf(total)));

        return pstm.executeUpdate() > 0;
    }


    public static String generateNextGRId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT grn_id FROM  grn ORDER BY grn_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("GR");

            // Ensure that the array has at least two elements
            int id;
            if (split.length > 1) {
                id = Integer.parseInt(split[1]); //01
            } else {

                id = 0;
            }

            id++;
            return "GR00" + id;
        } else {
            // Handle the case when currentOrderId is null
            return "GR001";
        }
    }

}

