package com.cineMille.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "sala")
public class Sala {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSala tipo;
    @Column(nullable = false)
    private int posti;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "sala")
    @JsonIgnore
    private List<Programmazione> programmazioni= new ArrayList<>();
	public Sala(String nome, TipoSala tipo, int posti) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.posti = posti;
	}
	public Sala(Long id, String nome, TipoSala tipo, int posti) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.posti = posti;
	}
    
}
