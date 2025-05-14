package com.alejandro.tareadwesalejandro.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.tareadwesalejandro.modelo.Mensajes;
import com.alejandro.tareadwesalejandro.repositorios.MensajesRepository;

@Service
public class ServiciosMensajes {

    @Autowired
    private MensajesRepository mensajesRepository;

    public Mensajes guardar(Mensajes mensaje) {
        return mensajesRepository.save(mensaje);
    }

    public List<Mensajes> listarTodos() {
        return mensajesRepository.findAll();
    }

    public List<Mensajes> buscarPorEjemplar(Long idEjemplar) {
        return mensajesRepository.findByEjemplarId(idEjemplar);
    }

    public List<Mensajes> buscarEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        return mensajesRepository.findByFechahoraBetween(inicio, fin);
    }

    public int contarPorEjemplar(Long idEjemplar) {
        return mensajesRepository.contarMensajesPorEjemplar(idEjemplar);
    }

    public Optional<LocalDateTime> obtenerUltimaFechaPorEjemplar(Long idEjemplar) {
        return mensajesRepository.obtenerUltimaFechaMensaje(idEjemplar);
    }

    public List<Mensajes> buscarPorNombrePersona(String nombre) {
        return mensajesRepository.findByNombrePersona(nombre);
    }

    public List<Mensajes> buscarPorCodigoPlanta(String codigoPlanta) {
        return mensajesRepository.findByCodigoPlanta(codigoPlanta);
    }
}
