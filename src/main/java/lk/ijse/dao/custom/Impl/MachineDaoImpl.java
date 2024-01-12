package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.MachineDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.MachineInstallDto;
import lk.ijse.dto.tm.MrnTM;
import lk.ijse.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDaoImpl implements MachineDao {
    public boolean updateMachineQty(MachineInstallDto ob) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ - 1 WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ob.getMachineId());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE machine SET qty_ = qty_ - 1 WHERE m_id = ?",ob.getMachineId());
    }


    public  boolean updateQty2(List<MrnTM> list) throws SQLException {
        for (MrnTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }

    public boolean updateQty2(MrnTM ob) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ + ? WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getMachineId());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE machine SET qty_ = qty_ + ? WHERE m_id = ?",ob.getQty(),ob.getMachineId());
    }

   /*


    public static boolean updateMachineQty(MachineInstallDto ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ - 1 WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ob.getMachineId());
        return pstm.executeUpdate() > 0;
    }


    public static String generateNextMRId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT mrn_id FROM  mrn ORDER BY mrn_id DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

        private static String splitOrderId(String currentOrderId) {
            if (currentOrderId != null) {
                String[] split = currentOrderId.split("MR");

                // Ensure that the array has at least two elements
                int id;
                if (split.length > 1) {
                    id = Integer.parseInt(split[1]); //01
                } else {

                    id = 0;
                }

                id++;
                return "MR00" + id;
            } else {
                // Handle the case when currentOrderId is null
                return "MR001";
            }
        }


        public static boolean updateQty2(List<MrnTM> list) throws SQLException {
        for (MrnTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty2(MrnTM ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ + ? WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getMachineId());
        return pstm.executeUpdate() > 0;
    }

    public char[] countMachines() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) AS machine_count FROM machine;";

        // Prepare the statement
        PreparedStatement pstm = connection.prepareStatement(sql);

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Process the result set
        int machineCount = 0;
        if (resultSet.next()) {
            machineCount = resultSet.getInt("machine_count");
        }



        // Convert the supplier count to char array
        char[] resultCharArray = String.valueOf(machineCount).toCharArray();

        return resultCharArray;
    }*/

    @Override
    public boolean save(Machine entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO machine (m_id,name, qty_,unit_price) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM machine WHERE m_id=?",
                id);
    }

    @Override
    public boolean update(Machine entity) throws SQLException {
        return SQLUtil.execute("UPDATE machine SET name=?, qty_=? ,unit_price=? WHERE m_id=?",
                entity.getName(),entity.getQty(),entity.getPrice(),entity.getId());
    }

    @Override
    public ArrayList<Machine> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM machine");

        ArrayList<Machine> getAllMachine = new ArrayList<>();

        while (rst.next()) {
            Machine entity = new Machine(rst.getString("m_id"), rst.getString("name"), rst.getInt("qty_"),rst.getDouble("unit_price"));
            getAllMachine.add(entity);
        }

        return getAllMachine;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT m_id FROM machine WHERE m_id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT m_id FROM machine ORDER BY m_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("m_id");
            int newCustomerId = Integer.parseInt(id.replace("M00-", "")) + 1;
            return String.format("M00-%03d", newCustomerId);
        } else {
            return "M00-001";
        }
    }

    @Override
    public Machine search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM machine WHERE m_id = ?",id);


        Machine entity = null;
        while (rst.next()) {
            entity = new Machine(rst.getString("m_id"), rst.getString("name"), rst.getInt("qty_"),rst.getDouble("unit_price"));

        }

        return entity;
    }

    @Override
    public ResultSet count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(m_id) FROM machine;");
    }

    //extra ones

   /* public static boolean updateQty2(List<MrnTM> list) throws SQLException {
        for (MrnTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }*/

   /* private static boolean updateQty2(MrnTM ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ + ? WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getMachineId());
        return pstm.executeUpdate() > 0;*/
    }



