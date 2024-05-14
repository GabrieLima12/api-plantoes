package com.gabriel.apiplantoes.medico;

import com.gabriel.apiplantoes.dtos.*;
import com.gabriel.apiplantoes.especialidade.Especialidade;
import com.gabriel.apiplantoes.especialidade.EspecialidadeRepository;
import com.gabriel.apiplantoes.exception.EspecialidadeException;
import com.gabriel.apiplantoes.exception.MedicoException;
import com.gabriel.apiplantoes.exception.RelacaoMedicoException;
import com.gabriel.apiplantoes.exception.UnidadeException;
import com.gabriel.apiplantoes.relacaomedico.RelacaoMedico;
import com.gabriel.apiplantoes.relacaomedico.RelacaoMedicoRepository;
import com.gabriel.apiplantoes.unidade.Unidade;
import com.gabriel.apiplantoes.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UnidadeRepository unidadeRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final RelacaoMedicoRepository relacaoMedicoRepository;

    public MedicoService(MedicoRepository medicoRepository, UnidadeRepository unidadeRepository, RelacaoMedicoRepository relacaoMedicoRepository, EspecialidadeRepository especialidadeRepository) {
        this.medicoRepository = medicoRepository;
        this.unidadeRepository = unidadeRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.relacaoMedicoRepository = relacaoMedicoRepository;
    }

    public void cadastrarMedico(CadastroMedico dados) {
        Medico medico = new Medico();
        medico.setNomeMedico(dados.nomeMedico());
        medico.setCrm(dados.crm());
        medico.setDataCadastro(LocalDate.now());
        medico.setStatus(Status.ATIVO);

        List<Unidade> unidades = dados.idsUnidades().stream()
                .map(unidadeId -> unidadeRepository.findById(unidadeId).orElseThrow(UnidadeException::new))
                .toList();

        try {
            medico = medicoRepository.save(medico);
        } catch (MedicoException exception) {
            throw new MedicoException("Erro ao cadastrar médico!");
        }

        for (EspecialidadeDTO especialidadeDTO : dados.especialidades()) {
            Especialidade especialidade = especialidadeRepository.findById(especialidadeDTO.getId()).orElseThrow(EspecialidadeException::new);
            for (Unidade unidade : unidades) {
                RelacaoMedico relacaoMedico = new RelacaoMedico();
                relacaoMedico.setMedico(medico);
                relacaoMedico.setEspecialidade(especialidade);
                relacaoMedico.setUnidade(unidade);
                relacaoMedico.setPrimary(especialidadeDTO.isPrimary());

                try {
                    relacaoMedicoRepository.save(relacaoMedico);
                } catch (RelacaoMedicoException ex) {
                    throw new RelacaoMedicoException("Erro ao relacionar medico e unidade!");
                }

            }
        }
    }

//    public ListagemMedico listarMedicoPorId(Long id) {
//        Medico medico = medicoRepository.findById(id).orElseThrow(MedicoException::new);
//        try {
//            List<Long> idsUnidades = relacaoMedicoRepository.findAllUnitsByMedicoId(id);
//            List<Especialidade> especialidades = relacaoMedicoRepository.findAllSpecialtyByMedicoId(id);
//
//            return new ListagemMedico(
//                    medico.getId(),
//                    medico.getNomeMedico(),
//                    medico.getCrm(),
//                    especialidades,
//                    medico.getStatus(),
//                    idsUnidades
//            );
//        } catch (RelacaoMedicoException exception) {
//            throw new RelacaoMedicoException("Erro ao buscar unidades relacionada ao médico: " + medico.getNomeMedico());
//        }
//    }

    public List<ListagemMedico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<ListagemMedico> listagemMedicos = new ArrayList<>();

        for (Medico medico: medicos) {
            try {
                List<Long> idsUnidades = relacaoMedicoRepository.findAllUnitsByMedicoId(medico.getId());
                List<EspecialidadeDTO> especialidadeDTO = new ArrayList<>();
                List<RelacaoMedico> relacaoMedicos = relacaoMedicoRepository.findAllByMedicoId(medico.getId());

                for (RelacaoMedico relacaoMedico : relacaoMedicos) {
                    especialidadeDTO.add(EspecialidadeDTO.builder().id(relacaoMedico.getEspecialidade().getId()).primary(relacaoMedico.isPrimary()).build());
                }

                listagemMedicos.add(new ListagemMedico(medico.getId(),
                        medico.getNomeMedico(),
                        medico.getCrm(),
                        especialidadeDTO,
                        medico.getStatus(),
                        idsUnidades));
            } catch (RelacaoMedicoException exception) {
                throw new RelacaoMedicoException("Erro ao buscar unidades relacionada ao médico: " + medico.getNomeMedico());
            }
        }

        return listagemMedicos;
    }

}
