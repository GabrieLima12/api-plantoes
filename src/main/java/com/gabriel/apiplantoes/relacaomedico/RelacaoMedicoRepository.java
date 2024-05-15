package com.gabriel.apiplantoes.relacaomedico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacaoMedicoRepository extends JpaRepository<RelacaoMedico, Long> {

    @Query("SELECT DISTINCT rm.unidade.id FROM RelacaoMedico rm WHERE rm.medico.id = :medicoId")
    List<Long> findAllUnitsByMedicoId(Long medicoId);

}
