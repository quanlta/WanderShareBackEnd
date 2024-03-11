package com.admin.admin.service.impl;

import com.admin.admin.model.*;
import com.admin.admin.repository.OrderDetailRepository;
import com.admin.admin.repository.OrderItemRepository;
import com.admin.admin.repository.TimeshareRepository;
import com.admin.admin.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderItemRepository orderItemRepository;
    private final TimeshareRepository timeshareRepository;
    @Override
    public int getTimeshareSold() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        int ans = 0;
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
        ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                ans += item.getQuantity();
            }
        }
        return ans;
    }

    @Override
    public float getTotalBudget() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        float ans = 0;
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
        ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                ans += (item.getPrice() * item.getQuantity());
            }
        }
        return ans;
    }

    @Override
    public ResponseEntity<?> getChartCategory() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        AtomicInteger longT = new AtomicInteger();
        AtomicInteger shortT = new AtomicInteger();
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
        ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                Optional<Timeshare> timeshare = timeshareRepository.findById(item.getTimeshare_id());
                timeshare.stream().forEach(timeshare1 -> {
                    if (timeshare1.getCategory_id() == 1){
                        longT.addAndGet(item.getQuantity());
                    }else {
                        shortT.addAndGet(item.getQuantity());
                    }
                });
            }
        }
        CategoryChartReponse categoryChartReponse = new CategoryChartReponse(longT,shortT);
        return ResponseEntity.ok(categoryChartReponse);
    }


    @Override
    public List<Timeshare> getAvailableTimeshares() {
        // Your logic to retrieve available timeshares
        return timeshareRepository.findByStatus(true);
    }
}
