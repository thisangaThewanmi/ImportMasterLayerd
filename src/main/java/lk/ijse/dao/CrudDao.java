package lk.ijse.dao;


import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends  SuperDao {

          boolean save(T dto) throws SQLException;

          boolean delete(String id) throws SQLException;

          boolean update(T dto) throws SQLException ;

         List<T> getAll() throws SQLException;


    boolean exsit(String id) throws SQLException, ClassNotFoundException;

    String nextId() throws SQLException, ClassNotFoundException;

    T search(String newValue) throws SQLException, ClassNotFoundException;

    public char[] count() throws SQLException;

}
