package lk.ijse.bo;

import lk.ijse.dao.*;
import lk.ijse.dao.custom.GRNDao;
import lk.ijse.dao.custom.GRNDetailDao;
import lk.ijse.dao.custom.Impl.StockDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceGRNdto;
import lk.ijse.dto.StockDto;
import lk.ijse.dto.supDto;
import lk.ijse.entity.Stock;
import lk.ijse.entity.Supplier;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceGRNBoImpl implements PlaceGRNBO {

    GRNDao grnDao = (GRNDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GRN);

    GRNDetailDao grnDetailDao = (GRNDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GRN_DETAIL);

     StockDao stockDao = (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER); //SupplierDao

    @Override
    public List<StockDto> getAllStock() throws SQLException {
        ArrayList<Stock> allStock = stockDao.getAll();
        ArrayList<StockDto>stockDTOS= new ArrayList<>();

        for (Stock stock: allStock) {
            stockDTOS.add(new StockDto(stock.getId(), stock.getName(), stock.getQty(),stock.getPrice()));
        }

        return stockDTOS;
    }

    @Override
    public List<supDto> getAllSuppliers() throws SQLException {
        ArrayList<Supplier> allSuppliers = supplierDao.getAll();
        ArrayList<supDto> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : allSuppliers) {
            supplierDTOS.add(new supDto(supplier.getId(), supplier.getName(),supplier.getAddress(),supplier.getTel()));
        }

        return supplierDTOS;
    }

    @Override
    public String generateNextGRId() throws SQLException, ClassNotFoundException {
      return grnDao.nextId();
    }

    public  boolean placeGrnOrder(PlaceGRNdto dto) throws SQLException {

            Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isGrnOrderSaved = grnDao.saveOrder(dto.getGrnId(), dto.getGrnDate(), dto.getSupplierId(), dto.getSupplierName() ,dto.getTotal());
            if (isGrnOrderSaved) {
                boolean isUpdated = stockDao.updateQty3(dto.getGrnTmList());
                if (isUpdated) {
                    boolean isGrnOrderDetailSaved = grnDetailDao.saveGrnOrderDetails(dto.getGrnId(),dto.getGrnTmList());
                    if (isGrnOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }

            connection.rollback();


        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }


}


