package com.alejandro.tareadwesalejandro.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
