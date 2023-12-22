package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EngineerStockModel {
    public static boolean issueStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isEngineerStockSaved = saveEngineerStock(engineerStockDto);
            if (isEngineerStockSaved){
                boolean isALlSaved = EngineerStockDetailsModel.saveEngineerStockDetails(engineerStockDto.getDetailsList());
                if (isALlSaved){
                    boolean isALlUpdated = StockModel.updateQty(engineerStockDto.getDetailsList());
                    if (isALlUpdated){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean saveEngineerStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO engineerstock VALUES(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, engineerStockDto.getEsinId());
        ps.setObject(2, engineerStockDto.getDate());
        System.out.println(engineerStockDto.getEmpId());
        ps.setObject(3, engineerStockDto.getEmpId());
        return ps.executeUpdate() > 0;
    }
}
