package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceGRNdto;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceGRNModel {

    public static boolean placeGrnOrder(PlaceGRNdto placeGRNdto) throws SQLException {

        System.out.println(placeGRNdto);

        String grnId = placeGRNdto.getGrnId();

        LocalDate date = placeGRNdto.getGrnDate();
        String supId = placeGRNdto.getSupplierId();
        String supName = placeGRNdto.getSupplierName();
        Double total = Double.valueOf(placeGRNdto.getTotal());





            Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isGrnOrderSaved = GRNModel.saveOrder(grnId, date, supId, supName ,total);
            if (isGrnOrderSaved) {
                boolean isUpdated = StockDaoImpl.updateQty3(placeGRNdto.getGrnTmList());
                if (isUpdated) {
                    boolean isGrnOrderDetailSaved = GRNDetailModel.saveGrnOrderDetails(placeGRNdto.getGrnId(), placeGRNdto.getGrnTmList());
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


