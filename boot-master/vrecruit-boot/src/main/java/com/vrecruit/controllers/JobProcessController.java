package com.vrecruit.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrecruit.entities.JobApplication;
import com.vrecruit.entities.JobProcessDetails;
import com.vrecruit.entities.User;
import com.vrecruit.repository.JobApplicationRepository;
import com.vrecruit.repository.JobProcessRepository;
import com.vrecruit.repository.UserRepository;
@RequestMapping("/jobprocess")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//
public class JobProcessController {
	@Autowired
	JobProcessRepository jobprocessrepo;
	@Autowired
	private UserRepository userrepo;

	@Autowired
	JobApplicationRepository jobAppRepo;

	
	@GetMapping("joblist/{id}")
	public Collection<JobApplication> getById(@PathVariable Long id){
		Optional<User> userobj=userrepo.findById(id);
		Optional<JobProcessDetails> user=jobprocessrepo.findByUser(userobj);
		 
		 if(user.isPresent()) {
			 return null;
		 }// System.out.println(user);
			 return jobAppRepo.findAll();
		
	}
	@GetMapping("status/{id}")
	public Optional<JobProcessDetails> checkstatus(@PathVariable Long id){
		Optional<User> userobj=userrepo.findById(id);
		Optional<JobProcessDetails> user=jobprocessrepo.findByUser(userobj);
		 
		 if(user.isPresent()) {
			 return user;
		 }// System.out.println(user);
			 return null;
		
	}
	
	//***********************
@PostMapping("/applyjob/{id}")
public User applyforjob(@PathVariable Long id)
{
	
	return null;
}

//*****************************
	
//	
//	
//	List<JobApplication> lst;
//	JobProcessDetails job;
//	JobApplication jobApplication;
//	User user;
//	private static final String MESSAGE = "message";
//
////		CANDIDATE PART
//	@GetMapping(value = "/candidateJobAppList")
//	public ModelAndView candidateJobAppList(HttpServletRequest request) {
//		ModelAndView m = new ModelAndView();
//
//		HttpSession session = request.getSession();
//
//		int id = (int) session.getAttribute("id");
//
//		user = userService.viewprofile(id);
//
////	    	Getting list of interviewer from database
//		lst = jobAppDao.getAll();
//		boolean jobs = jobProcessService.validate(user);
//		if (jobs) {
//			m.setViewName("CandidateJobApplicationList");
//			m.addObject("lst", lst);
//			
//		} else {
//			String msg="You have already applied for a job";
//				m.addObject("msg", msg);
//				m.setViewName(MESSAGE);
//				
//
//		}
//
//		return m;
//	}
//
//	@RequestMapping(value = "/apply")
//	public ModelAndView applyforjob(@RequestParam("id") int id, HttpServletRequest request) {
//		ModelAndView mav=new ModelAndView("CandidateJobApplication");
//		 jobApplication = jobProcessService.getJob(id);
//		HttpSession session = request.getSession();
//		session.setAttribute("jid", jobApplication.getJid());
//		job = new JobProcessDetails();
//		mav.addObject("job", job);
//		return mav;
//		
//
//	}
//
//	@PostMapping(value = "/uploadresume")
//	public ModelAndView handleFileUpload(@ModelAttribute("job") JobProcessDetailsPOJO jobPOJO, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//
//		HttpSession session = request.getSession();
//
//		int jid = (int) session.getAttribute("jid");
//		
//		JobProcessDetails jobs = new JobProcessDetails();
//		BeanUtils.copyProperties(jobPOJO, jobs);
//		
//		// save job ID
//		jobApplication = jobProcessService.getJob(jid);
//		jobs.setJobApplication(jobApplication);
//		// save User ID
//		jobs.setUser(user);
//		user.setJobProcessDetails(jobs);
//		String msg;
//		// Save Resume
//		if(jobs.getResume().getSize() != 0) {
//			jobProcessService.save(jobs);
//			 msg="You have successfully applied for a job";
//		}
//		else
//		{
//			 msg="No resume found! try again";
//		}
//		mav.addObject("msg", msg);
//		
//	mav.setViewName(MESSAGE);
//	
//		
//	return mav;
//	}
//	@RequestMapping(value = "/status")
//	public ModelAndView status(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		String status;
//		HttpSession session = request.getSession();
//
//		int id = (int) session.getAttribute("id");
//
//		user = userService.viewprofile(id);
//		job = user.getJobProcessDetails();
//		if(job==null)
//		{
//			String msg="You have not applied for a job";
//			mav.addObject("msg", msg);
//		mav.setViewName(MESSAGE);
//		return mav;
//			
//		}
//		mav.addObject("job", job);
//		jobApplication = job.getJobApplication();
//		mav.addObject("jobApplication", jobApplication);
//		boolean a=job.getSelected();
//		if (a) {
//			if (jobApplication.getRounds() == job.getCurrentround()) {
//				status = "Congrats!!!! You are selected for the applied position";
//			} else {
//				status = "Congrats!!!! You are selected for the next round";
//			}
//		} else if ( job.getCurrentround() > 0) {
//			status = "Better luck next time!!";
//		} else {
//			status = "Waiting for interview";
//		}
//		mav.addObject("status", status);
//
//		mav.setViewName("status");
//		return mav;
//	}
//	
//	
//	
//
}
