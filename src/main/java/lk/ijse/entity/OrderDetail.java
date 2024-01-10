package lk.ijse.entity;

import lk.ijse.dto.tm.StockTM;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {

        private String orderId;

        private LocalDate orderDate;

        private String engineerId;

        private String engineerName;

        private String machineId;

        private String machineName;

        private String CustomerId;

        private String CustomerName;

        private List<StockTM> StockTMlist = new ArrayList<>();


    }

