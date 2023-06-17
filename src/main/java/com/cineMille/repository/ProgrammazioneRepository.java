package com.cineMille.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cineMille.model.Film;
import com.cineMille.model.Programmazione;
import com.cineMille.model.Sala;

@Repository
public interface ProgrammazioneRepository extends JpaRepository<Programmazione, Long>, PagingAndSortingRepository<Programmazione, Long> {
	 List<Programmazione> findProgrammazioneByData(LocalDate data);
	 List<Programmazione> findProgrammazioneByOrario1(LocalTime orario1);
	 List<Programmazione> findProgrammazioneByOrario2(LocalTime orario2);
	 List<Programmazione> findProgrammazioneByOrario3(LocalTime orario3);
	 List<Programmazione> findProgrammazioneByTitolo(String titolo);
	 @Query("SELECT p FROM Programmazione p WHERE p.sala.id = :id ")
	 List<Programmazione> findProgrammazioneBySalaId(Long id);
	 @Query("SELECT p FROM Programmazione p WHERE p.film.titolo = :titolo ")
	 List<Programmazione> findProgrammazioneByTitoloFilm(String titolo);
	 List<Programmazione> findProgrammazioneByDataAndSala(LocalDate data, Sala sala);
	 List<Programmazione> findProgrammazioneByDataAndSalaAndOrario1(LocalDate data, Sala sala, LocalTime orario1);
	 List<Programmazione> findProgrammazioneByDataAndSalaAndOrario2(LocalDate data, Sala sala, LocalTime orario2);
	 List<Programmazione> findProgrammazioneByDataAndSalaAndOrario3(LocalDate data, Sala sala, LocalTime orario3);

}
