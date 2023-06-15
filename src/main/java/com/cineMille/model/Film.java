package com.cineMille.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "film")
public class Film {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = true,length = 256)
    private String immagine;
    @Column(nullable = false)
    private LocalDate data_uscita;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "film")
    @JsonIgnore
    private List<Programmazione> programmazioni= new ArrayList<>();
	public Film(String titolo, String immagine, LocalDate data_uscita) {
		super();
		this.titolo = titolo;
		this.immagine = immagine;
		this.data_uscita = data_uscita;
	}
    
}
