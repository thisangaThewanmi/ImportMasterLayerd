package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.dto.tm.GrnTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockModel {
    public static boolean saveStock(StockDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql ="INSERT INTO stock VALUES (?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setInt(3, dto.getQty());
        pstm.setDouble(4, dto.getPrice());


        return pstm.executeUpdate() > 0;
    }

   /* public static boolean updateStock(List<grnTM> dto) throws SQLException {



            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE stock SET name = ?, qty = ?,  unit_price = ? WHERE s_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getStockName());
            pstm.setInt(2, dto.getQty());
            pstm.setDouble(3, dto.getPrice());
            pstm.setString(4, dto.getId());

            return pstm.executeUpdate() > 0;


    }*/

    public static boolean deleteStock(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM stock WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM stock";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<StockDto> allStocks = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            allStocks.add(new StockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }
        return allStocks;
    }

    public static boolean updateQty(List<EngineerStockDetailsDto> list) throws SQLException {
        for (EngineerStockDetailsDto ob : list) {
            if (!updateQty(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(EngineerStockDetailsDto ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty - ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getStockId());
        return pstm.executeUpdate() > 0;
    }






    public static boolean updateStock(StockDto stockDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE stock SET description = ?, unit_price = ?, qty_on_hand = ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, stockDto.getName());
        pstm.setDouble(2, stockDto.getPrice());
        pstm.setInt(3, stockDto.getQty());
        pstm.setString(4, stockDto.getId());

        return pstm.executeUpdate() > 0;
    }

    public List<OrderStockDto> getEngineerStock(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql="SELECT engineerstock.e_id, engineerstock.ESIN_No, engineer_stock_details.stock_id, engineer.name, stock.name AS stock_name, engineer_stock_details.qty AS engineer_qty, stock.unit_price FROM engineer_stock_details JOIN engineerstock ON engineer_stock_details.ESIN_No = engineerstock.ESIN_No JOIN stock ON engineer_stock_details.stock_id = stock.s_id JOIN engineer ON engineer.e_id = engineerstock.e_id WHERE engineerstock.e_id = ?;";


        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        List<OrderStockDto> stockDtos = new ArrayList<>();
        while (resultSet.next()){
            stockDtos.add(new OrderStockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7)
            ));
        }
        return stockDtos;
    }

    public StockDto searchStock(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stock WHERE s_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        StockDto dto = null;
        if (resultSet.next()) {
            String stockId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            double unitPrice = resultSet.getDouble(4);


            dto = new StockDto(stockId, name, qty, unitPrice);
        }
        return dto;
    }

    public static boolean updateQty2(List<StockTM> list) throws SQLException {
        for (StockTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty2(StockTM ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty - ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getId());
        return pstm.executeUpdate() > 0;
    }

    public static boolean updateQty3(List<GrnTM> list) throws SQLException {
        for (GrnTM ob : list) {
            if (!updateQty3(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty3(GrnTM ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty + ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getStockId());
        return pstm.executeUpdate() > 0;
    }

    public char[] countStock() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) AS stock_count FROM stock;";

        // Prepare the statement
        PreparedStatement pstm = connection.prepareStatement(sql);

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Process the result set
        int stockCount = 0;
        if (resultSet.next()) {
            stockCount = resultSet.getInt("stock_count");
        }



        // Convert the supplier count to char array
        char[] resultCharArray = String.valueOf(stockCount).toCharArray();

        return resultCharArray;
    }
    }



