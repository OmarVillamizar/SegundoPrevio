package com.example.sprevio.entities;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
@Data
public class Pais {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String nombre;

}
