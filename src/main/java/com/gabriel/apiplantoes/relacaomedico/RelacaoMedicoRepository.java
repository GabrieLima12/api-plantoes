package com.gabriel.apiplantoes.relacaomedico;

import com.gabriel.apiplantoes.especialidade.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacaoMedicoRepository extends JpaRepository<RelacaoMedico, Long> {

    @Query("SELECT DISTINCT rm.unidade.id FROM RelacaoMedico rm WHERE rm.medico.id = :medicoId")
    List<Long> findAllUnitsByMedicoId(Long medicoId);

    @Query("SELECT rm.especialidade FROM RelacaoMedico rm WHERE rm.medico.id = :medicoId")
    List<Especialidade> findAllSpecialtyByMedicoId(Long medicoId);

    @Query("SELECT rm.primary FROM RelacaoMedico rm WHERE rm.especialidade.id = :especialidadeId AND rm.medico.id = :medicoId")
    List<Boolean> findPrimarySpecialtyByMedicoId(Long especialidadeId, Long medicoId);

    @Query("SELECT DISTINCT rm FROM RelacaoMedico rm WHERE rm.medico.id = :medicoId")
    List<RelacaoMedico> findAllByMedicoId(Long medicoId);
}
