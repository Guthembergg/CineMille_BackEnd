package com.cineMille.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cineMille.model.Film;
import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.service.FilmService;
import com.cineMille.service.SalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sala")
public class SalaController {
	@Autowired SalaService service;
	
	@GetMapping
	public ResponseEntity<?> recuperaALLSala(){
		return new ResponseEntity<>(service.findAllSala(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> trovaSalaById(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> trovaSalaByNome(@PathVariable String nome){
		try {
			return new ResponseEntity<>(service.findSalabyNome(nome), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> trovaSalaByTipo(@PathVariable String tipo){
		try {
			return new ResponseEntity<>(service.findSalabyTipo(TipoSala.valueOf(tipo)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> aggiungiSala(@RequestBody Sala f){
		try {
			return new ResponseEntity<>(service.addSala(f), HttpStatus.CREATED);			
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modificaSala(@RequestBody Sala f, @PathVariable Long id){
		try {
			f.setId(id);
			f.setProgrammazioni(service.findById(id).getProgrammazioni());
		return new ResponseEntity<Sala>(service.editSala(f), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}		 
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminaSala(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.deleteSalaById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@PostMapping("/upload")	
	@PreAuthorize("hasRole('ADMIN')")
	  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	      try {
	        service.saveCSV(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return new ResponseEntity<String>(message, HttpStatus.OK);
	      
	      } catch (Exception e) {
	        return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
	      }
	    
	}
}
