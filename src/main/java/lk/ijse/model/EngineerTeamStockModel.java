package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerTeamStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerTeamStockModel {

    public static List<EngineerTeamStockDto> getAllEngineerTeamStockDetails() throws SQLException {
        List<EngineerTeamStockDto> engineerTeamStockList = new ArrayList<>();

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT\n" +
                "  engineerstock.e_id,\n" +
                "  engineerstock.ESIN_No,\n" +
                "  engineer_stock_details.stock_id,\n" +
                "  stock.name AS stock_name,\n" +
                "  engineer_stock_details.qty AS engineer_qty,\n" +
                "  stock.unit_price,\n" +
                "FROM engineer_stock_details\n" +
                "JOIN engineerstock ON engineer_stock_details.ESIN_No = engineerstock.ESIN_No\n" +
                "JOIN stock ON engineer_stock_details.stock_id = stock.s_id\n" +
                "WHERE engineerstock.e_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "E001");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    EngineerTeamStockDto teamStock = new EngineerTeamStockDto(
                            resultSet.getString("e_id"),
                            resultSet.getString("ESIN_No"),
                            resultSet.getString("stock_id"),
                            resultSet.getString("stock_name"),
                            resultSet.getInt("engineer_qty"),
                            resultSet.getDouble("unit_price")

                    );
                    engineerTeamStockList.add(teamStock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return engineerTeamStockList;
    }
}
