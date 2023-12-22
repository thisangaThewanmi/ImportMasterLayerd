package lk.ijse.model;

import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceGRNdto;
import lk.ijse.dto.PlaceOrderDto;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

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
                boolean isUpdated = StockModel.updateQty3(placeGRNdto.getGrnTmList());
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


