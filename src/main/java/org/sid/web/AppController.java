package org.sid.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.sid.dao.CandidatRepository;
import org.sid.dao.EntrepriseRepository;
import org.sid.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import org.sid.entities.Candidat;
import org.sid.entities.Entreprise;
import org.sid.entities.Offre;
import org.sid.entities.Role;
import org.sid.entities.User;

@Controller
public class AppController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	CandidatRepository candidatRepository;
	
	/*
	 * @RequestMapping("/") public String viewHomePage(Model model) {
	 * List<Ressource> listRessources = service.listAll();
	 * model.addAttribute("listRessources", listRessources);
	 * 
	 * return "index"; }
	 */
	
	@RequestMapping("/") 
	public String viewHomePage() {
	    return "login"; 
	}
	/*
	 * @RequestMapping("/loginValid") public String viewLoginPage() { return
	 * "index"; }
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,Model model) {
		System.out.println("achraf tarueour \n\nggg\ngg\nggg\nggggg");
//		Principal principal = request.getUserPrincipal();
//		String username=  principal.getName();
//		User user=userRepository.getUserByUsername(username);
//		model.addAttribute("user", user);
		
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		  org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (auth != null) {
		    new SecurityContextLogoutHandler().logout(request, response, auth);
		  }
		  return "redirect:/login?logout";
}
	
	@RequestMapping(value="/accueil")
	public String accueil(Model model,HttpServletRequest request) {
		
		
				  
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 

		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		
		
    return "accueil"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
		
}
	
	@GetMapping("/registerCandidat")
	public ModelAndView getRegisterCandidat()
	{
		String viewName = "registerCandidat";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("guest", new Candidat());
		return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/registerEntreprise")
	public ModelAndView getRegisterEntreprise()
	{
		String viewName = "registerEntreprise";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("guest", new Entreprise());
		return new ModelAndView(viewName , model);
	}
	
	
	@PostMapping("/registerCandidat")
	public String RegisterCandidat(Candidat c)
	{
		String viewName = "login";
		Map<String, Object> model = new HashMap<String, Object>();
		Set<Role> roles=new HashSet<>();
		Role role=new Role();
		role.setName("candidat");
		roles.add(role);
		c.setRoles(roles);
		c.setEnabled(true);
		
		userRepository.save(c);
		return "success2";
	}
	
	@PostMapping("/registerEntreprise")
	public String RegisterEntreprise(Entreprise e)
	{
		String viewName = "login";
		Map<String, Object> model = new HashMap<String, Object>();
		
		Set<Role> roles=new HashSet<>();
		Role role=new Role();
		role.setName("entreprise");
		roles.add(role);
		e.setRoles(roles);
		e.setEnabled(true);
		userRepository.save(e);
		
		return "success2";
		}
	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register (Model model,HttpServletRequest request) {
		  
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 

		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		
		  return "register";
}
}
	
	
	

