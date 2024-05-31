package com.example.sprevio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sprevio.entities.Manga;
import com.example.sprevio.entities.Pais;
import com.example.sprevio.entities.Tipo;
import com.example.sprevio.models.MangaRequest;
import com.example.sprevio.models.MangaResponse;
import com.example.sprevio.repository.FavoritoRepository;
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

    @Autowired
    private FavoritoRepository favoritoRepository;

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
    public ResponseEntity<Object> crearManga(@RequestBody MangaRequest nuevoMangaRequest) {
        // Validar existencia del país y tipo
        Optional<Pais> paisOpt = paisRepository.findById(nuevoMangaRequest.getPaisId());
        Optional<Tipo> tipoOpt = tipoRepository.findById(nuevoMangaRequest.getTipoId());

        if (paisOpt.isEmpty()) {
            return ResponseEntity.status(400).body("{\"error\":true,\"msg\":\"Pais no existe\"}");
        }

        if (tipoOpt.isEmpty()) {
            return ResponseEntity.status(400).body("{\"error\":true,\"msg\":\"Tipo no existe\"}");
        }

        Manga nuevoManga = new Manga();
        nuevoManga.setNombre(nuevoMangaRequest.getNombre());
        nuevoManga.setFechaLanzamiento(nuevoMangaRequest.getFechaLanzamiento());
        nuevoManga.setTemporadas(nuevoMangaRequest.getTemporadas());
        nuevoManga.setPais(paisOpt.get());
        nuevoManga.setTipo(tipoOpt.get());
        nuevoManga.setAnime(nuevoMangaRequest.getAnime());
        nuevoManga.setJuego(nuevoMangaRequest.getJuego());
        nuevoManga.setPelicula(nuevoMangaRequest.getPelicula());

        Manga mangaGuardado = mangaRepository.save(nuevoManga);
        MangaResponse mangaResponse = new MangaResponse(mangaGuardado.getId(), mangaGuardado.getNombre(), mangaGuardado.getFechaLanzamiento(), mangaGuardado.getTemporadas(), paisOpt.get().getNombre(), mangaGuardado.getAnime(), mangaGuardado.getJuego(), mangaGuardado.getPelicula(), tipoOpt.get().getNombre());

        return ResponseEntity.ok(mangaResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarManga(@PathVariable Integer id, @RequestBody MangaRequest mangaRequest) {
        Optional<Manga> mangaOpt = mangaRepository.findById(id);
        if (mangaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("{\"error\":true,\"msg\":\"Objeto no encontrado\"}");
        }

        Optional<Pais> paisOpt = paisRepository.findById(mangaRequest.getPaisId());
        Optional<Tipo> tipoOpt = tipoRepository.findById(mangaRequest.getTipoId());

        if (paisOpt.isEmpty()) {
            return ResponseEntity.status(400).body("{\"error\":true,\"msg\":\"Pais no existe\"}");
        }

        if (tipoOpt.isEmpty()) {
            return ResponseEntity.status(400).body("{\"error\":true,\"msg\":\"Tipo no existe\"}");
        }

        Manga manga = mangaOpt.get();
        manga.setNombre(mangaRequest.getNombre());
        manga.setFechaLanzamiento(mangaRequest.getFechaLanzamiento());
        manga.setTemporadas(mangaRequest.getTemporadas());
        manga.setPais(paisOpt.get());
        manga.setTipo(tipoOpt.get());
        manga.setAnime(mangaRequest.getAnime());
        manga.setJuego(mangaRequest.getJuego());
        manga.setPelicula(mangaRequest.getPelicula());

        Manga mangaActualizado = mangaRepository.save(manga);
        MangaResponse mangaResponse = new MangaResponse(mangaActualizado.getId(), mangaActualizado.getNombre(), mangaActualizado.getFechaLanzamiento(), mangaActualizado.getTemporadas(), paisOpt.get().getNombre(), mangaActualizado.getAnime(), mangaActualizado.getJuego(), mangaActualizado.getPelicula(), tipoOpt.get().getNombre());

        return ResponseEntity.ok(mangaResponse);
    }
}
