package com.alejandro.tareadwesalejandro.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.tareadwesalejandro.dto.RegistroPlantaDTO;
import com.alejandro.tareadwesalejandro.modelo.Plantas;
import com.alejandro.tareadwesalejandro.repositorios.PlantasRepository;

@Service
public class ServiciosPlantas {

    @Autowired
    private PlantasRepository plantasRepository;

    public Plantas guardarOActualizar(Plantas planta) {
        return plantasRepository.save(planta);
    }

    public void eliminarPorCodigo(String codigo) {
        plantasRepository.deleteByCodigo(codigo);
    }

    public Optional<Plantas> buscarPorCodigo(String codigo) {
        return plantasRepository.findByCodigo(codigo);
    }

    public List<Plantas> listarTodas() {
        return plantasRepository.findAll();
    }

    public boolean existePorCodigo(String codigo) {
        return plantasRepository.existsByCodigo(codigo);
    }
    
    public boolean codigoExiste(String codigo) {
        return plantasRepository.findByCodigo(codigo).isPresent();
    }
    
    public void registrarPlanta(RegistroPlantaDTO dto) {
        Plantas planta = new Plantas();
        planta.setCodigo(dto.getCodigo());
        planta.setNombreCientifico(dto.getNombreCientifico());
        planta.setNombreComun(dto.getNombreComun());
        plantasRepository.save(planta);
    }
    
    public Plantas obtenerPorCodigo(String codigo) {
        return plantasRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Planta no encontrada"));
    }

    public void actualizarPlanta(String codigo, String nuevoCientifico, String nuevoComun) {
        Plantas planta = obtenerPorCodigo(codigo);
        planta.setNombreCientifico(nuevoCientifico);
        planta.setNombreComun(nuevoComun);
        plantasRepository.save(planta);
    }


}
