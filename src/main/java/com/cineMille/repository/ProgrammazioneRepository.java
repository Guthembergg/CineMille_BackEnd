package com.cineMille.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cineMille.model.Film;
import com.cineMille.model.Programmazione;

@Repository
public interface ProgrammazioneRepository extends JpaRepository<Programmazione, Long>, PagingAndSortingRepository<Programmazione, Long> {
	 List<Programmazione> findProgrammazioneByData(LocalDate data);
	 List<Programmazione> findProgrammazioneByOrario1(SimpleDateFormat orario1);
	 List<Programmazione> findProgrammazioneByOrario2(SimpleDateFormat orario2);
	 List<Programmazione> findProgrammazioneByOrario3(SimpleDateFormat orario3);
	 List<Programmazione> findProgrammazioneByTitolo(String titolo);
}
