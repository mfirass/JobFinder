package org.sid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.awt.Button;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.coyote.Request;
import org.sid.dao.CandidatRepository;
import org.sid.dao.EntrepriseRepository;
import org.sid.dao.OffreRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Candidat;
import org.sid.entities.Entreprise;
import org.sid.entities.Offre;
import org.sid.entities.User;
@Controller
public class CandidatController {
	@Autowired
	private OffreRepository offreRepository ;
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private CandidatRepository candidatRepository ;
	
	@RequestMapping(value="/list_cand")
	public String list_cand(Model model,HttpServletRequest request) {
		List<Candidat> page=candidatRepository.findAll();
		model.addAttribute("listcand",page);
			
			
		
		
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 

		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		else return "login";
		
    return "list_cand"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
		
	
}
}
