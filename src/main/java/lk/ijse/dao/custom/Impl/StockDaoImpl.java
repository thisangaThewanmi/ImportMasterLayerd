package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.StockDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {
    @Override
    public boolean save(Stock entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO stock (s_id,name, qty,unit_price) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException {
       return SQLUtil.execute("DELETE FROM stock WHERE s_id=?",
                id);
    }

    @Override
    public boolean update(Stock entity) throws SQLException {
        return SQLUtil.execute("UPDATE stock SET name=?, qty=? ,unit_price=? WHERE s_id=?",
                entity.getName(),entity.getQty(),entity.getPrice(),entity.getId());
    }

    @Override
    public ArrayList<Stock> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock");

        ArrayList<Stock> getAllStock = new ArrayList<>();

        while (rst.next()) {
            Stock entity= new Stock(rst.getString("s_id"), rst.getString("name"), rst.getInt("qty"),rst.getDouble("unit_price"));
            getAllStock.add(entity);
        }

        return getAllStock;
    }


    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT s_id FROM stock WHERE s_id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT s_id FROM stock ORDER BY s_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("s_id");
            int newStockId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("00-%03d", newStockId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public Stock search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock WHERE s_id = ?",id);


       Stock entity = null;
        while (rst.next()) {
            entity = new Stock(rst.getString("s_id"), rst.getString("name"), rst.getInt("qty"),rst.getDouble("unit_price"));

        }

        return entity;
    }

    @Override
    public ResultSet count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(s_id) FROM stock;");

    }


    public boolean updateQty(List<EngineerStockDetailsDto> list) throws SQLException {
        for (EngineerStockDetailsDto ob : list) {
            if (!updateQty(ob)){
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(EngineerStockDetailsDto ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty - ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getStockId());
        return pstm.executeUpdate() > 0;
    }








     public boolean updateQty2(List<StockTM> list) throws SQLException {
        for (StockTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }

     public boolean updateQty2(StockTM ob) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty - ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getId());
        return pstm.executeUpdate() > 0;*/

         return SQLUtil.execute("UPDATE stock SET qty = qty - ? WHERE s_id = ?",ob.getQty(),ob.getId());

    }

    public boolean updateQty3(List<GrnTM> list) throws SQLException {
        for (GrnTM ob : list) {
            if (!updateQty3(ob)){
                return false;
            }
        }
        return true;
    }

     public boolean updateQty3(GrnTM ob) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE stock SET qty = qty + ? WHERE s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getStockId());
        return pstm.executeUpdate() > 0;
    }*/
         return SQLUtil.execute("UPDATE stock SET qty = qty + ? WHERE s_id = ?", ob.getQty(), ob.getStockId());
     }

   }


