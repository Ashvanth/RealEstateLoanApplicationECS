package com.demo.dnb.repository;

import com.demo.dnb.entity.ApplicantInformation;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface ApplicantionRepository extends JpaRepository<ApplicantInformation,Long> {


}
