package com.cineMille.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.repository.FilmRepository;
import com.cineMille.repository.SalaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SalaService {
	@Autowired
	SalaRepository repo;
	
	public List<Sala> findAllSala() {

		return repo.findAll();
	}
	public List<Sala> findSalabyNome(String nome) {
		if (repo.findSalaByNome(nome).isEmpty()) {
			throw new EntityNotFoundException("Nessuna sala con questo nome");
		}
		return repo.findSalaByNome(nome);
	}
	public List<Sala> findSalabyTipo(TipoSala tipo) {
		if (repo.findSalaByTipo(tipo).isEmpty()) {
			throw new EntityNotFoundException("Nessuna sala con questo tipo");
		}
		return repo.findSalaByTipo(tipo);
	}

	public Page<Sala> getAllSalaPageable(Pageable pageable) {
		if (repo.findAll(pageable).isEmpty()) {
			throw new EntityNotFoundException("Nessuna sala in archivio");
		} else
			return repo.findAll(pageable);
	}

	public Sala findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessuna sala associata a questo ID");
		}
		return repo.findById(id).get();
	}

	public String deleteSalaById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessuna sala trovata con questo ID");
		}
		repo.deleteById(id);
		return "sala eliminata";
	}

	public String addSala(Sala sala) {

		repo.save(sala);
		return "Sala aggiunta";
	}

	public Sala editSala(Sala sala) {
		if (!repo.existsById(sala.getId())) {
			throw new EntityNotFoundException("Nessuna sala trovata con questo ID");
		}
		return repo.save(sala);
	}
}
