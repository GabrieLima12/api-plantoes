package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.model.DadosDeAlteracaoStatusMedico;
import com.gabriel.apiplantoes.model.DadosDeCadastroMedico;
import com.gabriel.apiplantoes.model.MedicoModel;
import com.gabriel.apiplantoes.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarMedico(DadosDeCadastroMedico dados) {
        MedicoModel medico = new MedicoModel(dados);
        repository.save(medico);
    }

    public void atualizarStatusMedico(DadosDeAlteracaoStatusMedico dados) {
        MedicoModel medico = repository.getReferenceById(dados.id());
        medico.atualizarStatusMedico(dados);
    }

}
