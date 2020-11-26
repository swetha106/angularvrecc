package com.vrecruit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vrecruit.entities.Interviewer;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer,Long> {
	 public Optional<Interviewer> findByEmail(String email);
}
