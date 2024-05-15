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

        Especialidade especialidadePrimaria = especialidadeRepository.findById(dados.idEspecialidadePrincipal()).orElseThrow(EspecialidadeException::new);
        especialidadePrimaria.setId(dados.idEspecialidadePrincipal());

        if (dados.idEspecialidadeSecundaria() != null) {
            Especialidade especialidadeSecundaria = especialidadeRepository.findById(dados.idEspecialidadeSecundaria()).orElseThrow(EspecialidadeException::new);
            medico.setEspecialidadeSecundaria(especialidadeSecundaria);
        }

        List<Unidade> unidades = dados.idsUnidades().stream()
                .map(unidadeId -> unidadeRepository.findById(unidadeId).orElseThrow(UnidadeException::new))
                .toList();

        try {
            medico = medicoRepository.save(medico);
        } catch (MedicoException exception) {
            throw new MedicoException("Erro ao cadastrar médico!");
        }

        for (Unidade unidade : unidades) {
            RelacaoMedico relacaoMedico = new RelacaoMedico();
            relacaoMedico.setMedico(medico);
            relacaoMedico.setUnidade(unidade);

            try {
                relacaoMedicoRepository.save(relacaoMedico);
            } catch (RelacaoMedicoException ex) {
                throw new RelacaoMedicoException("Erro ao relacionar medico e unidade!");
            }

        }

    }

    public ListagemMedico listarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(MedicoException::new);
        try {
            List<Long> idsUnidades = relacaoMedicoRepository.findAllUnitsByMedicoId(id);
            Especialidade especialidadePrimaria = medico.getEspecialidadePrimaria();
            Especialidade especialidadeSecundaria = medico.getEspecialidadeSecundaria();

            Long idEspecialidadePrimaria = especialidadePrimaria != null ? especialidadePrimaria.getId() : null;
            Long idEspecialidadeSecundaria = especialidadeSecundaria != null ? especialidadeSecundaria.getId() : null;

            return new ListagemMedico(
                    medico.getId(),
                    medico.getNomeMedico(),
                    medico.getCrm(),
                    idEspecialidadePrimaria,
                    idEspecialidadeSecundaria,
                    medico.getStatus(),
                    idsUnidades
            );

        } catch (Exception exception) {
            throw new RelacaoMedicoException("Erro ao buscar unidades relacionada ao médico: " + medico.getNomeMedico());
        }
    }

    public List<ListagemMedico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<ListagemMedico> listagemMedicos = new ArrayList<>();

        for (Medico medico: medicos) {
            try {
                List<Long> idsUnidades = relacaoMedicoRepository.findAllUnitsByMedicoId(medico.getId());
                Especialidade especialidadePrimaria = medico.getEspecialidadePrimaria();
                Especialidade especialidadeSecundaria = medico.getEspecialidadeSecundaria();

                Long idEspecialidadePrimaria = especialidadePrimaria != null ? especialidadePrimaria.getId() : null;
                Long idEspecialidadeSecundaria = especialidadeSecundaria != null ? especialidadeSecundaria.getId() : null;

                listagemMedicos.add(new ListagemMedico(medico.getId(),
                        medico.getNomeMedico(),
                        medico.getCrm(),
                        idEspecialidadePrimaria,
                        idEspecialidadeSecundaria,
                        medico.getStatus(),
                        idsUnidades));
            } catch (Exception exception) {
                throw new RelacaoMedicoException("Erro ao buscar unidades relacionada ao médico: " + medico.getNomeMedico());
            }
        }

        return listagemMedicos;
    }

}
