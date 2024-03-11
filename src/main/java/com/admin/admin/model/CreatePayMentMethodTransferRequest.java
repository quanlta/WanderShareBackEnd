package com.admin.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class CreatePayMentMethodTransferRequest {
    public String vnp_Amount ;
    public String vnp_OrderInfo ;
    public String vnp_OrderType = "Thanh toan hoa don";
    public String vnp_TxnRef;
    private List<DataOrderRequest> dataOrderRequests;
}
