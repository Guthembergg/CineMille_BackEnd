package com.cineMille.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
import com.cineMille.model.Programmazione;
import com.cineMille.model.ProgrammazioneDto;
import com.cineMille.service.FilmService;
import com.cineMille.service.ProgrammazioneService;
import com.cineMille.service.SalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/programmazione")
public class ProgrammazioneController {
	@Autowired
	ProgrammazioneService service;
	@Autowired
	FilmService serviceF;
	@Autowired
	SalaService serviceS;

	@GetMapping
	public ResponseEntity<?> recuperaALLProgrammazione() {
		return new ResponseEntity<>(service.findAllProgrammazione(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> trovaProgrammazioneById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@GetMapping("/data")
	public ResponseEntity<?> trovaProgrammazioneByData(@RequestBody LocalDate data) {
		try {
			return new ResponseEntity<>(service.findAllbyData(data), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@GetMapping("/titoloFilm/{titolo}")
	public ResponseEntity<?> trovaProgrammazioneByData(@PathVariable String titolo) {
		try {
			return new ResponseEntity<>(service.findByTitoloFilm(titolo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@GetMapping("/orario1/{orario1}")
	public ResponseEntity<?> trovaProgrammazioneByOrario1(@PathVariable String orario1) {
		try {
			LocalTime time = LocalTime.parse(orario1);
			LocalTime Time = LocalDateTime.of(LocalDate.now(), time).atZone(ZoneId.of("Europe/Paris")).toLocalTime();

			return new ResponseEntity<>(service.findAllbyOrario1(Time), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@GetMapping("/orario2/{orario2}")
	public ResponseEntity<?> trovaProgrammazioneByOrario2(@RequestBody String orario2) {
		try {
			LocalTime time = LocalTime.parse(orario2);
			LocalTime Time = LocalDateTime.of(LocalDate.now(), time).atZone(ZoneId.of("Europe/Paris")).toLocalTime();

			return new ResponseEntity<>(service.findAllbyOrario2(Time), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@GetMapping("/orario3/{orario3}")
	public ResponseEntity<?> trovaProgrammazioneByOrario3(@PathVariable String orario3) {
		try {
			LocalTime time = LocalTime.parse(orario3);
			LocalTime Time = LocalDateTime.of(LocalDate.now(), time).atZone(ZoneId.of("Europe/Paris")).toLocalTime();

			return new ResponseEntity<>(service.findAllbyOrario3(Time), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@PostMapping("/{id_film}/{id_sala}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> aggiungiProgrammazione(@RequestBody ProgrammazioneDto p, @PathVariable Long id_film,
			@PathVariable Long id_sala) {
		LocalDate data_uscita = serviceF.findById(id_film).getDatauscita();
		LocalDate data_programma = p.getData();
		if (data_uscita.datesUntil(data_programma).count() > 22 || data_uscita.datesUntil(data_programma).count() < 7) {
			return new ResponseEntity<String>("Data fuori programma rispetto alla data di uscita del film",
					HttpStatus.BAD_REQUEST);
		}
		LocalTime time1 = LocalTime.parse(p.getOrario1());
		LocalTime Time1 = LocalDateTime.of(LocalDate.now(), time1).atZone(ZoneId.of("Europe/Paris")).toLocalTime();
		LocalTime Time2 = Time1.plusHours(2);

		LocalTime Time3 = Time2.plusHours(2);
		if (service.findAllbyDataAndSala(p.getData(), serviceS.findById(id_sala)).isEmpty()) {
			try {

				Programmazione p2 = new Programmazione(p.getTitolo(), p.getData(), Time1, Time2, Time3,
						serviceF.findById(id_film), serviceS.findById(id_sala));
				return new ResponseEntity<>(service.addProgrammazione(p2), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("sala occupata in questa data", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modificaProgrammazione(@RequestBody ProgrammazioneDto p, @PathVariable Long id) {
		LocalTime time1 = LocalTime.parse(p.getOrario1());

		LocalTime Time1 = LocalDateTime.of(LocalDate.now(), time1).atZone(ZoneId.of("Europe/Paris")).toLocalTime();
		LocalTime Time2 = Time1.plusHours(2);

		LocalTime Time3 = Time2.plusHours(2);

		try {
			Programmazione p2 = new Programmazione(id, p.getTitolo(), p.getData(), Time1, Time2, Time3,
					service.findById(id).getFilm(), service.findById(id).getSala());

			return new ResponseEntity<Programmazione>(service.editProgrammazione(p2), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> eliminaProgrammazione(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(service.deleteProgrammazioneById(id), HttpStatus.OK);
		} catch (Exception e) {
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
