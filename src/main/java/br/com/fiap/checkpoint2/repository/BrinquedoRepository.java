package br.com.fiap.checkpoint2.repository;

import br.com.fiap.checkpoint2.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}