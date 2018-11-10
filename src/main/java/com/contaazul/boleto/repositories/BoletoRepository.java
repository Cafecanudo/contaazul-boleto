package com.contaazul.boleto.repositories;

import com.contaazul.boleto.entities.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, UUID> {
}
