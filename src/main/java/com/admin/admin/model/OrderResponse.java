package com.admin.admin.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private OrderDetail orderDetail;
    private List<OrderDetail> orderDetails;
    private float total;

}