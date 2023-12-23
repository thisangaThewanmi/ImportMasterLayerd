package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class MrnModel {

    public static boolean saveMRN(String mrnId, LocalDate date, String supId,  Double total) throws SQLException {


            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO mrn VALUES(?, ?, ?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, mrnId);
            pstm.setDate(2, Date.valueOf(date.toString()));
            pstm.setString(3, supId);
            pstm.setDouble(4, Double.parseDouble(String.valueOf(total)));

            return pstm.executeUpdate() > 0;
        }
    }

