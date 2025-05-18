package com.alejandro.tareadwesalejandro.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.tareadwesalejandro.modelo.Credenciales;

public interface CredencialesRepository extends JpaRepository<Credenciales, Long> {

    boolean existsByUsuario(String usuario);   
    Optional<Credenciales> findByUsuario(String usuario);
    
}

