package com.galaxy_swords.mvc.repository;

import com.galaxy_swords.mvc.model.Adventurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventurerRepository extends JpaRepository<Adventurer, String> {
    Adventurer findByEmail(String email);
}
