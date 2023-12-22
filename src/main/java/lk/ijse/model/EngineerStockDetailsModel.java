package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerStockDetailsDto;
import lk.ijse.dto.StockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerStockDetailsModel {
    public static boolean saveEngineerStockDetails(List<EngineerStockDetailsDto> list) throws SQLException, ClassNotFoundException {
        for (EngineerStockDetailsDto ob : list) {
            if (!saveEngineerStockDetails(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean saveEngineerStockDetails(EngineerStockDetailsDto engineerStockDetailsDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO engineer_stock_details VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, engineerStockDetailsDto.getEsinId());
        ps.setObject(2, engineerStockDetailsDto.getStockId());
        ps.setObject(3, engineerStockDetailsDto.getQty());
        ps.setObject(4, engineerStockDetailsDto.getTotal());
        return ps.executeUpdate() > 0;
    }

    public static List<EngineerStockDetailsDto> getAllEngineerStockDetails () throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM engineer_stock_details";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<EngineerStockDetailsDto> allEnginnerStockDetails = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            allEnginnerStockDetails.add(new EngineerStockDetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }
        return allEnginnerStockDetails ;
    }
    }

