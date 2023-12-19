package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.model.*;
import com.gabriel.apiplantoes.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarMedico(CadastroMedico dados) {
        MedicoModel medico = new MedicoModel(dados);
        repository.save(medico);
    }

    public void atualizarStatusMedico(AlteracaoStatusMedico dados) {
        MedicoModel medico = repository.getReferenceById(dados.id());
        medico.atualizarStatusMedico(dados);
    }

    public List<ListagemMedicos> listagemDeMedicos() {
        var listagem = repository.findAll().stream().map(ListagemMedicos::new);
        return listagem.toList();
    }

    public ListagemMedicos plantonistaPorId(Long id) {
        MedicoModel plantonista = repository.getReferenceById(id);
        return new ListagemMedicos(plantonista);
    }

    public void atualizarUnidadeMedico(AlteracaoUnidadeAssistencial dados) {
        MedicoModel novaUnidade = repository.getReferenceById(dados.id());
        novaUnidade.atualizarUnidadeAssistencial(dados);
    }

}
