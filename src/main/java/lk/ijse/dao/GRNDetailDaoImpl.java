package lk.ijse.dao;

import lk.ijse.dao.custom.GRNDetailDao;
import lk.ijse.dto.tm.GrnTM;
import lk.ijse.entity.GRNDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GRNDetailDaoImpl implements GRNDetailDao {
   public boolean saveGrnOrderDetails(String orderId, List<GrnTM> stockTMList) throws SQLException {
        for(GrnTM tm : stockTMList) {
            if(!saveOrderDetails(orderId, tm)) {    //run karala balannoo
                return false;// hariyoooooo run wenooo
            }
        }
        return true;
    }

     public boolean saveOrderDetails(String grnId, GrnTM tm) throws SQLException {
        return SQLUtil.execute("INSERT INTO grn_details VALUES(?, ?, ?, ?, ?)", grnId, tm.getStockId(), tm.getStockName(), tm.getQty(), tm.getUnitPrice());
    }

    @Override
    public boolean save(GRNDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(GRNDetails dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<GRNDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public GRNDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return new char[0];
    }
}


