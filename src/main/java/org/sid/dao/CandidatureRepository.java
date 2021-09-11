package org.sid.dao;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Candidat;
import org.sid.entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;





public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    
}
