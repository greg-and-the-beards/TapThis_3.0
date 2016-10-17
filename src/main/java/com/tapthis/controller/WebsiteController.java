package com.tapthis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.tapthis.entity.ReviewInfo;
import com.tapthis.entity.UserInfo;
import com.tapthis.service.ReviewService;
import com.tapthis.service.UserService;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;

	// index
	@RequestMapping("/")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}

	// user search results page with multiple beers
	@RequestMapping("/APIResults")
	public ModelAndView APIResults(ModelAndView mv) {
		return mv;
	}

	// specific beer selected from APIResults page
	@RequestMapping("/beerDetails")
	public ModelAndView beerDetails(ModelAndView mv) {
		return mv;
	}

	// registration page form for writing one user to database
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		return mv;
	}
	
	// POST one user to user table
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView eachUser(@ModelAttribute UserInfo user, ModelAndView mv) {
		userService.addUser(user);
		return new ModelAndView("redirect:/");
	}

	// HTTP login session
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ResponseEntity<List<UserInfo>> userLogin(@Valid @RequestBody UserInfo user, HttpSession sessionObj) {
		List<UserInfo> success = userService.verifyPassword(user.getUserName(), user.getPassword());
		String isValid = user.getPassword();
		if (!(success.get(0).getPassword().equals(isValid))) {
			sessionObj.setAttribute("error", "Username or password invalid!");
			return new ResponseEntity<List<UserInfo>>(HttpStatus.CONFLICT);
		} else {
			sessionObj.setAttribute("user", success.get(0));
			return new ResponseEntity<List<UserInfo>>(success, HttpStatus.OK);
		}
	}


//	 // GET one user from user table
//	 @RequestMapping("/updateUser/{id}")
//	 public ModelAndView editUser(@PathVariable("id") int id, ModelAndView mv)
//	 {
//	 mv.addObject("user", this.userService.getUserById(id));
//	 mv.setViewName("updateUser");
//	 return mv;
//	 }

//	// GET user for HTTP session
//	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
//	public ResponseEntity<UserInfo> getUser(HttpSession sessionObj, Model mv) {
//		System.out.println("hello");
//		UserInfo user = (UserInfo) sessionObj.getAttribute("user");
//		UserInfo updateUser = userService.getUserByUsername(user.getUserName());
//		sessionObj.setAttribute("user", updateUser);
//		System.out.println("hello2");
//		return new ResponseEntity<UserInfo>(updateUser, HttpStatus.OK);
//	}

	@RequestMapping(value = "/updateUser")
	public ModelAndView updateUserView(ModelAndView mv) {
		return mv;
	}

	// PUT one user to user table
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute UserInfo user, @PathVariable("id") int id, ModelAndView mv) {
		mv.addObject("user", this.userService.getUserById(id));
		userService.updateUser(user);
		return new ModelAndView("redirect:/");
	}

	// has not been implemented
	// DELETE one user to user table
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}

	// POST one review to review table
	@RequestMapping(value = "/beerDetails", method = RequestMethod.POST)
	public ModelAndView eachReview(@ModelAttribute ReviewInfo review, ModelAndView mv) {
		reviewService.addReview(review);
		return new ModelAndView("redirect:/");
	}

	// GET all reviews by one beerName from review table
	@RequestMapping(value = "/beerDetails", params = {"beerInfo"} )
	public ModelAndView getReviewsByBeerName(@RequestParam("beerInfo") String beerInfo, ModelAndView mv) {
		mv.addObject("reviews", this.reviewService.getReviewByBeerName(beerInfo));
		mv.setViewName("beerDetails");
		return mv;
	}

	// //GET all reviews by one reviewUserId from review table
	// @RequestMapping(value="/allreviewsbyid/{reviewUserId}", method =
	// RequestMethod.GET)
	// public ResponseEntity<List<ReviewInfo>>
	// getAllReviewsByUserId(@PathVariable("reviewUserId") int reviewUserId) {
	// List<ReviewInfo> review =
	// reviewService.getAllReviewsByUserId(reviewUserId);
	// return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	// }
	//
	// //GET one review by one reviewUserId from review table
	// @RequestMapping(value="/onereviewbyid/{reviewUserId}/{reviewId}", method
	// = RequestMethod.GET)
	// public ResponseEntity<List<ReviewInfo>>
	// getOneReviewByUserId(@PathVariable("reviewUserId") int reviewUserId,
	// @PathVariable("reviewId") int reviewId) {
	// List<ReviewInfo> review =
	// reviewService.getOneReviewByUserId(reviewUserId, reviewId);
	// return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	// }
	//
	// //PUT one review by reviewId to review table
	// @RequestMapping(value="/review/{id}", method = RequestMethod.PUT )
	// public ResponseEntity<ReviewInfo> updateReview(@RequestBody ReviewInfo
	// review) {
	// reviewService.updateReview(review);
	// return new ResponseEntity<ReviewInfo>(review, HttpStatus.OK);
	// }
	//
	// //DELETE one review by reviewId to review table
	// @RequestMapping(value="/review/{id}", method = RequestMethod.DELETE )
	// public ResponseEntity<ReviewInfo> deleteReview(@PathVariable("id")
	// Integer reviewId) {
	// reviewService.deleteReview(reviewId);
	// return new ResponseEntity<ReviewInfo>(HttpStatus.NO_CONTENT);
	// }
}
