package com.alejandro.tareadwesalejandro.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.tareadwesalejandro.modelo.Ejemplares;
import com.alejandro.tareadwesalejandro.repositorios.EjemplaresRepository;

@Service
public class ServiciosEjemplares {

    @Autowired
    private EjemplaresRepository ejemplaresRepository;

    public Ejemplares guardar(Ejemplares ej) {
        return ejemplaresRepository.save(ej);
    }

    public Optional<Ejemplares> buscarPorId(Long id) {
        return ejemplaresRepository.findById(id);
    }

    public Optional<Ejemplares> buscarPorNombre(String nombre) {
        return ejemplaresRepository.findByNombre(nombre);
    }

    public List<Ejemplares> listarTodos() {
        return ejemplaresRepository.findAll();
    }

    public List<Ejemplares> buscarPorTipo(String tipoCodigo) {
        return ejemplaresRepository.findByTipo(tipoCodigo);
    }

    public void eliminar(Long id) {
        ejemplaresRepository.deleteById(id);
    }

    public int contarPorTipo(String tipoCodigo) {
        return ejemplaresRepository.findByTipo(tipoCodigo).size();
    }

    public Long obtenerUltimoId() {
        return ejemplaresRepository.findLastId();
    }
}
