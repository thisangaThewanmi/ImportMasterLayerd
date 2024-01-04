package lk.ijse.bo;

import lk.ijse.dto.supDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO{
    ArrayList<supDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean saveSupplier(supDto dto) throws SQLException, ClassNotFoundException;


    boolean updateSupplier(supDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    String nextSupplierId() throws SQLException, ClassNotFoundException;
}
