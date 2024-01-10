package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {

            System.out.println(placeOrderDto);

            String orderId = placeOrderDto.getOrderId();
            String customerId = placeOrderDto.getCustomerId();
            LocalDate date = placeOrderDto.getOrderDate();
            String engineerId = placeOrderDto.getEngineerId();
            String engineerName = placeOrderDto.getEngineerName();
            String machineId = placeOrderDto.getMachineId();
            String machineName = placeOrderDto.getMachineName();
            String customerName = placeOrderDto.getCustomerName();

            Connection connection = null;
            try {
                connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = OrderDAOImpl.saveOrder(orderId, machineId, engineerId, customerId ,date);
                if (isOrderSaved) {
                    boolean isUpdated = StockDaoImpl.updateQty2(placeOrderDto.getStockTMlist());
                    if (isUpdated) {
                        boolean isOrderDetailSaved = OrderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getStockTMlist());
                        if (isOrderDetailSaved) {
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

