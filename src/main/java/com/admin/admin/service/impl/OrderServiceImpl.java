package com.admin.admin.service.impl;

import com.admin.admin.model.*;
import com.admin.admin.repository.OrderDetailRepository;
import com.admin.admin.repository.OrderItemRepository;
import com.admin.admin.repository.TimeshareRepository;
import com.admin.admin.repository.UserRepository;
import com.admin.admin.service.MailService;
import com.admin.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    private final TimeshareRepository timeshareRepository;
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final MailService mailService;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> addOrder(OrderRequest orderRequest) {
        try {
            MailOrderInfor orderInfor = new MailOrderInfor();
            OrderResponse response = new OrderResponse();
            String id = orderRequest.getUser_id();
            Optional<Users> users = userRepository.findByEmail(id);
            users.stream().forEach(users1 -> {
                orderInfor.setName(users1.getUsername());
            });
            String idOrder = RandomStringUtils.random(5, true, true);
            String orderType = orderRequest.getOrderType();
            float[] total = {0};
            List<OrderDetail> orderDetails = new ArrayList<>(); // Danh sách đơn hàng tách riêng
            List<OrderMailTimeshareRespone> timeshareList = new ArrayList<>(); // Danh sách timeshare trong đơn hàng
            for (DataOrderRequest i : orderRequest.getDataOrderRequests()) {
                Optional<Timeshare> priceTimeshare = timeshareRepository.findById(i.getId());
                priceTimeshare.stream().forEach(timeshare -> {
                    float price = timeshare.getPrice() * i.getValue();
                    total[0] += price;
                    OrderDetail orderDetail = new OrderDetail(idOrder,
                            id,
                            LocalDateTime.now(),
                            price,
                            1);
                    orderDetailRepository.save(orderDetail);
                    orderDetails.add(orderDetail);
                    OrderMailTimeshareRespone mailTimeshareRespone = new OrderMailTimeshareRespone();
                    mailTimeshareRespone.setName(timeshare.getName());
                    mailTimeshareRespone.setQuality(i.getValue());
                    mailTimeshareRespone.setPrice(i.getValue() * timeshare.getPrice());
                    mailTimeshareRespone.setOrderType(orderType);
                    timeshareList.add(mailTimeshareRespone);
                    orderItemRepository.save(new OrderItem(orderDetail.getId(),
                            i.getId(),
                            i.getValue(),
                            timeshare.getPrice(),
                            orderType
                    ));
                    timeshare.setStatus(false);
                    timeshareRepository.save(timeshare);
                });
            }
            orderInfor.setTotal(total[0]);
            response.setOrderDetails(orderDetails);
            response.setTotal(total[0]);
            orderInfor.setTimeshareList(timeshareList);
            orderInfor.setEmail(orderRequest.getEmail());
            mailService.sendemail(orderInfor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getOrderByDate(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(orderDetailRepository.findByOrderdateBetween(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }

    @Override
    public boolean setStatus(String id) {
        try {
            Optional<OrderDetail> detail = orderDetailRepository.findById(id);
            if (!detail.isPresent()) {
                return false;
            }
            detail.stream().forEach(orderDetail -> {
                int check = orderDetail.getOrder_status();
                if (check == 1) {
                    check = 0;
                } else {
                    check = 1;
                }
                orderDetail.setOrder_status(check);
                orderDetailRepository.save(orderDetail);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponseEntity<?> getOrderByUserID(String id) {
        List<OrderDataRespone> dataRespones = new ArrayList<>();
        List<OrderDetail> orderDetails = orderDetailRepository.findByUserid(id);
        OrderDataRespone orderDataRespone = null;
        for (OrderDetail orderDetail : orderDetails) {
            orderDataRespone = new OrderDataRespone();
            orderDataRespone.setOrderDetail(orderDetail);
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            orderDataRespone.setOrderItemList(orderItemList);
            dataRespones.add(orderDataRespone);
        }
        return ResponseEntity.ok(dataRespones);
    }
}