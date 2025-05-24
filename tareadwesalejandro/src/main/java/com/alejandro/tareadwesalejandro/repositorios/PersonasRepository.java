package com.alejandro.tareadwesalejandro.repositorios;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.tareadwesalejandro.modelo.Personas;

public interface PersonasRepository extends JpaRepository<Personas, Long> {

    Optional<Personas> findByEmail(String email);

    boolean existsByEmail(String email);
    
    Optional<Personas> findByCredenciales_Usuario(String usuario);

    
}