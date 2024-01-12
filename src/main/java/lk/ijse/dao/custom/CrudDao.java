package lk.ijse.dao.custom;


import lk.ijse.dao.SuperDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao {

          boolean save(T dto) throws SQLException;

          boolean delete(String id) throws SQLException;

          boolean update(T dto) throws SQLException ;

         ArrayList<T> getAll() throws SQLException;


    boolean exsit(String id) throws SQLException, ClassNotFoundException;

    String nextId() throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

     ResultSet count() throws SQLException;



}
