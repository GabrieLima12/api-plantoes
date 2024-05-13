package com.gabriel.apiplantoes.medico;

import com.gabriel.apiplantoes.dtos.*;
import com.gabriel.apiplantoes.exception.MedicoException;
import com.gabriel.apiplantoes.exception.UnidadeException;
import com.gabriel.apiplantoes.medicounidade.MedicoUnidade;
import com.gabriel.apiplantoes.medicounidade.MedicoUnidadeRepository;
import com.gabriel.apiplantoes.unidade.Unidade;
import com.gabriel.apiplantoes.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UnidadeRepository unidadeRepository;
    private final MedicoUnidadeRepository medicoUnidadeRepository;

    public MedicoService(MedicoRepository medicoRepository, UnidadeRepository unidadeRepository, MedicoUnidadeRepository medicoUnidadeRepository) {
        this.medicoRepository = medicoRepository;
        this.unidadeRepository = unidadeRepository;
        this.medicoUnidadeRepository = medicoUnidadeRepository;
    }

    public void cadastrarMedico(CadastroMedico dados) {
        Medico medico = new Medico();
        medico.setNomeMedico(dados.nomeMedico());
        medico.setCrm(dados.crm());
        medico.setEspecialidade(dados.especialidade());
        medico.setDataCadastro(LocalDate.now());
        medico.setStatus(Status.ATIVO);

        List<Unidade> unidades = dados.idsUnidades().stream()
                .map(unidadeId -> unidadeRepository.findById(unidadeId).orElseThrow(UnidadeException::new))
                .toList();

        medico = medicoRepository.save(medico);

        for (Unidade unidade : unidades) {
            MedicoUnidade medicoUnidade = new MedicoUnidade();
            medicoUnidade.setUnidade(unidade);
            medicoUnidade.setMedico(medico);

            medicoUnidadeRepository.save(medicoUnidade);
        }

    }

    public ListagemMedico listarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(MedicoException::new);
        List<Long> idsUnidades = medicoUnidadeRepository.findAllUnitsByMedicoId(id);

        return new ListagemMedico(
                medico.getId(),
                medico.getNomeMedico(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getStatus(),
                idsUnidades
        );
    }

    public List<ListagemMedico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<ListagemMedico> listagemMedicos = new ArrayList<>();

        for (Medico medico: medicos) {
            List<Long> idsUnidades = medicoUnidadeRepository.findAllUnitsByMedicoId(medico.getId());
            listagemMedicos.add(new ListagemMedico(medico.getId(),
                    medico.getNomeMedico(),
                    medico.getCrm(),
                    medico.getEspecialidade(),
                    medico.getStatus(),
                    idsUnidades));
        }

        return listagemMedicos;
    }

}
