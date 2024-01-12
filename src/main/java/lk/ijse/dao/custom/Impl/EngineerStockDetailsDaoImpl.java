package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EngineerStockDetailsDao;
import lk.ijse.dto.EngineerStockDetailsDto;
import lk.ijse.entity.EngineerStockDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerStockDetailsDaoImpl implements EngineerStockDetailsDao {
     public boolean saveEngineerStockDetails(List<EngineerStockDetailsDto> list) throws SQLException, ClassNotFoundException {
        for (EngineerStockDetailsDto ob : list) {
            if (!saveEngineerStockDetails(ob)){
                return false;
            }
        }
        return true;
    }

    public boolean saveEngineerStockDetails(EngineerStockDetailsDto engineerStockDetailsDto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO engineer_stock_details VALUES(?,?,?,?)", engineerStockDetailsDto.getEsinId(), engineerStockDetailsDto.getStockId(), engineerStockDetailsDto.getQty(), engineerStockDetailsDto.getTotal());
    }



    public ArrayList<EngineerStockDetails> getAllEngineerStockDetails() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM engineer_stock_details");

        ArrayList<EngineerStockDetails> getAllEngineerStockDetails = new ArrayList<>();

        while (rst.next()) {
            EngineerStockDetails entity = new EngineerStockDetails(rst.getString("esin_id"), rst.getString("stock_id"), rst.getInt("qty"), rst.getDouble("total"));
            getAllEngineerStockDetails.add(entity);
        }

        return getAllEngineerStockDetails;
    }

    @Override
    public boolean save(EngineerStockDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(EngineerStockDetails dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<EngineerStockDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public EngineerStockDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(*) AS grn_count FROM engineer_stock_details;");
    }
}

