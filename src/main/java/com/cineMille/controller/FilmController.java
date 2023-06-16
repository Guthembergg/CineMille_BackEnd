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
import com.cineMille.service.FilmService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/film")
public class FilmController {
	@Autowired FilmService service;
	
	@GetMapping
	public ResponseEntity<?> recuperaALLFilm(){
		return new ResponseEntity<>(service.findAllFilm(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> trovaFilm(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/data/{data}")
	public ResponseEntity<?> trovaFilmByData(@PathVariable LocalDate data){
		try {
			return new ResponseEntity<>(service.findByData(data), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	@GetMapping("titolo/{titolo}")
	public ResponseEntity<?> trovaFilmByTitolo(@PathVariable String titolo){
		try {
			return new ResponseEntity<>(service.findByTitolo(titolo), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> aggiungiFilm(@RequestBody Film f){
		try {
			return new ResponseEntity<>(service.addFilm(f), HttpStatus.CREATED);			
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modificaFilm(@RequestBody Film f, @PathVariable Long id){
		try {
			f.setId(id);
			f.setProgrammazioni(service.findById(id).getProgrammazioni());
		return new ResponseEntity<Film>(service.editFilm(f), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}		 
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminaFilm(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.deleteFilmById(id), HttpStatus.OK);
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
