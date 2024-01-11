package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.EngineerDao;
import lk.ijse.dao.StockDao;
import lk.ijse.dao.custom.EngineerStockDao;
import lk.ijse.dao.custom.EngineerStockDetailsDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.EngineerStockDto;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceEngineerStockBOImpl  implements PlaceEngineerStockBO {

    EngineerStockDao engineerStockDao = (EngineerStockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNERSTOCK);

    EngineerStockDetailsDao engineerStockDetailsDao = (EngineerStockDetailsDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNER_STOCK_DETAILS);

    StockDao stockDao = (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    EngineerDao engineerDao = (EngineerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNER);


    public boolean issueStock(EngineerStockDto engineerStockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isEngineerStockSaved = engineerStockDao.saveEngineerStock(engineerStockDto);
            if (isEngineerStockSaved) {
                boolean isALlSaved = engineerStockDetailsDao.saveEngineerStockDetails(engineerStockDto.getDetailsList());
                if (isALlSaved) {
                    boolean isALlUpdated = stockDao.updateQty(engineerStockDto.getDetailsList());
                    if (isALlUpdated) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return engineerStockDao.nextId();
    }

    @Override
    public List<EngineerDTO> getAllEngineers() throws SQLException {
        ArrayList<Engineer> allEngineers = engineerDao.getAll();
        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();

        for (Engineer engineer : allEngineers) {
            engineerDTOS.add(new EngineerDTO(engineer.getEId(), engineer.getName(), engineer.getAddress(),engineer.getTel()));
        }

        return engineerDTOS;
    }

    @Override
    public List<StockDto> getAllStock() throws SQLException {
        ArrayList<Stock> allStock = stockDao.getAll();
        ArrayList<StockDto>stockDTOS= new ArrayList<>();

        for (Stock stock: allStock) {
            stockDTOS.add(new StockDto(stock.getId(), stock.getName(), stock.getQty(),stock.getPrice()));
        }

        return stockDTOS;
    }
}
