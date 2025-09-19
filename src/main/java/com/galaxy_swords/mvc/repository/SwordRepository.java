package com.galaxy_swords.mvc.repository;

import com.galaxy_swords.mvc.model.Sword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwordRepository extends JpaRepository<Sword, Long> {
}
