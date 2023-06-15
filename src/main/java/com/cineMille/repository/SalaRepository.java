package com.cineMille.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cineMille.model.Sala;
import com.cineMille.model.TipoSala;
@Repository
public interface SalaRepository  extends JpaRepository<Sala, Long>, PagingAndSortingRepository<Sala, Long> {
	 List<Sala> findSalaByNome(String username);
	 List<Sala> findSalaByTipo(TipoSala tipo);
}
