package com.alejandro.tareadwesalejandro.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.tareadwesalejandro.modelo.Personas;
import com.alejandro.tareadwesalejandro.repositorios.PersonasRepository;

@Service
public class ServiciosPersonas {

    @Autowired
    private PersonasRepository personasRepository;

    public Personas guardar(Personas persona) {
        return personasRepository.save(persona);
    }

    public Optional<Personas> buscarPorId(Long id) {
        return personasRepository.findById(id);
    }

    public Optional<Personas> buscarPorEmail(String email) {
        return personasRepository.findByEmail(email);
    }

    public List<Personas> listarTodas() {
        return personasRepository.findAll();
    }

    public boolean emailExiste(String email) {
        return personasRepository.existsByEmail(email);
    }

    public void actualizarCredenciales(Personas persona, Long idCredenciales) {
        personasRepository.save(persona);
    }
}
