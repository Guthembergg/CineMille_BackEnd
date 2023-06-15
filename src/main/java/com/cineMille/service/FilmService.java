package com.cineMille.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cineMille.model.Film;
import com.cineMille.repository.FilmRepository;


import jakarta.persistence.EntityNotFoundException;
@Service
public class FilmService {
	@Autowired
	FilmRepository repo;
	
	public List<Film> findAllFilm() {

		return repo.findAll();
	}
	public List<Film> findAllbyTitolo(String titolo) {
		if (repo.findFilmByTitolo(titolo).isEmpty()) {
			throw new EntityNotFoundException("Nessun film con questo titolo");
		}
		return repo.findFilmByTitolo(titolo);
	}

	public Page<Film> getAllFilmPageable(Pageable pageable) {
		if (repo.findAll(pageable).isEmpty()) {
			throw new EntityNotFoundException("Nessun film in archivio");
		} else
			return repo.findAll(pageable);
	}

	public Film findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessun film associato a questo ID");
		}
		return repo.findById(id).get();
	}
	public List<Film> findByTitolo(String titolo) {
		if (repo.findFilmByTitolo(titolo).isEmpty()) {
			throw new EntityNotFoundException("Nessun film con questo titolo");
		}
		return repo.findFilmByTitolo(titolo);
		
	}	
	public List<Film> findByData(LocalDate data) {
		if (repo.findFilmByDatauscita(data).isEmpty()) {
			throw new EntityNotFoundException("Nessun film con questa data");
		}
		return repo.findFilmByDatauscita(data);
	}

	public String deleteFilmById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessun film trovato con questo ID");
		}
		repo.deleteById(id);
		return "film eliminato";
	}

	public String addFilm(Film film) {

		repo.save(film);
		return "Film aggiunto";
	}

	public Film editFilm(Film dream) {
		if (!repo.existsById(dream.getId())) {
			throw new EntityNotFoundException("Nessun film trovato");
		}
		return repo.save(dream);
	}
}
