package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.StockDao;

import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDao stockDao= (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public ArrayList<StockDto> getAllStock() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStock = stockDao.getAll();
        ArrayList<StockDto>stockDTOS= new ArrayList<>();

        for (Stock stock: allStock) {
            stockDTOS.add(new StockDto(stock.getId(), stock.getName(), stock.getQty(),stock.getPrice()));
        }

        return stockDTOS;

    }

    @Override
    public boolean existStock(String id) throws SQLException, ClassNotFoundException {
        return stockDao.exsit(id);
    }

    @Override
    public boolean saveStock(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDao.save(new Stock(dto.getId(),dto.getName(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public boolean updateStock(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDao.update(new Stock(dto.getId(),dto.getName(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public boolean deleteStock(String id) throws SQLException, ClassNotFoundException {
        return stockDao.delete(id);
    }

    @Override
    public String nextStockId() throws SQLException, ClassNotFoundException {
        return stockDao.nextId();
    }

    @Override
    public ResultSet countStock() throws SQLException {
        return stockDao.count();
    }

    @Override
    public Stock searchStock(String id) throws SQLException, ClassNotFoundException {
        return stockDao.search(id);
    }
}
