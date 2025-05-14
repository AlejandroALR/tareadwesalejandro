package com.alejandro.tareadwesalejandro.repositorios;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alejandro.tareadwesalejandro.modelo.Ejemplares;

public interface EjemplaresRepository extends JpaRepository<Ejemplares, Long> {

    Optional<Ejemplares> findByNombre(String nombre);

    @Query("SELECT e FROM Ejemplares e WHERE e.planta.codigo = ?1")
    List<Ejemplares> findByTipo(String tipoCodigo);

    @Query("SELECT MAX(e.id) FROM Ejemplares e")
    Long findLastId();
}
