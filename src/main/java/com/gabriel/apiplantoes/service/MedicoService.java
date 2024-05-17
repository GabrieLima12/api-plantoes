package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.CadastroMedico;
import com.gabriel.apiplantoes.domain.dtos.ListagemMedico;
import com.gabriel.apiplantoes.domain.endereco.Endereco;
import com.gabriel.apiplantoes.domain.medico.Medico;
import com.gabriel.apiplantoes.domain.medico.Status;
import com.gabriel.apiplantoes.domain.relacaomedico.RelacaoMedico;
import com.gabriel.apiplantoes.repository.*;
import com.gabriel.apiplantoes.domain.unidade.Unidade;
import com.gabriel.apiplantoes.domain.especialidade.Especialidade;
import com.gabriel.apiplantoes.exception.EspecialidadeException;
import com.gabriel.apiplantoes.exception.MedicoException;
import com.gabriel.apiplantoes.exception.RelacaoMedicoException;
import com.gabriel.apiplantoes.exception.UnidadeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UnidadeRepository unidadeRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final RelacaoMedicoRepository relacaoMedicoRepository;
    private final EnderecoService enderecoService;

    public MedicoService(MedicoRepository medicoRepository, UnidadeRepository unidadeRepository,
                         RelacaoMedicoRepository relacaoMedicoRepository, EspecialidadeRepository especialidadeRepository,
                         EnderecoService enderecoService) {
        this.medicoRepository = medicoRepository;
        this.unidadeRepository = unidadeRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.relacaoMedicoRepository = relacaoMedicoRepository;
        this.enderecoService = enderecoService;
    }

    public void cadastrarMedico(CadastroMedico dados) {
        Medico medico = new Medico();

        medico.setNomeMedico(dados.nomeMedico());
        medico.setCrm(dados.crm());
        medico.setDataCadastro(LocalDate.now());
        medico.setStatus(Status.ATIVO);

        Endereco endereco = enderecoService.consultaDeEnderecoPorCep(dados.cep(), dados.numero(), dados.complemento());
        medico.setEndereco(endereco);

        Especialidade especialidadePrimaria = especialidadeRepository.findById(dados.idEspecialidadePrincipal()).orElseThrow(EspecialidadeException::new);
        medico.setEspecialidadePrimaria(especialidadePrimaria);

        if (dados.idEspecialidadeSecundaria() != null) {
            Especialidade especialidadeSecundaria = especialidadeRepository.findById(dados.idEspecialidadeSecundaria()).orElseThrow(EspecialidadeException::new);
            medico.setEspecialidadeSecundaria(especialidadeSecundaria);
        }

        List<Unidade> unidades = dados.idsUnidades().stream()
                .map(unidadeId -> unidadeRepository.findById(unidadeId).orElseThrow(UnidadeException::new))
                .toList();

        try {
            medico = medicoRepository.save(medico);
        } catch (Exception exception) {
            log.error("Erro ao cadastra médico: ", exception);
            throw new MedicoException("Erro ao cadastrar médico!");
        }

        for (Unidade unidade : unidades) {
            RelacaoMedico relacaoMedico = new RelacaoMedico();
            relacaoMedico.setMedico(medico);
            relacaoMedico.setUnidade(unidade);

            try {
                relacaoMedicoRepository.save(relacaoMedico);
            } catch (Exception ex) {
                log.error("Erro ao relacionar medico e unidade: ", ex);
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
                    idsUnidades,
                    medico.getEndereco()
            );

        } catch (Exception exception) {
            log.error("Erro ao buscar médicos ou unidades relacionadas: ", exception);
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

                listagemMedicos.add(new ListagemMedico(
                        medico.getId(),
                        medico.getNomeMedico(),
                        medico.getCrm(),
                        idEspecialidadePrimaria,
                        idEspecialidadeSecundaria,
                        medico.getStatus(),
                        idsUnidades,
                        medico.getEndereco()));
            } catch (Exception exception) {
                log.error("Erro ao buscar médico ou unidades relacionadas: ", exception);
                throw new RelacaoMedicoException("Erro ao buscar unidades relacionada ao médico: " + medico.getNomeMedico());
            }
        }

        return listagemMedicos;
    }

}
