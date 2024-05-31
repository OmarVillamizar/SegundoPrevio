package com.example.sprevio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sprevio.entities.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
}
