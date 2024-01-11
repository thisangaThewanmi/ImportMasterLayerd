package lk.ijse.entity;

import lk.ijse.dto.tm.GrnTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GRNDetails {

    private String grnId;

    private LocalDate stock_id;

    private String stock_name;

    private int qty;

    private Double unitPrice;

    private  List<GrnTM> grnTmList= new ArrayList<>();



}
