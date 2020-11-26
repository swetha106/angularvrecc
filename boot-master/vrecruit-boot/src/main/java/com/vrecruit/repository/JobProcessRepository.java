package com.vrecruit.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vrecruit.entities.Interviewer;
import com.vrecruit.entities.JobApplication;
import com.vrecruit.entities.JobProcessDetails;
import com.vrecruit.entities.User;

@Repository
public interface JobProcessRepository extends JpaRepository<JobProcessDetails, Long> {
	
	public Collection<JobProcessDetails> findByJobApplication(Optional<JobApplication> obj);
	public Optional<JobProcessDetails> findByUser(Optional<User>  user);
	

}
