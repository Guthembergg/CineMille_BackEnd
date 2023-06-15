package com.cineMille.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cineMille.model.Film;
import com.cineMille.model.Programmazione;
import com.cineMille.service.FilmService;
import com.cineMille.service.ProgrammazioneService;
import com.cineMille.service.SalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/programmazione")
public class ProgrammazioneController {
	@Autowired ProgrammazioneService service;
	@Autowired FilmService serviceF;
	@Autowired SalaService serviceS;
	@GetMapping
	public ResponseEntity<?> recuperaALLProgrammazione(){
		return new ResponseEntity<>(service.findAllProgrammazione(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> trovaProgrammazioneById(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping()
	public ResponseEntity<?> trovaProgrammazioneByData(@RequestBody LocalDate data){
		try {
			return new ResponseEntity<>(service.findAllbyData(data), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping()
	public ResponseEntity<?> trovaProgrammazioneByOrario1(@RequestBody SimpleDateFormat orario1){
		try {
			return new ResponseEntity<>(service.findAllbyOrario1(orario1), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping()
	public ResponseEntity<?> trovaProgrammazioneByOrario2(@RequestBody SimpleDateFormat orario2){
		try {
			return new ResponseEntity<>(service.findAllbyOrario2(orario2), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping()
	public ResponseEntity<?> trovaProgrammazioneByOrario3(@RequestBody SimpleDateFormat orario3){
		try {
			return new ResponseEntity<>(service.findAllbyOrario3(orario3), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	
	@PostMapping("/{id_film}/{id_sala}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> aggiungiProgrammazione(@RequestBody Programmazione p,@PathVariable Long id_film,@PathVariable Long id_sala){
		LocalDate data_uscita=serviceF.findById(id_film).getData_uscita();
		LocalDate data_programma=p.getData();
		if(data_uscita.datesUntil(data_programma).count() > 22 && data_uscita.datesUntil(data_programma).count()<7) {
			return new ResponseEntity<String>("Data fuori programma rispetto alla data di uscita del film", HttpStatus.BAD_REQUEST);
		}
		
		try {
			p.setSala(serviceS.findById(id_sala));
			p.setFilm(serviceF.findById(id_film));
			return new ResponseEntity<>(service.addProgrammazione(p), HttpStatus.CREATED);			
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modificaProgrammazione(@RequestBody Programmazione p, @PathVariable Long id){
		try {
			p.setId(id);
			p.setFilm(service.findById(id).getFilm());
			p.setSala(service.findById(id).getSala());
		return new ResponseEntity<Programmazione>(service.editProgrammazione(p), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}		 
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminaProgrammazione(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.deleteProgrammazioneById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	
}
