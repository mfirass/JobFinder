package org.sid.web;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.sid.dao.ArticleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Article;
import org.sid.entities.Entreprise;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/articles")
	public ModelAndView getArticles(@RequestParam(name="page",defaultValue = "0") int p ,
			   @RequestParam(name="size",defaultValue = "4") int s,
			   @RequestParam(name="moCle",defaultValue = "") String mc,HttpServletRequest request)
	{
		
		Page<Article> pageArticles=
				articleRepository.chercher( "%"+mc+"%",PageRequest.of(p, s));
			int[] pages=new	int[pageArticles.getTotalPages()];	
		String viewName = "articles";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("articles", articleRepository.findAll());
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.put("user", user);
			model.put("pages", pages);
			model.put("size",s);
			model.put("pageCourante",p);
			model.put("moCle",mc);		
		return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/addArticle")
	public ModelAndView getArticleForm(HttpServletRequest request)
	{
		String viewName = "addArticle";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("article", new Article());
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.put("user", user);
		return new ModelAndView(viewName , model);
	}
	
	
	@PostMapping("/addArticle")
	public ModelAndView addArticle(Article a,@RequestParam(name="page",defaultValue = "0") int p ,
			   @RequestParam(name="size",defaultValue = "4") int s,
			   @RequestParam(name="moCle",defaultValue = "") String mc, HttpServletRequest request)
	{
		String viewName = "articles";
		Page<Article> pageArticles=
				articleRepository.chercher( "%"+mc+"%",PageRequest.of(p, s));
			int[] pages=new	int[pageArticles.getTotalPages()];
		Map<String, Object> model = new HashMap<String, Object>();
		Principal principal = request.getUserPrincipal();
		String username=  principal.getName();
		User user=userRepository.getUserByUsername(username);
		model.put("user", user);
		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm");
		Date now = new Date();
		a.setDate_article(""+df.format(now));
		Entreprise e = (Entreprise) user;
		a.setEntreprise(e);
		articleRepository.save(a);
		model.put("articles", articleRepository.findAll());
		model.put("pages", pages);
		model.put("size",s);
		model.put("pageCourante",p);
		model.put("moCle",mc);
		return new ModelAndView(viewName , model);
		}
		
}
