package com.cineMille.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "programmazione")
public class Programmazione {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String titolo;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private LocalTime orario1 ;
    @Column(nullable = false)
    private LocalTime orario2;
    @Column(nullable = false)
    private LocalTime orario3;
    
 
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
 
    private Film film;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  
    private Sala sala;
	public Programmazione(String titolo, LocalDate data, LocalTime orario1, LocalTime orario2,
			LocalTime orario3, Film film, Sala sala) {
		super();
		this.titolo = titolo;
		this.data = data;
		this.orario1 = orario1;
		this.orario2 = orario2;
		this.orario3 = orario3;
		this.film = film;
		this.sala = sala;
	}
    
}
