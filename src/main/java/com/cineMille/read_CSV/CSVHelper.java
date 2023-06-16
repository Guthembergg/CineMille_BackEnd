package com.cineMille.read_CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cineMille.model.Film;
import com.cineMille.model.Programmazione;
import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
import com.cineMille.service.FilmService;
import com.cineMille.service.SalaService;

@Controller
public class CSVHelper {

	@Autowired
	
	static FilmService servF;

	@Autowired 
	static SalaService servS;
	public static String TYPE = "text/csv";

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Film> csvToFilm(InputStream is) {// InputStream is
		try // (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,
			// "UTF-8"));
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(';').withFirstRecordAsHeader()
						.withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames());) {

			List<Film> films = new ArrayList<Film>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Film film = new Film(csvRecord.get(0), csvRecord.get(1), LocalDate.parse(csvRecord.get(2))

				);

				films.add(film);
			}

			return films;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static List<Sala> csvToSala(InputStream is) {// InputStream is
		try // (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,
			// "UTF-8"));
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(';').withFirstRecordAsHeader()
						.withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames());) {

			List<Sala> sale = new ArrayList<Sala>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Sala sala = new Sala(csvRecord.get(0), TipoSala.valueOf(csvRecord.get(1)),
						Integer.parseInt(csvRecord.get(2))

				);

				sale.add(sala);
			}

			return sale;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

	}

	public static    List<Programmazione> csvToProgrammazione(InputStream is) {// InputStream is
		try // (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,
		// "UTF-8"));
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

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

			return programmazioni;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

	}
}
