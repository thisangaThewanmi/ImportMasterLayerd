package lk.ijse.dao.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SuperDao;
import lk.ijse.dto.EngineerStockDto;
import lk.ijse.entity.EngineerStock;

import java.sql.SQLException;

public interface EngineerStockDao extends CrudDao<EngineerStock> {

    boolean saveEngineerStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException;
}
