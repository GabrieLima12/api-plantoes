package com.gabriel.apiplantoes.medico;

import com.gabriel.apiplantoes.dtos.*;
import com.gabriel.apiplantoes.medicounidade.MedicoUnidade;
import com.gabriel.apiplantoes.medicounidade.MedicoUnidadeRepository;
import com.gabriel.apiplantoes.unidade.Unidade;
import com.gabriel.apiplantoes.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        medico = medicoRepository.save(medico);

        List<Unidade> unidades = dados.idsUnidades().stream()
                .map(unidadeId -> unidadeRepository.findById(unidadeId).orElse(null))
                .toList();

        for (Unidade unidade : unidades) {
            MedicoUnidade medicoUnidade = new MedicoUnidade();
            medicoUnidade.setUnidade(unidade);
            medicoUnidade.setMedico(medico);

            medicoUnidadeRepository.save(medicoUnidade);
        }

    }

    public ListagemMedico listarMedicoPorId(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        List<Long> idsUnidades = medicoUnidadeRepository.findAllUnitsByMedicoId(id);

        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();
            return new ListagemMedico(
                    medico.getId(),
                    medico.getNomeMedico(),
                    medico.getCrm(),
                    medico.getEspecialidade(),
                    medico.getStatus(),
                    idsUnidades
            );
        }

        return null;
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
