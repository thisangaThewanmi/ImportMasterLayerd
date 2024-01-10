package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerStockDetailsDto;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.dto.tm.StockTM;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public interface StockDao extends CrudDao<Stock> {

     boolean updateQty2(List<StockTM> list) throws SQLException ;


    boolean updateQty2(StockTM ob) throws SQLException;

     boolean updateQty3(List<GrnTM> list) throws SQLException;
    boolean updateQty3(GrnTM ob) throws SQLException ;

    boolean updateQty(List<EngineerStockDetailsDto> list) throws SQLException;


    boolean updateQty(EngineerStockDetailsDto ob) throws SQLException ;


    }

