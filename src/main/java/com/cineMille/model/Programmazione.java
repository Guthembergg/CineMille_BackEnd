package com.cineMille.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
    private SimpleDateFormat orario1 = new SimpleDateFormat("HH");
    @Column(nullable = true)
    private SimpleDateFormat orario2 = new SimpleDateFormat("HH");
    @Column(nullable = true)
    private SimpleDateFormat orario3 = new SimpleDateFormat("HH");
    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Film film;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Sala sala;
	public Programmazione(String titolo, LocalDate data, SimpleDateFormat orario1, SimpleDateFormat orario2,
			SimpleDateFormat orario3, Film film, Sala sala) {
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
