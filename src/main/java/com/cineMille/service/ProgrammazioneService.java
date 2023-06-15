package com.cineMille.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cineMille.model.Programmazione;
import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.repository.ProgrammazioneRepository;
import com.cineMille.repository.SalaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProgrammazioneService {
	@Autowired
	ProgrammazioneRepository repo;
	
	public List<Programmazione> findAllProgrammazione() {

		return repo.findAll();
	}
	public List<Programmazione> findAllbyTitolo(String nome) {
		if (repo.findProgrammazioneByTitolo(nome).isEmpty()) {
			throw new EntityNotFoundException("Nessuna Programmazione con questo nome");
		}
		return repo.findProgrammazioneByTitolo(nome);
	}public List<Programmazione> findAllbyData(LocalDate data) {
		if (repo.findProgrammazioneByData(data).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data");
		}
		return repo.findProgrammazioneByData(data);
	}
	public List<Programmazione> findAllbyOrario1(LocalTime orario) {
		if (repo.findProgrammazioneByOrario1(orario).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questo orario1");
		}
		return repo.findProgrammazioneByOrario1(orario);
	}
	public List<Programmazione> findAllbyOrario2(LocalTime orario) {
		if (repo.findProgrammazioneByOrario2(orario).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questo orario2");
		}
		return repo.findProgrammazioneByOrario2(orario);
	}	
	public List<Programmazione> findAllbyOrario3(LocalTime orario) {
		if (repo.findProgrammazioneByOrario3(orario).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questo orario3");
		}
		return repo.findProgrammazioneByOrario3(orario);
	}
	public Page<Programmazione> getAllProgrammazionePageable(Pageable pageable) {
		if (repo.findAll(pageable).isEmpty()) {
			throw new EntityNotFoundException("Nessuna Programmazione in archivio");
		} else
			return repo.findAll(pageable);
	}

	public Programmazione findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessuna Programmazione associata a questo ID");
		}
		return repo.findById(id).get();
	}

	public String deleteProgrammazioneById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Nessuna Programmazione trovata con questo ID");
		}
		repo.deleteById(id);
		return "Programmazione eliminata";
	}

	public String addProgrammazione(Programmazione p) {

		repo.save(p);
		return "Programmazione aggiunta";
	}

	public Programmazione editProgrammazione(Programmazione sala) {
		if (!repo.existsById(sala.getId())) {
			throw new EntityNotFoundException("Nessuna Programmazione trovata con questo ID");
		}
		return repo.save(sala);
	}
}
