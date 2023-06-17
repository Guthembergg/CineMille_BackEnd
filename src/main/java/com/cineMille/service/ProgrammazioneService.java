package com.cineMille.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cineMille.model.Film;
import com.cineMille.model.Programmazione;
import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.read_CSV.CSVHelper;
import com.cineMille.repository.ProgrammazioneRepository;
import com.cineMille.repository.SalaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Configurable
public class ProgrammazioneService {
	@Autowired
	ProgrammazioneRepository repo;
	@Autowired
	FilmService servF;
	@Autowired
	SalaService servS;

	public List<Programmazione> findAllProgrammazione() {

		return repo.findAll();
	}

	public List<Programmazione> findAllbyTitolo(String nome) {
		if (repo.findProgrammazioneByTitolo(nome).isEmpty()) {
			throw new EntityNotFoundException("Nessuna Programmazione con questo nome");
		}
		return repo.findProgrammazioneByTitolo(nome);
	}

	public List<Programmazione> findByTitoloFilm(String titolo) {
		if (repo.findProgrammazioneByTitoloFilm(titolo).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questo titolo film");
		}
		return repo.findProgrammazioneByTitoloFilm(titolo);
	}
	public List<Programmazione> findBySalaId(Long id) {
		if (repo.findProgrammazioneBySalaId(id).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa sala");
		}
		return repo.findProgrammazioneBySalaId(id);
	}
	
	public List<Programmazione> findAllbyDataAndSala(LocalDate data, Sala sala) {
		if (repo.findProgrammazioneByDataAndSala(data, sala).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data e sala e orario1");
		}
		return repo.findProgrammazioneByDataAndSala(data, sala);
	}
	public List<Programmazione> findAllbyData(LocalDate data) {
		if (repo.findProgrammazioneByData(data).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data");
		}
		return repo.findProgrammazioneByData(data);
	}

	public List<Programmazione> findAllbyDataandSalaAndOrario1(LocalDate data, Sala sala, LocalTime orario1) {
		if (repo.findProgrammazioneByDataAndSalaAndOrario1(data, sala, orario1).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data e sala e orario1");
		}
		return repo.findProgrammazioneByDataAndSalaAndOrario2(data, sala, orario1);
	}

	public List<Programmazione> findAllbyDataandSalaAndOrario2(LocalDate data, Sala sala, LocalTime orario2) {
		if (repo.findProgrammazioneByDataAndSalaAndOrario2(data, sala, orario2).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data e sala e orario2");
		}
		return repo.findProgrammazioneByDataAndSalaAndOrario2(data, sala, orario2);
	}

	public List<Programmazione> findAllbyDataandSalaAndOrario3(LocalDate data, Sala sala, LocalTime orario3) {
		if (repo.findProgrammazioneByDataAndSalaAndOrario1(data, sala, orario3).isEmpty()) {
			throw new EntityNotFoundException("Nessuna programmazione con questa data e sala e orario3");
		}
		return repo.findProgrammazioneByDataAndSalaAndOrario1(data, sala, orario3);
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

	public void saveCSV(MultipartFile file) {

		try // (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,
		// "UTF-8"));
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));

				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(';').withFirstRecordAsHeader()
						.withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames());) {

			List<Programmazione> programmazioni = new ArrayList<Programmazione>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				LocalTime time1 = LocalTime.parse(csvRecord.get(2));
				LocalTime time2 = LocalTime.parse(csvRecord.get(3));
				LocalTime time3 = LocalTime.parse(csvRecord.get(4));
				LocalTime Time1 = LocalDateTime.of(LocalDate.now(), time1).atZone(ZoneId.of("Europe/Paris"))
						.toLocalTime();

				LocalTime Time2 = LocalDateTime.of(LocalDate.now(), time2).atZone(ZoneId.of("Europe/Paris"))
						.toLocalTime();

				LocalTime Time3 = LocalDateTime.of(LocalDate.now(), time3).atZone(ZoneId.of("Europe/Paris"))
						.toLocalTime();

				Programmazione programmazione = new Programmazione(csvRecord.get(0), LocalDate.parse(csvRecord.get(1)),
						Time1, Time2, Time3, servF.findById(Long.parseLong(csvRecord.get(5))),
						servS.findById(Long.parseLong(csvRecord.get(6)))

				);

				programmazioni.add(programmazione);
			}

			repo.saveAll(programmazioni);
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

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
