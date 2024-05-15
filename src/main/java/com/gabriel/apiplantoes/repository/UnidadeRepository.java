package com.gabriel.apiplantoes.repository;

import com.gabriel.apiplantoes.domain.unidade.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
