package org.sid.dao;

import org.sid.entities.Candidat;
import org.sid.entities.Entreprise;
import org.sid.entities.Offre;
import org.sid.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
	public Entreprise getChefByUsername(@Param("username") String username);
    
	
	



//	
//	@Query(value="SELECT * FROM ressource r, offre o WHERE r.offre_id <> o.id_offre ",nativeQuery = true)
//	public List<Ressource> ressource_offre();
}
