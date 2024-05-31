package com.example.sprevio.repository;

import com.example.sprevio.entities.Favorito;
import com.example.sprevio.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    boolean existsByManga(Manga manga);
}
