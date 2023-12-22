package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.MrnTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MrnDetailModel {


    public static boolean saveMrnDetails(String mrnId, List<MrnTM> machineTMList) throws SQLException {
        for (MrnTM tm : machineTMList) {
            if (!saveMrnDetails(mrnId, tm)) {

                return false;
            }
        }
        return true;
    }

    private static boolean saveMrnDetails(String mrnId, MrnTM tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO mrn_details VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, mrnId);
        pstm.setString(2, tm.getMachineId());
        pstm.setString(3, tm.getMachineName());
        pstm.setInt(4, tm.getQty());
        pstm.setDouble(5, tm.getTotal());


        return pstm.executeUpdate() > 0;
    }}



