package org.sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.controller.service.SampleService;
import org.sample.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    SampleService sampleService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("signupForm", new SignupForm());    	
        
    	model = addTeam(model);
    	
    	return model;
    }
    
    private ModelAndView addTeam(ModelAndView model) {
		List<Team> teamList = new ArrayList<Team>();
		Iterable<Team> teams = sampleService.getTeamList();
		
		for(Team t : teams) {
			teamList.add(t);
		}
		
		model.addObject("teamList", teamList);
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveFrom(signupForm);
            	model = new ModelAndView("show");
            	addTeam(model);
            } catch (InvalidUserException e) {
            	model = new ModelAndView("index");
            	addTeam(model);
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        	addTeam(model);
        }   	
    	return model;
    }
	
    @RequestMapping(value = "/newteam", method = RequestMethod.GET)
    public ModelAndView newTeam() {
    	ModelAndView model = new ModelAndView("new-team");
    	model.addObject("teamForm", new TeamForm());    	
        return model;
    }

    @RequestMapping(value = "/teamCreated", method = RequestMethod.POST)
    public ModelAndView createTeam(@Valid TeamForm teamForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model = new ModelAndView("teamCreated");    	
    	
        try {
        	sampleService.saveTeam(teamForm);
        } catch (Exception e) {
            model = new ModelAndView("new-team");
            model.addObject("page_error", e.getMessage());
        }
 	
    	return model;
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfileByUserId(@RequestParam(value = "userId", required = true) Long userId, HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView model = new ModelAndView("profile");
    	model.addObject("user", sampleService.getUser(userId));
    	return model;
    	
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }

}
