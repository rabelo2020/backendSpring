package com.backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.demo.model.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

	//boolean existsByEmail(String email);
	//List<Cliente> findByNomeContaining(String nome);
	//Optional<Cliente> findByEmail(String email);

}
