package lk.ijse.dao;

import lk.ijse.dao.custom.MRNDao;
import lk.ijse.entity.MRN;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MrnDaoImpl  implements MRNDao {

     public boolean saveMRN(String mrnId, LocalDate date, String supId, Double total) throws SQLException {




        return SQLUtil.execute("INSERT INTO mrn VALUES(?, ?, ?,?)", mrnId, Date.valueOf(date.toString()), supId, total);
        }

    @Override
    public boolean save(MRN dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(MRN dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<MRN> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT mrn_id FROM mrn ORDER BY mrn_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("mrn_id");
            int newCustomerId = Integer.parseInt(id.replace("M00-", "")) + 1;
            return String.format("M00-%03d", newCustomerId);
        } else {
            return "M00-001";
        }
    }


    @Override
    public MRN search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet count() throws SQLException {
      return   SQLUtil.execute("SELECT COUNT(*) AS mrn_count FROM mrn;");
    }
}

