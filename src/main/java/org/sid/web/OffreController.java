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
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class OffreController {
	@Autowired
	private OffreRepository offreRepository ;
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private CandidatureRepository candidatureRepository ;
	
	@RequestMapping(value="/offre")
	public  String offre(Model model , @RequestParam(name="page",defaultValue = "0") int p ,
			   @RequestParam(name="size",defaultValue = "4") int s,
			   @RequestParam(name="moCle",defaultValue = "") String mc,HttpServletRequest request) {
			Page<Offre> pageOffres=
				offreRepository.chercher( "%"+mc+"%",PageRequest.of(p, s));
			model.addAttribute("listOffres",pageOffres.getContent());
			int[] pages=new	int[pageOffres.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			model.addAttribute("moCle",mc);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (SecurityContextHolder.getContext().getAuthentication() != null &&
					 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
					          instanceof AnonymousAuthenticationToken) ) { 
			Principal principal = request.getUserPrincipal();
			String username=  principal.getName();
			User user=userRepository.getUserByUsername(username);
			model.addAttribute("user", user);
			}
			
			
			else return "offres_non";
			
			
		
			return "offres";

	}
	
	
	@RequestMapping(value="/offre_non")
	public  String offre_non(Model model , @RequestParam(name="page",defaultValue = "0") int p ,
			   @RequestParam(name="size",defaultValue = "4") int s,
			   @RequestParam(name="moCle",defaultValue = "") String mc) {
			Page<Offre> pageOffres=
				offreRepository.chercher( "%"+mc+"%",PageRequest.of(p, s));
			model.addAttribute("listOffres",pageOffres.getContent());
			int[] pages=new	int[pageOffres.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			model.addAttribute("moCle",mc);
			
			
			
			return "offres_non";

	}
	
	@RequestMapping(value="/detail")
	public String detail(Model model, int id,String m,HttpServletRequest request) {
		Offre o=this.offreRepository.getOne((long) id);
		Candidature pc =new  Candidature();
		
		model.addAttribute("cand",pc);
		
		Offre of =new  Offre();
		

		Long id1=of.getId();
		//offre.getOffre().setId(id1);
		//model.addAttribute("id",id1);
		//model.addAttribute("off",o);
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		else return"login";
		

		model.addAttribute("offre",o);
	
		return "detail_offre";
	}
	
	@RequestMapping(value="/ajout_ann",method=RequestMethod.GET)
	public String ajout_ann(Model model,HttpServletRequest request) {
		//Ressource ressource = new  Ressource();
		
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 

		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		}
		else return "login";
		model.addAttribute("localDate", LocalDate.now());

		model.addAttribute("offre",new Offre());
		
//		System.out.println("\n\n\n achraf bghaaaaaaaaaaaaaaha\n\n"+ressource.toString());
		//model.addAttribute("ressource",ressource);
		
		
		return "ajout_ann";
	}
	
	@RequestMapping(value="/saver_ann",method=RequestMethod.POST)
	public String saver_ann(Model model, Offre offre,HttpServletRequest request ) {
		Offre pc =new  Offre();
		model.addAttribute("offre",pc);
		//model.addAttribute("localDate", LocalDate.now());
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = localDate.format(formatter);
		 offre.setDate_offre(formattedString);
		 
		 if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		
		Long id=user.getId();
		offre.getEntreprise().setId(id);
		
		 }
		 offreRepository.save(offre); 
			
		return  "success4";
	}
	@RequestMapping(value="/postuler",method=RequestMethod.POST)
	public String postuler(Model model, Candidature offre,HttpServletRequest request ) {
		Candidature pc =new  Candidature();
		Offre o =new  Offre();
		model.addAttribute("cand",pc);

		//Long id1=o.getId();
		//offre.getOffre().setId(id1);
		String id11= (String) request.getParameter("id_off");
		System.out.println("\n\n\n\n"+id11+"\n\n\n");
		Long id1=Long.parseLong(id11);
		
		model.addAttribute("id",id1);
		model.addAttribute("offres",o);
		 
		 if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		
		Long id=user.getId();
		offre.getCandidat().setId(id);
		//offre.setEtat_cand("ouvert");
		Offre of=this.offreRepository.getOne((long) id);
				
		offre.setOffre(of);
		offre.setEtat_cand("en_attente");
		 }
		 else return "login";
		 candidatureRepository.save(offre);
		 
		 
		    return "success";
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(long id,HttpServletRequest request) {
		
		offreRepository.deleteById(id);
		
	    return "redirect:/offre";
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String delete(Model model ,long id,HttpServletRequest request) {
		Offre o=this.offreRepository.getOne((long) id);
		model.addAttribute("offre", o);
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		
		
		 }
		 else return "login";
		
	    return "edit_ann";
	}
	
	@PostMapping("/save_edit/{id}")
	public String save_edit(Model model, Offre offre,HttpServletRequest request,@PathVariable("id") long id ) {
		
//		LocalDate localDate = LocalDate.now();//For reference
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
//		String formattedString = localDate.format(formatter);
//		 offre.setDate_offre(formattedString); 
		
		 if (SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken) ) { 
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.addAttribute("user", user);
		
		 }
		
		 offreRepository.save(offre); 
			
		return  "redirect:/offre";
	}
	
}
