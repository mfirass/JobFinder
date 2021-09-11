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
import org.springframework.web.bind.annotation.PostMapping;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.coyote.Request;
import org.sid.dao.CandidatRepository;
import org.sid.dao.CandidatureRepository;
import org.sid.dao.OffreRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Candidat;
import org.sid.entities.Candidature;
import org.sid.entities.Offre;
import org.sid.entities.User;
@Controller
public class CandidatureController {
	@Autowired
	private OffreRepository offreRepository ;
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private CandidatureRepository candidatureRepository ;
	
	@RequestMapping(value="/candidature")
	public String candidature(Model model,HttpServletRequest request) {
		
		List<Offre> offre=offreRepository.findAll();
		model.addAttribute("listoffre",offre);
		
		
		
		List<Candidature> candidature=candidatureRepository.findAll();
		List<Candidature> candidature2 = new ArrayList<Candidature>();
		for (Candidature i : candidature) {
			if(i.getEtat_cand().equals("en_attente"))
			{
				candidature2.add(i);
				model.addAttribute("listcand",candidature2);
			}
			else {
				
		          }
		}
		

		
		
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 

		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		else return "login";
		
		//offre.getOffre().setId(id1);
		//model.addAttribute("id",id1);
		//model.addAttribute("off",o);
		
		return "candi";
	}
	
	@PostMapping("/accepter/{id}")
	public String postuler(Model model,@PathVariable("id") long id, Candidature offre,HttpServletRequest request ) {
		Candidature pc =new  Candidature();
		Offre o =new  Offre();
		model.addAttribute("cand",pc);

		//String etat= (String) request.getParameter("etat");
		//model.addAttribute("etat",etat);
		
		 if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		
		
		Candidature of=this.candidatureRepository.getOne((long) id);
		
		
		of.setEtat_cand("accepte");
		//Long id=user.getId();
		//offre.getCandidat().setId(id);
		//offre.setEtat_cand("ouvert");
		//Offre of=this.offreRepository.getOne((long) id);
				
		//offre.setEtat_cand("accepter");
		 }
		 else return "login";
		 candidatureRepository.save(offre);
		 
		    return "success3";
		
	}
	
}
