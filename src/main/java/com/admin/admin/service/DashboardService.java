package com.admin.admin.service;

import com.admin.admin.model.Exchange;
import com.admin.admin.model.Timeshare;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashboardService {
    int getTimeshareSold();
    float getTotalBudget();
    ResponseEntity<?> getChartCategory();

    List<Timeshare> getAvailableTimeshares();

}
