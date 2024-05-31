package com.example.sprevio.entities;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private Date fechaLanzamiento;
    private int temporadas;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    private int anime;
    private int juego;
    private int pelicula;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;
}
