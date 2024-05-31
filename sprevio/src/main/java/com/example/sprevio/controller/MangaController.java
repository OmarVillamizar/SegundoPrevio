package com.example.sprevio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sprevio.entities.Manga;
import com.example.sprevio.entities.Pais;
import com.example.sprevio.entities.Tipo;
import com.example.sprevio.repository.MangaRepository;
import com.example.sprevio.repository.PaisRepository;
import com.example.sprevio.repository.TipoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mangas")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping
    public ResponseEntity<List<Manga>> obtenerTodosLosMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        return ResponseEntity.ok(mangas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerMangaPorId(@PathVariable Integer id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isPresent()) {
            return ResponseEntity.ok(manga.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\":true,\"msg\":\"Objeto no encontrado\"}");
        }
    }

    @PostMapping
    public ResponseEntity<Object> crearManga(@RequestBody Manga nuevoManga) {
        Optional<Pais> pais = paisRepository.findById(nuevoManga.getPais().getId());
        Optional<Tipo> tipo = tipoRepository.findById(nuevoManga.getTipo().getId());

        if (pais.isPresent() && tipo.isPresent()) {
            nuevoManga.setPais(pais.get());
            nuevoManga.setTipo(tipo.get());
            Manga mangaGuardado = mangaRepository.save(nuevoManga);
            return ResponseEntity.ok(mangaGuardado);
        } else {
            return ResponseEntity.status(400).body("{\"error\":true,\"msg\":\"El país o el tipo especificado no existe\"}");
        }
    }
}
