package com.alejandro.tareadwesalejandro.repositorios;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.tareadwesalejandro.modelo.Plantas;

public interface PlantasRepository extends JpaRepository<Plantas, Long> {
	Optional<Plantas> findByCodigo(String codigo);

	void deleteByCodigo(String codigo);

	boolean existsByCodigo(String codigo);
}
