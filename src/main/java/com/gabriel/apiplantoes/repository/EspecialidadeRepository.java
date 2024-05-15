package com.gabriel.apiplantoes.repository;

import com.gabriel.apiplantoes.domain.especialidade.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
