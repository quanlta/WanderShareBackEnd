package com.admin.admin.repository;

import com.admin.admin.model.ExchangeDetail;
import com.admin.admin.model.ExchangeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeDetailRepository extends JpaRepository<ExchangeDetail, Long> {
}
