package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.MachineInstallation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MachineInstallationDaoImpl implements MachineInstallationDao {


    public boolean saveInstallation(String MIid, String machineId, String engineerId, String customerId, LocalDate date, double price) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        return SQLUtil.execute( "INSERT INTO machineinstallation VALUES(?,?,?,?,?,?)", MIid, machineId, engineerId, customerId, date, price);
    }

    @Override
    public boolean save(MachineInstallation dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(MachineInstallation dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<MachineInstallation> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT m_id FROM machineinstallation ORDER BY m_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("m_id");
            int newInstallId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newInstallId);
        } else {
            return "I00-001";
        }
    }


    @Override
    public MachineInstallation search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return new char[0];
    }
}

