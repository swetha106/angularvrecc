package com.vrecruit.controllers;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vrecruit.entities.Interviewer;
import com.vrecruit.entities.JobApplication;
import com.vrecruit.entities.User;
import com.vrecruit.repository.UserRepository;


@RequestMapping("/candidate")
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
//	 private static final Logger logger = Logger.getLogger(UserController.class);
//	 private static final String USERPAGE = "userpage";
//	 private static final String USERSESSION = "usersession";
//
//
	@Autowired
	private UserRepository userrepo;
	
	
	
	@PostMapping("/registeruser")
	
	public User registerUser(@RequestBody User user) throws Exception{
		String tempEmailId=user.getEmail();
		if(tempEmailId != null && ! "".equals(tempEmailId)) {
			User userobj = userrepo.findByEmail(tempEmailId);
			if(userobj != null) {
				throw new Exception("user with "+tempEmailId+"is already exists");
			}
		}
		User userobj=null;
		userobj = userrepo.save(user);
		return userobj;
	}
	@GetMapping("/get/{id}")
	public Optional<User> getById(@PathVariable Long id){
		return userrepo.findById(id);
	}
	@PostMapping("/login")

		public ResponseEntity<?> login(@RequestBody User user)
				throws URISyntaxException {
			String email=user.getEmail();
			String password=user.getPassword();
			Optional<User> userobj = Optional.ofNullable(userrepo.findByEmail(email));

			if (userobj.isPresent()) {
				if (userobj.get().getPassword().equals(password)) {
					return ResponseEntity.created(new URI("/api/employee/" + userobj.get().getId()))
							.body(userobj.get());
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	@PutMapping("/update")
    public ResponseEntity<?> updatePooling(@RequestBody User user){
    	User result = userrepo.save(user);
   	return ResponseEntity.ok().body(result);
    }

//	@RequestMapping(value = "/home")
//	public ModelAndView home() {
//		return new ModelAndView("home");
//		
//
//	}
//
//	@GetMapping("/list")
//	public String listUser(Model theModel) {
//		List<User> users = userService.getUsers();
//		theModel.addAttribute("user", users);
//		return "listusers";
//	}
//
//	@PostMapping(value = "/saveuser")
//	public ModelAndView createUser(@Valid @ModelAttribute("user") UserPOJO userPOJO, BindingResult result,HttpServletRequest request) throws UserExistsException {
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//
//		ModelAndView mav = new ModelAndView();
//
//		if (result.hasErrors()) {
//
//			mav.setViewName("userregistration");
//			// form validation error
//			return mav;
//		}
//		mav.setViewName(USERPAGE);
//		if(userService.checkuser(user.getEmail()))
//		{
//			  userService.saveCustomer(user);
//			  mav.addObject("user", user);
//			  mav.addObject(USERSESSION, user);
//			  HttpSession session = request.getSession();
//			   session.setAttribute("id",user.getId());
//			
//		    	
//		}
//		else
//		{
//			throw new UserExistsException("User exists....");
//		}
//     	
//   	return mav;
//
//	}
//	@ExceptionHandler({UserExistsException.class})
//	   public ModelAndView handleException(UserExistsException e) {
//		ModelAndView mav=new ModelAndView();
//		mav.setViewName("usererror");
//		mav.addObject("errormsg",e.getUserException());
//	      return mav;
//	}
//
//	@RequestMapping(value = "/userregistration")
//	public ModelAndView showLogin(@ModelAttribute("user") UserPOJO userPOJO) {
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//
//		ModelAndView mav = new ModelAndView("userregistration");
//
//		mav.addObject("user", new User());
//
//		return mav;
//
//	}
//
//	@GetMapping(value = "/loginform")
//	public ModelAndView showLoginform(@ModelAttribute("user") UserPOJO userPOJO) {
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//
//		ModelAndView mav = new ModelAndView("candidatelogin");
//
//		mav.addObject("user", user);
//
//		return mav;
//
//	}
//
//	@GetMapping(value = "/userpage")
//	public ModelAndView showUserPage(@ModelAttribute("usersession") UserPOJO userPOJO) {
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//
//		ModelAndView mav = new ModelAndView(USERPAGE);
//		mav.addObject(USERSESSION, user);
//
//		return mav;
//
//	}
//
//	@PostMapping(value = "/login")
//
//	public ModelAndView authenticate(@ModelAttribute("user") UserPOJO userPOJO,HttpServletRequest request) throws UserLoginException  {
//		
//		User users = new User();
//		BeanUtils.copyProperties(userPOJO, users);
//
//		String email = users.getEmail();
//		String password = users.getPassword();
//	 users = userService.validate(email, password);
//		if (users != null) {
//
//			ModelAndView mav = new ModelAndView(USERPAGE);
//			mav.addObject(USERSESSION, users);
//			 HttpSession session = request.getSession();
//			   session.setAttribute("id",users.getId());
//			   logger.info("user login successful!");
//				
//				return mav;
//
//		} else {
//			throw new UserLoginException("Login not successful..");
//		}
//		
//	}
//	@ExceptionHandler({UserLoginException.class})
//	   public ModelAndView handleException(UserLoginException e) {
//		ModelAndView mav=new ModelAndView();
//		mav.setViewName("usererror");
//		mav.addObject("errormsg",e.getUserException());
//	      return mav;
//	}
//	@GetMapping(value = "/viewprofile")
//	public ModelAndView viewprofile(@ModelAttribute("usersession") UserPOJO userPOJO,HttpServletRequest request) {
//		
//		ModelAndView mav=new ModelAndView();
//		HttpSession session = request.getSession();
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//
//		int id = (int) session.getAttribute("id");
//
//		 user = userService.viewprofile(id);
//		
//				mav.addObject(USERSESSION, user);
//			
//mav.setViewName("profile");
//		return mav;
//		 
//	}
//
//	@RequestMapping(value = "/editprofile")
//	public ModelAndView editprofile(@ModelAttribute("usersession") UserPOJO userPOJO,HttpServletRequest request) {
//		User user = new User();
//		BeanUtils.copyProperties(userPOJO, user);
//		HttpSession session = request.getSession();
//
//		int id = (int) session.getAttribute("id");
//
//		 user = userService.viewprofile(id);
//
//		ModelAndView mav = new ModelAndView("editprofile");
//		mav.addObject(USERSESSION, user);
//		return mav;
//	}
//
//	@PostMapping(value = "/updateuser")
//	public ModelAndView updateUser(@Valid @ModelAttribute("usersession") UserPOJO userPOJO, BindingResult result,HttpServletRequest request) {
//		HttpSession session = request.getSession();
//
//		int id = (int) session.getAttribute("id");
//
//		
//		User users;
//		 users = userService.viewprofile(id);
//			
//		BeanUtils.copyProperties(userPOJO, users);
//
//		ModelAndView mav = new ModelAndView();
//			if (result.hasErrors()) {
//			
//			mav.setViewName("editprofile");
//			// form validation error
//			return mav;
//		}
//		userService.saveCustomer(users);
//		
//		mav.setViewName("message");
//		String msg="Edited successfully!!";
//		mav.addObject("msg", msg);
//		
//		mav.addObject(USERSESSION, users);
//		return mav;
//
//	}
//
}
