package com.side.naruto.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.side.naruto.models.Jutsu;
import com.side.naruto.models.LoginUser;
import com.side.naruto.models.User;
import com.side.naruto.services.JutsuService;
import com.side.naruto.services.UserService;

@Controller
public class HomeController {
	private UserService userServ;
	private JutsuService jutsuServ;
	public HomeController(UserService userServ, JutsuService jutsuServ) {
		this.userServ = userServ;
		this.jutsuServ = jutsuServ;
	}
	
	//#################################################################################################
	//########################      START OF LOGIN AND REG       ######################################
	//#################################################################################################

	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		model.addAttribute("newUser", new User()); 
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@GetMapping("/dashboard")
	public String home(HttpSession session, Model model) {
		//check if "user_id" is not in session
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";			
		} else {
			//This sends our User info to the JSP
			model.addAttribute("user", userServ.findOneUser((Long) session.getAttribute("user_id")));
			//This sends our "allJutsus" info to the jsp
			model.addAttribute("allJutsus", jutsuServ.getAllJutsus());
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//clears the session signing out the user then redirecting to login page
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser")User newUser, 
			BindingResult result, Model model, HttpSession session) {
		//first we call on our service method register to check if the email is in session
		userServ.register(newUser,  result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		//setting our user_id into session
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";		
		}
	@PostMapping("/login")
	public String login (@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
			Model model, HttpSession session) {
		User user = userServ.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new User());
			return "index.jsp";
	}
		session.setAttribute("user_id", user.getId());
		return "redirect:/dashboard";
	}
	//#################################################################################################
	//########################      END OF LOGIN AND REG       ########################################
	//#################################################################################################
	
	
	//render view for create jutsu

	@GetMapping("/newJutsu")
	public String newJutsu(HttpSession session, Model model) {
		User user = userServ.findOneUser((Long)session.getAttribute("user_id"));
		model.addAttribute("user", user);
		model.addAttribute("jutsu", new Jutsu());
		return "newJutsu.jsp";
	}
	
	
	
	//post route to create
	@PostMapping("/createJutsu")
	public String createJutsu(@Valid @ModelAttribute("jutsu")Jutsu jutsu, BindingResult result) {
		if(result.hasErrors()) {
			return"newJutsu.jsp";
		}
		//make sure we tie the user to the jutsu
		jutsuServ.createJutsu(jutsu);
		return "redirect:/dashboard";
	}
	
	//render view for edit
	@GetMapping("/editJutsu/{id}")
	public String editJutsu(@PathVariable("id") Long id, @ModelAttribute("jutsu") Jutsu jutsu, Model model, HttpSession session) {
		model.addAttribute("jutsu", jutsuServ.getOneJutsu(id)); //finding info for the jutsu by id
		model.addAttribute("user", userServ.findOneUser ((Long)session.getAttribute("user_id"))); //finding info from the user id tied to the jutsu
		return "editJutsu.jsp";
	}
	
	//post route to update
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String updatingJutsu(@Valid @PathVariable("id")Long id, @ModelAttribute("jutsu")Jutsu jutsu, BindingResult result) {
		if(result.hasErrors()) {
			return"editJutsu.jsp";
		}
		jutsuServ.updateJutsu(jutsu);
		return "redirect:/dashboard";
	}
	
	
	//Render page to view one jutsu
	@GetMapping("/oneJutsu/{id}")
	public String oneJutsu(@PathVariable("id")Long id, Model model, HttpSession session) {
		model.addAttribute("jutsu",jutsuServ.getOneJutsu(id)); // getting one jutsu's info and sending it to the JSP
		model.addAttribute("user", userServ.findOneUser((Long) session.getAttribute("user_id"))); //getting the user in session and sending that to the JSP
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";			
		} else {
			return "oneJutsu.jsp";
		}
	}
	
	@RequestMapping(value="/jutsu/{id}/delete")
	public String deleteJutsu(@PathVariable("id")Long id, Model model) {
		this.jutsuServ.deleteJutsu(id);
		return "redirect:/dashboard";
	}
	
	
}
