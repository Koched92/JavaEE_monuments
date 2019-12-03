package m2.proj.celebrite.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.entities.User;
import m2.proj.celebrite.metier.IMetier;
import m2.proj.celebrite.metier.ProjMetier;
import m2.proj.celebrite.models.GetDepartementForm;


@Controller
public class ProjectController {
	@Autowired
	ProjMetier metier;
	
	
	@RequestMapping(value="/celebrite", method = RequestMethod.GET)
	public String Celebrite(Model model){
		List<Celebrite> list = metier.listAllCelebrity();
		model.addAttribute("ListCelebrity", list);
		return "celebrites";
	}
	
	@RequestMapping(value="/addCelebrite", method = RequestMethod.GET)
	public String AddCelebrite(Model model){
		Celebrite celebrite = new Celebrite();
		model.addAttribute("AddCelebrity", celebrite);
		return "addCelebrite";
	}
	
	/*
	 * @RequestMapping( value="/edit") public String UpdateCelebrite(int
	 * numCelebrite, Model model) { Celebrite celeb=
	 * metier.findCelebrityByNum(numCelebrite);
	 * model.addAttribute("UpdateCelebrite",celeb); return("addCelebrite"); }
	 */
	/*
	 * @RequestMapping(value="/updateCelebrite/{numCelebrite}", method =
	 * RequestMethod.GET) public String updateCelebrite(@PathVariable int
	 * numCelebrite) throws Exception { ModelAndView model = new ModelAndView();
	 * Celebrite celebrite = metier.findCelebrityByNum(numCelebrite);
	 * model.addObject("addCelebrite", celebrite); return "addCelebrite"; }
	 */
	@GetMapping("/edit/{numCelebrite}")
	public String showUpdateForm(@PathVariable("numCelebrite") int numCelebrite, Model model) {
	    Celebrite celebrite = metier.findCelebrityByNum(numCelebrite);

	    model.addAttribute("celebrite", celebrite);
	    return "update-user";
	}
	@PostMapping("/update/{numCelebrite}")
	public String updateUser(@PathVariable("numCelebrite") int numCelebrite, @Valid Celebrite celebrite,
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        celebrite.setNumCelebrite(numCelebrite);
	        return "update-user";

	    }else {
	    metier.updateCelebrity(celebrite);
	    model.addAttribute("celebrite", celebrite);
	    
	    return ("redirect:/celebrite");
	    }
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCeleb(@ModelAttribute("addCelebrite") Celebrite celebrite) {
		if (celebrite != null) {
			metier.addCelebrity(celebrite);
		} else {
			metier.updateCelebrity(celebrite);
		}
		return ("redirect:/celebrite");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{numCelebrite}")
	public String deleteCelebrity(@PathVariable int numCelebrite) throws Exception {
	    metier.deleteCelebrity(numCelebrite);
	    return ("redirect:/celebrite");
	}
	
	///////////////////////////////////////LOGIN CONTROLLER//////////////////////////////////::
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(metier.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "user already exists!");			
		}
		// we will save the user if, no binding errors
		else {
			metier.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	
  }


