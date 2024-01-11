package lk.ijse.bo;

import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.EngineerStockDto;
import lk.ijse.dto.StockDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceEngineerStockBO  extends SuperBO{

    boolean issueStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException ;

    String generateNewId() throws SQLException, ClassNotFoundException;

    List<EngineerDTO> getAllEngineers() throws SQLException;

    List<StockDto> getAllStock() throws SQLException;
}
