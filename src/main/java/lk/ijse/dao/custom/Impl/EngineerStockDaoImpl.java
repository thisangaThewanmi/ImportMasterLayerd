package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EngineerStockDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerStockDto;
import lk.ijse.entity.EngineerStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EngineerStockDaoImpl implements EngineerStockDao {


 /*   public static boolean issueStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isEngineerStockSaved = saveEngineerStock(engineerStockDto);
            if (isEngineerStockSaved){
                boolean isALlSaved = EngineerStockDetailsDaoImpl.saveEngineerStockDetails(engineerStockDto.getDetailsList());
                if (isALlSaved){
                    boolean isALlUpdated = StockDaoImpl.updateQty(engineerStockDto.getDetailsList());
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
    }*/

    public boolean saveEngineerStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO engineerstock VALUES(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, engineerStockDto.getEsinId());
        ps.setObject(2, engineerStockDto.getDate());
        System.out.println(engineerStockDto.getEmpId());
        ps.setObject(3, engineerStockDto.getEmpId());
        return ps.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO engineerstock VALUES(?,?,?)", engineerStockDto.getEsinId(), engineerStockDto.getDate(), engineerStockDto.getEmpId());
    }

    @Override
    public boolean save(EngineerStock dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(EngineerStock dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<EngineerStock> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ESIN_No FROM  engineerstock ORDER BY ESIN_No DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("ESIN_No");
            int EngStockId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", EngStockId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public EngineerStock search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return new char[0];
    }
}
