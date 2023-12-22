package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.MachineInstallDto;

import java.sql.*;
import java.time.LocalDate;

public class MachineInstallationModel {
    public static String generateNextMachineInstallationId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT m_id FROM machineinstallation ORDER BY m_id DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

    private static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("MI");

            // Ensure that the array has at least two elements
            int id;
            if (split.length > 1) {
                id = Integer.parseInt(split[1]); //01
            } else {

                id = 0;
            }

            id++;
            return "MI00" + id;
        } else {
            // Handle the case when currentOrderId is null
            return "MI001";
        }
    }

    public static boolean saveInstallation(String MIid, String machineId, String engineerId, String customerId, LocalDate date, double price) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO machineInstallation VALUES(?,?,?,?,?,?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, MIid);
        pstm.setString(2,machineId);
        pstm.setString(3, engineerId);
        pstm.setString(4, customerId);
        pstm.setDate(5, Date.valueOf(date));
        pstm.setDouble(6, price);


        return pstm.executeUpdate() > 0;
    }

}

