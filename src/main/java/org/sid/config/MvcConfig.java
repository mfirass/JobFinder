package org.sid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


	 
	 public void addViewControllers2(ViewControllerRegistry registry) {
	 registry.addViewController("/403").setViewName("403"); }
	 
	/*
	 * 
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/login").setViewName("login");
	 * registry.setOrder(Ordered.HIGHEST_PRECEDENCE); }
	 */
	 
	

}
