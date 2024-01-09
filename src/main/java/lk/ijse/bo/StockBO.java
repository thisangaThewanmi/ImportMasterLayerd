package lk.ijse.bo;

import lk.ijse.dto.StockDto;

import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO  extends SuperBO{

    ArrayList<StockDto> getAllStock() throws SQLException, ClassNotFoundException;
    boolean existStock(String id) throws SQLException, ClassNotFoundException;
    boolean saveStock(StockDto dto) throws SQLException, ClassNotFoundException;
    boolean updateStock(StockDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteStock(String id) throws SQLException, ClassNotFoundException;
    String nextStockId() throws SQLException, ClassNotFoundException;

    public char[] countStock() throws SQLException;

    Stock searchStock(String id) throws SQLException, ClassNotFoundException;

}
