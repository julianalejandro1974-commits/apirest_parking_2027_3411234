package com.sena.apiparking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.apiparking.model.Tarifas;
import com.sena.apiparking.model.TipoVehiculo;

import jakarta.persistence.LockModeType;

public interface TarifaRepository extends JpaRepository<Tarifas, Long> {

	@Query("select count(t)>0 from Tarifas t where t.tipo = :tipo and t.vigenteHasta is null")
	boolean exexistsByTipoAndVigenteHastaIsNull(@Param("tipo") TipoVehiculo tipo);

	Optional<Tarifas> findFirstByTipoAndVigenteHastaIsNull(TipoVehiculo tipo);

	List<Tarifas> findAllByTipoAscVigenteDesdeDesc();

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select t from Tarifas t where t.tipo = :tipo and t.vigenteHasta is null")
	Optional<Tarifas> bloquearVigente(@Param("tipo") TipoVehiculo tipo);

	@Query("select t from Tarifas t Where t.tipo = :tipo and t.vigenteDesde <= :momento "
			+ "and (t.vigenteHasta is null or t.vigenteHasta > :momento) order by t.vigenteDesde desc")
	List<Tarifas> vigentesEn(@Param("tipo") TipoVehiculo tipo, @Param("momento") LocalDateTime momento);
}
