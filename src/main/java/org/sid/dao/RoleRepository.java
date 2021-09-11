package org.sid.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.sid.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{

    @Query("SELECT r FROM Role r WHERE r.name = :name")
	public Role getRoleByName(@Param("name") String name);
}
