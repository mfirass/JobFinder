package org.sid.dao;

import org.sid.entities.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


	public interface OffreRepository extends JpaRepository<Offre, Long> {
		@Query(value="select * from offre o where o.titre like :x", nativeQuery = true)
		public Page<Offre> chercher(@Param("x")String mc,Pageable pageble);
	
}
