package com.learnify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnify_backend.entity.OTP;

public interface OtpRepository  extends JpaRepository<OTP, Long> {
    public OTP findByEmail(String email);
    public void deleteByEmail(String email);
}
