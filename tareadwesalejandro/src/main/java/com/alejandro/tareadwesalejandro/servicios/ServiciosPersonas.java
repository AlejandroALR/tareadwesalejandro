package com.alejandro.tareadwesalejandro.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.tareadwesalejandro.dto.RegistroPersonaDTO;
import com.alejandro.tareadwesalejandro.modelo.Credenciales;
import com.alejandro.tareadwesalejandro.modelo.Personas;
import com.alejandro.tareadwesalejandro.repositorios.CredencialesRepository;
import com.alejandro.tareadwesalejandro.repositorios.PersonasRepository;

@Service
public class ServiciosPersonas {

    @Autowired
    private CredencialesRepository credencialesRepository;

    @Autowired
    private PersonasRepository personasRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registrarPersonaConCredenciales(RegistroPersonaDTO dto) {
        Personas persona = new Personas();
        persona.setNombre(dto.getNombre());
        persona.setEmail(dto.getEmail());
        persona.setRol(dto.getRol());

        persona = personasRepository.save(persona);

        Credenciales credencial = new Credenciales();
        credencial.setUsuario(dto.getUsuario());
        credencial.setPassword(passwordEncoder.encode(dto.getPassword()));
        credencial.setPersona(persona);

        persona.setCredenciales(credencial);

        credencialesRepository.save(credencial);
    }

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
    
    public boolean usuarioExiste(String usuario) {
        return credencialesRepository.findByUsuario(usuario).isPresent();
    }


    public void actualizarCredenciales(Personas persona, Long idCredenciales) {
        personasRepository.save(persona);
    }
}