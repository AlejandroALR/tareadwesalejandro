package com.alejandro.tareadwesalejandro.repositorios;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.tareadwesalejandro.modelo.Ejemplares;
import com.alejandro.tareadwesalejandro.modelo.Plantas;

public interface EjemplaresRepository extends JpaRepository<Ejemplares, Long> {

    Optional<Ejemplares> findByNombre(String nombre);

    @Query("SELECT e FROM Ejemplares e WHERE e.planta.codigo = ?1")
    List<Ejemplares> findByTipo(String tipoCodigo);

    @Query("SELECT MAX(e.id) FROM Ejemplares e")
    Long findLastId();
    
    int countByPlanta_Codigo(String codigoPlanta);
    
    List<Ejemplares> findByPlanta_CodigoIn(List<String> codigos);
    
    @Query("SELECT e FROM Ejemplares e LEFT JOIN FETCH e.mensajes WHERE e.planta.codigo IN :codigos")
    List<Ejemplares> findWithMensajesByPlantaCodigoIn(@Param("codigos") List<String> codigos);

    @Query("SELECT e FROM Ejemplares e LEFT JOIN FETCH e.mensajes m LEFT JOIN FETCH m.persona WHERE e.id = :id")
    Optional<Ejemplares> findByIdConMensajesYPersonas(@Param("id") Long id);

    long countByPlanta(Plantas planta);
}
