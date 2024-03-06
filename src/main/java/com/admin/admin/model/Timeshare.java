package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "timeshare_item")
public class Timeshare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private Long category_id;
    private String name;
    private String description;
    private float price;
    private String timeshare_image;
    private Date startDate;
    private Date endDate;
    private Boolean is_check ;
    private Boolean status ;

    public Timeshare(String userid, Long category_id, String name, String description, float price, String timeshare_image, Date startDate, Date endDate, Boolean is_check, Boolean status) {
        this.userid = userid;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.timeshare_image = timeshare_image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.is_check = is_check;
        this.status = status;
    }
}