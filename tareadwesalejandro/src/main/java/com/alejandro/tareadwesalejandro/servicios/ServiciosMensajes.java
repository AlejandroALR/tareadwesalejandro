package com.alejandro.tareadwesalejandro.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.tareadwesalejandro.dto.RegistroMensajeDTO;
import com.alejandro.tareadwesalejandro.modelo.Mensajes;
import com.alejandro.tareadwesalejandro.repositorios.EjemplaresRepository;
import com.alejandro.tareadwesalejandro.repositorios.MensajesRepository;
import com.alejandro.tareadwesalejandro.repositorios.PersonasRepository;

@Service
public class ServiciosMensajes {

	@Autowired
	private MensajesRepository mensajesRepository;

	@Autowired
	private EjemplaresRepository ejemplaresRepository;

	@Autowired
	private PersonasRepository personasRepository;

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

	public void guardarMensaje(RegistroMensajeDTO dto, Long idPersona) {
		Mensajes mensaje = new Mensajes();
		mensaje.setMensaje(dto.getMensaje());
		mensaje.setFechahora(LocalDateTime.now());
		mensaje.setEjemplar(ejemplaresRepository.findById(dto.getIdEjemplar()).orElseThrow());
		mensaje.setPersona(personasRepository.findById(idPersona).orElseThrow());
		mensajesRepository.save(mensaje);
	}

	public List<Mensajes> filtrarMensajes(Long idPersona, String codigoPlanta, LocalDate desde, LocalDate hasta) {

		return mensajesRepository.findAll().stream()
				.filter(m -> idPersona == null || m.getPersona().getId().equals(idPersona))
				.filter(m -> codigoPlanta == null || m.getEjemplar().getPlanta().getCodigo().equals(codigoPlanta))
				.filter(m -> {
					LocalDate fechaMensaje = m.getFechahora().toLocalDate();
					boolean desdeOK = desde == null || !fechaMensaje.isBefore(desde);
					boolean hastaOK = hasta == null || !fechaMensaje.isAfter(hasta);
					return desdeOK && hastaOK;
				}).toList();
	}

}
