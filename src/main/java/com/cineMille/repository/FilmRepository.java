package com.cineMille.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cineMille.model.Film;
@Repository
public interface FilmRepository  extends JpaRepository<Film, Long>, PagingAndSortingRepository<Film, Long> {
	 List<Film> findFilmByTitolo(String titolo);
	 List<Film> findFilmByDatauscita(LocalDate datauscita);
}
