package com.example.sprevio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sprevio.entities.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {
}
