package com.admin.admin.controller;

import com.admin.admin.service.impl.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final DashboardServiceImpl dashboardService;
    @GetMapping("/timesharesold")
    public int getTimeshareSold(){
        return dashboardService.getTimeshareSold();
    }

    @GetMapping("/budget")
    public float getTotalBudget(){
        return dashboardService.getTotalBudget();
    }

    @GetMapping("/getChartCategory")
    public ResponseEntity<?> getChartCategory(){
        return ResponseEntity.ok(dashboardService.getChartCategory());
    }
}
