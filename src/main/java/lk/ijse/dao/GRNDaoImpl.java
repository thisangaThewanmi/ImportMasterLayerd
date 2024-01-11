package lk.ijse.dao;

import lk.ijse.dao.custom.GRNDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.GRN;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GRNDaoImpl implements GRNDao {
   /* public static String generateNextOrderId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT grn_id FROM grn ORDER BY grn_id DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

      private static String splitOrderId(String currentOrderId) {
            if(currentOrderId != null) {
                String[] split = currentOrderId.split("GRN0");

                int id = Integer.parseInt(split[2]); //01
                id++;
                return "GRN00" + id;
            } else {
                return "O001";
            }
        }*/

     public boolean saveOrder(String grnId, LocalDate date, String supplierId, String supplierName, Double total) throws SQLException {
       return SQLUtil.execute("INSERT INTO grn VALUES(?,?,?,?,?)",  grnId,date,supplierId,supplierName);
    }

    @Override
    public boolean save(GRN dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(GRN dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<GRN> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT grn_id FROM grn ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("G00-", "")) + 1;
            return String.format("G00-%03d", newCustomerId);
        } else {
            return "G00-001";
        }
    }

    @Override
    public GRN search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(*) AS grn_count FROM grn;");
    }


   /* public static String generateNextGRId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT grn_id FROM  grn ORDER BY grn_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("GR");

            // Ensure that the array has at least two elements
            int id;
            if (split.length > 1) {
                id = Integer.parseInt(split[1]); //01
            } else {

                id = 0;
            }

            id++;
            return "GR00" + id;
        } else {
            // Handle the case when currentOrderId is null
            return "GR001";
        }
    }
*/
}

