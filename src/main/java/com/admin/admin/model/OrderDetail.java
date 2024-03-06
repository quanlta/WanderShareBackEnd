package com.admin.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private String id;
    private String userid;
    private LocalDateTime orderdate;
    private int order_status;

    public OrderDetail(String id, String user_id, LocalDateTime order_date, float v, int order_status) {
        this.id = id;
        this.userid = user_id;
        this.orderdate = order_date;
        this.order_status = order_status;
    }
}
