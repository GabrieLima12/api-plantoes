package com.gabriel.apiplantoes.medicounidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoUnidadeRepository extends JpaRepository<MedicoUnidade, Long> {

    @Query("SELECT mu.unidade.id FROM MedicoUnidade mu WHERE mu.medico.id = :medicoId")
    List<Long> findAllUnitsByMedicoId(Long medicoId);
}
