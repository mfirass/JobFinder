package org.sid.dao;

import org.sid.entities.Entreprise;
import org.sid.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	
	@Query(value="select * from entreprise o where o.nom_entre like :x", nativeQuery = true)
	public Page<Entreprise> chercher2(@Param("x")String mc,Pageable pageble);
}
