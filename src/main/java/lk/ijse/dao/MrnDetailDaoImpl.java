package lk.ijse.dao;

import lk.ijse.dao.custom.MRNDetailsDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.tm.MrnTM;
import lk.ijse.entity.MRNDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MrnDetailDaoImpl implements MRNDetailsDao {

    public boolean saveMrnDetails(String mrnId, List<MrnTM> machineTMList) throws SQLException {
        for (MrnTM tm : machineTMList) {
            if (!saveMrnDetails(mrnId, tm)) {

                return false;
            }
        }
        return true;
    }

     public boolean saveMrnDetails(String mrnId, MrnTM tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
/*
        String sql = "INSERT INTO mrn_details VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, mrnId);
        pstm.setString(2, tm.getMachineId());
        pstm.setString(3, tm.getMachineName());
        pstm.setInt(4, tm.getQty());
        pstm.setDouble(5, tm.getTotal());


        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO mrn_details VALUES(?, ?, ?, ?,?)", mrnId, tm.getMachineId(), tm.getMachineName(), tm.getQty(), tm.getTotal());
    }

    @Override
    public boolean save(MRNDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(MRNDetails dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<MRNDetails> getAll() throws SQLException {
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
    public MRNDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(id) FROM mrn_details;");
    }
}



