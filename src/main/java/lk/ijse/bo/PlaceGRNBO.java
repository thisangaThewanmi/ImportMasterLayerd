package lk.ijse.bo;

import lk.ijse.dto.PlaceGRNdto;
import lk.ijse.dto.StockDto;
import lk.ijse.dto.supDto;
import lk.ijse.entity.Stock;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface PlaceGRNBO extends SuperBO{
    List<StockDto> getAllStock() throws SQLException;

    List<supDto> getAllSuppliers() throws SQLException;

    String generateNextGRId() throws SQLException, ClassNotFoundException;

   boolean placeGrnOrder(PlaceGRNdto dto) throws SQLException;

    Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException;

    Stock searchStock(String id) throws SQLException, ClassNotFoundException;
}
