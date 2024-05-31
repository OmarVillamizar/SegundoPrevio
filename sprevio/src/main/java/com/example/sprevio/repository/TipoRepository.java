package com.example.sprevio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sprevio.entities.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
