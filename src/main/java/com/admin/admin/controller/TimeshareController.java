package com.admin.admin.controller;


import com.admin.admin.model.Timeshare;
import com.admin.admin.repository.TimeshareCusRepo;
import com.admin.admin.repository.TimeshareRepository;
import com.admin.admin.service.TimeshareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timeshare")
@RequiredArgsConstructor
public class TimeshareController {
    private final TimeshareRepository timeshareRepository;
    private final TimeshareCusRepo timeshareCusRepo;
    private final TimeshareService timeshareService;
    @GetMapping
    public ResponseEntity<?> getAllTimeshare(){
        List<Timeshare> timeshares = timeshareRepository.findAll();
        return ResponseEntity.ok(timeshares);
    }
    @GetMapping("/count")
    public ResponseEntity<?> countTimeshare(){

        return ResponseEntity.ok(timeshareRepository.count());
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteTimeshare(@RequestParam Long id){
        try {
            timeshareRepository.deleteById(id);
            return ResponseEntity.ok("200");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());

        }

    }
    @PostMapping("/add")
    public ResponseEntity<?> addTimeshare(@RequestBody Timeshare timeshare){
        try {
            timeshare.setIs_check(false);
            timeshare.setStatus(false);
            timeshareRepository.save(timeshare);
            return ResponseEntity.ok("Add success");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PostMapping("/changeCheck")
    public boolean changeCheck(Long id){
        return timeshareService.setIsCheck(id);
    }
    @PostMapping("/changeStatus")
    public boolean changeStatus(Long id){
        return timeshareService.setStatus(id);
    }
    @GetMapping("/seacrhByName")
    public ResponseEntity<?> getTimeshareByName(@RequestParam String q){
        List<Timeshare> timeshares = timeshareRepository.findTop5ByNameContainingIgnoreCase(q);
        return ResponseEntity.ok(timeshares);
    }
    @GetMapping("/getAllOrderByPriceAsc")
    public ResponseEntity<?> getAllOrderByPrice(){
        List<Timeshare> timeshares = timeshareRepository.findByOrderByPriceAsc();
        return ResponseEntity.ok(timeshares);
    }
    @GetMapping("/getAllOrderByPriceDesc")
    public ResponseEntity<?> getAllOrderByPriceDesc(){
        List<Timeshare> timeshares = timeshareRepository.findByOrderByPriceDesc();
        return ResponseEntity.ok(timeshares);
    }
    @GetMapping("/getAllOrderByNameAsc")
    public ResponseEntity<?> getAllOrderByNameAsc(){
        List<Timeshare> timeshares = timeshareCusRepo.findByOrderByNameAsc();
        return ResponseEntity.ok(timeshares);
    }
    @GetMapping("/getAllOrderByNameDesc")
    public ResponseEntity<?> getAllOrderByNameDesc(){
        List<Timeshare> timeshares = timeshareCusRepo.findByOrderByNameDesc();
        return ResponseEntity.ok(timeshares);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTimeshare(@RequestParam Long id,
                                             @RequestBody Timeshare timeshare){
        return ResponseEntity.ok(timeshareService.updateTimeshare(id,timeshare));
    }
}