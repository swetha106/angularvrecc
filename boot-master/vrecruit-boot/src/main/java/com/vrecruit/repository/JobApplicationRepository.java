package com.vrecruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vrecruit.entities.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

}
