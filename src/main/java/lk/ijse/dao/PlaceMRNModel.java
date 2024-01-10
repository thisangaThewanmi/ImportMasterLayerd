package lk.ijse.dao;

import lk.ijse.dao.custom.Impl.MachineDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceMrnDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.time.LocalDate.parse;

public class PlaceMRNModel {
    public static boolean placeMRNOrder(PlaceMrnDto placeMrnDto) throws SQLException {

        System.out.println(placeMrnDto);

        String mrnId = placeMrnDto.getMRNId();

        LocalDate date = placeMrnDto.getMRNdate();
        String supId = placeMrnDto.getSupplierId();

        String supName = placeMrnDto.getSupplierName();

        Double total = Double.valueOf(placeMrnDto.getTotal());



        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isGrnOrderSaved = MrnModel.saveMRN(mrnId, date, supId,total);
            if (isGrnOrderSaved) {
                boolean isUpdated = MachineDaoImpl.updateQty2(placeMrnDto.getMachineGrnTMS());
                if (isUpdated) {
                    boolean isMrnOrderDetailSaved = MrnDetailModel.saveMrnDetails(placeMrnDto.getMRNId(), placeMrnDto.getMachineGrnTMS());
                    if (isMrnOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }

            connection.rollback();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }


}

