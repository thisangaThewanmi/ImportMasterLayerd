package lk.ijse.entity;

import lombok.*;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private  String orderId;

    private String orderDate;

    private  String m_id;

    private  String  cus_id;

    private String e_id;
}
