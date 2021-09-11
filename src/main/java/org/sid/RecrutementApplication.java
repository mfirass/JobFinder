package org.sid;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.sid.dao.CandidatRepository;
import org.sid.dao.OffreRepository;
import org.sid.entities.Candidat;
import org.sid.entities.Entreprise;
import org.sid.entities.Offre;
import org.sid.entities.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class RecrutementApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(RecrutementApplication.class, args);
		OffreRepository offre= ctx.getBean(OffreRepository.class);
		offre.save(new Offre("Developpeur","djkzkjdzkjjkdzkjdzjk","OUJDA","https://cdn.futura-sciences.com/buildsv6/images/wide1920/5/e/7/5e7e3edfb3_50149494_developpeur-web.jpg",
				"Informatique",8000,"Bac +5") );
		
		
		org.sid.dao.UserRepository user= ctx.getBean(org.sid.dao.UserRepository.class);

		Set<Role> role3=new HashSet<>();
		 Role roles3=new Role();
		 roles3.setName("ADMIN");
		 role3.add(roles3);


		Set<Role> role1=new HashSet<>();
		 Role roles1=new Role();
		 roles1.setName("candidat");
		 role1.add(roles1);

		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 String rawPassword = "admin";
		 String encodedPassword = encoder.encode(rawPassword);

		 user.save(new Candidat("candidat",encodedPassword,true,role1,"benali","abdelhak","sidi yhaya oujda","abdel.benali1998@gmail.com","0624255708","célibataire",
					"ambitieux,creatif,esprits d équipe") );


		 Set<Role> role2=new HashSet<>();
		 Role roles2=new Role();
		 roles2.setName("entreprise");
		 role2.add(roles2);



		 user.save(new Entreprise("entreprise",encodedPassword,true,role2,"Alten","2010","Ain chkef rue 20","Fes","Alten.noreply@gmail.com",
					"0536542547","Informatique et Télecommunication","commercial") );

		 
		 
		 
}
}