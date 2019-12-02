package m2.proj.celebrite.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.metier.IMetier;
import m2.proj.celebrite.metier.ProjMetier;
import m2.proj.celebrite.models.GetDepartementForm;


@Controller
@RequestMapping(value="/user")
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
	
	@RequestMapping(value="/updateCelebrite/{numCelebrite}", method = RequestMethod.GET)
	public String updateCelebrite(Model model){
		Celebrite celebrite = new Celebrite();
		model.addAttribute("UpdatCelebrity", celebrite);
		return "updateCelebrite";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCeleb(@ModelAttribute("addCelebrite") Celebrite celebrite) {
		if (celebrite != null) {
			metier.addCelebrity(celebrite);
		} else {
			metier.updateCelebrity(celebrite);
		}
		return ("redirect:/user/celebrite");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{numCelebrite}")
	public String deleteCelebrity(@PathVariable int numCelebrite) throws Exception {
	    metier.deleteCelebrity(numCelebrite);
	    return ("redirect:/user/celebrite");
	}
	
  }


