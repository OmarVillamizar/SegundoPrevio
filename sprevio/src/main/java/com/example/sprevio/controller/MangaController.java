package com.example.sprevio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sprevio.entities.Manga;
import com.example.sprevio.repository.MangaRepository;

import java.util.List;

@RestController
@RequestMapping("/mangas")
public class MangaController {
    
    @Autowired
    private MangaRepository mangaRepository;

    @GetMapping
    public ResponseEntity<List<Manga>> obtenerTodosLosMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        return ResponseEntity.ok(mangas);
    }
}