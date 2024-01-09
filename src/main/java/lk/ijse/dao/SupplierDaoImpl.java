package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.supDto;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {



/*    public static char[] countSuppliers() throws SQLException {



            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT COUNT(*) AS supplier_count FROM supplier;";

            // Prepare the statement
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = pstm.executeQuery();

            // Process the result set
            int supplierCount = 0;
            if (resultSet.next()) {
                supplierCount = resultSet.getInt("supplier_count");
            }



            // Convert the supplier count to char array
            char[] resultCharArray = String.valueOf(supplierCount).toCharArray();

            return resultCharArray;
        }



    public boolean SaveSupplier(supDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getTel());

        return pstm.executeUpdate() > 0;

    }


    public boolean updateCustomer(supDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET name = ?, address = ?, tel = ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static supDto searchSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        supDto dto = null;
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);


            dto = new supDto(supId, name, address, tel);
        }
        return dto;
    }


    public List<supDto> getAllSuppliers() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier";

        PreparedStatement ps = connection.prepareStatement(sql);
        List<supDto> allSuppliers = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            allSuppliers.add(new supDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return allSuppliers;


    }*/

    @Override
    public boolean save(Supplier entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier (sup_id,name, address,tel) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getAddress(),entity.getTel());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM supplier WHERE sup_id=?",
                id);
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET name=?, address=? ,tel=? WHERE sup_id=?",
                entity.getName(),entity.getAddress(),entity.getTel(),entity.getId());
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");

        ArrayList<Supplier> getAllSupplier = new ArrayList<>();

        while (rst.next()) {
            Supplier entity = new Supplier(rst.getString("sup_id"), rst.getString("name"), rst.getString("address"), rst.getString("tel"));
            getAllSupplier.add(entity);
        }

        return getAllSupplier;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT id FROM supplier WHERE sup_id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT sup_id FROM supplier ORDER BY emp_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newSupplierId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newSupplierId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public Supplier search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE sup_id=?");

        Supplier supplier = null;
        if (rst.next()) {
            supplier = new Supplier(rst.getString("sup_id"), rst.getString("name"), rst.getString("address"), rst.getString("tel"));
        }

        return supplier;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(m_id) FROM machine;");

    }
}









