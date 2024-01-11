package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.EngineerDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerDaoImpl  implements EngineerDao {





    @Override
    public boolean save(Engineer entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO engineer(e_id,name, address,tel) VALUES (?,?,?,?)",
                entity.getEId(),entity.getName(),entity.getAddress(),entity.getTel());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM engineer WHERE e_id=?",
                id);
    }

    @Override
    public boolean update(Engineer entity) throws SQLException {
        return SQLUtil.execute("UPDATE engineer SET name=?, address=? ,tel=? WHERE e_id=?",
                entity.getName(),entity.getAddress(),entity.getTel(),entity.getEId());
    }

    @Override
    public ArrayList<Engineer> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM engineer");

        ArrayList<Engineer> getAllEngineer = new ArrayList<>();

        while (rst.next()) {
            Engineer entity = new Engineer(rst.getString("e_id"), rst.getString("name"), rst.getString("address"),rst.getString("tel"));
            getAllEngineer.add(entity);
        }

        return getAllEngineer;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT id FROM engineer WHERE e_id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Engineer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM engineer WHERE e_id = ?",id);


        Engineer entity = null;
        while (rst.next()) {
            entity = new Engineer(rst.getString("e_id"), rst.getString("name"), rst.getString("address"), rst.getString("tel"));

        }

        return entity;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(e_id) FROM engineer;");
    }
}

