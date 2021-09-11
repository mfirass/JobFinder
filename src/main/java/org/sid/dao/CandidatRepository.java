package org.sid.dao;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;





public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
	public Candidat getChefByUsername(@Param("username") String username);
    
    
//	
//	@Query(value="SELECT * FROM ressource r, offre o WHERE r.offre_id <> o.id_offre ",nativeQuery = true)
//	public List<Ressource> ressource_offre();
}
