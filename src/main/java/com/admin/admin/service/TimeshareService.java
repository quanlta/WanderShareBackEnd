package com.admin.admin.service;

import com.admin.admin.model.Timeshare;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TimeshareService {
    boolean setStatus(Long id);
    boolean setIsCheck(Long id);
    ResponseEntity<?> updateTimeshare(Long timeshareID, Timeshare timeshare);
}