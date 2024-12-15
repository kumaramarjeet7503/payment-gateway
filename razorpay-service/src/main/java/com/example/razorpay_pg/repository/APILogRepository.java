package com.example.razorpay_pg.repository;

import com.example.razorpay_pg.entity.APILog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APILogRepository extends BaseRepository<APILog,Long> {
}
